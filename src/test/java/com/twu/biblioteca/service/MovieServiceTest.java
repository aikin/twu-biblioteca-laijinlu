package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Movie;
import com.twu.biblioteca.domain.repo.MovieRepo;
import com.twu.biblioteca.util.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieServiceTest extends TestFixtures {

    private MovieService movieService;
    private MovieRepo movieRepo;
    private SimpleDateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat("yyyy");
        movieRepo = new MovieRepo();
        movieService = new MovieService(movieRepo);
    }

    @After
    public void tearDown() {
        formatter = null;
        movieRepo = null;
        movieService = null;
    }

    @Test
    public void should_fetch_list_of_books_when_checked_out_books_is_empty() throws ParseException {
        List<Movie> moviesCanCheckout = movieService.fetchMoviesCanCheckout();

        assertThat(moviesCanCheckout.size(), is(6));
        assertThat(moviesCanCheckout.get(0).getId(), is("M-01"));
        assertThat(moviesCanCheckout.get(0).getName(), is("The Shawshank Redemption"));
        assertThat(moviesCanCheckout.get(0).getDirector(), is("Frank Darabont"));
        assertThat(moviesCanCheckout.get(0).getYear(), is(formatter.parse("1994")));
        assertThat(moviesCanCheckout.get(0).getRating(), is("10"));
    }
}
