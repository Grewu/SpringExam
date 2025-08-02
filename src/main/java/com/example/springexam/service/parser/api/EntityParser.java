package com.example.springexam.service.parser.api;

import org.jsoup.nodes.Element;

public interface EntityParser<T, P> {
    T parseAndSave(Element container, P parentEntity);
}
