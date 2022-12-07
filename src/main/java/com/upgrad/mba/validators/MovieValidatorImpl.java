package com.upgrad.mba.validators;

import com.upgrad.mba.dto.MovieDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mba.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieValidatorImpl implements MovieValidator {
    @Autowired
    StatusService statusService;

    public void validateMovie(MovieDTO movieDTO) throws APIException, StatusDetailsNotFoundException {

        if(movieDTO.getMovieName() == null || movieDTO.getMovieName().length() <= 0)
            throw new APIException("Invalid movie name");
        if(movieDTO.getCoverPhotoUrl() == null || movieDTO.getCoverPhotoUrl().length() <=0)
            throw new APIException("Invalid cover url");
        if(movieDTO.getTrailerUrl() == null || movieDTO.getTrailerUrl().length() <=0)
            throw new APIException("Invalid trailer url");
        if(movieDTO.getMovieDescription() == null || movieDTO.getMovieDescription().length() <=0)
            throw new APIException("Invalid  description");
        if(movieDTO.getDuration() <= 25 || movieDTO.getDuration() > 240)
            throw new APIException("Invalid duration");
        if(movieDTO.getStatusId() <= 0)
            throw new APIException("Invalid status");
        if(statusService.getStatusDetails(movieDTO.getStatusId()).getStatusName().equalsIgnoreCase("Upcoming")){
            if(movieDTO.getReleaseDate().compareTo(LocalDateTime.now()) <0 ){
                throw new APIException("Invalid movie release date");
            }
        }
        if(statusService.getStatusDetails(movieDTO.getStatusId()).getStatusName().equalsIgnoreCase("Released")){
            if(movieDTO.getReleaseDate().compareTo(LocalDateTime.now())>0){
                throw new APIException("Invalid movie release date");
            }
        }
        if(statusService.getStatusDetails(movieDTO.getStatusId()).getStatusName().equalsIgnoreCase("Blocked")){
            throw new APIException("Invalid movie status");
        }
    }
}
