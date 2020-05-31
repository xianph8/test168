package com.test.test168.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public abstract class BaseFragment extends Fragment {

    protected Activity mContext = null;

    protected ProgressDialog progressDialog = null;

    protected View root = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getRootViewId(), container, false);
        mContext = getActivity();
        initViews();
        return root;
    }

    protected abstract int getRootViewId();

    protected abstract void initViews();

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


    @Nullable
    public <T extends View> T findViewById(@IdRes int id) {
        if (id == View.NO_ID) {
            return null;
        }
        if (getView() == null) return null;
        return getView().findViewById(id);
    }
}
