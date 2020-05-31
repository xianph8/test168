package com.xian.common.arch;


import androidx.lifecycle.LifecycleOwner;
import androidx.annotation.Nullable;

/**
 * 不建议直接使用此类去实现功能
 * <p>
 * 建议继承此类，封装通用的场景基类，以便使用
 *
 * @param <T>
 */
public abstract class SimpleLoadingObserver<T> implements LoadingObserver<T> {

    /**
     * 此方法被下面其它方法代替，不需要重写，
     * 在 {@link LoadingLiveData#observe(LifecycleOwner, LoadingObserver)} 里有具体逻辑
     *
     * @param o t
     */
    @Override
    public final void onChanged(@Nullable T o) {
        // can not override
    }

    public void loading(@Nullable String identifier) {
    }

    public final void dataChange(@Nullable String identifier, @Nullable T data) {
        onDataChange(data);
    }

    protected abstract void onDataChange(@Nullable T data);

    public void error(@Nullable String identifier, @Nullable Throwable error) {
    }
}
