package com.test.test168.arch;

import android.annotation.SuppressLint;

import com.test.test168.arch.model.DetailsUI;
import com.test.test168.bean.Response;
import com.test.test168.arch.model.TestBaseModel;
import com.xian.common.arch.BaseViewModel;
import com.xian.common.arch.LoadingLiveData;
import com.xian.common.arch.LoadingMutableLiveData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestBaseViewModelViewModel extends BaseViewModel {

    private DetailsUI mDetailsUI = new DetailsUI();
    private LoadingMutableLiveData<DetailsUI> mDetailsUILoadingLiveData = new LoadingMutableLiveData<>();
    private LoadingMutableLiveData<List<String>> mListLoadingLiveData = new LoadingMutableLiveData<>();
    private TestBaseModel mTestBaseModel = new TestBaseModel();

    public LoadingLiveData<DetailsUI> subDetailsData() {
        return mDetailsUILoadingLiveData;
    }

    public LoadingLiveData<List<String>> subListData() {
        return mListLoadingLiveData;
    }

    public void refresh() {
        loadList(1);
    }

    public void loadMore() {
        loadList(1 + 1);
    }

    @SuppressLint("CheckResult")
    private void loadList(int page) {
        mListLoadingLiveData.loading();
        mTestBaseModel.loadList(page, "p")
                .doOnSubscribe(onSubscribe())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isSuccess()) {
                        mListLoadingLiveData.postSuccess(response.getData());
                        // or
                        mListLoadingLiveData.success(response.getData());
                    } else {
                        mListLoadingLiveData.toast(response.getMessage());
                        // or
                        mListLoadingLiveData.error(response.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mListLoadingLiveData.error(throwable);
                    }
                });

    }


    @SuppressLint("CheckResult")
    public void loadDetails() {
        mTestBaseModel.loadDetails("id")
                .doOnNext(response -> {
                    if (response.isSuccess()) {
                        mDetailsUI.setData(response);// 如果转换过程有耗时操作，可以这样写在这里
                    }
                })
                .doOnSubscribe(onSubscribe())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isSuccess()) {
                        mDetailsUI.setData(response);// 如果转换过程没有耗时操作，可以这样写在这里
                        mDetailsUILoadingLiveData.postSuccess(mDetailsUI);
                        // or
                        mDetailsUILoadingLiveData.success(mDetailsUI);
                    } else {
                        mDetailsUILoadingLiveData.toast(response.getMessage());
                        // or
                        mDetailsUILoadingLiveData.error(response.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mDetailsUILoadingLiveData.error(throwable);
                    }
                });
    }


    @SuppressLint("CheckResult")
    public Observable<Response<String>> submitInfo(String name, String signature, String age) {
        return mTestBaseModel.submitInfo(name, signature, age)
                .doOnSubscribe(onSubscribe())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
