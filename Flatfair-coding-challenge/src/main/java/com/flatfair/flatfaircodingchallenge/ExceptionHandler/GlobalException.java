package com.flatfair.flatfaircodingchallenge.ExceptionHandler;

import lombok.Data;
import lombok.ToString;

/**
 * @className: GlobalException
 * @author: wenjie.xia
 * @description: customise a global exception
 * @date: 23/08/2022 22:55
 * @version: 1.0
 */
@Data
@ToString
public class GlobalException extends RuntimeException{
    private Integer code = -1;

    private String errMsg;

    public GlobalException(String message) {
        super(message);
        this.errMsg = message;
    }
}
