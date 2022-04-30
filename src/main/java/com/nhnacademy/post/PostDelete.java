package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDelete implements Communicable {
    private final PostRepository repository;

    public PostDelete(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        // TODO : 현재 로그인 된 유저의 동일한 글인지 확인 필요
        repository.remove(id);
        return "/posts.nhn";
    }
}
