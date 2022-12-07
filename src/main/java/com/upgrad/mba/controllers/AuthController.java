package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.CustomerDTO;
import com.upgrad.mba.dto.LoginDTO;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.exceptions.*;
import com.upgrad.mba.services.CustomerServiceImpl;
import com.upgrad.mba.validators.CustomerValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/movie_app/v1")
public class AuthController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CustomerValidator customerValidator;

    @PostMapping(value = "/signup",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signup(@RequestBody CustomerDTO customerDTO) throws APIException, CustomerUserNameExistsException, UserTypeDetailsNotFoundException {

        customerValidator.validateCustomer(customerDTO);
        Customer newCustomer = modelMapper.map(customerDTO,Customer.class);
        Customer savedCustomer = customerService.acceptCustomerDetails(newCustomer);
        CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer,CustomerDTO.class);
        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) throws APIException, CustomerDetailsNotFoundException, BadCredentialsException {
        customerValidator.validateUserLogin(loginDTO);
        Map<String, String> model = new HashMap<>();
        Customer savedCustomer = customerService.getCustomerDetailsByUsername(loginDTO.getUsername());
        if (!savedCustomer.getPassword().equals(loginDTO.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        model.put("message","Logged in Successfully");
        model.put("token",savedCustomer.getUsername());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
