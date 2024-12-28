package com.my.cook_recipe.common.util;

import com.my.cook_recipe.common.error.exception.CustomException;
import org.springframework.util.StringUtils;

public class StringUtil {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean notEquals(String str1, String str2) {
        if (!(StringUtils.hasText(str1) || StringUtils.hasText(str2))) {
            throw new CustomException("[StringUtil]: Null Point Exception", false);
        }
        return !str1.equals(str2);
    }
}
