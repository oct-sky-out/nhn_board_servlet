package com.nhnacademy.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    @DisplayName("글을 등록하게되면 Map의 요소가 하나 추가된다.")
    void register() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);
        assertThat(postCrud.getPosts()).hasSize(1);
        verify(post, times(2)).getId();
    }

    @Test
    @DisplayName("글을 삭제하면 Map의 요소가 하나 지워진다..")
    void remove() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);

        Post removedPost = postCrud.remove(1L);
        assertThat(removedPost).isEqualTo(this.post);
        assertThat(postCrud.getPosts()).isEmpty();
        verify(post, times(2)).getId();
    }

    @Test
    @DisplayName("글의 id값을 이용하여 게시물을 찾아온다.")
    void getPostById() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);

        assertThat(postCrud.getPostById(post.getId())).isEqualTo(post);
        verify(post, times(3)).getId();
    }

    @Test
    @DisplayName("모든 게시물을 조회한다.")
    void getPosts() {
        when(post.getId()).thenReturn(1L);
        assertThat(postCrud.register(post)).isEqualTo(1L);

        assertThat(postCrud.getPosts()).hasSize(1);
        verify(post, times(2)).getId();
    }

    @Nested
    class NotMockingTest{
        @Test
        void modify() {
            Post post1 = new Post();
            post1.setId(1L);
            post1.setTitle("게시글1");
            post1.setAuthor("mockUser");
            post1.setContent("mock content");
            post1.setWriteTime(LocalDateTime.now());

            postCrud.register(post1);

            post1.setContent("mock content modified");

            assertThat(postCrud.modify(post1)).isEqualTo(post1);
            assertThat(postCrud.modify(post1).getContent())
                .isNotEqualTo("mock content");
        }
    }
}
