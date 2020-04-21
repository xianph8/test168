package com.xian.common.arch;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

/**
 * 供 监听者 使用
 *
 * @param <T>
 */
public class LoadingLiveData<T> extends LiveData<LoadingResource<T>> {

    public void observe(LifecycleOwner owner, LoadingObserver<T> observer) {
        super.observe(owner, tLoadingResource -> {
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
        });
    }

    @Nullable
    public T getData() {
        LoadingResource<T> value = getValue();
        if (value != null) {
            return value.data;
        } else {
            return null;
        }
    }
}
