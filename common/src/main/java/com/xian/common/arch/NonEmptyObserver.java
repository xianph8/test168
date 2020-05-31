package com.xian.common.arch;

import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
