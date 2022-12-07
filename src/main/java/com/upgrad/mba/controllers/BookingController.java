package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.exceptions.*;
import com.upgrad.mba.services.BookingService;
import com.upgrad.mba.utils.DTOEntityConverter;
import com.upgrad.mba.utils.EntityDTOConverter;
import com.upgrad.mba.validators.BookingValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/movie_app/v1")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    BookingValidator bookingValidator;

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity getBookingDetails(@PathVariable(name = "id") int id) throws BookingDetailsNotFoundException {
        Booking responseBooking = bookingService.getBookingDetails(id);
        BookingDTO responseBookingDTO = modelmapper.map(responseBooking,BookingDTO.class);
        return new ResponseEntity<>(responseBookingDTO, HttpStatus.OK);
    }

    @GetMapping(value="/bookings",produces= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllBookings() {
        List<Booking> bookings = bookingService.getAllBookingDetails();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping(value="/bookings",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newBooking(@RequestBody BookingDTO bookingDTO) throws CustomerDetailsNotFoundException, MovieTheatreDetailsNotFoundException, APIException, BookingFailedException {
        bookingValidator.validateBooking(bookingDTO);
        Booking newBooking = dtoEntityConverter.convertToBookingEntity(bookingDTO);
        Booking savedBooking = bookingService.acceptBookingDetails(newBooking);
        BookingDTO savedBookingDTO = entityDTOConverter.convertToBookingDTO(savedBooking);
        return new ResponseEntity<>(savedBookingDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> removeBookingDetails(@PathVariable("id") int id) throws BookingDetailsNotFoundException{
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking details successfully removed ",HttpStatus.OK);
    }
}
