package com.upgrad.mba.validators;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;


public interface MovieValidator {
    public void validateMovie(MovieDTO movieDTO) throws APIException, StatusDetailsNotFoundException;
}