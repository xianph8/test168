package com.test.test168.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by w07 on 2016/10/31 16:43
 * Usage :
 */

//Toast统一管理类
public class T {

    private static Toast toast;

    public static void showToast(Context context, CharSequence content, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    private T() {
       /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            showToast(context, message, Toast.LENGTH_SHORT);
    }

//    /**
//     * 短时间显示Toast
//     *
//     * @param context
//     * @param message
//     */
//    public static void showShort(Context context, int message) {
//        if (isShow)
//            showToast(context, message, Toast.LENGTH_SHORT);
//    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            showToast(context, message, Toast.LENGTH_LONG);
    }

//    /**
//     * 长时间显示Toast
//     *
//     * @param context
//     * @param message
//     */
//    public static void showLong(Context context, int message) {
//        if (isShow)
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            showToast(context, message, duration);
    }

//    /**
//     * 自定义显示Toast时间
//     *
//     * @param context
//     * @param message
//     * @param duration
//     */
//    public static void show(Context context, int message, int duration) {
//        if (isShow)
//            Toast.makeText(context, message, duration).show();
//    }

}