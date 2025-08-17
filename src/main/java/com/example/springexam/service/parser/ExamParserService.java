package com.example.springexam.service.parser;

import com.example.springexam.model.dto.response.html.HtmlParsedResponse;
import com.example.springexam.service.loader.api.DocumentLoader;
import com.example.springexam.service.parser.api.DocumentParser;
import com.example.springexam.service.parser.api.ParseService;
import com.example.springexam.utils.HtmlSelectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamParserService implements DocumentParser {

    private final DocumentLoader documentLoader;
    private final ParseService<HtmlParsedResponse, Element> htmlParser;

    @Override
    public List<HtmlParsedResponse> parse() {
        List<Document> documents = documentLoader.loadAllDocuments();
        return parseDocuments(documents);
    }

    private List<HtmlParsedResponse> parseDocuments(List<Document> documents) {
        return documents.stream()
                .flatMap(doc -> doc.select(HtmlSelectors.Question.CONTAINER).stream())
                .map(htmlParser::parseHtml)
                .peek(info -> log.debug("Parsed question: {}", info.question()))
                .toList();
    }
}