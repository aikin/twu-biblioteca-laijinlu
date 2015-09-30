package com.twu.biblioteca.helper;

import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.domain.repo.UserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SessionHelperTest {

    private SessionHelper sessionHelper;

    @Before
    public void setUp() {
        UserRepo userRepo = new UserRepo();
        sessionHelper = new SessionHelper(userRepo);
    }

    @After
    public void tearDown() {
        sessionHelper = null;
    }


    @Test
    public void should_return_user_when_login_success() {
        User user = sessionHelper.login("C00-0001", "twu46");
        assertThat(user.getName(), is("aikin"));
        assertThat(user.getId(), is("C00-0001"));
    }

    @Test
    public void should_return_null_when_login_failure() {
        User user = sessionHelper.login("error", "twu46");
        assertThat(user, nullValue());
    }
}
