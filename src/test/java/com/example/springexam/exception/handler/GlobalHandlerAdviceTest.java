package com.example.springexam.exception.handler;

import com.example.springexam.exception.EntityAlreadyExistsException;
import com.example.springexam.exception.EntityNotFoundException;
import com.example.springexam.exception.handler.util.ControllerFake;
import com.example.springexam.exception.handler.util.MockEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@WebMvcTest
@ContextConfiguration(classes = {ControllerFake.class, GlobalHandlerAdvice.class})
class GlobalHandlerAdviceTest {

    private static final String MOCK_ENTITY_MESSAGE = "message";
    private static final String ENTITY_ALREADY_EXISTS_MESSAGE =
            "MockEntity with " + MOCK_ENTITY_MESSAGE + " already exists";

    private static final Long MOCK_ENTITY_ID = 1L ;
    private static final String ENTITY_NOT_FOUND_MESSAGE_ID =
            "MockEntity with ID " + MOCK_ENTITY_ID + " was not found";
    @MockitoSpyBean
    private GlobalHandlerAdvice handlerAdvice;

    @Test
    void handleEntityNotFoundExceptionShouldReturnNotFoundStatus() {
        var exception = new EntityNotFoundException(MockEntity.class, 1L);
        var response = handlerAdvice.handle(exception);

        assertThat(response)
                .hasFieldOrPropertyWithValue("statusCode", NOT_FOUND)
                .extracting("body")
                .hasFieldOrPropertyWithValue("status", NOT_FOUND)
                .hasFieldOrPropertyWithValue("message", ENTITY_NOT_FOUND_MESSAGE_ID);
    }

    @Test
    void handleEntityAlreadyExistsException_shouldReturnConflictStatus() {
        var exception = new EntityAlreadyExistsException(MockEntity.class, "message");

        var response = handlerAdvice.handle(exception);

        assertThat(response)
                .hasFieldOrPropertyWithValue("statusCode", CONFLICT)
                .extracting("body")
                .hasFieldOrPropertyWithValue("status", CONFLICT)
                .hasFieldOrPropertyWithValue("message", ENTITY_ALREADY_EXISTS_MESSAGE);
    }

    @Test
    void handleValidationErrors_ShouldReturnMapOfFieldErrors() {
        //  given
        var bindingResult = new BeanPropertyBindingResult(new Object(), "objectName");
        bindingResult.addError(new FieldError("objectName", "field1", "must not be null"));
        bindingResult.addError(new FieldError("objectName", "field2", "size must be between 1 and 10"));

        var ex = new MethodArgumentNotValidException(null, bindingResult);

        // when
        var result = handlerAdvice.handleValidationErrors(ex);

        // then
        assertThat(result)
                .hasSize(2)
                .containsEntry("field1", "must not be null")
                .containsEntry("field2", "size must be between 1 and 10");
    }
}