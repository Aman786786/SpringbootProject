package com.upgrad.mba.services;

import com.upgrad.mba.dao.CustomerDao;
import com.upgrad.mba.entities.Customer;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.CustomerUserNameExistsException;
import com.upgrad.mba.exceptions.UserTypeDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private UserTypeService userTypeService;

    @Override
    public Customer acceptCustomerDetails(Customer customer) throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException {
        if (customerDao.findByUsername(customer.getUsername()).isPresent()) {
            throw new CustomerUserNameExistsException("This username is already taken.");
        }
        userTypeService.getUserTypeDetails(customer.getUserType().getUserTypeId());
        return customerDao.save(customer);
    }

    @Override
    public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException {
        return customerDao.findById(id)
                .orElseThrow(
                        () -> new CustomerDetailsNotFoundException("Customer not found with id: " + id)
                );
    }

    @Override
    public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException {
        return customerDao.findByUsername(username)
                .orElseThrow(
                        () -> new CustomerDetailsNotFoundException("Customer not found with username: " + username)
                );
    }

    @Override
    public Customer updateCustomerDetails(int id, Customer customer)
            throws CustomerUserNameExistsException, CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException {
        Customer savedCustomer = getCustomerDetails(id);
        if (customerDao.findByUsername(customer.getUsername()).isPresent()) {
            throw new CustomerUserNameExistsException("This username is already taken.");
        }
        userTypeService.getUserTypeDetails(customer.getUserType().getUserTypeId());

        if (isNotNullOrZero(customer.getFirstName())) {
            savedCustomer.setFirstName(customer.getFirstName());
        }

        if (isNotNullOrZero(customer.getLastName())) {
            savedCustomer.setLastName(customer.getLastName());
        }

        if (isNotNullOrZero(customer.getUsername())) {
            savedCustomer.setUsername(customer.getUsername());
        }

        if (isNotNullOrZero(customer.getPassword())) {
            savedCustomer.setPassword(customer.getPassword());
        }

        if (isNotNullOrZero(customer.getDateOfBirth())) {
            savedCustomer.setDateOfBirth(customer.getDateOfBirth());
        }

        if (isNotNullOrZero(customer.getPhoneNumbers())) {
            savedCustomer.setPhoneNumbers(customer.getPhoneNumbers());
        }

        if (isNotNullOrZero(customer.getUserType())) {
            savedCustomer.setUserType(customer.getUserType());
        }

        if (isNotNullOrZero(customer.getLanguage())) {
            savedCustomer.setLanguage(customer.getLanguage());
        }

        return customerDao.save(savedCustomer);
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }
}
