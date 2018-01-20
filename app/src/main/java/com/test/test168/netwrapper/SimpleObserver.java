package com.test.test168.netwrapper;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author xian
 * @date2017/11/3
 */

public abstract class SimpleObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
