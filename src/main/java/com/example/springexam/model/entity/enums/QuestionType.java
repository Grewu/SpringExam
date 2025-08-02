package com.example.springexam.model.entity.enums;

import java.util.Arrays;

public enum QuestionType {
    SINGLE_CHOICE,
    MULTIPLE_CHOICE

    //    SINGLE_CHOICE("SINGLE_CHOICE"),
//    MULTIPLE_CHOICE("MULTIPLE_CHOICE");
//    private final String displayName;
//
//    QuestionType(String displayName) {
//        this.displayName = displayName;
//    }
//
//    public static QuestionType fromDisplayName(String displayName) {
//        if (displayName == null || displayName.isBlank()) {
//            return null;
//        }
//        String normalizedInput = displayName.trim().toLowerCase();
//        return Arrays.stream(values())
//                .filter(topic -> topic.displayName.toLowerCase().equals(normalizedInput))
//                .findFirst()
//                .orElseThrow();
//    }
}