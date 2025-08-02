package com.example.springexam.service.parser;

import com.example.springexam.model.dto.response.ParsedInfo;
import com.example.springexam.service.api.DocumentLoader;
import com.example.springexam.service.parser.api.DocumentParser;
import com.example.springexam.service.parser.api.ParseService;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ExamParserService implements DocumentParser {
    private final DocumentLoader documentLoader;
    private final ParseService htmlParser;
    @Override
    public List<ParsedInfo> parse(String url)  {
        List<Document> document = documentLoader.loadAllDocuments();
        return parseDocument(document, url);
    }

    private List<ParsedInfo> parseDocument(Document document, String url) {
        return document.select(HtmlSelectors.Question.CONTAINER).stream()
                .map(container -> htmlParser.parseHtml(container, url))
                .peek(info -> log.debug("Parsed question: {}", info.getQuestion()))
                .toList();
    }

    private List<ParsedInfo> parseDocument(List<Document> documents, String url) {
        return documents.stream()
                .flatMap(document -> document.select(HtmlSelectors.Question.CONTAINER).stream())
                .map(container -> htmlParser.parseHtml(container, url))
                .peek(info -> log.debug("Parsed question: {}", info.getQuestion()))
                .toList();

    }
}