package com.example.springexam.service.api;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface DocumentLoader {
    Document loadDocument(String url);
    List<Document> loadAllDocuments();
}
