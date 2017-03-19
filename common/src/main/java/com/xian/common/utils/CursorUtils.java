package com.xian.common.utils;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 通过SQL语句查询出结果并封闭到VO里
 *
 * @author dengzer@gmail.com
 * @version 创建时间：2011-10-28 下午05:27:39
 */
public class CursorUtils {

    @SuppressWarnings("rawtypes")
    private static Map<Class, Class> basicMap = new HashMap<Class, Class>();

    static {
        basicMap.put(int.class, Integer.class);
        basicMap.put(long.class, Long.class);
        basicMap.put(float.class, Float.class);
        basicMap.put(double.class, Double.class);
        basicMap.put(boolean.class, Boolean.class);
        basicMap.put(byte.class, Byte.class);
        basicMap.put(short.class, Short.class);
    }

    /**
     * 通过SQL语句获得对应的VO。注意：Cursor的字段名或者别名一定要和VO的成员名一样
     *
     * @param db    sd
     * @param sql   sd
     * @param clazz sds
     * @return sd
     */
    @SuppressWarnings("rawtypes")
    public static <T> T sql2VO(SQLiteDatabase db, String sql, Class<T> clazz) {
        Cursor c = db.rawQuery(sql, null);
        return cursor2VO(c, clazz);
    }

    /**
     * 通过SQL语句获得对应的VO。注意：Cursor的字段名或者别名一定要和VO的成员名一样
     *
     * @param db            sd
     * @param sql           sd
     * @param selectionArgs sd
     * @param clazz         sd
     * @return sd
     */
    @SuppressWarnings("rawtypes")
    public static <T> T sql2VO(SQLiteDatabase db, String sql,
                               String[] selectionArgs, Class<T> clazz) {
        Cursor c = db.rawQuery(sql, selectionArgs);
        return cursor2VO(c, clazz);
    }

    /**
     * 通过SQL语句获得对应的VO的List。注意：Cursor的字段名或者别名一定要和VO的成员名一样
     *
     * @param db    sd
     * @param sql   sd
     * @param clazz sd
     * @return ds
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> sql2VOList(SQLiteDatabase db, String sql, Class<T> clazz) {
        Cursor c = db.rawQuery(sql, null);
        return cursor2VOList(c, clazz);
    }

    /**
     * 通过SQL语句获得对应的VO的List。注意：Cursor的字段名或者别名一定要和VO的成员名一样
     *
     * @param db            ds
     * @param sql           d
     * @param selectionArgs dd
     * @param clazz         d
     * @return d
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> sql2VOList(SQLiteDatabase db, String sql,
                                         String[] selectionArgs, Class<T> clazz) {
        Cursor c = db.rawQuery(sql, selectionArgs);
        return cursor2VOList(c, clazz);
    }

    /**
     * 通过Cursor转换成对应的VO。注意：Cursor里的字段名（可用别名）必须要和VO的属性名一致
     *
     * @param c     d
     * @param clazz d
     * @return d
     */
    @SuppressWarnings({"rawtypes", "unused"})
    private static <T> T cursor2VO(Cursor c, Class<T> clazz) {
        if (c == null) {
            return null;
        }
        T obj;
        int i = 1;
        try {
            c.moveToNext();
            obj = setValues2Fields(c, clazz);

            return obj;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR @：cursor2VO");
            return null;
        } finally {
            c.close();
        }
    }

    /**
     * 通过Cursor转换成对应的VO集合。注意：Cursor里的字段名（可用别名）必须要和VO的属性名一致
     *
     * @param c     d
     * @param clazz d
     * @return d
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> List<T> cursor2VOList(Cursor c, Class<T> clazz) {
        if (c == null) {
            return null;
        }
        List<T> list = new LinkedList();
        T obj;
        try {
            while (c.moveToNext()) {
                obj = setValues2Fields(c, clazz);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR @：cursor2VOList");
            return null;
        } finally {
            c.close();
        }
    }

    /**
     * * 把值设置进类属性里
     *
     * @param c     d
     * @param clazz d
     * @return d
     * @throws Exception d
     */
    @SuppressWarnings("rawtypes")
    private static <T> T setValues2Fields(Cursor c, Class<T> clazz)
            throws Exception {
        String[] columnNames = c.getColumnNames();// 字段数组
        T obj = clazz.newInstance();
        Field[] fields = clazz.getFields();

        for (Field _field : fields) {
            Class<? extends Object> typeClass = _field.getType();// 属性类型
            for (int j = 0; j < columnNames.length; j++) {
                String columnName = columnNames[j];
                typeClass = getBasicClass(typeClass);
                boolean isBasicType = isBasicType(typeClass);

                if (isBasicType) {
                    if (columnName.equalsIgnoreCase(_field.getName())) {// 是基本类型
                        String _str = c.getString(c.getColumnIndex(columnName));
                        if (_str == null) {
                            break;
                        }
                        _str = _str == null ? "" : _str;
                        Constructor<? extends Object> cons = typeClass
                                .getConstructor(String.class);
                        Object attribute = cons.newInstance(_str);
                        _field.setAccessible(true);
                        _field.set(obj, attribute);
                        break;
                    }
                } else {
                    Object obj2 = setValues2Fields(c, typeClass);// 递归
                    _field.set(obj, obj2);
                    break;
                }

            }
        }
        return obj;
    }

    /**
     * 判断是不是基本类型
     *
     * @param typeClass d
     * @return d
     */
    @SuppressWarnings("rawtypes")
    private static boolean isBasicType(Class typeClass) {
        return typeClass.equals(Integer.class) || typeClass.equals(Long.class)
                || typeClass.equals(Float.class)
                || typeClass.equals(Double.class)
                || typeClass.equals(Boolean.class)
                || typeClass.equals(Byte.class)
                || typeClass.equals(Short.class)
                || typeClass.equals(String.class);
    }

    /**
     * 获得包装类
     *
     * @param typeClass d
     * @return d
     */
    @SuppressWarnings("all")
    public static Class<? extends Object> getBasicClass(Class typeClass) {
        Class _class = basicMap.get(typeClass);
        if (_class == null)
            _class = typeClass;
        return _class;
    }
}