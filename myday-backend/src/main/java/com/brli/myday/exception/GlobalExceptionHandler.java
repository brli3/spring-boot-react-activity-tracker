package com.brli.myday.exception;

import com.brli.myday.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author brandon
 * 2022-09-10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private ErrorMessage buildMessage(HttpStatus httpStatus, Exception ex, WebRequest request) {
    return  ErrorMessage.builder()
            .status(httpStatus.value())
            .timestamp(StringUtils.formatDateTime(LocalDateTime.now()))
            .message(ex.getMessage())
            .description(request.getDescription(false))
            .build();
  }


  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex,
                                                                WebRequest request) {
    var message = buildMessage(HttpStatus.NOT_FOUND, ex, request);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(IdNotInRequestException.class)
  public ResponseEntity<ErrorMessage> idNotInRequestException(IdNotInRequestException ex,
                                                                WebRequest request) {
    var message = buildMessage(HttpStatus.BAD_REQUEST, ex, request);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(UserExistsException.class)
  public ResponseEntity<ErrorMessage> userExistsException(UserExistsException ex,
                                                              WebRequest request) {
    var message = buildMessage(HttpStatus.BAD_REQUEST, ex, request);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(ResourceInUseException.class)
  public ResponseEntity<ErrorMessage> resourceInUseException(ResourceInUseException ex,
                                                          WebRequest request) {
    var message = buildMessage(HttpStatus.BAD_REQUEST, ex, request);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> exception(Exception ex, WebRequest request) {
    var message = buildMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
  }

}

