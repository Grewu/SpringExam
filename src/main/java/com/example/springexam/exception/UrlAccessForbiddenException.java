package com.example.springexam.exception;

import org.springframework.http.HttpStatus;

public class UrlAccessForbiddenException extends AbstractExceptionMessageException {
    private static final String EXCEPTION_MESSAGE = "Access denied to URL: %s";

    public UrlAccessForbiddenException(String url) {
        super(String.format(EXCEPTION_MESSAGE,url));
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.FORBIDDEN;
    }
}
