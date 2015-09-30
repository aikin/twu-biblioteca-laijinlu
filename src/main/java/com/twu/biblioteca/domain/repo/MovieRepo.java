package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MovieRepo {

    private final Map<String, Movie> originalMovies = new HashMap<>();

    public MovieRepo() {
        this.initMovies();
    }

    public Map<String, Movie> getOriginalMovies() {
        return originalMovies;
    }

    private void initMovies() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        try {
            originalMovies.put("M-01", new Movie("M-01", "The Shawshank Redemption", "Frank Darabont", formatter.parse("1994"), "10"));
            originalMovies.put("M-02", new Movie("M-02", "The Godfather", "Francis Ford Coppola", formatter.parse("1972"), "9"));
            originalMovies.put("M-03", new Movie("M-03", "The Godfather: Part II", "Francis Ford Coppola", formatter.parse("1974"), "9"));
            originalMovies.put("M-04", new Movie("M-04", "The Dark Knight", "Christopher Nolan", formatter.parse("2008"), "unrated"));
            originalMovies.put("M-05", new Movie("M-05", "Angry Men", "Sidney Lumet", formatter.parse("1957"), "8"));
            originalMovies.put("M-06", new Movie("M-06", "The Lord of the Rings: The Return of the King", "Peter Jackson", formatter.parse("2003"), "unrated"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
