package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Communicable {
    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session)){
            session.invalidate();
            int logged = (int) req.getServletContext().getAttribute("logged");
            req.getServletContext().setAttribute("logged", --logged);
        }

        return "redirect:/posts.nhn?pageNo=1&size=10";
    }
}
