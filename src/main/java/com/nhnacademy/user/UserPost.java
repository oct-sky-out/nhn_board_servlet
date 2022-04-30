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
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String fileName = req.getParameter("file");

        User user = new User(id, password, name, fileName);

        repository.add(user);

        return "redirect:/userList.nhn";
    }
}
