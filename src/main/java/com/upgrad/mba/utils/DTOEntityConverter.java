package com.upgrad.mba.utils;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.MovieTheatreDetailsNotFoundException;
import com.upgrad.mba.services.CustomerService;
import com.upgrad.mba.services.MovieTheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOEntityConverter {
    @Autowired
    CustomerService customerService;

    @Autowired
    MovieTheatreService movieTheatreService;

    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws CustomerDetailsNotFoundException, MovieTheatreDetailsNotFoundException {
        Booking booking = new Booking();
        booking.setNoOfSeats(bookingDTO.getNoOfSeats());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setCustomer(customerService.getCustomerDetails(bookingDTO.getCustomerId()));
        booking.setMovieTheatre(movieTheatreService.getMovieTheatreDetails(bookingDTO.getMovieTheatreId()));
        return booking;
    }
}