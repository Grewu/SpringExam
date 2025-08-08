package com.example.springexam.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerFeedback {
    @XmlElement(name = "text")
    private String text;
}