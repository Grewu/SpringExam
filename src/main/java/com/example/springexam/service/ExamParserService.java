package com.example.springexam.service;

import com.example.springexam.model.ParsedInfo;
import com.example.springexam.service.api.DocumentLoader;
import com.example.springexam.service.api.DocumentParser;
import com.example.springexam.service.parser.QuestionParser;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamParserService implements DocumentParser {
    private final DocumentLoader documentLoader;
    private final QuestionParser questionParser;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamParserService.class);
    @Override
    public List<ParsedInfo> parse(String url)  {
        Document document = null;
        try {
            document = documentLoader.loadDocument(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parseDocument(document, url);
    }

    private List<ParsedInfo> parseDocument(Document document, String url) {
        return document.select(HtmlSelectors.Question.CONTAINER).stream()
                .map(container -> questionParser.parseQuestion(container, url))
                .peek(info -> LOGGER.debug("Parsed question: {}", info.getQuestion()))
                .toList();
    }
}