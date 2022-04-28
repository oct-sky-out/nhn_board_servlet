package com.nhnacademy.post;

import java.util.List;

public interface PostCrud {
    long register(Post post);

    void modify(Post post);

    Post remove(long id);

    Post getPostById(long id);

    List<Post> getPosts();
}
