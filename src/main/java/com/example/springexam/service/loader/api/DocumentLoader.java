package com.example.springexam.service.loader.api;

import org.jsoup.nodes.Document;

import java.util.List;

public interface DocumentLoader {
    List<Document> loadAllDocuments();
}
