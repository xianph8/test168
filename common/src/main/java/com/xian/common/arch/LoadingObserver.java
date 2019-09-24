package com.xian.common.arch;

import android.support.annotation.Nullable;

public interface LoadingObserver<T> {
    void loading(@Nullable String identifier);

    void dataChange(@Nullable String identifier, @Nullable T data);

    void error(@Nullable String identifier, @Nullable Throwable error);
}
