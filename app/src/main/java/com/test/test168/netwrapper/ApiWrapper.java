package com.test.test168.netwrapper;


import com.test.test168.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 * @author xian
 * @date 2016/7/28
 * 基于 retrofit 封装的网络访问单例工具
 * 是基于 retrofit + rxjava 2.0
 * 所以必需添加第三方库使用
 */
public class ApiWrapper {

    private static final int CONNECT_TIMEOUT = 60;// 延时设置得大一点，防止连接时间过长没得到结果
    private static final String BASE_URL = "http://api.mmd6666.com";
    private static ApiWrapper instance;
    private Retrofit retrofit;

    private ApiWrapper() {
        //设置缓存路径
//        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "cache");
        //设置缓存 10M
//        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//                .cache(cache)
                ;

        // 只有 debug 版本添加 log 信息打印
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addNetworkInterceptor(interceptor);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    public static ApiWrapper getInstance() {
        if (instance == null) {
            synchronized (ApiWrapper.class) {
                if (instance == null) {
                    instance = new ApiWrapper();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> services) {
        return retrofit.create(services);
    }
}