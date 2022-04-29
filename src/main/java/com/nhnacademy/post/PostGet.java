package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostGet implements Communicable {
    private final PostRepository repository;

    public PostGet(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));

        Post post = repository.getPostById(id);
        req.setAttribute("post", post);

        return "/post.jsp?id=" + post.getId();
    }
}
