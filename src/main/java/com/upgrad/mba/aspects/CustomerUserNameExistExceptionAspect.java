package com.upgrad.mba.aspects;

import com.upgrad.mba.exceptions.CustomerUserNameExistsException;
import com.upgrad.mba.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerUserNameExistExceptionAspect {
    @ExceptionHandler(CustomerUserNameExistsException.class)
    public ResponseEntity<CustomResponse> handleCustomerUserNameExistsException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}