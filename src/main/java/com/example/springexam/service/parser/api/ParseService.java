package com.example.springexam.service.parser.api;

public interface ParseService<E,T> {
    E parseHtml(T t);
}
