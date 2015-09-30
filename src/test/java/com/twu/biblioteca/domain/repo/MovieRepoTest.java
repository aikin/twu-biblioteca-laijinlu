package com.twu.biblioteca.domain.repo;

import com.twu.biblioteca.domain.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MovieRepoTest {

    private MovieRepo movieRepo;

    private SimpleDateFormat formatter;

    @Before
    public void setUp() {
        movieRepo = new MovieRepo();
        formatter = new SimpleDateFormat("yyyy");
    }

    @After
    public  void tearDown() {
        movieRepo = null;
        formatter = null;
    }

    @Test
    public void should_can_get_list_of_movies() throws ParseException {
        Map<String, Movie> listOfBooks = movieRepo.getOriginalMovies();

        assertThat(listOfBooks.size(), is(6));
        assertThat(listOfBooks.get("M-01").getId(), is("M-01"));
        assertThat(listOfBooks.get("M-01").getName(), is("Refactoring"));
        assertThat(listOfBooks.get("M-01").getDirector(), is("Martin Fowler & Kent Beck"));
        assertThat(listOfBooks.get("M-01").getYear(), is(formatter.parse("1999-07-08")));
        assertThat(listOfBooks.get("M-01").getRating(), is("10"));
        assertThat(listOfBooks.get("M-04").getId(), is("M-04"));
        assertThat(listOfBooks.get("M-04").getName(), is("The Art of Readable Code"));
        assertThat(listOfBooks.get("M-04").getDirector(), is("Dustin Boswell & Trevor Foucher"));
        assertThat(listOfBooks.get("M-04").getYear(), is(formatter.parse("2011-12-02")));
        assertThat(listOfBooks.get("M-01").getRating(), is("unrated"));
    }
}
