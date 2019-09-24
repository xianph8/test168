package com.xian.common.arch;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseViewModel extends ViewModel {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    protected Consumer<Disposable> onSubscribe() {
        return new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                disposables.add(disposable);
            }
        };
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
