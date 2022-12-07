package com.upgrad.mba.services;

import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.CustomerUserNameExistsException;
import com.upgrad.mba.exceptions.UserTypeDetailsNotFoundException;

public interface CustomerService {
    public Customer acceptCustomerDetails(Customer customer) throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException;
    public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException;
    public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException;
    public Customer updateCustomerDetails(int id, Customer customer) throws CustomerUserNameExistsException, CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException;
}
