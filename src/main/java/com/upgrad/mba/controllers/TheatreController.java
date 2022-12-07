package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.TheatreDTO;
import com.upgrad.mba.entities.Theatre;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.BadCredentialsException;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mba.services.CustomerService;
import com.upgrad.mba.services.TheatreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/movie_app/v1")
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value = "/theatres/{id}")
    public ResponseEntity getTheatreDetails(@PathVariable(name = "id") int id) throws TheatreDetailsNotFoundException {
        Theatre responseTheatre = theatreService.getTheatreDetails(id);
        TheatreDTO responseTheatreDTO = modelmapper.map(responseTheatre,TheatreDTO.class);
        return new ResponseEntity<>(responseTheatreDTO, HttpStatus.OK);
    }

    public ResponseEntity newTheatre(@RequestBody TheatreDTO theatreDTO, @RequestHeader(value = "ACCESS-TOKEN") String token) throws APIException, BadCredentialsException, CustomerDetailsNotFoundException {
        if(token == null)
            throw new APIException("Please add proper authentication");
        if(!customerService.getCustomerDetailsByUsername(token).getUserType().getUserTypeName().equalsIgnoreCase("Admin"))
            throw new BadCredentialsException("This feature is only available to admin");
        Theatre newTheatre = modelmapper.map(theatreDTO, Theatre.class);
        Theatre savedTheatre = theatreService.acceptTheatreDetails(newTheatre);
        TheatreDTO savedTheatreDTO = modelmapper.map(savedTheatre, TheatreDTO.class);
        return new ResponseEntity<>(savedTheatreDTO, HttpStatus.CREATED);
    }
}
