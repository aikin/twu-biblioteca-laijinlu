package com.twu.biblioteca.domain.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Comparable<Movie> {

    private String id;
    private String name;
    private String director;
    private Date year;
    private String rating;

    public Movie(String id, String name, String director, Date year, String rating) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Movie movie) {
        return this.getId().compareTo(movie.getId());
    }

    public static String generateColumnHeader() {
        return "ID         Name         Director     Year     Rating";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return id + ",   " + name + ",  " + director + ",  " +  formatter.format(year) + ",  " + rating + "    ";
    }
}
