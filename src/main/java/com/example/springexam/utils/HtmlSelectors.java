package com.example.springexam.utils;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

public final class HtmlSelectors {
    // Приватный конструктор для запрета создания экземпляров
    private HtmlSelectors() {}

    // Группировка селекторов по логическим блокам
    public static final class Question {
        public static final String CONTAINER = "div.result-pane--question-result-pane-wrapper--2bGiz";
        public static final String PROMPT = "#question-prompt";
        public static final String TEXT = ".question-text";
    }

    public static final class Answer {
        public static final String CONTAINER = ".answer-container";
        public static final String TEXT = "[id='answer-text']";
        public static final String CORRECT_MARKER = ".correct-answer";
    }

    public static final class Explanation {
        public static final String CONTAINER = "#overall-explanation";
        public static final String DETAILS = ".explanation-details";
    }

    public static final class Topic {
        public static final String PANE = "div[data-purpose=domain-pane]";
        public static final String NAME = "div.ud-text-md";
        public static final String META = ".topic-meta";
    }

}
