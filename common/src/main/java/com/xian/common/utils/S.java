package com.xian.common.utils;

import android.content.Context;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

//Toast统一管理类
public class S {

    private S() {
       /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    public static void show(View view, String msg, int time) {
        Snackbar.make(view, msg, time);
    }

    public static void show(View view, String msg, int time, String tips, View.OnClickListener onClickListener) {
        Snackbar.make(view, msg, time).setAction(tips, onClickListener).show();
    }

    public static void showShort(View view, String msg, String tips, View.OnClickListener onClickListener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction(tips, onClickListener).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(Context mContext, View view, int resId) {
        Snackbar.make(view, mContext.getString(resId), Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(Context mContext, View view, int resId1, int resId2, View.OnClickListener onClickListener) {
        Snackbar.make(view, mContext.getString(resId1), Snackbar.LENGTH_SHORT).setAction(mContext.getString(resId2), onClickListener).show();
    }

    public static void showLong(String msg, String tips, View.OnClickListener onClickListener) {
        Snackbar.make(null, msg, Snackbar.LENGTH_LONG).setAction(tips, onClickListener).show();
    }

}