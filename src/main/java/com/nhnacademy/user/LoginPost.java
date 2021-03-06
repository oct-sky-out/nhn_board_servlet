package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginPost implements Communicable {
    private final UserCrud repository;

    @Autowired
    public LoginPost(UserCrud repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        AdminUser adminUser = (AdminUser) req.getServletContext().getAttribute("admin");

        if(checkAdmin(id, password, adminUser)){
            setSession(req, id);
            return "redirect:/posts.nhn?pageNo=1&size=10";
        }

        User user = repository.getUserById(id);
        if(isInvalidUser(id, password, user)) {
            return "redirect:/loginForm.jsp";
        }

        setSession(req, id);
        return "redirect:/posts.nhn?pageNo=1&size=10";
    }

    private boolean checkAdmin(String id, String password, AdminUser adminUser) {
        return Objects.equals(adminUser.getId(), id) &&
            Objects.equals(adminUser.getPassword(), password);
    }

    private boolean isInvalidUser(String id, String password, User user) {
        return Objects.isNull(user) || !Objects.equals(user.getId(), id) ||
            !Objects.equals(user.getPassword(), password);
    }

    private void setSession(HttpServletRequest req, String id) {
        req.getSession().setAttribute("id", id);
        int logged = (int) req.getServletContext().getAttribute("logged");
        req.getSession().getServletContext().setAttribute("logged", ++logged);
    }
}
