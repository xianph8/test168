package com.xian.common.arch;

import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;


public interface LoadingObserver<T> extends Observer<T> {
    void loading(@Nullable String identifier);

    void dataChange(@Nullable String identifier, @Nullable T data);

    void error(@Nullable String identifier, @Nullable Throwable error);
}
