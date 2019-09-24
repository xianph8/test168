package com.xian.common.arch;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class NonEmptyObserver implements Observer {
    @Override
    public void onChanged(@Nullable Object o) {
        if (o == null) {
            return;
        }
        subChanged(o);
    }

    protected abstract void subChanged(@NonNull Object o);

}
