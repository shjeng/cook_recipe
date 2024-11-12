package com.my.cook_recipe.common.error.code;

import lombok.Getter;

@Getter
public enum JoinErrorCode implements ErrorCode {

    ;
    private String code;
    private String message;

    JoinErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
