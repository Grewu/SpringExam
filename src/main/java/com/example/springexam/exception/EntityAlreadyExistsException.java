package com.example.springexam.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends AbstractExceptionMessageException {
  private static final String EXCEPTION_MESSAGE = "%s with ID %s already exists";
  private static final String EXCEPTION_MESSAGE_WITH_FIELD = "%s with '%s' already exists";

  public <T> EntityAlreadyExistsException(Class<T> entity, Long id) {
    super(String.format(EXCEPTION_MESSAGE, entity.getSimpleName(), id));
  }

  public <T> EntityAlreadyExistsException(Class<T> entity, String fieldValue) {
    super(String.format(EXCEPTION_MESSAGE_WITH_FIELD, entity.getSimpleName(), fieldValue));
  }

  @Override
  public HttpStatus getStatusCode() {
    return HttpStatus.CONFLICT;
  }
}
