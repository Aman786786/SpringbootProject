package com.upgrad.mba.aspects;

import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionAspect {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<CustomResponse> handleAPIException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}