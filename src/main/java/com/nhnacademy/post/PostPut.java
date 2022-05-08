package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class PostPut implements Communicable {
    private final PostCrud repository;

    @Autowired
    public PostPut(PostCrud repository) {
        this.repository = repository;
    }


    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Post post = repository.getPostById(id);
        post.setContent(content);
        post.setTitle(title);

        Post modified = repository.modify(post);

        return "redirect:/post.nhn?id="+ modified.getId();
    }
}
