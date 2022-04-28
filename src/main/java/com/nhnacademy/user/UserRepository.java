package com.nhnacademy.user;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserCrud {
    public static final UserRepository INSTANCE = new UserRepository();
    private final List<User> userList = new ArrayList<>();

    private UserRepository() {
    }

    @Override
    public User add(User user) {
        return null;
    }

    @Override
    public User modify(User user) {
        return null;
    }

    @Override
    public User remove(User user) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
