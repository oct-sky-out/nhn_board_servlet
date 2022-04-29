package com.nhnacademy.post;

import com.nhnacademy.commnicate.Communicable;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostPost implements Communicable {
    private final PostRepository repository;

    public PostPost(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String author = req.getParameter("author");
        int postsCount = repository.getPosts().size();
        Post post = createPost(postsCount, title, content, author);

        long postId = repository.register(post);

        return "/posts?id="+postId+".nhn";
    }

    private Post createPost(int postsCount, String title, String content, String author) {
        Post post = new Post();
        post.setId(++postsCount);
        post.setWriteTime(LocalDateTime.now());
        post.setContent(content);
        post.setAuthor(author);
        post.setTitle(title);

        return post;
    }
}