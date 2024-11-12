package com.my.cook_recipe.common.error.code;

import lombok.Getter;

@Getter
public enum CommonErrorCode implements ErrorCode{
    BAD_REQUEST_ERROR("BD", "잘못된 요청입니다."),
    NO_EXIST_ENTITY("NE", "존재하지 않는 엔티티입니다.")
    ;
    private String code;
    private String message;

    CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

