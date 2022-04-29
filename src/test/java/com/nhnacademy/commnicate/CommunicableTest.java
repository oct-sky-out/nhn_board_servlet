package com.nhnacademy.commnicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.post.Post;
import com.nhnacademy.post.PostPost;
import com.nhnacademy.post.PostRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommunicableTest {
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
        PostRepository repository =  mock(PostRepository.class);
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


}
