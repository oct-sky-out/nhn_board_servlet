package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class UserListGet implements Communicable {
    private final UserCrud repository;

    @Autowired
    public UserListGet(UserCrud repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        List<User> userList = repository.getAllUser();
        req.setAttribute("userList", userList);

        return "/users.jsp";
    }
}
