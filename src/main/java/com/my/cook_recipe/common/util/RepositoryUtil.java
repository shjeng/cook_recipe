package com.my.cook_recipe.common.util;

import com.my.cook_recipe.common.error.exception.CustomException;

import java.util.Optional;

import static com.my.cook_recipe.common.error.code.JoinErrorCode.NOT_EXIST_ENTITY;


public class RepositoryUtil {
    public static Object optionalCheck(Optional<?> optionalObject){
        return optionalObject.orElseThrow(() -> new CustomException(NOT_EXIST_ENTITY.getMessage()));
    }
}
