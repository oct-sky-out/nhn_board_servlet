package com.nhnacademy.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserCrudTest {
    UserCrud userCrud;

    @BeforeEach
    void setUp() {
        userCrud = UserRepository.INSTANCE;
    }

    @Test
    @DisplayName("유저를 추가한다.")
    void add() {
        User user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser).isEqualTo(user);
    }

    @Test
    @DisplayName("유저의 정보 변경 발생시 반영한다.")
    void modify() {
        User user = new User("mock1", "1234", "mock", null);
        String newPassword = "new password";
        byte[] newImage = {1,2};

        userCrud.add(user);

        user.setPassword(newPassword);
        user.setImage(newImage);
        User modifiedUser = userCrud.modify(user);

        assertThat(modifiedUser.getPassword()).isEqualTo(newPassword);
        assertThat(modifiedUser.getImage()).isEqualTo(newImage);
    }

    @Test
    void remove() {
        fail("미구현");
    }

    @Test
    @DisplayName("유저를 유저 id를 기반으로하여 검색한다.")
    void getUserById() {
        User user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser.getId()).isEqualTo("1");
    }

    @Test
    void getAllUser() {
        fail("미구현");
    }

    private User makeUserMock() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("1");
        userCrud.add(user);
        return user;
    }

}
