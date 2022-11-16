package org.example;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JSON {
    private static Gson gson = new Gson();

    public static <T> T deserialize(String body, Type returnType) {
        return gson.fromJson(body, returnType);
    }

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }
}
