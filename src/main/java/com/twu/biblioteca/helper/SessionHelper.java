package com.twu.biblioteca.helper;


import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.domain.repo.UserRepo;

public class SessionHelper {

    private final UserRepo userRepo;

    public SessionHelper(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String userId, String password) {
        if (userRepo.verifyLoginUser(userId, password)) {
            return userRepo.findUser(userId);
        }
        return null;
    }
}
