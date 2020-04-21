package com.xian.common.arch;

/**
 * 供 ViewModel 使用
 *
 * @param <T>
 */
public class LoadingMutableLiveData<T> extends LoadingLiveData<T> {

    public void setValue(LoadingResource<T> value) {
        super.setValue(value);
    }

    protected void postValue(LoadingResource<T> value) {
        super.postValue(value);
    }

    public void loading() {
        this.postValue(LoadingResource.loading());
    }

    public void success(T t) {
        this.setValue(LoadingResource.success(t));
    }

    public void postSuccess(T t) {
        this.postValue(LoadingResource.success(t));
    }

    public void error(Throwable throwable) {
        if (throwable != null) {
            this.postValue(LoadingResource.error(throwable));
        } else {
            this.postValue(LoadingResource.error("加载异常！请稍候再试"));
        }
    }

    public void error(String msg) {
        this.postValue(LoadingResource.error(msg == null ? "加载异常！请稍候再试" : msg));
    }

    public void toast(String s) {
        this.postValue(LoadingResource.toast(s));
    }
}
