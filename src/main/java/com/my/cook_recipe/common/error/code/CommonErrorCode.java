package com.my.cook_recipe.common.error.code;

import lombok.Getter;

@Getter
public enum CommonErrorCode {
    EXPIRED_JWT_TOKEN("EJT", "{\"code:\": \"EJT\", \"message\": \"Expired Jwt Token.\"}");

    private final String code;
    private final String description;

    CommonErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
