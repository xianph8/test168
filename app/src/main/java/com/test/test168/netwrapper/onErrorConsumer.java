package com.test.test168.netwrapper;

import android.annotation.SuppressLint;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.function.Consumer;

/**
 * Created by xian on 2017/8/5.
 */

@SuppressLint("NewApi")
public abstract class onErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(Throwable e) {
        e.printStackTrace();
        if (e instanceof UnknownHostException) {
            onFailure("服务器地址异常，请重新确认");
        } else if (e instanceof SocketException) {
            onFailure("网络异常，请稍后再试");
        } else if (e instanceof SocketTimeoutException) {
            onFailure("网络异常，请稍后再试");
        } else {
            onFailure(e.getMessage());
        }
    }

    protected abstract void onFailure(String error);

}
