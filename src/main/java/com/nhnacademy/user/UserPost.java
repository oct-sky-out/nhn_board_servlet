package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPost implements Communicable {
    private final UserRepository repository;

    public UserPost(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = (String) req.getAttribute("id");
        String password = (String) req.getAttribute("password");
        String name = (String) req.getAttribute("name");
        String fileName = (String) req.getAttribute("imageName");

        User user = new User(id, password, name, fileName);

        repository.add(user);

        return "redirect:/users.nhn";
    }
}
