package com.xian.common.module;

import android.accounts.NetworkErrorException;
import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by w07 on 2016/9/14 14:04
 * Description : rxjava 的 订阅者 网络调用 的封装
 */
public abstract class CustomSub<T> implements Observer<RequestResult<T>> {

    private Context mContext = null;

    public CustomSub() {

    }

    public CustomSub(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onComplete() {
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
    public void onNext(RequestResult<T> o) {
        if (o.isSuccess()) {
            onSuccess(o.getData());
        } else {
            onFailure(o.getMessage());
        }
    }

    protected abstract void onSuccess(T result);

    protected abstract void onFailure(String errorMsg);

}
