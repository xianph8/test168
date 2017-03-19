package com.xian.common.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.xian.common.R;


/**
 * Created by Test on 2016/7/5.
 */
public class AskDialog {
//    private static AskDialog ourInstance = new AskDialog();

    private AskDialog() {
    }

    public static AskDialog getInstance() {
        return new AskDialog();
    }

    public void show(Context mContext,
                     String title,
                     String message,
                     DialogInterface.OnClickListener onPositiveClickListener,
                     DialogInterface.OnClickListener onNegativeClickListener) {
        show(mContext,
                title,
                message,
                "确定", onPositiveClickListener,
                "取消", onNegativeClickListener);
    }

    public void show(Context mContext,
                     int title,
                     int message,
                     DialogInterface.OnClickListener onPositiveClickListener,
                     DialogInterface.OnClickListener onNegativeClickListener) {
        show(mContext,
                mContext.getString(title),
                mContext.getString(message),
                "确定", onPositiveClickListener,
                "取消", onNegativeClickListener);
    }

    public void show(Context mContext,
                     String title,
                     String message,
                     String positiveText,
                     DialogInterface.OnClickListener onPositiveClickListener,
                     String negativeText,
                     DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext, R.style.CustomAlertDialog)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .setOnDismissListener(new AskDialogDismissListener());
        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    private class AskDialogDismissListener implements DialogInterface.OnDismissListener {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
//            ourInstance = null;
        }
    }
}

/**
 * /**
 * 含有标题、内容、两个按钮的对话框
 * <p/>
 * protected android.app.AlertDialog showAlertDialog(String title, String message,
 * String positiveText,
 * DialogInterface.OnClickListener onPositiveClickListener,
 * String negativeText,
 * DialogInterface.OnClickListener onNegativeClickListener) {
 * android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).setTitle(title)
 * .setMessage(message)
 * .setPositiveButton(positiveText, onPositiveClickListener)
 * .setNegativeButton(negativeText, onNegativeClickListener)
 * .show();
 * return alertDialog;
 * }
 */
