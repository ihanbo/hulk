package com.hans.coreutils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;


/**
 * 数据解析
 *
 * @Author hanbo
 * @Since 2018/8/21
 */
public class ParseUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }


    public static <T> T fromJson(String json, Type type) {
        if (json == null) {
            return null;
        }
        try {
            return (T) gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
