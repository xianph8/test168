package com.test.test168.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.test.test168.R;
import com.test.test168.base.BaseActivity;
import com.test.test168.service.TestIntentService;
import com.xian.common.widget.dialog.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestIntentServiceActivity extends BaseActivity {

    protected ProgressDialog progressDialog = null;

    @BindView(R.id.btn_start_intent_service)
    Button mBtnStartIntentService;
    @BindView(R.id.activity_test_intent_service)
    RelativeLayout mActivityTestIntentService;

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_test_intent_service);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @OnClick(R.id.btn_start_intent_service)
    public void onStartService() {
        progressDialog = LoadingDialog.getInstance(mActivity, "显示 loading dialog ", false, false);
        progressDialog.show();
        Intent in = new Intent(mActivity, TestIntentService.class);
        startService(in);
    }
}
