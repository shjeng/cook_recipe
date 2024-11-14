package com.my.cook_recipe.common.error.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private boolean msgOpen;
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, boolean msgOpen) {
        super(message);
        this.msgOpen = msgOpen;
    }
}
