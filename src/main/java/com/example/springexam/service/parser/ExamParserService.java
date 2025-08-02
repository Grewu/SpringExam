package com.example.springexam.service.parser;

import com.example.springexam.model.ParsedInfo;
import com.example.springexam.service.api.DocumentLoader;
import com.example.springexam.service.parser.api.DocumentParser;
import com.example.springexam.service.parser.api.ParseService;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamParserService implements DocumentParser {
    private final DocumentLoader documentLoader;
    private final ParseService htmlParser;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamParserService.class);
    @Override
    public List<ParsedInfo> parse(String url)  {
        Document document = documentLoader.loadDocument(url);
        return parseDocument(document, url);
    }

    private List<ParsedInfo> parseDocument(Document document, String url) {
        return document.select(HtmlSelectors.Question.CONTAINER).stream()
                .map(container -> htmlParser.parseHtml(container, url))
                .peek(info -> LOGGER.debug("Parsed question: {}", info.getQuestion()))
                .toList();
    }
}