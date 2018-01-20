package com.test.test168.rx;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.xian.common.utils.T;
import com.xian.common.utils.XLog;

import java.util.concurrent.TimeUnit;


import static com.test.test168.R.id.tv_test1;
import static com.test.test168.R.id.tv_test2;

/**
 * 在这个Activity学习使用RxAndroid
 */

/***
 * Attention：
 * 1.使用RxBinding的时候，要同时引入支持包
 * 2.
 */
public class RxJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);  //
        initView();
    }

    @Override
    protected void initViews() {

    }

    private void initView() {
    }

}
