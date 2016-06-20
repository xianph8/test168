package com.test.test168.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.test168.utils.L;
import com.test.test168.view.dialog.LoadingDialog;


public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        mActivity = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        L.i("onOptionsItemSelected : ");
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //bn = $(R.id.bn1);
    protected <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


    protected void showLoadingDialog(String text) {
        LoadingDialog.getInstance().show(mActivity, text);
    }

    protected void dismissLoadingDialog() {
        LoadingDialog.getInstance().dismiss();
    }

    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        showShortToast(getString(resId));
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void showShortToast(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有标题和内容的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).show();
        return alertDialog;
    }

    /**
     * 含有标题、内容、两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
        return alertDialog;
    }

    /**
     * 含有标题、内容、图标、两个按钮的对话框
     **/
    protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setIcon(icon)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
        return alertDialog;
    }
}
