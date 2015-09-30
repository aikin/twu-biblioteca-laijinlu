package com.twu.biblioteca.helper;

import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.domain.repo.UserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

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
        User user = new User("C00-0001", "twu46", "aikin", "aikin@biblioteca.com", "18282828562", "CUSTOMER");
        when(interactionHelper.readUserInputWithPrompt("Please input library number: ")).thenReturn("C00-0001");
        when(interactionHelper.readUserInputWithPrompt("Please input password: ")).thenReturn("twu46");
        when(userRepo.verifyLoginUser(anyString(), anyString())).thenReturn(true);
        when(userRepo.findUser(anyString())).thenReturn(user);

        assertEquals(SessionHelper.login(interactionHelper, userRepo), user);
    }
}
