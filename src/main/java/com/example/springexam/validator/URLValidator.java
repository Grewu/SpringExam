package com.example.springexam.validator;
import com.example.springexam.exception.UrlParserException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class URLValidator {

    public void validate(String url) {
       UrlValidator validator = new UrlValidator();
        if(!validator.isValid(url))
            throw new UrlParserException(url);
    }
}
