package com.test.test168.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.test.test168.event.LoadingEvent;
import com.xian.common.utils.XLog;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by King on 2016/3/27.
 */
public class LoadingSingletonDialog {

    ProgressDialog progressDialog = null;

    public LoadingSingletonDialog() {
    }

    //内部类，在装载该内部类时才会去创建单利对象
    private static class LoadingDialogHolder {
        public static LoadingSingletonDialog instance = new LoadingSingletonDialog();
    }

    public static LoadingSingletonDialog getInstance() {
        return LoadingDialogHolder.instance;
    }

    public void show(Context mContext, String msg) {

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
        } else {
            return;
        }

        if (msg != null && !msg.equals("")) {
            progressDialog.setMessage(msg);
        } else {
            progressDialog.setMessage("正在加载中，请稍候…");
        }

        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.setIndeterminate(true);

        setListener();

        progressDialog.show();
    }

    public void dismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
//            progressDialog.hide();
            progressDialog = null;
        }
    }

    private void setListener() {
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                XLog.i("progressDialog onCancel : " + dialog);
                EventBus.getDefault().post(new LoadingEvent(LoadingEvent.CANCEL));
            }
        });

        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                XLog.i("progressDialog onDismiss : " + dialog);
                EventBus.getDefault().post(new LoadingEvent(LoadingEvent.DISMISS));
            }
        });

        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                XLog.i("progressDialog onKey : " + dialog);
                XLog.i("progressDialog onKey : " + keyCode);
                XLog.i("progressDialog onKey : " + event);
                EventBus.getDefault().post(new LoadingEvent(LoadingEvent.KEY));
                return false;
            }
        });

        progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                XLog.i("progressDialog onShow : " + dialog);
                EventBus.getDefault().post(new LoadingEvent(LoadingEvent.SHOW));
            }
        });
    }

}
