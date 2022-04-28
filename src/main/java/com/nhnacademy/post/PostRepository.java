package com.nhnacademy.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PostRepository implements PostCrud {
    public static final PostRepository INSTANCE = new PostRepository();
    private Map<Long, Post> postMap = new HashMap<>();

    private PostRepository() {
    }

    @Override
    public long register(Post post) {
        postMap.put(post.getId(), post);
        return post.getId();
    }

    @Override
    public void modify(Post post) {

    }

    @Override
    public Post remove(long id) {
        return null;
    }

    @Override
    public Post getPostById(long id) {
        return null;
    }

    @Override
    public List<Post> getPosts() {
        return null;
    }
}
