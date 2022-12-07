package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.CustomerDTO;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.exceptions.*;
import com.upgrad.mba.services.CustomerService;
import com.upgrad.mba.validators.CustomerValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/movie_app/v1")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerValidator customerValidator;

    @Autowired
    ModelMapper modelmapper;

    @PutMapping(value="/customers/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomerDetails(@PathVariable(name = "id") int id , @RequestBody CustomerDTO customerDTO) throws CustomerUserNameExistsException, CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException, APIException {
        customerValidator.validateCustomer(customerDTO);
        Customer newCustomer = modelmapper.map(customerDTO,Customer.class);
        Customer updatedCustomer =  customerService.updateCustomerDetails(id, newCustomer);
        CustomerDTO updatedCustomerDTO = modelmapper.map(updatedCustomer, CustomerDTO.class);
        return new ResponseEntity<>(updatedCustomerDTO, HttpStatus.OK);
    }
}