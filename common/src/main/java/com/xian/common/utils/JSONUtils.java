package com.xian.common.utils;

import com.google.gson.Gson;

/**
 * Created by w07 on 2017/2/20 11:55
 * Description :
 */

public class JSONUtils {

    public static String obj2json(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T json2obj(String str, Class<T> obj) {
        return new Gson().fromJson(str, obj);
    }

}
