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
    public List<ParsedInfo> parse()  {
        List<Document> document = documentLoader.loadAllDocuments();
        return parseDocument(document);
    }

    private List<ParsedInfo> parseDocument(List<Document> documents) {
        return documents.stream()
                .flatMap(document -> document.select(HtmlSelectors.Question.CONTAINER).stream())
                .map(htmlParser::parseHtml)
                .peek(info -> log.debug("Parsed question: {}", info.question()))
                .toList();

    }
}