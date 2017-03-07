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
public class JuheApiWrapper {

    public static final int CONNECT_TIMEOUT = 60;// 延时设置得大一点，防止连接超时没得到结果
    //    public static String BASE_URL = Constants.BASE_URL;
    public static String BASE_URL = "http://op.juhe.cn";
    private static JuheApiWrapper instance;
    private Retrofit retrofit;

    private JuheApiWrapper() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static JuheApiWrapper getInstance() {

        if (instance == null) {
            synchronized (JuheApiWrapper.class) {
                if (instance == null) {
                    instance = new JuheApiWrapper();
                }
            }
        }
        return instance;
    }

    public static JuheApiWrapper getInstance(String url) {
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
        instance = new JuheApiWrapper();
    }

    public <T> T create(Class<T> services) {
        return retrofit.create(services);
    }
}