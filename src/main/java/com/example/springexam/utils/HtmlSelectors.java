package com.example.springexam.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class containing HTML selectors for parsing exam-related elements.
 * Organized into nested classes for Question, Answer, Explanation, and Topic.
 */
@UtilityClass
public final class HtmlSelectors {

    /**
     * Selectors related to the question container and prompt.
     */
    public static final class Question {
        public static final String CONTAINER = "div.result-pane--question-result-pane-wrapper--2bGiz";
        public static final String PROMPT = "#question-prompt";
    }

    /**
     * Selectors related to answers, including text, blocks, correctness, and IDs.
     */
    public static final class Answer {
        public static final String TEXT = "div.answer-result-pane--answer-correct--PLOEU";
        public static final String BLOCK = "div.result-pane--answer-result-pane--Niazi";
        public static final String IS_CORRECT = "span.result-pane--answer-by-user-label--PSH86";
        public static final String ANSWER_ID = "div[id=answer-text]";
    }

    /**
     * Selectors for the explanation container and details.
     */
    public static final class Explanation {
        public static final String CONTAINER = "div[class*='overall-explanation-pane--overall-explanation']";
        public static final String DETAILS = "div.ud-text-md.rt-scaffolding";
    }

    /**
     * Selectors for the topic pane and name.
     */
    public static final class Topic {
        public static final String PANE = "div[data-purpose=domain-pane]";
        public static final String NAME = "div.ud-text-md";
    }

}