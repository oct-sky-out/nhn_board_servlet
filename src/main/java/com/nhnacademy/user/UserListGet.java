package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListGet implements Communicable {
    private final UserRepository repository;

    public UserListGet(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        List<User> userList = repository.getAllUser();
        req.setAttribute("userList", userList);

        return "/userList.jsp";
    }
}
