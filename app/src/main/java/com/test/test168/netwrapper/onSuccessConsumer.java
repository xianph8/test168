package com.test.test168.netwrapper;

import io.reactivex.functions.Consumer;

/**
 * Created by xian on 2017/8/5.
 */

public abstract class onSuccessConsumer<T> implements Consumer<ServerResponse<T>> {

    @Override
    public void accept(ServerResponse<T> tServerResult) throws Exception {
        if (tServerResult.isSuccess()) {
            onSuccess(tServerResult.data);
        } else {
            onFailure(tServerResult.msg);
        }
    }

    protected abstract void onFailure(String message);

    protected abstract void onSuccess(T data);

}
