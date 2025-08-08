package com.example.springexam.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionTags {
    @XmlElement(name = "tag")
    private List<Tag> tags;
}
