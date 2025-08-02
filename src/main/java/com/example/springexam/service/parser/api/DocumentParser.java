package com.example.springexam.service.parser.api;

import com.example.springexam.model.dto.response.ParsedInfo;

import java.io.IOException;
import java.util.List;

public interface DocumentParser {
    List<ParsedInfo> parse(String url) throws IOException;
}