package com.example.springsecuritybasic.exception.utils;

import com.example.springsecuritybasic.resource.apiResource.output.MessageErrorOutputResource;
import org.springframework.http.HttpStatus;

public class UtilsJsonToObjectException extends UtilsException{
    public UtilsJsonToObjectException(String msgC, Exception eC) {
        super(new MessageErrorOutputResource("040101", "Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR, msgC, eC);
    }
}
