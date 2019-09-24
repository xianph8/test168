package com.xian.common.arch;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

public class LoadingLiveData<T> extends MutableLiveData<LoadingResource<T>> {

    public void observe(LifecycleOwner owner, final LoadingObserver<T> observer) {
        super.observe(owner, new Observer<LoadingResource<T>>() {
            @Override
            public void onChanged(@Nullable LoadingResource<T> tLoadingResource) {
                if (tLoadingResource == null) {
                    return;
                }
                switch (tLoadingResource.status) {
                    case LOADING:
                        observer.loading(tLoadingResource.identifier);
                        break;
                    case SUCCESSFUL:
                        observer.dataChange(tLoadingResource.identifier, tLoadingResource.data);
                        break;
                    case FAILED:
                        observer.error(tLoadingResource.identifier, tLoadingResource.error);
                        break;
                }
            }
        });
    }

    public void success(T t) {
        setValue(LoadingResource.success(t));
    }

    public void error(Throwable throwable) {
        setValue(LoadingResource.<T>error(throwable));
    }

    public void error(String errorMsg) {
        setValue(LoadingResource.<T>error(errorMsg));
    }

    public void loading() {
        setValue(LoadingResource.<T>loading());
    }

}
