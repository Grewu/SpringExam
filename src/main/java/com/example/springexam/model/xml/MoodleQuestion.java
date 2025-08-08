package com.example.springexam.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class MoodleQuestion {
    @XmlAttribute
    private String type = "multichoice";

    @XmlElement(name = "name")
    private Name name;

    @XmlElement(name = "questiontext")
    private Text questionText;

    @XmlElement(name = "answer")
    private List<MoodelAnswer> answers;

    @XmlElement(name = "feedback")
    private GeneralFeedback generalFeedback;

    @XmlElement(name = "topic")
    private Topic topic;
}