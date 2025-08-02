//package com.example.springexam.controller;
//
//import com.example.springexam.model.dto.response.ParsedInfo;
//import com.example.springexam.model.dto.request.Search;
//import com.example.springexam.service.parser.DocumentParser;
//import com.example.springexam.validator.URLValidator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Validated
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/")
//public class UrlParseController {
//
//    private final DocumentParser documentParser;
//    private final URLValidator urlValidator;
//
////    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
////    public ResponseEntity<ParsedInfo> parse(@RequestBody Search search) {
////        urlValidator.validate(search.getUrl());
////        return ResponseEntity.status(HttpStatus.OK)
////                .body(documentParser.parse(search.getUrl()));
////    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ParsedInfo>> parse(@RequestBody Search search) {
//        urlValidator.validate(search.getUrl());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(documentParser.parse(search.getUrl()));
//    }
//
//}
