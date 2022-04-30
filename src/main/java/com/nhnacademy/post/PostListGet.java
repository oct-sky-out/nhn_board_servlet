package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostListGet implements Communicable {
    private final PostRepository repository;

    public PostListGet(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("postList", repository.getPosts());
        return "/index.jsp";
    }
}
