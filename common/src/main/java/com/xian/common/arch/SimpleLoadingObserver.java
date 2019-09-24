package com.xian.common.arch;

import android.support.annotation.Nullable;

public abstract class SimpleLoadingObserver<T> implements LoadingObserver<T> {
    public void loading(@Nullable String identifier) {
    }

    public void dataChange(@Nullable String identifier, @Nullable T data) {
        onDataChange(data);
    }

    protected abstract void onDataChange(@Nullable T data);

    public void error(@Nullable String identifier, @Nullable Throwable error) {
    }
}
