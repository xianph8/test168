package com.test.test168.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @author xian
 * @date 2018/4/23
 */

public class TestCircleView extends View {
    public TestCircleView(Context context) {
        super(context);
        TextView textView = new TextView(context);
    }

    public TestCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        Paint refreshingPaint = new Paint();
        refreshingPaint.setAntiAlias(true);//取消锯齿
        refreshingPaint.setColor(Color.GREEN);
        refreshingPaint.setStyle(Paint.Style.STROKE);
        refreshingPaint.setStrokeWidth(10);
        canvas.drawArc(rectF, 90, 360, false, refreshingPaint);
    }

    /**
     * 一般在自定义 View 过程中，不会重写该方法，只有在此 View 需要支持 wrap_content 才需要重写这个方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    /**
     * 通用的测量方法，当默认的宽高不一样时，需要分开写
     *
     * @param measureSpec 需要测量的宽高
     * @return 测量结果
     */
    private int measureSpec(int measureSpec) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
