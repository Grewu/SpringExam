package com.example.springexam.service.api;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface DocumentLoader {
    Document loadDocument(String url) ;
}
