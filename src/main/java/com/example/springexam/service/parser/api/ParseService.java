package com.example.springexam.service.parser.api;

import com.example.springexam.model.ParsedInfo;
import org.jsoup.nodes.Element;

public interface ParseService {
    ParsedInfo parseHtml(Element container, String url);
}
