package com.watchList.controller;

import com.watchList.exception.AlreadyExistInWatchListException;
import com.watchList.exception.NoUserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CentralExceptionHandler {

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(AlreadyExistInWatchListException.class)
    public String cityAlreadyExists(AlreadyExistInWatchListException e) {
        String message = e.getMessage();
        return message;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoUserFoundException.class)
    public String userNotFound(NoUserFoundException e) {
        String message = e.getMessage();
        return message;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraint(ConstraintViolationException e){
        String message = e.getMessage();
        return message;
    }


}
