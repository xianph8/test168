package com.test.test168.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.utils.ImageUtils;
import com.test.test168.utils.L;
import com.test.test168.view.SlideView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlideActivity extends BaseActivity {

    private SlideView slideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        ButterKnife.bind(this);
        L.i("onCreate :　");

        initView();

        initData();

    }

    private void initView() {
        L.i("init view : ");
        slideView = $(R.id.sv_banner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = $(R.id.iv_test);
        ImageUtils.displayUrl(mActivity, "http://365jia.cn/uploads/13/0926/5244094a0cdda.jpg", imageView);
    }

    private void initData() {
        L.i("init data : ");
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
        slideView.setOnImageClickListener(new SlideView.OnImageClickListener() {
            @Override
            public void onClick(int position) {        // 添加监听器，点击有反应
                showShortToast("this is : " + position);
                L.i("position : " + position);
            }
        });
        slideView.setPlayTime(3);// 设置滚动时间间隔为2秒
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


    @OnClick({R.id.btn_start, R.id.btn_stop})
    void controller(View view) {

        switch (view.getId()) {
            case R.id.btn_start:
                slideView.setAutoPlay(true);
                showShortToast("start");
                break;
            case R.id.btn_stop:
                slideView.setAutoPlay(false);
                showShortToast("stop");
                break;
        }

    }

}
