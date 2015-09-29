package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookServiceTest {

    private BookService bookService;
    private BookRepo bookRepo;
    private SimpleDateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        bookRepo = new BookRepo();
        bookService = new BookService(bookRepo);
    }

    @After
    public void tearDown() {
        formatter = null;
        bookRepo = null;
        bookService = null;
    }

    @Test
    public void should_fetch_list_of_books_when_checked_out_books_is_empty() throws ParseException {
        List<Book> booksCanCheckout = bookService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(6));
        assertThat(booksCanCheckout.get(0).getId(), is("B-01"));
        assertThat(booksCanCheckout.get(0).getTitle(), is("Refactoring"));
        assertThat(booksCanCheckout.get(0).getAuthor(), is("Martin Fowler & Kent Beck"));
        assertThat(booksCanCheckout.get(0).getPublishedYear(), is(formatter.parse("1999-07-08")));
    }
}
