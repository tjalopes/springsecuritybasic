package com.example.springsecuritybasic.exception;

import com.example.springsecuritybasic.resource.apiResource.output.MessageErrorOutputResource;
import org.springframework.http.HttpStatus;

public class APIException extends Exception {

    private final MessageErrorOutputResource messageErrorOutputResource;
    private final HttpStatus httpStatus;

    public APIException(MessageErrorOutputResource messageErrorOutputResource, HttpStatus httpStatus, String message, Exception exception) {
        super(message, exception);
        this.messageErrorOutputResource = messageErrorOutputResource;
        this.httpStatus = httpStatus;
    }

    public APIException(MessageErrorOutputResource messageErrorOutputResource, HttpStatus httpStatus, String message) {
        super(message);
        this.messageErrorOutputResource = messageErrorOutputResource;
        this.httpStatus = httpStatus;
    }

    public APIException(MessageErrorOutputResource messageErrorOutputResource, HttpStatus httpStatus) {
        this.messageErrorOutputResource = messageErrorOutputResource;
        this.httpStatus = httpStatus;
    }

    public MessageErrorOutputResource getMessageErrorOutputResource() {
        return messageErrorOutputResource;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
