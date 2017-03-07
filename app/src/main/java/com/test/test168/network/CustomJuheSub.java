package com.test.test168.network;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.test.test168.bean.JuheResult;
import com.test.test168.utils.XLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;


/**
 * Created by w07 on 2016/9/14 14:04
 * Description : rxjava 的 订阅者 网络调用 的封装
 */
public abstract class CustomJuheSub<T> extends Subscriber<JuheResult<T>> {

    private Context mContext = null;

    public CustomJuheSub() {

    }

    public CustomJuheSub(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mContext != null) {
//            UIUtils.openLoadingDialog(mContext, false);
        }
    }

    @Override
    public void onCompleted() {
        closeDialog();
    }

    private void closeDialog() {
//        if (mContext != null && mContext instanceof BaseActivity) {
//            ProgressDialog dialog = ((BaseActivity) mContext).mDialog;
//            if (dialog != null) dialog.dismiss();
//        }
    }

    @Override
    public void onError(Throwable e) {
        XLog.e(e);
        closeDialog();
        if (e instanceof ConnectException) {
            onFailure("网络连接错误");
        } else if (e instanceof NetworkErrorException) {
            onFailure("网络连接错误");
        } else if (e instanceof TimeoutException) {
            onFailure("网络连接超时");
        } else if (e instanceof SocketTimeoutException) {
            onFailure("网络连接超时");
        }
    }

    @Override
    public void onNext(JuheResult<T> o) {
        if (o.error_code == 0) {
            onSuccess(o.result);
        } else {
            onFailure(o.reason);
        }
    }

    protected abstract void onSuccess(T result);

    protected abstract void onFailure(String errorMsg);

}
