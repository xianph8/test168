package com.test.test168.activity;

import android.os.Handler;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.utils.ImageLoader;
import com.test.test168.view.slide.OnImageClickListener;
import com.test.test168.view.slide.SlideView;
import com.xian.common.utils.XLog;

import java.util.ArrayList;
import java.util.List;


/**
 * 这个Activity展示的是我自定义的控件
 */
public class SlideActivity extends BaseActivity {

    private SlideView slideView;

    private ImageView iv_test;

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_slide);
        XLog.i("onCreate :　");
        initView();

        initData();
    }

    private void initView() {
        XLog.i("init view : ");
        slideView = $(R.id.sv_banner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include_head_toolbar);
        setSupportActionBar(toolbar);

        iv_test = $(R.id.iv_test);
        ImageLoader.display(mActivity, "http://365jia.cn/uploads/13/0926/5244094a0cdda.jpg", iv_test);

        startRotate();

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller(v);
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller(v);
            }
        });
    }

    private Handler handler = null;
    private Runnable runnable = null;
    private int rotateTime = 30;// 毫秒

    private void startRotate() {
        XLog.i("------> rotateLoop : ");
        if (handler == null && runnable == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    float rotation = iv_test.getRotation() + 10;
                    iv_test.setRotation(rotation == 360 ? 0 : rotation);
                    XLog.i("------> run : " + rotation);
                    handler.postDelayed(runnable, rotateTime);
                }
            };
            handler.postDelayed(runnable, rotateTime);
        }
    }

    private void stopRotate() {
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }
    }

    private void initData() {
        XLog.i("init data : ");
        List<String> imageList = new ArrayList<>();
        imageList.add("http://www.emirates.com/cn/chinese/images/Emirates-A380-800_tcm266-2361507.jpg");
        imageList.add("http://cimg.163.com/catchpic/B/BD/BD2BDB5ED1308F46A29BBFD5B4DD335B.jpg");
        imageList.add("http://www.ce.cn/xwzx/gjss/gdxw/200408/12/W020040812662571968623.jpg");
        imageList.add("http://file27.mafengwo.net/M00/0D/62/wKgB6lP4RCCAbMDMAAVnKGTv1VU90.jpeg");
        imageList.add("http://img.fs0757.com/editor/201110/20111019101755174.jpg");
        imageList.add("http://365jia.cn/uploads/13/0926/5244094a0cdda.jpg");
        imageList.add("http://photocdn.sohu.com/20100120/Img269723231.jpg");
        imageList.add("http://upload.17u.net/uploadpicbase/2011/11/23/aa/2011112315323160754.jpg");

        slideView.setImageList(imageList); // 最后 为SlideView设置图片来源
        slideView.setOnImageClickListener(new OnImageClickListener() {
            @Override
            public void onClick(int position) {        // 添加监听器，点击有反应
                showToast("this is : " + position);
                XLog.i("position : " + position);
            }
        });
        slideView.setPlayTime(2);// 设置滚动时间间隔为2秒
        slideView.setAutoPlay(true);// 在界面重新显示的时候，开始滚动
    }

    @Override
    protected void onStart() {
        super.onStart();
        slideView.setAutoPlay(true);// 在界面重新显示的时候，开始滚动
    }

    @Override
    protected void onPause() {
        super.onPause();
        slideView.setAutoPlay(false);// 在界面不在栈顶的时候，暂停滚动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        slideView.destroy();
    }


    void controller(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startRotate();
                slideView.setAutoPlay(true);
                showToast("start");
                break;
            case R.id.btn_stop:
                stopRotate();
                slideView.setAutoPlay(false);
                showToast("stop");
                break;
        }
    }
}
