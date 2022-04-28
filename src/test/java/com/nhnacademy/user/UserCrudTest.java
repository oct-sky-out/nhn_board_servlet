package com.nhnacademy.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserCrudTest {
    UserCrud userCrud;

    @BeforeEach
    void setUp() {
        userCrud = UserRepository.INSTANCE;
    }

    @Test
    void add() {
        User user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser).isEqualTo(user);
    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }

    @Test
    void getUserById() {
        User user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser.getId()).isEqualTo("1");
    }

    @Test
    void getAllUser() {
    }

    private User makeUserMock() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("1");
        userCrud.add(user);
        return user;
    }

}
