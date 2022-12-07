package com.upgrad.mba.services;

import com.upgrad.mba.entities.Movie;
import com.upgrad.mba.exceptions.MovieDetailsNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    public Movie acceptMovieDetails(Movie movie);
    public Movie getMovieDetails(int id) throws MovieDetailsNotFoundException;
    public Movie updateMovieDetails(int id, Movie movie) throws MovieDetailsNotFoundException;
    public boolean deleteMovie(int id) throws MovieDetailsNotFoundException ;
    public List<Movie> getAllMoviesDetails();
    public Page<Movie> getPaginatedMoviesDetails(Pageable pageRequest);
}
