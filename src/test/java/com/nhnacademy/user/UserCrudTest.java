package com.nhnacademy.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserCrudTest {
    UserCrud userCrud;
    User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        userCrud = UserRepository.INSTANCE;
    }

    @Test
    @DisplayName("유저를 추가한다.")
    void add() {
        user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser).isEqualTo(user);
    }


    @Test
    @DisplayName("특정 유저 1명을 삭제한다.")
    void remove() {
        user = makeUserMock();
        assertThat(this.userCrud.getAllUser()).hasSize(1);

        User removedUser = this.userCrud.remove(user);
        assertThat(this.userCrud.getAllUser()).isEmpty();
        assertThat(removedUser).isEqualTo(user);
    }

    @Test
    @DisplayName("유저를 유저 id를 기반으로하여 검색한다.")
    void getUserById() {
        user = makeUserMock();
        User sameUser = userCrud.getUserById(user.getId());
        assertThat(sameUser.getId()).isEqualTo("1");
    }

    @Test
    @DisplayName("모든 유저를 조회한다.")
    void getAllUser() {
        putManyUser();
        assertThat(this.userCrud.getAllUser()).hasSize(10);
    }

    private void putManyUser() {
        for(int i = 0; i < 10; i++){
            User user = mock(User.class);
            when(user.getId()).thenReturn("" + i);
            this.userCrud.add(user);
        }
    }

    private User makeUserMock() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("1");
        userCrud.add(user);
        return user;
    }

    @Nested
    class NotUseMockObjectTest{
        @Test
        @DisplayName("유저의 정보 변경 발생시 반영한다.")
        void modify() {
            User user = new User("mock", "1234", "example", null);
            String newPassword = "new password";
            byte[] newImage = {1,2};

            when(user.getId()).thenReturn("1");
            userCrud.add(user);

            user.setPassword(newPassword);
            user.setImage(newImage);
            User modifiedUser = userCrud.modify(user);

            assertThat(modifiedUser.getPassword()).isEqualTo(newPassword);
            assertThat(modifiedUser.getImage()).isEqualTo(newImage);
        }
    }
}
