package com.test.test168.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class IndexSmartRefreshHeaderView extends View {
    public static final String TAG = "IndexSmartRefreshView";
    public static final int PULLING = 1;
    public static final int REFRESHING = 2;
    public static final int FINISH = 3;
    public static final int ANIMATOR_TIME = 800;
    @ColorInt
    public int viewColor = Color.WHITE;

    // 公共变量
    private int animationRadius = 80;// 圆圈半径，包括全部的圆圈
    private RectF animationRectF;// 动画的范围，包括全部动画的范围
    private int paintStrokeWidth = 10; // 全部图形的粗细
    private int drawingGraphics = PULLING;// 正在绘制的图形（PULLING 1: 下拉图形；REFRESHING 2: 刷新动画；FINISH 3: 完成动画）

    /************** pulling 下拉动作相关变量****************/
    private PointF pullingLineBottomPoint; // 竖线最低点
    private Paint pullingPaint = null;
    private float pullingPercent = 0.5f;// 下拉动画绘制圆圈的触发点
    private float pullingAnimationPercent = 0;// 下拉动画进度，根据此字段绘制对应进度的动画

    /************** refreshing 正在刷新相关变量*************/
    private Paint refreshingPaint = null;
    private Matrix refreshingCircleMatrix = null;
    private ValueAnimator refreshingAnimator; // 刷新动画
    private boolean isShrinkage = false;// 是否已经展示过收起竖线动画

    /**************** finish 完成动画相关变量******************/
    private Paint finishPaint = null;
    private ValueAnimator finishAnimator; // 结束动画
    private Path finishCirclePath;// 圆圈绘制路径
    private Path finishRightMarkPath;// 对勾绘制路径
    private PathMeasure finishPathMeasure; // 当前的部分绘制路径
    private Path finishDstPath;// 保存当前的绘制路径
    private SweepGradient refreshingSweepGradient;

    public IndexSmartRefreshHeaderView(Context context) {
        super(context);
        init(context);
    }

    public IndexSmartRefreshHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndexSmartRefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IndexSmartRefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        pullingPaint = new Paint();
        pullingPaint.setAntiAlias(true);//取消锯齿
        pullingPaint.setColor(viewColor);
        pullingPaint.setStrokeWidth(paintStrokeWidth);
        refreshingPaint = new Paint();
        refreshingPaint.setAntiAlias(true);//取消锯齿
        refreshingPaint.setColor(viewColor);
        refreshingPaint.setStrokeWidth(paintStrokeWidth);
        refreshingPaint.setStyle(Paint.Style.STROKE);
        refreshingPaint.setStrokeCap(Paint.Cap.ROUND);
        finishPaint = new Paint();
        finishPaint.setAntiAlias(true);//取消锯齿
        finishPaint.setColor(viewColor);
        finishPaint.setStrokeWidth(paintStrokeWidth);
        finishPaint.setStyle(Paint.Style.STROKE);
        finishPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setColor(@ColorInt int color) {
        pullingPaint.setColor(color);
        refreshingPaint.setColor(color);
        finishPaint.setColor(color);
        viewColor = color;
        // initDrawData();
    }

    public void updatePercent(@FloatRange(from = 0f, to = 1f) float percent) {
        drawingGraphics = PULLING;
        pullingAnimationPercent = percent;
        invalidate();
    }

    public void startRefreshingAnimation() {
        drawingGraphics = REFRESHING;
        isShrinkage = false;// 再次刷新的时候再次执行收起竖线的显示
        // 圆环动画
        refreshingAnimator = ValueAnimator.ofFloat(0, 1);
        // 动画过程
        refreshingAnimator.addUpdateListener(animation -> {
            invalidate();
        });
        // 动画时间
        refreshingAnimator.setDuration(ANIMATOR_TIME);
        // 插值器
        refreshingAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        refreshingAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isShrinkage = true;// 第二次绘制就表示已经绘制过
                if (drawingGraphics == REFRESHING) {
                    refreshingAnimator.start();// 循环执行
                }
            }
        });
        refreshingAnimator.start();// 循环执行
    }

    public void startFinishAnimation() {
        stopRefreshingAnimator();
        drawingGraphics = FINISH; // 圆环动画
        finishAnimator = ValueAnimator.ofFloat(0, 2);
        // 动画过程
        finishAnimator.addUpdateListener(animation -> {
            invalidate();
        });
        // 动画时间
        finishAnimator.setDuration(ANIMATOR_TIME / 2);// 因为外面的调用者(CustomAnimatorHeader)，只给了 500 毫秒的显示完成时间
        // 插值器
        finishAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        finishAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = 300;
        // 获取合适的宽度值
        int width = getProperSize(defaultSize, widthMeasureSpec);
        // 获取合适的高度值
        int height = getProperSize(defaultSize, heightMeasureSpec);
        // 设置宽高尺寸大小值，此方法决定view最终的尺寸大小
        setMeasuredDimension(width, height);
    }

    /**
     * 获取合适的大小
     *
     * @param defaultSize 默认大小
     * @param measureSpec 测量规格
     * @return a
     */
    private int getProperSize(int defaultSize, int measureSpec) {
        int properSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                // 没有指定大小，设置为默认大小
                properSize = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                // 固定大小，无需更改其大小
                // 会有如下两个情况：
                // 1. match_parent ,也就是 measureSpec 里的尺寸，也就是父容器大小
                // 2. 固定大小 ,也就是布局里设置的尺寸
                // 所以都不用管，直接用 size
                properSize = size;
                break;
            case MeasureSpec.AT_MOST:
                // 此处该值可以取小于等于最大值的任意值，即是 wrap_content
                properSize = defaultSize;
        }

        return properSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, " onDraw : " + drawingGraphics);

        // 需要在这里初始化这些个变量
        if (pullingLineBottomPoint == null || animationRectF == null) {
            initDrawData();
        }

        switch (drawingGraphics) {
            case PULLING:// 下拉动画
                pullingPaint.setStyle(Paint.Style.FILL);
                if (pullingAnimationPercent < pullingPercent) {// 前 40% 绘制竖线
                    drawLine(canvas, 0);
                } else {// 后 60% 绘制圆圈
                    drawLine(canvas, 0 + pullingLineBottomPoint.y * pullingAnimationPercent * 0.95f);// 绘制竖线收缩
                    pullingPaint.setStyle(Paint.Style.STROKE);
                    float circlePercent = (pullingAnimationPercent - pullingPercent);
                    float endAngle = 360 * circlePercent / (1 - pullingPercent);// 计算圆形绘制的弧度
                    canvas.drawArc(animationRectF, 0, endAngle, false, pullingPaint);
                }
                break;
            case REFRESHING:// 正在刷新动画
                float refreshingCirclePercent = (float) refreshingAnimator.getAnimatedValue();
                float sweepAngle = 360;
                if (isShrinkage) {// 绘制正在刷新动画
                    refreshingCircleMatrix.setRotate(360 * refreshingCirclePercent, animationRectF.centerX(), animationRectF.centerY());
                    refreshingSweepGradient.setLocalMatrix(refreshingCircleMatrix);
                    refreshingPaint.setShader(refreshingSweepGradient);
                } else {
                    sweepAngle = 360 * refreshingCirclePercent;
                }
                canvas.drawArc(animationRectF, 0, sweepAngle, false, refreshingPaint);
                break;
            case FINISH:// 完成动画
                if (finishDstPath == null || finishPathMeasure == null) {
                    finishDstPath = new Path();
                    finishPathMeasure = new PathMeasure();
                }
                float finishAnimationPercent = (float) finishAnimator.getAnimatedValue();

                float pathLength = 0;
                float animatorValue = 0;
                if (finishAnimationPercent < 1) {// 正在绘制圆圈
                    finishPathMeasure.setPath(finishCirclePath, false);
                } else {// 正在绘制对勾
                    canvas.drawPath(finishCirclePath, finishPaint);
                    finishPathMeasure.setPath(finishRightMarkPath, false);
                    animatorValue = finishAnimationPercent % 1;//减去绘制圆圈消耗的值
                }

                // 刷新当前截取 Path
               //  finishDstPath.reset();
                // 避免硬件加速的Bug
                // finishDstPath.lineTo(0, 0);

                pathLength = finishPathMeasure.getLength();
                // 截取片段
                float stop = pathLength * animatorValue;
                finishPathMeasure.getSegment(0, stop, finishDstPath, true);
                // 绘制截取的片段
                canvas.drawPath(finishDstPath, finishPaint);

                break;
        }

    }

    private void initDrawData() {  // 计算竖线的最低点，因为圆圈是依靠这个最低点计算绘制范围的，所以需要计算精准
        int stopX = (int) (getMeasuredWidth() / 2 + animationRadius + pullingPaint.getStrokeWidth());
        int stopY = (int) (getMeasuredHeight() - animationRadius - pullingPaint.getStrokeWidth());
        pullingLineBottomPoint = new PointF(stopX, stopY);
        // 根据竖线最低点，确定圆圈的绘制范围
        animationRectF = new RectF(
                pullingLineBottomPoint.x - animationRadius * 2, pullingLineBottomPoint.y - animationRadius,
                pullingLineBottomPoint.x, pullingLineBottomPoint.y + animationRadius);
        float sweepCenterX = animationRectF.left / 2 + animationRadius;
        float sweepCenterY = animationRectF.top / 2 + animationRadius;
        refreshingSweepGradient = new SweepGradient(sweepCenterX, sweepCenterY,
                new int[]{Color.TRANSPARENT, viewColor, viewColor}, new float[]{0.0f, 0.5f, 1f});
        refreshingCircleMatrix = new Matrix();
        refreshingSweepGradient.setLocalMatrix(refreshingCircleMatrix);
        refreshingPaint.setShader(refreshingSweepGradient);
        initFinishAnimationPath();
    }

    private void initFinishAnimationPath() {
        float animationWidth = animationRectF.width();
        float animationHeight = animationRectF.height();
        float circleTop = animationRectF.top;
        float circleLeft = animationRectF.left;

        // 添加圆环路径
        finishCirclePath = new Path();
        float x = animationRectF.width() / 2 + circleLeft;
        float y = animationRectF.height() / 2 + circleTop;
        finishCirclePath.addCircle(x, y, animationRadius, Path.Direction.CW);

        // 添加对勾路径
        Path path = new Path();
        // 对号起点
        float startX = (float) (0.3 * animationWidth) + circleLeft;
        float startY = (float) (0.5 * animationHeight) + circleTop;
        path.moveTo(startX, startY);
        // 对号拐角点
        float cornerX = (float) (0.43 * animationWidth) + circleLeft;
        float cornerY = (float) (0.66 * animationHeight) + circleTop;
        path.lineTo(cornerX, cornerY);
        // 对号终点
        float endX = (float) (0.75 * animationWidth) + circleLeft;
        float endY = (float) (0.4 * animationHeight) + circleTop;
        path.lineTo(endX, endY);
        finishRightMarkPath = path;
    }

    private void drawLine(Canvas canvas, float startY) {
        float startX = pullingLineBottomPoint.x;
        canvas.drawLine(startX, startY, pullingLineBottomPoint.x, pullingLineBottomPoint.y, pullingPaint);
    }

    /**
     * 当View从屏幕消失时，关闭可能在执行的动画，以免可能出现内存泄漏
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRefreshingAnimator();
        stopFinishAnimator();
    }

    private void stopFinishAnimator() {
        if (finishAnimator != null) {
            finishAnimator.cancel();
        }
    }

    private void stopRefreshingAnimator() {
        if (refreshingAnimator != null) {
            refreshingAnimator.cancel();
        }
    }

}
