package com.my.cook_recipe.common.error.exception;

import com.my.cook_recipe.common.error.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String code;
    private final String msg;

    public CustomException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public CustomException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }
}
