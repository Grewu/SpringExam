package com.example.springexam.service.parser.api;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;

import java.util.List;

public interface DocumentParser {
    List<HtmlParsedResponse> parse();
}