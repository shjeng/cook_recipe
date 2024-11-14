package com.my.cook_recipe.common.error.controller;

import com.my.cook_recipe.common.error.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalApiControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> customExceptionHandler(CustomException e){
        if (e.isMsgOpen()) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body("예기치 못한 문제가 발생했습니다.");
    }
}
