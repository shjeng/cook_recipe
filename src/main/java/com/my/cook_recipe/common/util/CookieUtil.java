package com.my.cook_recipe.common.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie createCookie(String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
//        cookie.setSecure(true); // https 통신을 진행할 경우 주석 해제
//        cookie.setPath("/"); // 쿠키 범위
        cookie.setHttpOnly(true); // 클라이언트에서 js로 접근하지 못하도록 설정
        return cookie;
    }
}
