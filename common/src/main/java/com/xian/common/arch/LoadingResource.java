package com.xian.common.arch;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


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

    public static <D> LoadingResource<D> loading() {
        return loading(null);
    }

    public static <D> LoadingResource<D> error(@Nullable Throwable throwable) {
        return error(null, throwable);
    }

    public static <D> LoadingResource<D> error(@Nullable String msg) {
        return error(null, msg == null ? new InvalidResponseException("网络出错！请稍候再试") : new InvalidResponseException(msg));
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

    public enum Status {
        LOADING,

        SUCCESSFUL,

        FAILED
    }

    public interface PresetIdentifiers {
        String REFRESH = "REFRESH";
        String LOAD_MORE = "LOAD_MORE";
    }
}