package com.test.test168.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by test168 on 2016/7/28 14:02
 * Description : retrofit 封装类，单例模式
 */
public class ApiWrapper {

    public static final int CONNECT_TIMEOUT = 60;// 延时设置得大一点，防止连接超时没得到结果
    //    public static String BASE_URL = Constants.BASE_URL;
    public static String BASE_URL = "";
    private static ApiWrapper instance;
    private Retrofit retrofit;

    private ApiWrapper() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
//                .addNetworkInterceptor(new StethoInterceptor())
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        XLog.d(String.format("\nrequest:\n%s\nheaders:\n%s",
//                                request.body().toString(), request.headers()));
//                        Response response = chain.proceed(request);
//                        XLog.d(String.format("\nrequest:\n%s\nheaders:\n%s",
//                                response.body().toString(), response.headers()));
//                        return response;
//                    }
//                })
                .build();

        //Converter.Factory factory = Constants.IS_C8 ? GsonConverterFactory.create() : FastJsonConverterFactory.create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
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

    public static ApiWrapper getInstance(String url) {
        BASE_URL = url;
        instance = null;
        return getInstance();
    }

    /**
     * 重置BASE_URL字段
     *
     * @param serverAddress 服务器地址
     */
    public static void resetBaeUrl(String serverAddress) {
        BASE_URL = serverAddress;
        instance = new ApiWrapper();
    }

    public <T> T create(Class<T> services) {
        return retrofit.create(services);
    }
}