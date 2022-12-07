package com.upgrad.mba.validators;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.entities.MovieTheatre;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.BookingFailedException;
import com.upgrad.mba.exceptions.MovieTheatreDetailsNotFoundException;
import com.upgrad.mba.services.MovieTheatreService;
import com.upgrad.mba.utils.DateDifference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingValidatorImpl implements BookingValidator {


    @Autowired
    MovieTheatreService movieTheatreService;

    @Override
    public void validateBooking(BookingDTO bookingDTO) throws APIException, MovieTheatreDetailsNotFoundException, BookingFailedException {
        if(bookingDTO.getCustomerId() <= 0)
            throw new APIException("Invalid userId");
        if(bookingDTO.getNoOfSeats() <= 0)
            throw new APIException("Invalid number of seats");
        if(bookingDTO.getMovieTheatreId() <= 0)
            throw new APIException("Invalid MovieTheatreID");
        int dateDifference = DateDifference.differenceBetweenDates(bookingDTO.getBookingDate(), LocalDateTime.now());
        if(dateDifference < 0 || dateDifference >= 3)
            throw new APIException("Invalid booking date");

        MovieTheatre movieTheatre = movieTheatreService.getMovieTheatreDetails(bookingDTO.getMovieTheatreId());
        Movie bookedMovie = movieTheatre.getMovie();
        if(bookedMovie == null){
            throw new BookingFailedException("Movie details not found");
        }else{
            if(!bookedMovie.getStatus().getStatusName().equalsIgnoreCase("Released"))
                throw new BookingFailedException("Movie is not released");
        }
    }
}
