package com.nhnacademy.user;

import java.util.List;

public interface UserCrud {
    User add(User user);

    User modify(User user);

    User remove(User user);

    User getUserById(String id);

    List<User> getAllUser();
}
