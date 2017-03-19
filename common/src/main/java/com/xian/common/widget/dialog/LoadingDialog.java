package com.xian.common.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.xian.common.R;


/**
 * Created by Administrator on 2016-09-30.
 */

public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static ProgressDialog getInstance(Context mContext,
                                             String message,
                                             boolean canTouchOutside,
                                             boolean canCancelable) {
        ProgressDialog progressDialog = new ProgressDialog(mContext, R.style.CustomAlertDialog);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(canTouchOutside);
        progressDialog.setCancelable(canCancelable);
        progressDialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
//                MyEventBus.post(new CloseEvent());
            }
        });
        return progressDialog;
    }

    public static ProgressDialog getInstance(Context mContext,
                                             int message,
                                             boolean canTouchOutside,
                                             boolean canCancelable) {
        return getInstance(mContext, mContext.getString(message), canTouchOutside, canCancelable);
    }

    public static ProgressDialog getInstance(Context mContext,
                                             String message,
                                             boolean canTouchOutside) {
        return getInstance(mContext, message, canTouchOutside, true);
    }

    public static ProgressDialog getInstance(Context mContext,
                                             int message,
                                             boolean canTouchOutside) {
        return getInstance(mContext, message, canTouchOutside, true);
    }

    public static ProgressDialog getInstance(Context mContext, String message) {
        return getInstance(mContext, message, true, true);
    }

    public static ProgressDialog getInstance(Context mContext, int message) {
        return getInstance(mContext, message, true, true);
    }

}
