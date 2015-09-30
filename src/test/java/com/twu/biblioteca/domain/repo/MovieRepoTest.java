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
        Map<String, Movie> movies = movieRepo.getOriginalMovies();

        assertThat(movies.size(), is(6));
        assertThat(movies.get("M-01").getId(), is("M-01"));
        assertThat(movies.get("M-01").getName(), is("The Shawshank Redemption"));
        assertThat(movies.get("M-01").getDirector(), is("Frank Darabont"));
        assertThat(movies.get("M-01").getYear(), is(formatter.parse("1994")));
        assertThat(movies.get("M-01").getRating(), is("10"));
        assertThat(movies.get("M-04").getId(), is("M-04"));
        assertThat(movies.get("M-04").getName(), is("The Dark Knight"));
        assertThat(movies.get("M-04").getDirector(), is("Christopher Nolan"));
        assertThat(movies.get("M-04").getYear(), is(formatter.parse("2008")));
        assertThat(movies.get("M-04").getRating(), is("unrated"));
    }
}
