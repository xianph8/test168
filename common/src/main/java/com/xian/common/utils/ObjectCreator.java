package com.xian.common.utils;

import java.lang.reflect.Constructor;


/**
 * Created by Administrator on 2016-09-28.
 */

public class ObjectCreator {

    /**
     * @param superClass 父类   如 PromotionStyle
     * @param typeName   类型名 如 dp
     * @return 类     如 Dp
     */
    public static Object createObject(Class superClass, String typeName) {

        long startTime = System.currentTimeMillis();

        if (typeName == null || typeName.isEmpty()) {
            return null;
        }

        String first = typeName.substring(0, 1).toUpperCase();
        typeName = first + typeName.substring(1, typeName.length());

        try {
            String packageName = superClass.getPackage().getName() + ".";
            Class<?> clazz = Class.forName(packageName + typeName);
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            XLog.e("createObject---e:" + e.toString());
        }

        XLog.d("createObject---elapsed:" + (System.currentTimeMillis() - startTime));


        return null;
    }


}
