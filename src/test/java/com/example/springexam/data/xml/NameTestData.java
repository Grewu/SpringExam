package com.example.springexam.data.xml;

import com.example.springexam.model.dto.response.xml.Name;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class NameTestData {
    @Builder.Default private String text = "Question";

    public Name buildName() {
        return Name.builder()
                .text(text)
                .build();
    }
}