package com.example.springexam.exception.handler.util;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.exception.EntityNotFoundException;
import com.example.springexam.exception.handler.util.MockEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fake")
public class ControllerFake {

    @GetMapping("/entity/{id}")
    ResponseEntity<Void> throwEntityNotFoundException(@PathVariable Long id) {
        throw new EntityNotFoundException(MockEntity.class, id);
    }


    @PostMapping("/entity-exist/{id}")
    ResponseEntity<Void> invalidEntityAlreadyExistsException(@PathVariable Long id) {
        throw new EntityAlreadyExistsException(MockEntity.class, "");
    }

}