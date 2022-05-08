package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class PostGet implements Communicable {
    private final PostCrud repository;

    @Autowired
    public PostGet(PostCrud repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String isModify = req.getParameter("modify");

        Post post = repository.getPostById(id);
        post.increaseViewCount();
        req.setAttribute("post", post);

        if(Objects.nonNull(isModify)){
            return "/post/modify.jsp?id=" + post.getId();
        }

        return "/post.jsp?id=" + post.getId();
    }
}
