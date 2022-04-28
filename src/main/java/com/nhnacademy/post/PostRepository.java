package com.nhnacademy.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Post remove(Post post) {
        return this.postMap.remove(post.getId());
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
