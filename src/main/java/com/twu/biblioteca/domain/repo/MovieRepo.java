package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepo {

    private final Map<String, Movie> originalMovies = new HashMap<>();

    public Map<String, Movie> getOriginalMovies() {
        return originalMovies;
    }
}
