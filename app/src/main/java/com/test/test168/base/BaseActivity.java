package com.test.test168.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.test.test168.R;
import com.xian.common.widget.dialog.LoadingDialog;


/**
 * Created by ddh  on 2015/11/ 16:33 Usage : activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity = BaseActivity.this;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }

    //bn = $(R.id.bn1);
    protected <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


    public void BtnFinish(View view) {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract void initViews();

//    protected void setTitleText(int resId) {
//        ((TextView) findViewById(R.id.tv_title)).setText(resId);
//    }
//
//    protected void setTitleText(String title) {
//        ((TextView) findViewById(R.id.tv_title)).setText(title);
//    }

    protected void startActivity(Class cls) {
        Intent i = new Intent(mActivity, cls);
        startActivity(i);
    }

    protected void showToast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int message) {
        showToast(getString(message));
    }

    protected void showLoadingDialog() {
        showLoadingDialog(R.string.loading);
    }

    protected void showLoadingDialog(int resId) {
        if (resId == 0) {
            showLoadingDialog();
        }
        if (progressDialog == null) {
            progressDialog = LoadingDialog.getInstance(mActivity, resId, false, false);
        } else {
            progressDialog.setMessage(mActivity.getString(resId));
        }
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void dismissLoadingDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
