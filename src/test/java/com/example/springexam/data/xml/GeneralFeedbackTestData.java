package com.example.springexam.data.xml;


import com.example.springexam.model.dto.response.xml.GeneralFeedback;
import lombok.Builder;

@Builder(setterPrefix = "with")
public class GeneralFeedbackTestData {
    @Builder.Default private String text = "";

    public GeneralFeedback buildGeneralFeedback() {
        return GeneralFeedback.builder()
                .text(text)
                .build();
    }


}