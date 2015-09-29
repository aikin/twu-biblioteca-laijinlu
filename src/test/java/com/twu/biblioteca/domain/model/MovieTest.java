package com.twu.biblioteca.domain.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieTest {

    private Movie movie;
    private Date currentDate;

    @Before
    public void setUp()  {
        currentDate = new Date();
        movie = new Movie("M-01", "movie1", "director1", currentDate, "8");
    }

    @After
    public void tearDown() {
        currentDate = null;
        movie = null;
    }

    @Test
    public void should_create_correct_book_instance() {
        assertThat(movie.getId(), is("M-01"));
        assertThat(movie.getName(), is("movie1"));
        assertThat(movie.getDirector(), is("director1"));
        assertThat(movie.getYear(), is(currentDate));
        assertThat(movie.getRating(), is("8"));
    }

    @Test
    public void should_get_correct_result_after_set() {
        Date updatedPublishedDate = new Date();
        movie.setId("M-02");
        movie.setName("movie2");
        movie.setDirector("director2");
        movie.setYear(updatedPublishedDate);
        movie.setRating("unrated");

        assertThat(movie.getId(), is("M-02"));
        assertThat(movie.getName(), is("movie2"));
        assertThat(movie.getDirector(), is("director2"));
        assertThat(movie.getYear(), is(updatedPublishedDate));
        assertThat(movie.getRating(), is("unrated"));
    }
}
