package com.nhnacademy.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository implements PostCrud {
    public static final PostRepository INSTANCE = new PostRepository();
    private final Map<Long, Post> postMap = new HashMap<>();

    private PostRepository() {
    }

    @Override
    public long register(Post post) {
        postMap.put(post.getId(), post);
        return post.getId();
    }

    @Override
    public Post modify(Post post) {
        return postMap.replace(post.getId(), post);
    }

    @Override
    public Post remove(long id) {
        return this.postMap.remove(id);
    }

    @Override
    public Post getPostById(long id) {
        return this.postMap.get(id);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(this.postMap.values());
    }
}
