package com.test.test168.netwrapper;

import com.google.gson.TypeAdapter;
import com.xian.common.utils.JSONUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by w07 on 2017/7/6 14:49
 * Description : 接口返回结果响应体
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    // 此处是在接口接收到结果的，第一步，可以自行作处理
    @Override
    public T convert(ResponseBody value) throws IOException {
        byte[] bytes = value.bytes();
        String json = new String(bytes, "UTF-8");
        System.out.println("convert json = " + json);
        try {
            return adapter.fromJson(json);
        } catch (Exception e) {// 当有可能是解释不正确时或返回结果不按正确格式来返回时，就可能报异常
            e.printStackTrace();
            try {
                // 当解释结果出错时，就用将其当成错误的对象来解释
                return (T) JSONUtils.json2obj(json, ServerErrorResponse.class);
            } catch (Exception e1) {
                System.out.println("e1 = " + e1);
                e1.printStackTrace();
                return null;
            }
        }
    }
}