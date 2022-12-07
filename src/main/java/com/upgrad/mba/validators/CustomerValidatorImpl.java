package com.upgrad.mba.validators;

import com.upgrad.mba.dto.CustomerDTO;
import com.upgrad.mba.dto.LoginDTO;
import com.upgrad.mba.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidatorImpl implements CustomerValidator{

    @Override
    public void validateCustomer(CustomerDTO customerDTO) throws APIException {
        if(customerDTO.getUsername() == null || customerDTO.getUsername().length() <= 0)
            throw new APIException("Invalid username");
        if(customerDTO.getFirstName() == null || customerDTO.getFirstName().length() <= 0 )
            throw new APIException("Invalid firstname");
        if(customerDTO.getLastName() == null || customerDTO.getLastName().length() <= 0 )
            throw new APIException("Invalid lastname");
        if(customerDTO.getPassword() == null || customerDTO.getPassword().length() <= 0   )
            throw new APIException("Invalid password");
        if(customerDTO.getDateOfBirth() == null)
            throw new APIException("Invalid date of birth");
        if(customerDTO.getUserTypeId() <= 0 )
            throw new APIException("Invalid user type");
    }

    public void validateUserLogin(LoginDTO user) throws APIException {
        if (user.getUsername() == null || user.getUsername().length() <= 0) {
            throw new APIException("Invalid username");
        }
        if(user.getPassword() == null || user.getPassword().length() <= 0   ) {
            throw new APIException("Invalid password");
        }
    }
}
