package com.twu.biblioteca.domain.repo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class UserRepoTest {

    @Test
    public void should_verify_user_correct() {
        UserRepo userRepo = new UserRepo();
        assertTrue(userRepo.verifyLoginUser("C00-0001", "twu46"));
    }

    @Test
    public void should_verify_user_incorrect_when_user_id_error() {
        UserRepo userRepo = new UserRepo();
        assertFalse(userRepo.verifyLoginUser("error user id", "twu46"));
    }

    @Test
    public void should_verify_user_incorrect_when_password_id_error() {
        UserRepo userRepo = new UserRepo();
        assertFalse(userRepo.verifyLoginUser("C00-0001", "incorrect password"));
    }

    @Test
    public void should_find_correct_user() {
        UserRepo userRepo = new UserRepo();
        assertThat(userRepo.findUser("C00-0001").getName(), is("aikin"));
    }


}
