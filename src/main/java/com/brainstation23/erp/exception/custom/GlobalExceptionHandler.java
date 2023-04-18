package com.brainstation23.erp.exception.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e){
        log.info("An exception occured in thread " + t.getName()
                + ": " + e.getMessage());
    }
}


