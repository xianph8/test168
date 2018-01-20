package com.test.test168.netwrapper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.xian.common.utils.XLog;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by w07 on 2017/7/6 14:48
 * Description : 接口请求体
 */

public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    // 在此处可以，将请求体作处理，处理成你需要的结果
    @Override
    public RequestBody convert(T value) throws IOException {
        XLog.i(" convert : " + value.toString());
        return RequestBody.create(MEDIA_TYPE, value.toString());
    }
}
