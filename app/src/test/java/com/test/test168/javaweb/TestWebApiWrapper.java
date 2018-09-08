package com.test.test168.javaweb;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.test.test168.netwrapper.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author xian
 * @date 2016/7/28
 * 基于 retrofit 2.0 + rxjava 2.0
 */
public class TestWebApiWrapper {

    private static final int CONNECT_TIMEOUT = 60;
    private static String BASE_URL = "http://localhost:8080";
    private static TestWebApiWrapper instance;
    private Retrofit retrofit;

    private TestWebApiWrapper() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addNetworkInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    public static TestWebApiWrapper getInstance() {
        if (instance == null) {
            synchronized (TestWebApiWrapper.class) {
                if (instance == null) {
                    instance = new TestWebApiWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * 重置 retrofit BASE_URL
     *
     * @param url 正确的服务器地址
     */
    public void resetBaseUrl(String url) {
        BASE_URL = url;
        instance = null;
    }

    public <T> T create(Class<T> services) {
        return retrofit.create(services);
    }
}