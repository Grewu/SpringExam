package com.example.springexam.exception;

import org.springframework.http.HttpStatus;

public class DocumentLoadException extends AbstractExceptionMessageException {
    private static final String EXCEPTION_MESSAGE = "Failed to load: %s";
    public DocumentLoadException(String fileName) {
        super(String.format(EXCEPTION_MESSAGE, fileName));
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
