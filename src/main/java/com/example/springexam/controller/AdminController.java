package com.example.springexam.controller;

import com.example.springexam.service.api.DataBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DataBaseService dataBaseService;

    @PostMapping("/reset-db")
    public ResponseEntity<Void> resetDatabase() {
        dataBaseService.hardReset();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}