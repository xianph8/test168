package com.test.test168.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xian.common.arch.LoadingResource;
import com.xian.common.arch.SimpleLoadingObserver;

public abstract class BaseLoadingFragment extends BaseFragment {



    /**
     * 内部类，统一处理 LoadingResource ，减少冗余
     *
     * @param <T>
     */
    protected abstract class BaseLoadingObserver<T> extends SimpleLoadingObserver<T> {
        @Override
        public void loading(@Nullable String identifier) {
            showLoadingDialog();
        }

        @Override
        public void error(@Nullable String identifier, @Nullable Throwable error) {
            if (error != null) {
                if (LoadingResource.PresetIdentifiers.TOAST.equalsIgnoreCase(identifier)) {
                    showToast(error.getMessage());
                } else {
                    dismissLoadingDialog();
                    error.printStackTrace();
                    showToast(error.getMessage());
                    onError(error);
                }
            }
        }

        @Override
        protected void onDataChange(@Nullable T data) {
            dismissLoadingDialog();
            if (data != null) {
                onSuccess(data);
            }
        }

        protected void onSuccess(@NonNull T data) {

        }

        protected void onError(Throwable error) {

        }

    }

}
