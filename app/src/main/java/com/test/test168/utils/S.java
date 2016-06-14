package com.test.test168.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

//Toast统一管理类
public class S {

    private S() {
       /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    public static void show(View view, String msg, int time) {
        if (isShow)
            Snackbar.make(view, msg, time);
    }

    public static void show(View view, String msg, int time, String tips, View.OnClickListener onClickListener) {
        if (isShow) Snackbar.make(view, msg, time).setAction(tips, onClickListener).show();
    }

    public static void showShort(View view, String msg, String tips, View.OnClickListener onClickListener) {
        if (isShow)
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction(tips, onClickListener).show();
    }

    public static void showShort(View view, String msg) {
        if (isShow) Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(Context mContext, View view, int resId) {
        if (isShow) Snackbar.make(view, mContext.getString(resId), Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(Context mContext, View view, int resId1, int resId2, View.OnClickListener onClickListener) {
        if (isShow)
            Snackbar.make(view, mContext.getString(resId1), Snackbar.LENGTH_SHORT).setAction(mContext.getString(resId2), onClickListener).show();
    }

    public static void showLong(String msg, String tips, View.OnClickListener onClickListener) {
        if (isShow)
            Snackbar.make(null, msg, Snackbar.LENGTH_LONG).setAction(tips, onClickListener).show();
    }


}