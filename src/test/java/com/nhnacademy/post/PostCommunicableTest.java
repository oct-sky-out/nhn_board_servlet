package com.nhnacademy.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
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
    PostRepository repository;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        repository = mock(PostRepository.class);
    }

    @Test
    @DisplayName("게시글 등록 후 등록된 게시글 화면으로 가는가?")
    void postPostTest() {
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
        api = new PostPut(repository);
        Post modifiedPost = new Post();
        modifiedPost.setId(2L);
        modifiedPost.setTitle("before");
        modifiedPost.setContent("before content");
        modifiedPost.setAuthor("author1");


        // 1. 파라미터로 id가 들어옴// 2. 수정한 내용이 같이 들어온다
        when(req.getParameter("id")).thenReturn("" + modifiedPost.getId());
        when(req.getParameter("content")).thenReturn("modified title");
        when(req.getParameter("title")).thenReturn("modified content text");

        when(repository.getPostById(2)).thenReturn(modifiedPost);
        when(repository.modify(modifiedPost)).thenReturn(modifiedPost);

        assertThat(api.communicate(req, res)).isEqualTo("/post.nhn?id=2");
    }

    @Test
    @DisplayName("게시글 삭제시 삭제가 완료되고, 메인 페이지로 이동해야한다.")
    void postDeleteTest() {
        api = new PostDelete(repository);
        Post deleteTarget = mock(Post.class);

        when(req.getParameter("id")).thenReturn("1");
        when(repository.remove(1L)).thenReturn(deleteTarget);

        assertThat(api.communicate(req, res)).isEqualTo("/posts.nhn");
    }

    @Test
    @DisplayName("전체 게시판을 조회한다.")
    void postsInquireTest() {
        api = new PostListGet(repository);
        assertThat(api.communicate(req, res)).isEqualTo("index.jsp");
    }

    @Test
    @DisplayName("게시글 하나를 조회한다.")
    void onePostInquireTest() {
        api = new PostGet(repository);
        Post post = new Post();
        post.setId(2);

        when(req.getParameter("id")).thenReturn("2");
        when(repository.getPostById(2)).thenReturn(post);

        assertThat(api.communicate(req, res)).isEqualTo("/post.jsp?id=2");
    }
}
