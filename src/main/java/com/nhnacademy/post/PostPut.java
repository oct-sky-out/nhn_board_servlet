package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostPut implements Communicable {
    private final PostRepository repository;

    public PostPut(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        // TODO : 유저의 이름이 같으면 수정권한을 줌.

        Post post = repository.getPostById(id);
        post.setContent(content);
        post.setTitle(title);

        Post modified = repository.modify(post);

        return "/posts?id="+ modified.getId() +".nhn";
    }
}
