package com.example.springexam.data.xml;


import com.example.springexam.model.dto.response.xml.Text;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class TextTestData {
    @Builder.Default private String format = "html";
    @Builder.Default private String questionText = "questionText";

    public Text buildText() {
        return Text.builder()
                .format(format)
                .text(questionText)
                .build();
    }

}