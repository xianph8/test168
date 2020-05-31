package com.test.test168.view.slide;

import android.content.Context;
import android.os.Handler;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test.test168.R;
import com.test.test168.utils.ImageLoader;
import com.xian.common.utils.XLog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * ViewPager实现的轮播图广告自定义视图； 既支持自动轮播页面也支持手势滑动切换页面,可以动态设置图片的张数
 *
 * @author me
 */
public class SlideView extends FrameLayout {

    // 调用控件页的上下文
    private Context mContext;

    // 显示出来的图片链表
    private List<ImageView> imageViewList;

    // 因为Glide 使用后不能设置tag，要用另一个设置tag的方法(加上tag设置) , id随便
    private int imageTag = R.id.imageView;

    // 当前的位置
    private int currentItem = 0;

    // 指示器布局
    private LinearLayout pointLayout;

    // 承载所有图片的 view pager
    private ViewPager mViewPager;

    // 设置轮播间隔时间
    private int playTime = 3;

    // 设置是否允许自动滚动
    private boolean isAutoPlay = false;

    /**
     * 用以通知 定时任务 工作
     */
    private Handler handler = new Handler();

    /**
     * 处理 定时任务
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            play();
        }
    };

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initUI(mContext);
    }

    /**
     * 初始化控件所需的ui
     */
    private void initUI(Context context) {
        XLog.i("slide view init ui : ");
        // 图片列表的来源
        imageViewList = new ArrayList<>();

        // 布局
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

        // 指示器
        pointLayout = (LinearLayout) findViewById(R.id.slideShowLinearLayout);

        // 轮播的主体 ViewPager
        mViewPager = (ViewPager) findViewById(R.id.slideShowViewPager);
        setListener(mViewPager);
    }

    /**
     * 设置需要轮播的图片列表（这个方法是接收图片主要来源的方法，可改成资源文件）
     */
    public void setImageList(List<String> imageUrl) {
        /*
        设置图片的时候 就把滚动关闭
         */
        stopPlay();

        // 添加第一张图片
        ImageView first = new ImageView(mContext);
        first.setScaleType(ImageView.ScaleType.CENTER_CROP);// 铺满屏幕
        ImageLoader.display(mContext, imageUrl.get(imageUrl.size() - 1), first);
        imageViewList.add(first);

        // 添加所有的图片
        for (int i = 0; i < imageUrl.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// 铺满屏幕
            ImageLoader.display(mContext, imageUrl.get(i), imageView);  // 为每个 ImageView 加载图片
            imageView.setTag(imageTag, i);// 为每张图片设置一个标记，点击的时候用到
            imageView.setOnClickListener(imageClick);// 为每张图片设置点击事件
            imageViewList.add(imageView);// 然后添加到整个轮播的列表中去，用于主体的ViewPager

            // point  每添加一张图片，就为其添加一个圆点
            ImageView point = new ImageView(mContext);
            point.setImageResource(R.drawable.point_seletor);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(mContext, 5), dip2px(mContext, 5));
            params.leftMargin = dip2px(mContext, 10);
            point.setLayoutParams(params);
            point.setEnabled(false);
            pointLayout.addView(point);
        }

        // 添加最后一张图片
        ImageView last = new ImageView(mContext);
//        last.setScaleType(ImageView.ScaleType.CENTER_CROP);// 铺满屏幕
        ImageLoader.display(mContext, imageUrl.get(0), last);
        imageViewList.add(last);

        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new MyPagerAdapter(imageViewList));

        mViewPager.setCurrentItem(1);// 让第一次显示在列表的第二张图片，也就是图片来源的第一张
        pointLayout.getChildAt(0).setEnabled(true);// 同步设置指示器

        // 改变一下viewpager的切换速度
        changeViewpagerSpace();

        /*
        设置完图片后再开启滚动
         */
        startPlay();
    }

    /**
     * 图片点击的点击事件
     */
    private OnClickListener imageClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (l != null) {
                l.onClick(Integer.valueOf(v.getTag(imageTag).toString().trim()));
            }
        }
    };

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 改变viewpager切换速度
     */
    private void changeViewpagerSpace() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mContext, new AccelerateInterpolator());
            field.set(mViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 轮播控件的点击接口
     */
    private OnImageClickListener l;

    /**
     * 设置轮播控件的点击接口
     */
    public void setOnImageClickListener(OnImageClickListener l) {
        this.l = l;
    }

    /**
     * 设置是否自动轮播
     *
     * @param play true 为自动轮播，false 为不自动轮播
     */
    public void setAutoPlay(boolean play) {
        XLog.i("auto play : " + play);
        isAutoPlay = play;
        if (isAutoPlay)
            startPlay();
        else
            stopPlay();
    }

    /***
     * 设置自动滚动时间间隔
     *
     * @param playTime int 类型 轮播时间 单位 ：秒
     */
    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        stopPlay();// 开始之前把之前的停了，避免多个handler执行
        handler.postDelayed(runnable, playTime * 1000);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay() {
        handler.removeCallbacks(runnable);
    }


    /**
     * 操作一次轮播
     */
    private void play() {

        if (isAutoPlay) {
            XLog.i("mViewPager current item : " + mViewPager.getCurrentItem());
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            handler.postDelayed(runnable, playTime * 1000);
        }

    }

    /**
     * 清除掉列表中所有的图片
     */
    public void destroy() {
        stopPlay();
        imageViewList.clear();
        pointLayout.removeAllViews();
        currentItem = 0;
        handler = null;
        runnable = null;
    }

    /**
     * 为 主体ViewPager设置两个监听器
     * <p>
     * OnPageChangeListener
     * <p>
     * OnTouchListener
     *
     * @param viewPager
     */
    private void setListener(ViewPager viewPager) {

        //     * 轮播控件的监听器（无限轮播的主要逻辑）
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                changePoint();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    if (currentItem == imageViewList.size() - 1) {
                        mViewPager.setCurrentItem(1, false);
                    } else if (currentItem == 0) {
                        mViewPager.setCurrentItem(imageViewList.size() - 2, false);
                    }
                }
            }

            private void changePoint() {
                for (int i = 0; i < pointLayout.getChildCount(); i++) {
                    pointLayout.getChildAt(i).setEnabled(false);
                }
                if (currentItem == imageViewList.size() - 1) {
                    pointLayout.getChildAt(0).setEnabled(true);//R.drawable.main_dot_light
                } else if (currentItem == 0) {
                    pointLayout.getChildAt(pointLayout.getChildCount() - 1).setEnabled(true);//R.drawable.main_dot_light
                } else {
                    pointLayout.getChildAt(currentItem - 1).setEnabled(true);//R.drawable.main_dot_light
                }
            }
        });

        //     * 轮播控件的触摸监听器
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {//按住事件发生后
//                    stopPlay();
                        setAutoPlay(false);
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {//移动事件发生后
//                    stopPlay();
                        setAutoPlay(false);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {//松开事件发生后
//                    startPlay();
                        setAutoPlay(true);
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
