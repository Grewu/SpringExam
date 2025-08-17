package com.example.springexam.exception.handler;


import com.example.springexam.exception.AbstractExceptionMessageException;
import com.example.springexam.exception.ExceptionMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalHandlerAdvice {

  @ExceptionHandler
  public ResponseEntity<ExceptionMessage> handle(AbstractExceptionMessageException e) {
    return Optional.of(e)
        .map(AbstractExceptionMessageException::getExceptionMessage)
        .map(exceptionMessage -> ResponseEntity.status(e.getStatusCode()).body(exceptionMessage))
        .orElseThrow();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
  }

}
