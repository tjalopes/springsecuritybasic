package com.example.springsecuritybasic.resource.apiResource.output;

public class MessageErrorOutputResource {

    private final String errorCode;
    private final String errorMessage;

    public MessageErrorOutputResource(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "MessageErrorOutputResource{" + "errorCode=" + errorCode + ", errorMessage=" + errorMessage + '}';
    }
}
