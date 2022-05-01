package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDelete implements Communicable {
    private final PostCrud repository;

    public PostDelete(PostCrud repository) {
        this.repository = repository;
    }


    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        repository.remove(id);
        return "redirect:/posts.nhn?pageNo=1&size=10";
    }
}
