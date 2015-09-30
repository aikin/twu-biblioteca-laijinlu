package com.twu.biblioteca.domain.repo;

import com.twu.biblioteca.domain.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    private final Map<String, User> users = new HashMap<>();

    public UserRepo() {
        this.initUsers();
    }

    public boolean verifyLoginUser(String userId, String password) {
        User currentUser = users.get(userId);
        return currentUser != null && currentUser.getPassword().equals(password);
    }

    public User findUser(String userId) {
        return users.get(userId);
    }

    private void initUsers() {
        users.put("C00-0001", new User("C00-0001", "twu46", "aikin", "aikin@biblioteca.com", "18282828562", "CUSTOMER"));
        users.put("C00-0002", new User("C00-0002", "twu46", "admin", "admin@biblioteca.com", "18282828563", "LIBRARIAN"));
        users.put("C00-0003", new User("C00-0003", "twu46", "luajin", "luajin@biblioteca.com", "18282828564", "CUSTOMER"));
    }
}
