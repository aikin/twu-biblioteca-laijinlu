package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Movie;
import com.twu.biblioteca.domain.repo.MovieRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MovieService {

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
}
