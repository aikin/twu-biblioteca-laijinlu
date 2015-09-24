package com.twu.biblioteca.domain.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    private Book book;
    private Date currentDate;

    @Before
    public void setUp()  {
        currentDate = new Date();
        book = new Book(1, "title1", "author1", currentDate);
    }

    @After
    public void tearDown() {
        currentDate = null;
        book = null;
    }

    @Test
    public void should_create_correct_book_instance() {
        assertThat(book.getId(), is(1));
        assertThat(book.getTitle(), is("title1"));
        assertThat(book.getAuthor(), is("author1"));
        assertThat(book.getPublishedYear(), is(currentDate));
    }

    @Test
    public void should_get_correct_result_after_set() {
        Date updatedPublishedDate = new Date();
        book.setId(2);
        book.setTitle("title2");
        book.setAuthor("author2");
        book.setPublishedYear(updatedPublishedDate);

        assertThat(book.getId(), is(2));
        assertThat(book.getTitle(), is("title2"));
        assertThat(book.getAuthor(), is("author2"));
        assertThat(book.getPublishedYear(), is(updatedPublishedDate));
    }
}
