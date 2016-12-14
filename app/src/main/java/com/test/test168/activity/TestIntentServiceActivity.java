package com.test.test168.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.test.test168.service.TestIntentService;
import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.view.LoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestIntentServiceActivity extends BaseActivity {

    protected ProgressDialog progressDialog = null;

    @Bind(R.id.btn_start_intent_service)
    Button mBtnStartIntentService;
    @Bind(R.id.activity_test_intent_service)
    RelativeLayout mActivityTestIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_service);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick(R.id.btn_start_intent_service)
    public void onStartService() {
        progressDialog = LoadingDialog.getInstance(mContext, "显示 loading dialog ", false, false);
        progressDialog.show();
        Intent in = new Intent(mContext, TestIntentService.class);
        startService(in);
    }
}
