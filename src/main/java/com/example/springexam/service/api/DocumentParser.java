package com.example.springexam.service.api;

import com.example.springexam.model.ParsedInfo;

import java.io.IOException;
import java.util.List;

public interface DocumentParser {
    List<ParsedInfo> parse(String url) throws IOException;
}