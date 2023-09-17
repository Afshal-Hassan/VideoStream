package com.youtube.VideoService.utils;


import java.util.HashMap;
import java.util.Map;

public final class HeaderUtils {

    private static Map<String, Object> headers = new HashMap<>();


    public static void setHeaders(String key, Object value) {
        headers.put(key, value);
    }

    public static Map<String, Object> getHeaders() {
        return headers;
    }
}
