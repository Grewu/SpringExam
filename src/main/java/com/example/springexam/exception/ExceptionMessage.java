package com.example.springexam.exception;

import org.springframework.http.HttpStatus;

public record ExceptionMessage(HttpStatus status, String message) {}
