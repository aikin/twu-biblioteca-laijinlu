package com.twu.biblioteca.helper;

import com.twu.biblioteca.domain.repo.UserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SessionHelperTest {

    private UserRepo userRepo;
    private InteractionHelper interactionHelper;

    @Before
    public void setUp() {
        userRepo = mock(UserRepo.class);
        interactionHelper = mock(InteractionHelper.class);
    }

    @After
    public void tearDown() {
        userRepo = null;
        interactionHelper = null;
    }


    @Test
    public void should_return_user_when_login_success() {
        SessionHelper.login(interactionHelper, userRepo);
    }
}
