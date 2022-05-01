package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.Objects;
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
        String imagePath = this.getClass().getClassLoader().getResource("").getPath();
        User user = repository.getUserById(id);

        req.setAttribute("user", user);
        req.setAttribute("userImagePath", imagePath + user.getProfileName());
        if(Objects.equals(req.getSession(false).getAttribute("id"), "admin")){
            req.setAttribute("isAdmin", true);
        } else {
            req.setAttribute("isAdmin", false);
        }

        return "/user.jsp?id=" + id;
    }
}
