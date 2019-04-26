package com.ruicheng.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Ruicheng
 * on 2019/4/18.
 */
public class JsonFormatterUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
