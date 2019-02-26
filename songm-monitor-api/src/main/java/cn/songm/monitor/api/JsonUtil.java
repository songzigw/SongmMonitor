package cn.songm.monitor.api;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.songm.common.utils.GsonDateTypeAdapter;

public class JsonUtil {

    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new GsonDateTypeAdapter());
        gson = builder.create();
    }
    
    public static String toJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }
    
}
