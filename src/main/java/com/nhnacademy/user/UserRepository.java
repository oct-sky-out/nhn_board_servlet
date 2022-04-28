package com.nhnacademy.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository implements UserCrud {
    public static final UserRepository INSTANCE = new UserRepository();
    private final Map<String, User> userList = new HashMap<>();

    private UserRepository() {
    }

    @Override
    public User add(User user) {
        userList.put(user.getId(), user);
        return user;
    }

    @Override
    public User modify(User user) {
        return this.userList.replace(user.getId(), user);
    }

    @Override
    public User remove(User user) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return userList.get(id);
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(this.userList.values());
    }
}
