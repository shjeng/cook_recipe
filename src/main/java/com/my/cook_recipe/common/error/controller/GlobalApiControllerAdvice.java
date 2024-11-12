package com.my.cook_recipe.common.error.controller;

import com.my.cook_recipe.common.error.exception.CustomException;
import groovy.util.logging.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@lombok.extern.slf4j.Slf4j
@Slf4j
@RestControllerAdvice
public class GlobalApiControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException> customExceptionHandler(CustomException e){
        log.debug(e.getMsg());
        return ResponseEntity.badRequest().body(e);
    }
}
