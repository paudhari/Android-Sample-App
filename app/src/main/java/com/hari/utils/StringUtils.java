package com.hari.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() < 1;
    }
}
