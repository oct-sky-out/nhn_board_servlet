package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserGet implements Communicable {
    private final UserRepository repository;

    public UserGet(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        User user = repository.getUserById(id);
        req.setAttribute("user", user);

        return "/user.jsp?id=" + id;
    }
}
