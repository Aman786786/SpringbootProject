package com.upgrad.mba.services;

import com.upgrad.mba.exceptions.*;

public interface InitService {
    public void init() throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException, TheatreDetailsNotFoundException, MovieDetailsNotFoundException, MovieTheatreDetailsNotFoundException, CustomerDetailsNotFoundException;
}
