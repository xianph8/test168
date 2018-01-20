package com.test.test168.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.service.TestIntentService;
import com.xian.common.widget.dialog.LoadingDialog;


public class TestIntentServiceActivity extends BaseActivity {

    protected ProgressDialog progressDialog = null;

    Button mBtnStartIntentService;
    RelativeLayout mActivityTestIntentService;

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_test_intent_service);
        mBtnStartIntentService = findViewById(R.id.btn_start_intent_service);
        mBtnStartIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartService();
            }
        });
        mActivityTestIntentService = findViewById(R.id.activity_test_intent_service);
        mActivity = this;
    }

    public void onStartService() {
        progressDialog = LoadingDialog.getInstance(mActivity, "显示 loading dialog ", false, false);
        progressDialog.show();
        Intent in = new Intent(mActivity, TestIntentService.class);
        startService(in);
    }
}
