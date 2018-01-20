package com.test.test168.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.test168.R;
import com.xian.common.widget.dialog.LoadingDialog;


/**
 * Created by w07 on 2016/10/13.
 * fragment 基类
 */

public abstract class BaseDialogFragment extends DialogFragment {

    protected Activity mContext = null;

    protected ProgressDialog progressDialog = null;

    protected View root = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BaseDialogFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        root = inflater.inflate(getRootViewId(), null);
        mContext = getActivity();
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    protected abstract int getRootViewId();

    protected abstract void initViews();

//    @Optional
//    @OnClick(R.id.dialog_fragment_close_btn)
    public void BtnDismiss(@Nullable View view) {
        dismiss();
    }

//    protected void setTitleText(int resId) {
//        ((TextView) root.findViewById(R.id.tv_title)).setText(resId);
//    }
//
//    protected void setTitleText(String title) {
//        ((TextView) root.findViewById(R.id.tv_title)).setText(title);
//    }

    protected void startActivity(Class cls) {
        Intent i = new Intent(mContext, cls);
        startActivity(i);
    }

    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int message) {
        showToast(getString(message));
    }

    protected void showLoadingDialog() {
        showLoadingDialog(R.string.loading);
    }

    protected void showLoadingDialog(int resId) {
        if (progressDialog == null) {
            progressDialog = LoadingDialog.getInstance(mContext, resId, false, false);
            progressDialog.show();
        }
    }

    protected void dismissLoadingDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
