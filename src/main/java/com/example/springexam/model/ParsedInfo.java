package com.example.springexam.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ParsedInfo {

    private String url;

    private String question;
    private List<String> answer;
    private String explanation;
    private String topic;

}

