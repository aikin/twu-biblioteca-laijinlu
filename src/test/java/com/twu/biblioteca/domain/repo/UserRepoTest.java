package com.twu.biblioteca.domain.repo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserRepoTest {

    private UserRepo userRepo;

    @Before
    public void setUp() {
        userRepo = new UserRepo();
    }

    @After
    public void tearDown() {
        userRepo = null;
    }

    @Test
    public void should_verify_user_correct() {
        assertTrue(userRepo.verifyLoginUser("C00-0001", "twu46"));
    }

    @Test
    public void should_verify_user_incorrect_when_user_id_error() {
        assertFalse(userRepo.verifyLoginUser("error user id", "twu46"));
    }

    @Test
    public void should_verify_user_incorrect_when_password_id_error() {
        assertFalse(userRepo.verifyLoginUser("C00-0001", "incorrect password"));
    }

    @Test
    public void should_find_correct_user() {
        assertThat(userRepo.findUser("C00-0001").getName(), is("aikin"));
    }


}
