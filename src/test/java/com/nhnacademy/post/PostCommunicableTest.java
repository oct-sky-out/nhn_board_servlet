package com.nhnacademy.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.commnicate.Communicable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCommunicableTest {
    Communicable api;
    HttpServletRequest req;
    HttpServletResponse res;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
    }

    @Test
    @DisplayName("게시글 등록 후 등록된 게시글 화면으로 가는가?")
    void postPostTest() {
        PostRepository repository = mock(PostRepository.class);
        api = new PostPost(repository);
        List<Post> postList = new ArrayList<>();
        postList.add(any());

        // 게시글 추가
        when(req.getParameter("title")).thenReturn("example");
        when(req.getParameter("content")).thenReturn("examP content");
        when(req.getParameter("author")).thenReturn("user1");

        when(repository.getPosts()).thenReturn(postList);
        when(repository.register(any())).thenReturn(2L);

        // 게시글 패이지로 이동
        assertThat(api.communicate(req, res)).isEqualTo("/posts?id=2.nhn");
    }

    @Test
    @DisplayName("게시글 수정시 수정이 반영되는가?")
    void postPutTest() {
        PostRepository repository = mock(PostRepository.class);
        api = new PostPut(repository);
        Post modifiedPost = getPost(2, "before", "before content", "author1");

        // 1. 파라미터로 id가 들어옴// 2. 수정한 내용이 같이 들어온다
        when(req.getParameter("id")).thenReturn("" + modifiedPost.getId());
        when(req.getParameter("content")).thenReturn("modified title");
        when(req.getParameter("title")).thenReturn("modified content text");

        when(repository.getPostById(2)).thenReturn(modifiedPost);
        when(repository.modify(modifiedPost)).thenReturn(modifiedPost);

        assertThat(api.communicate(req, res)).isEqualTo("/posts?id=2.nhn");
    }

    Post getPost(long id, String title, String content, String author) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        return post;
    }
}
