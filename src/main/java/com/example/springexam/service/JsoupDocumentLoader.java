package com.example.springexam.service;

import com.example.springexam.service.api.DocumentLoader;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JsoupDocumentLoader implements DocumentLoader {
    @Value("classpath:templates/test1.html")
    private Resource resourceFile;

    public Document loadDocument(String url) throws IOException {
        return Jsoup.parse(resourceFile.getFile());
    }

}