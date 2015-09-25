package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookRepoTest {

    private BookRepo bookRepo;

    private SimpleDateFormat formatter;

    @Before
    public void setUp() {
        bookRepo = new BookRepo();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @After
    public  void tearDown() {
        bookRepo = null;
        formatter = null;
    }

    @Test
    public void should_can_get_list_of_books() throws ParseException {
        Map<Integer, Book> listOfBooks = bookRepo.getListOfBooks();

        assertThat(listOfBooks.size(), is(6));
        assertThat(listOfBooks.get(1).getId(), is(1));
        assertThat(listOfBooks.get(1).getTitle(), is("Refactoring"));
        assertThat(listOfBooks.get(1).getAuthor(), is("Martin Fowler & Kent Beck"));
        assertThat(listOfBooks.get(1).getPublishedYear(), is(formatter.parse("1999-07-08")));
        assertThat(listOfBooks.get(4).getId(), is(4));
        assertThat(listOfBooks.get(4).getTitle(), is("The Art of Readable Code"));
        assertThat(listOfBooks.get(4).getAuthor(), is("Dustin Boswell & Trevor Foucher"));
        assertThat(listOfBooks.get(4).getPublishedYear(), is(formatter.parse("2011-12-02")));
    }

    @Test
    public void should_get_book_by_id() throws ParseException {
        Book book = bookRepo.getBookById(3);

        assertThat(book.getId(), is(3));
        assertThat(book.getTitle(), is("Test Driven Development: By Example"));
        assertThat(book.getAuthor(), is("Kent Beck"));
        assertThat(book.getPublishedYear(), is(formatter.parse("2002-11-18")));
    }
}
