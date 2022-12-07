package com.upgrad.mba.aspects;

import com.upgrad.mba.exceptions.MovieTheatreDetailsNotFoundException;
import com.upgrad.mba.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieTheatreExceptionAspect {
    @ExceptionHandler(MovieTheatreDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleMovieTheatreDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}