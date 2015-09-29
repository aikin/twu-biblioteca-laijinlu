package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.domain.util.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookServiceTest extends TestFixtures {

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

    @Test
    public void should_checkout_book_success_when_book_is_can_be_checked_out() {
        String message = bookService.checkoutBook("B-03", "C-01");
        List<Book> booksCanCheckout = bookService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(booksCanCheckout.get(2).getId(), is("B-04"));
        assertThat(message, is(SUCCESS_CHECKOUT_BOOK_MESSAGE));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }
}
