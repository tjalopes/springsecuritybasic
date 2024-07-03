package com.example.springsecuritybasic.exception.utils;

import com.example.springsecuritybasic.exception.APIException;
import com.example.springsecuritybasic.resource.apiResource.output.MessageErrorOutputResource;
import org.springframework.http.HttpStatus;

public class UtilsException extends APIException {

    public UtilsException(MessageErrorOutputResource meR, HttpStatus httpStatus, String msg, Exception e) {
        super(meR, httpStatus, msg, e);
    }

}