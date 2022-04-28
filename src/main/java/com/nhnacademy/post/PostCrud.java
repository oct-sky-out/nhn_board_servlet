package com.nhnacademy.post;

import java.util.List;

public interface PostCrud {
    long register(Post post);

    void modify(Post post);

    Post remove(Post post);

    Post getPostById(long id);

    List<Post> getPosts();
}
