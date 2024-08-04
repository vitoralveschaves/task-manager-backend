package com.hookfeat.todo.exceptionHandler;

import com.hookfeat.todo.entities.dtos.ErrorDto;
import com.hookfeat.todo.exceptions.IdNotFoundException;
import com.hookfeat.todo.exceptions.IdNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<ErrorDto> idNotFoundHandler(IdNotFoundException idNotFoundException) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND, idNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(IdNullException.class)
    private ResponseEntity<ErrorDto> IdNullExceptionHandler(IdNullException idNullException) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, idNullException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException runtimeException) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
