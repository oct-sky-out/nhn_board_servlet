package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostListGet implements Communicable {
    private final PostCrud repository;

    public PostListGet(PostCrud repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        try{
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));
            int size = Integer.parseInt(req.getParameter("size"));
            Page<Post> postPagination = repository.getPagePosts(pageNo, size);

            req.setAttribute("postList", postPagination);
            return "/index.jsp";
        }catch (NumberFormatException e) {
            return "posts.nhn?pageNo=1&size=10";
        }
    }
}
