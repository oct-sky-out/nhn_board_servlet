package com.nhnacademy.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.commnicate.Communicable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCommunicableTest {
    Communicable api;
    HttpServletRequest req;
    HttpServletResponse res;
    UserRepository repository;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        repository = mock(UserRepository.class);
    }

    @Test
    @DisplayName("유저의 id값이 query param 으로 들어올 시 id를 통한 유저를 조회한다.")
    void userGetTest() {
        api = new UserGet(repository);
        String userId = "exampleID";
        when(req.getParameter("id")).thenReturn(userId);

        assertThat(api.communicate(req, res)).isEqualTo("/user.jsp?id=" + userId);
    }

    @Test
    @DisplayName("관리자가 모든 유저를 조회 할때 조회 후 /userList.jsp로 이동하는가?")
    void userListGetTest() {
        api = new UserListGet(repository);

        when(repository.getAllUser()).thenReturn(new ArrayList<>());

        assertThat(api.communicate(req, res)).isEqualTo("/userList.jsp");
    }
}
