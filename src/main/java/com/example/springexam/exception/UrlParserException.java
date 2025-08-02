package com.example.springexam.exception;

import org.springframework.http.HttpStatus;

public class UrlParserException extends AbstractExceptionMessageException {
    private static final String EXCEPTION_MESSAGE = "Invalid URL: %s";

    public UrlParserException(String url) {
        super(String.format(EXCEPTION_MESSAGE,url));
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
