package com.twu.biblioteca.domain.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private User user;

    @Before
    public void setUp()  {
        user = new User("C00-0001", "twu46", "customer", "me@customer.com", "18282828282", "CUSTOMER");
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void should_create_correct_user_instance() {
        assertThat(user.getLibraryNumber(), is("C00-0001"));
        assertThat(user.getName(), is("customer"));
        assertThat(user.getPassword(), is("twu46"));
        assertThat(user.getEmail(), is("me@customer.com"));
        assertThat(user.getPhone(), is("18282828282"));
        assertThat(user.getRole(), is("CUSTOMER"));
    }

    @Test
    public void should_get_correct_result_after_set() {
        user.setName("customer1");
        user.setEmail("me1@customer.com");
        user.setPhone("18282828283");

        assertThat(user.getLibraryNumber(), is("C00-0001"));
        assertThat(user.getName(), is("customer1"));
        assertThat(user.getPassword(), is("twu46"));
        assertThat(user.getEmail(), is("me1@customer.com"));
        assertThat(user.getPhone(), is("18282828283"));
        assertThat(user.getRole(), is("CUSTOMER"));
    }

    @Test
    public void should_get_correct_result_when_call_toString() {
        assertThat(user.toString(), is("{name='customer', email='me@customer.com', phone='18282828282'}"));
    }
}
