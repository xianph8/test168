package com.test.test168.javaweb;

import com.test.test168.netwrapper.ServerResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author xian
 * @date 2018/6/28
 */

public interface TestApi {

    @GET("/HelloAndroidServlet")
    Observable<ServerResponse<HelloAndroidBean>> first(@QueryMap Map<String, String> params);

    @GET("/hello")
    Observable<ServerResponse<HelloAndroidBean>> second(@QueryMap Map<String, String> params);

}
