package com.my.cook_recipe.common.error.code;

import lombok.Getter;

@Getter
public enum JoinErrorCode implements ErrorMessage {
    NOT_EXIST_ENTITY("존재하지 않는 엔티티입니다.")
    ;
    private String message;
    JoinErrorCode(String message) {
        this.message = message;
    }

}
