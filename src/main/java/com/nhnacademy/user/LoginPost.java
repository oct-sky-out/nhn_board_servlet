package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPost implements Communicable {
    private final UserRepository repository;

    public LoginPost(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        AdminUser adminUser = (AdminUser) req.getServletContext().getAttribute("admin");

        if(checkAdmin(id, password, adminUser)){
            setSession(req, id);
            return "/index.jsp";
        }

        User user = repository.getUserById(id);
        if(!Objects.equals(user.getId(), id) || !Objects.equals(user.getPassword(), password)) {
            return "redirect:/loginForm.jsp";
        }

        setSession(req, id);
        return "/index.jsp";
    }

    private void setSession(HttpServletRequest req, String id) {
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
    }

    private boolean checkAdmin(String id, String password, AdminUser adminUser) {
        return Objects.equals(adminUser.getId(), id) &&
            Objects.equals(adminUser.getPassword(), password);
    }
}
