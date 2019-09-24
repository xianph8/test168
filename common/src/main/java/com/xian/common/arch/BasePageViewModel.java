package com.xian.common.arch;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BasePageViewModel<T> extends AndroidViewModel {

    protected final CompositeDisposable disposables = new CompositeDisposable();
    private List<T> mList = new ArrayList<>();
    private int mCurrentPage = 1;
    private int mTotalPage = 0;

    public BasePageViewModel(@NonNull Application application) {
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

    public abstract Observable<List<T>> loadMore();

    public abstract Observable<List<T>> refresh();

    @Override
    protected void onCleared() {

        super.onCleared();
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
