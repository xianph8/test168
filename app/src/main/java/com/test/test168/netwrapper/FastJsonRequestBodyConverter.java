package com.test.test168.netwrapper;


import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by w07 on 2017/7/6 14:48
 * Description : 接口请求体
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(@NonNull T value) throws IOException {
        System.out.println("value = [" + value + "]");
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
