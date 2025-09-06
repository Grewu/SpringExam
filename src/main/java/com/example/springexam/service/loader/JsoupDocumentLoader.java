package com.example.springexam.service.loader;

import com.example.springexam.exception.DocumentLoadException;
import com.example.springexam.service.loader.api.DocumentLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsoupDocumentLoader implements DocumentLoader {

    @Value("classpath:templates/**/*.html")
    private Resource[] htmlResources;

    @Override
    public List<Document> loadAllDocuments() {
        if (htmlResources == null || htmlResources.length == 0) {
            log.warn("No HTML resources found in classpath:templates/*.html");
            return List.of();
        }
        log.info("Loading {} HTML resources", htmlResources.length);
        return Arrays.stream(htmlResources)
                .filter(Objects::nonNull)
                .map(this::parseResource)
                .toList();
    }

    private Document parseResource(Resource resource) {
        Objects.requireNonNull(resource, "Resource cannot be null");
        String filename = resource.getFilename();
        log.debug("Parsing resource: {}", filename);
        try {
            return Jsoup.parse(resource.getInputStream(), "UTF-8", "");
        } catch (IOException e) {
            log.error("Failed to parse resource: {}", filename, e);
            throw new DocumentLoadException("Failed to load HTML document: " + filename);
        }
    }
}