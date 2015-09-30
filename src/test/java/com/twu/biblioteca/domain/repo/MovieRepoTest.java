package com.twu.biblioteca.domain.repo;

import com.twu.biblioteca.domain.model.Movie;
import com.twu.biblioteca.util.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MovieRepoTest extends TestFixtures {

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

    @Test
    public void should_judge_correct_when_call_is_movie_exist() {
        assertTrue(movieRepo.isMovieExist("M-02"));
        assertFalse(movieRepo.isMovieExist("M-08"));
    }

    @Test
    public void should_get_empty_checked_out_books() {
        assertThat(movieRepo.getCheckedOutMovies().size(), is(0));
    }

    @Test
    public void should_get_correct_checked_out_books_after_add() {
        movieRepo.addCheckedOutMovie("M-01", USER_ID);

        assertThat(movieRepo.getCheckedOutMovies().size(), is(1));
        assertThat(movieRepo.getCheckedOutMovies().get("M-01"), is(USER_ID));
    }

    @Test
    public void should_judge_correct_when_call_is_book_checked_out() {
        assertFalse(movieRepo.isMovieCheckedOut("M-01"));

        movieRepo.addCheckedOutMovie("M-01", USER_ID);

        assertTrue(movieRepo.isMovieCheckedOut("M-01"));
        assertFalse(movieRepo.isMovieCheckedOutForCurrentCustomer("M-01", "no checkedOut user"));
    }

    @Test
    public void should_can_remove_checked_out_book() {

        movieRepo.addCheckedOutMovie("M-01", USER_ID);

        assertTrue(movieRepo.isMovieCheckedOut("M-01"));

        movieRepo.removeCheckedOutMovie("M-01");

        assertFalse(movieRepo.isMovieCheckedOut("M-01"));
    }
}
