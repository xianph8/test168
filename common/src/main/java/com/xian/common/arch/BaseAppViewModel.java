package com.xian.common.arch;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseAppViewModel extends AndroidViewModel {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    public BaseAppViewModel(@NonNull Application application) {
        super(application);
    }

    protected Consumer<Disposable> onSubscribe() {
        return disposables::add;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    protected <T> boolean loadingResourceDataNull(LoadingLiveData<T> loadingResource) {
        return loadingResource == null || loadingResource.getValue() == null || loadingResource.getValue().data == null;
    }

    public String getString(@StringRes int stringId) {
        return getApplication().getString(stringId);
    }

    public String getString(@StringRes int resId, Object... formatArgs) {
        return getApplication().getString(resId, formatArgs);
    }

    public SharedPreferences getSharedPreferences(String configName) {
        return getApplication().getSharedPreferences(configName, Context.MODE_PRIVATE);
    }

}
