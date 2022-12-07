package com.upgrad.mba.utils;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOConverter {

    public BookingDTO convertToBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(booking.getCustomer().getCustomerId());
        bookingDTO.setMovieTheatreId(booking.getMovieTheatre().getMovieTheatreId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setNoOfSeats(booking.getNoOfSeats());
        bookingDTO.setBookingId(booking.getBookingId());
        return bookingDTO;
    }
}