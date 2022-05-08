package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDelete implements Communicable {
    private final UserCrud repository;

    @Autowired
    public UserDelete(UserCrud repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        User deleteUserTarget = repository.getUserById(id);

        repository.remove(deleteUserTarget);

        return "redirect:/users.nhn";
    }
}
