package com.test.test168.netwrapper;


import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Created by w07 on 2017/7/6 14:49
 * Description : 接口返回结果响应体
 */

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        return parseJSON(tempStr);
    }

    private T parseJSON(String tempStr) {
        System.out.println(" JSON = [" + tempStr + "]");
//        tempStr = YYNovelJSONParseUtils.parse(tempStr);
        return JSON.parseObject(tempStr, type);
    }
}