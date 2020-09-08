package com.slb.messageHashStore.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MessageControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { MessageNotFoundException.class })
    protected ResponseEntity<ErrorMessage> handleMessageNotFound(MessageNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
