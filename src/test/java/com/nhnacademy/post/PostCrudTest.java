package com.nhnacademy.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostCrudTest {
    PostCrud postCrud;
    Post post;

    @BeforeEach
    void setUp() {
        postCrud = PostRepository.INSTANCE;
        post = mock(Post.class);
    }

    @Test
    void register() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);
        verify(post, times(2)).getId();
    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);

        Post removedPost = postCrud.remove(post.getId());
        assertThat(removedPost).isEqualTo(this.post);

        verify(post, times(3)).getId();


    }

    @Test
    void getPostById() {
    }

    @Test
    void getPosts() {
    }
}
