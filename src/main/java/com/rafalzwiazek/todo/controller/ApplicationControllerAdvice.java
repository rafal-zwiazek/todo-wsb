package com.rafalzwiazek.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.rafalzwiazek.todo.exception.BindingResultSupport;
import com.rafalzwiazek.todo.exception.ResourceNotFoundException;
import com.rafalzwiazek.todo.data.ErrorDto;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handle(ResourceNotFoundException ex) {
        return ErrorDto.builder().message(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handle(MethodArgumentNotValidException ex) {
        return ErrorDto.builder().message(BindingResultSupport.asMessage(ex.getBindingResult())).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handle(Exception ex) {
        LOGGER.warn("Something went wrong: {}", ex.getMessage(), ex);
        return ErrorDto.builder().message("Something went wrong").build();
    }
}
