package com.my.cook_recipe.common.error.exception;

import com.my.cook_recipe.common.error.code.CommonErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomJwtExpiredException extends AuthenticationException {

    private CommonErrorCode code;

    public CustomJwtExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomJwtExpiredException(String msg) {
        super(msg);
    }

    public CustomJwtExpiredException(String msg, CommonErrorCode code) {
        super(msg);
        this.code = code;
    }
}
