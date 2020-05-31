package com.xian.common.arch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Represent the loading resources of UI.
 *
 * @param <D> The data type.
 */
public class LoadingResource<D> {

    @Nullable
    public final String identifier;

    @NonNull
    public final Status status;

    @Nullable
    public final D data;

    @Nullable
    public final Throwable error;

    private LoadingResource(@NonNull Status status, @Nullable String identifier, @Nullable D data, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.error = throwable;
        this.identifier = identifier;
    }

    public static <D> LoadingResource<D> success(@Nullable D data) {
        return success(null, data);
    }

    public static <D> LoadingResource<D> success() {
        return success(null, null);
    }

    public static <D> LoadingResource<D> loading() {
        return loading(null);
    }

    public static <D> LoadingResource<D> toast(String msg) {
        return error(PresetIdentifiers.TOAST, msg == null ? new LoadingException("网络出错！请稍候再试") : new LoadingException(msg));
    }

    public static <D> LoadingResource<D> error(@Nullable Throwable throwable) {
        return error(null, throwable);
    }

    public static <D> LoadingResource<D> error(@Nullable String msg) {
        return error(null, msg == null ? new LoadingException("网络出错！请稍候再试") : new LoadingException(msg));
    }

    public static <D> LoadingResource<D> success(String identifier, @Nullable D data) {
        return new LoadingResource<>(Status.SUCCESSFUL, identifier, data, null);
    }

    public static <D> LoadingResource<D> loading(String identifier) {
        return new LoadingResource<>(Status.LOADING, identifier, null, null);
    }

    public static <D> LoadingResource<D> error(String identifier, @Nullable Throwable throwable) {
        return new LoadingResource<>(Status.FAILED, identifier, null, throwable);
    }

    public boolean isSuccess() {
        return Status.SUCCESSFUL.equals(status);
    }

    public boolean isLoading() {
        return Status.LOADING.equals(status);
    }

    @Override
    public String toString() {
        return "LoadingResource{" +
                "identifier='" + identifier + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", error=" + error +
                '}';
    }

    public enum Status {
        LOADING,

        SUCCESSFUL,

        FAILED
    }

    public interface PresetIdentifiers {
        String REFRESH = "REFRESH";
        String LOAD_MORE = "LOAD_MORE";
        String NO_MORE = "NO_MORE";
        String TOAST = "TOAST";
    }
}
