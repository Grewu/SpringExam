package com.example.springexam.service.loader.api;

import com.example.springexam.model.dto.response.html.DocumentWithFilename;
import org.jsoup.nodes.Document;

import java.util.List;

public interface DocumentLoader {
    List<DocumentWithFilename> loadAllDocuments();
}
