package com.my.cook_recipe.common.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {
    public static String getAccessToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
