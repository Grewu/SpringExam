package com.example.springexam.model.dto.response.html;


import org.jsoup.nodes.Document;

public record DocumentWithFilename(Document document, String filename) {}