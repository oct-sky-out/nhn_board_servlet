package com.nhnacademy.frontservlet;

import static com.nhnacademy.frontservlet.ControllerProtocol.GET;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST;
import static com.nhnacademy.frontservlet.ControllerProtocol.generate;

import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.post.PostCrud;
import com.nhnacademy.post.PostDelete;
import com.nhnacademy.post.PostGet;
import com.nhnacademy.post.PostListGet;
import com.nhnacademy.post.PostPost;
import com.nhnacademy.post.PostPut;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.profile.ProfileGet;
import com.nhnacademy.profile.ProfilePost;
import com.nhnacademy.user.LoginPost;
import com.nhnacademy.user.Logout;
import com.nhnacademy.user.UserCrud;
import com.nhnacademy.user.UserDelete;
import com.nhnacademy.user.UserGet;
import com.nhnacademy.user.UserListGet;
import com.nhnacademy.user.UserPost;
import com.nhnacademy.user.UserPut;
import com.nhnacademy.user.UserRepository;
import java.util.Arrays;
import java.util.Objects;

public enum ControllerUtil implements ControllerConstructable {
    USER_GET(generate("/user.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new UserGet(userRepository);
        }
    },
    USER_POST(generate("/user.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new UserPost(userRepository);
        }
    },
    USER_PUT(generate("/user-modify.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new UserPut(userRepository);
        }
    },
    USER_DELETE(generate("/user-delete.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new UserDelete(userRepository);
        }
    },
    USER_LIST_GET(generate("/users.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new UserListGet(userRepository);
        }
    },
    POST_GET(generate("/post.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new PostGet(postRepository);
        }
    },
    POST_POST(generate("/post.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new PostPost(postRepository);
        }
    },
    POST_PUT(generate("/post-modify.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new PostPut(postRepository);
        }
    },
    POST_DELETE(generate("/post-delete.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new PostDelete(postRepository);
        }
    },
    POST_LIST_GET(generate("/posts.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new PostListGet(postRepository);
        }
    },
    LOGIN_POST(generate("/login.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new LoginPost(userRepository);
        }
    },
    LOGOUT_GET(generate("/logout.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new Logout();
        }
    },
    PROFILE_GET(generate("/profile.nhn", GET)) {
        @Override
        public Communicable construct() {
            return new ProfileGet();
        }
    },
    PROFILE_POST(generate("/profile.nhn", POST)) {
        @Override
        public Communicable construct() {
            return new ProfilePost();
        }
    };


    private static UserCrud userRepository;
    private static PostCrud postRepository;
    private final ControllerProtocol protocol;

    ControllerUtil(ControllerProtocol protocol) {
        this.protocol = protocol;
    }

    public static Communicable getControllerByProtocol(ControllerProtocol protocol) {
        ControllerUtil matchController = Arrays.stream(ControllerUtil.values())
            .filter(controllerGenerator -> controllerGenerator.protocol.equals(protocol))
            .findFirst()
            .orElse(null);

        if (Objects.nonNull(matchController)) {
            return matchController.construct();
        }

        throw new NotMatchUrlException("페이지가 존재하지 않습니다.");
    }

    public static void setUserRepository(UserCrud userRepository) {
        ControllerUtil.userRepository = userRepository;
    }

    public static void setPostRepository(PostCrud postRepository) {
        ControllerUtil.postRepository = postRepository;
    }
}
