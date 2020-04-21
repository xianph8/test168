package com.test.test168.arch.model;


import com.test.test168.api.ExampleApi;
import com.test.test168.bean.Response;
import com.xian.common.module.ApiWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class TestBaseModel {

    public Observable<Response<List<String>>> loadList(int page, String params1) {
        return Observable
                .fromCallable(() -> {
                    Map<String, String> params = new HashMap<>();
                    params.put("page", "" + page);
                    params.put("parmas1", params1);
                    return params;
                })
                .flatMapSingle(params -> {
                    return ApiWrapper.getInstance()
                            .create(ExampleApi.class).getList(params);
                })
                /*.map(response -> {
                    // covert response to list;
                    return response.data;
                })*/;
    }

    public Observable<Response<String>> submitInfo(String... params1) {
        return Observable
                .fromCallable(() -> {
                    Map<String, String> params = new HashMap<>();
                    for (String s : params1) {
                        params.put("parmas1", s);
                    }
                    return params;
                })
                .flatMapSingle(params -> {
                    return ApiWrapper.getInstance()
                            .create(ExampleApi.class).submit(params);
                })
                ;
    }

    public Observable<Response<String>> loadDetails(String id) {
        return Observable
                .fromCallable(() -> {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", id);
                    return params;
                })
                .flatMapSingle(params -> {
                    return ApiWrapper.getInstance()
                            .create(ExampleApi.class).submit(params);
                })
                ;
    }

}
