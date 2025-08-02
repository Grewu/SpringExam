package com.example.springexam.service.loader;

import com.example.springexam.exception.DocumentLoadException;
import com.example.springexam.service.api.DocumentLoader;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsoupDocumentLoader implements DocumentLoader {
    @Value("classpath:templates/*.html")
    private Resource[] htmlResources;

    public List<Document> loadAllDocuments() {
        return Arrays.stream(htmlResources)
                .map(this::parseResource)
                .toList();
    }

    private Document parseResource(Resource resource) {
        try {
            return Jsoup.parse(resource.getFile(), "UTF-8");
        } catch (IOException e) {
            throw new DocumentLoadException(resource.getFilename());
        }
    }

    public Document loadDocument(String url) {
        return Jsoup.parse(url);
    }

}