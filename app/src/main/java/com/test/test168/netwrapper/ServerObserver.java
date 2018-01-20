package com.test.test168.netwrapper;


import android.content.Context;
import android.widget.Toast;


import com.xian.common.utils.XLog;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ServerObserver<T> implements Observer<ServerResponse<T>> {

    public static final int STATE_NORMAL = 1;
    public static final int STATE_FAILURE = 0;
    public static final int STATE_PARAMS_ERROR = 400;
    public static final int STATE_LOGIN_ERROR = 401;

    public static final int STATE_NO_MONEY = 2;// 余额不足
    public static final int STATE_BOUGHT = 3;// 已经购买

    private Disposable mDisposable;

    public ServerObserver() {

    }

    public void onStart() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        onStart();
    }

    public void unSubscriber() {
        if (mDisposable != null && mDisposable.isDisposed()) {
            XLog.d(" unSubscriber mDisposable : " + mDisposable);
            mDisposable.dispose();
        }
    }

    @Override
    public void onError(Throwable e) {
        XLog.e("onError : " + e);
        e.printStackTrace();
        if (e instanceof UnknownHostException) {
            onFailure("网络异常，请检查网络连接");
        } else if (e instanceof SocketException) {
            onFailure("网络异常，请稍后再试");
        } else if (e instanceof SocketTimeoutException) {
            onFailure("网络异常，请稍后再试");
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        XLog.d("onComplete : ");
        unSubscriber();
        onFinish();
    }

    @Override
    public void onNext(ServerResponse<T> result) {
        int state = result.state;
        XLog.d("onNext : " + result.data);
        if (state == STATE_NORMAL) { // 接口访问成功，正常返回数据
            onSuccess(result.data);
        } else if (state == STATE_FAILURE) {// 接口访问失败，提示信息
            onFailure(result.msg);
        } else if (state == STATE_PARAMS_ERROR) {// 异常(参数/时间/签名/请求等异常)
            onParamsError();
        } else if (state == STATE_LOGIN_ERROR) {// 异常(没有权限/未登录)
            onUserNotLogin();
        } else {
            onFailure(result.state, result.data, result.msg);
        }
    }

    public void onFailure(int state, T data, String msg) {
        XLog.e("其他情况，可以重写该方法自己处理");
    }

    private void onParamsError() {
        XLog.e("参数异常哦，查查接口文档有什么错误");
    }

    public void onUserNotLogin() {
        XLog.e("用户没登录哦，自己作处理跳去登录页面吧");
//        Context context = AppContextUtils.getInstance().getContext();
//        if (context != null) {
//            Toast.makeText(context, "您没权限哦，请退出重新登录吧", Toast.LENGTH_SHORT).show();
//        }
    }

    public abstract void onSuccess(T data);

    public abstract void onFailure(String message);

    public void onFinish() {

    }

}
