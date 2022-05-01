package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPut implements Communicable {
    private final UserRepository repository;

    public UserPut(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String fileName = req.getParameter("file");

        User user = repository.getUserById(id);
        user.setPassword(password);
        user.setProfileName(fileName);
        user.setName(name);

        repository.modify(user);

        return "redirect:/user.nhn?id=" + user.getId();
    }
}
