
createTime: 2018年1月19日 11:50:55 by xian

#### JSON 解释工具

目前用过两个 JSON 解释工具，一个是 GSON ，一个是 fastJSON

下面是对这两个工具的封装，项目中使用其中一个就好

1. fastJSON
    ``` java
    import com.alibaba.fastjson.JSON;
    import com.alibaba.fastjson.JSONArray;
    import com.alibaba.fastjson.JSONObject;

    import java.util.List;

    public class JSONUtils {

        public static <T> T json2obj(String str, Class<T> typeClass) {
            return JSON.parseObject(str, typeClass);
        }

        public static String obj2json(Object obj) {
            return JSON.toJSONString(obj);
        }

        public static <T> List<T> json2List(String json, Class<T> typeClass) {
            return JSONObject.parseArray(json, typeClass);
        }

        public static <T> List<T> jsonArray2List(String jsonArray, Class<T> typeClass) {
            return JSONArray.parseArray(jsonArray, typeClass);
        }
    }
    ```

1. GSON

    ```java

    import com.google.gson.Gson;
    import com.google.gson.JsonArray;
    import com.google.gson.JsonElement;
    import com.google.gson.JsonParser;
    import com.google.gson.reflect.TypeToken;

    import java.lang.reflect.Type;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    public class JSONUtils {

        public static String obj2json(Object obj) {
            return new Gson().toJson(obj);
        }

        public static String obj2json(Object obj, Type typeOfT) {
            return new Gson().toJson(obj, typeOfT);
        }

        public static <T> T json2obj(String str, Class<T> obj) {
            return new Gson().fromJson(str, obj);
        }

        public static <T> List<T> json2List(String str, Class<T> cl) {
            return new Gson().fromJson(str, new TypeToken<List<T>>() {
            }.getType());
        }

        public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
            T[] arr = new Gson().fromJson(s, clazz);
            return Arrays.asList(arr);
            //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
        }

        public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
            List<T> list = new ArrayList<T>();
            try {
                Gson gson = new Gson();
                list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        public static <T> List<T> string2List(String jsonString, Class<T> cls) {
            List<T> list = new ArrayList<T>();
            try {
                Gson gson = new Gson();
                JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
                for (JsonElement jsonElement : arry) {
                    list.add(gson.fromJson(jsonElement, cls));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    }
    ```