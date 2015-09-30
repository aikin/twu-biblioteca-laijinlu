package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Movie;
import com.twu.biblioteca.domain.repo.MovieRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MovieService {

    private static final String SUCCESS_CHECKOUT_MOVIE_MESSAGE = "Thank you! Enjoy the movie.";
    private static final String FAILURE_CHECKOUT_MOVIE_MESSAGE = "That movie is not available.";
    private static final String SUCCESS_RETURN_MOVIE_MESSAGE = "Thank you for returning the movie.";
    private static final String FAILURE_RETURN_MOVIE_MESSAGE = "That is not a valid movie to return.";

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> fetchMoviesCanCheckout() {
        Map<String, Movie> originalMovies = movieRepo.getOriginalMovies();
        Map<String, String> checkedOutMovies = movieRepo.getCheckedOutMovies();
        List<Movie> movies = new ArrayList<>();

        for(Map.Entry<String, Movie> entry : originalMovies.entrySet()) {
            if (!checkedOutMovies.containsKey(entry.getKey())) {
                movies.add(entry.getValue());
            }
        }
        Collections.sort(movies);
        return movies;

    }

    public String checkoutMovie(String movieId, String userId) {
        Boolean isCanCheckoutMovie = movieRepo.isMovieExist(movieId) && !movieRepo.isMovieCheckedOut(movieId);
        if (isCanCheckoutMovie) {
            movieRepo.addCheckedOutMovie(movieId, userId);
            return SUCCESS_CHECKOUT_MOVIE_MESSAGE;
        }
        return FAILURE_CHECKOUT_MOVIE_MESSAGE;
    }
}
