package com.xian.common.arch;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseAppViewModel extends AndroidViewModel {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    public BaseAppViewModel(@NonNull Application application) {
        super(application);
    }

    protected Context getContext() {
        return getApplication();
    }

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
