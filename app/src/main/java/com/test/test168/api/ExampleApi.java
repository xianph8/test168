package com.test.test168.api;

import com.test.test168.bean.Response;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ExampleApi {

    @POST("/test/test/test")
    @FormUrlEncoded
    Single<Response<List<String>>> getList(@FieldMap Map<String, String> params);

    @POST("/test/test/test1")
    @FormUrlEncoded
    Single<Response<String>> submit(@FieldMap Map<String, String> params);

}
