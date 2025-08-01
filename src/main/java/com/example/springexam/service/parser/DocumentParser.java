//package com.example.springexam.service.parser;
//
//import com.example.springexam.exception.UrlParserException;
//import com.example.springexam.model.ParsedInfo;
//import com.example.springexam.service.impl.AnswerServiceImpl;
//import com.example.springexam.service.impl.ExplanationServiceImpl;
//import com.example.springexam.service.impl.QuestionServiceImpl;
//import com.example.springexam.service.impl.TopicServiceImpl;
//import com.example.springexam.utils.HtmlSelectorUtil;
//import com.example.springexam.utils.HtmlSelectors;
//import lombok.RequiredArgsConstructor;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class DocumentParser {
//    @Value("classpath:templates/test1.html")
//    Resource resourceFile;
//    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentParser.class);
//    private static final String DEFAULT_TOPIC = "Topic not found";
//    private static final String DEFAULT_EXPLANATION = "Explanation not found";
//    private static final String DEFAULT_QUESTION = "Question not found";
//
//
//    private final AnswerServiceImpl answerService;
//    private final ExplanationServiceImpl explanationService;
//    private final QuestionServiceImpl questionService;
//    private final TopicServiceImpl topicService;
//
//    public List<ParsedInfo> parse(String url) {
//        try {
//            Document document = getDocument(url);
//            return extractQuestions(document, url);
//        } catch (IOException e) {
//            throw new UrlParserException(url);
//        }
//    }
//
//    private List<ParsedInfo> extractQuestions(Document document, String url) {
//        return document.select(HtmlSelectors.Question.CONTAINER).stream()
//                .map(container -> parseQuestion(container, url))
//                .peek(info -> LOGGER.debug("Parsed question: {}", info.getQuestion()))
//                .toList();
//    }
//
//    private ParsedInfo parseQuestion(Element container, String url) {
//        return ParsedInfo.builder()
//                .url(url)
//                .question(findQuestion(container))
//                .answer(findAnswer(container))
//                .explanation(findExplanation(container))
//                .topic(findTopic(container))
//                .build();
//    }
//
//
//    private String findTopic(Element container) {
//        return Optional.ofNullable(container.selectFirst(HtmlSelectorUtil.DOMAIN_PANE))
//                .map(pane -> pane.selectFirst(HtmlSelectorUtil.TOPIC))
//                .map(Element::text)
//                .orElse(DEFAULT_TOPIC);
//    }
//
//    private String findExplanation(Element container) {
//        return Optional.ofNullable(container.selectFirst(HtmlSelectorUtil.EXPLANATION))
//                .map(Element::text)
//                .orElse(DEFAULT_EXPLANATION);
//    }
//
//    private List<String> findAnswer(Element container) {
//        return container.select(HtmlSelectorUtil.ANSWER)
//                .stream()
//                .limit(4)
//                .map(Element::text)
//                .map(String::trim)
//                .toList();
//    }
//
//    private String findQuestion(Element container) {
//        return Optional.ofNullable(container.selectFirst(HtmlSelectorUtil.QUESTION_PROMPT))
//                .map(Element::text)
//                .orElse(DEFAULT_QUESTION);
//    }
//
//    public Document getDocument(String url) throws IOException {
//        return Jsoup.parse(resourceFile.getFile());
//    }
//
////    public Document getDocument(String url) {
////        Document document = null;
////        try {
////            document = Jsoup.connect(url)
////                    .timeout(5000).get();
////        } catch (IOException e) {
////            LOGGER.error("Could not be created JSoup document "+ e.getMessage());
////            throw new ParserException("Could not be created JSoup document.");
////        }
////        return document;
////    }
//
//}
//
