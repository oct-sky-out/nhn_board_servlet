package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostListGet implements Communicable {
    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        return "index.jsp";
    }
}
