package com.upgrad.mba.validators;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.BookingFailedException;
import com.upgrad.mba.exceptions.MovieTheatreDetailsNotFoundException;

public interface BookingValidator {
    public void validateBooking(BookingDTO bookingDTO) throws APIException, MovieTheatreDetailsNotFoundException, BookingFailedException;
}
