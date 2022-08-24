package com.flatfair.flatfaircodingchallenge.ExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: GlobalExceptionHandler
 * @author: wenjie.xia
 * @description: global exception handler
 * @date: 23/08/2022 22:57
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Specify the type of exception to be intercepted
    @ExceptionHandler({Exception.class})
    public Object customExceptionHandler(Exception e) {
        // print exception log
        e.printStackTrace();
        if(e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            return globalException.getErrMsg();
        }
        return "Exception happened!";
    }
}
