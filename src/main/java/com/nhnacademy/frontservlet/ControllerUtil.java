package com.nhnacademy.frontservlet;

import static com.nhnacademy.frontservlet.ControllerProtocol.GET;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST;
import static com.nhnacademy.frontservlet.ControllerProtocol.generate;

import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.post.PostDelete;
import com.nhnacademy.post.PostGet;
import com.nhnacademy.post.PostListGet;
import com.nhnacademy.post.PostPost;
import com.nhnacademy.post.PostPut;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.user.LoginPost;
import com.nhnacademy.user.UserDelete;
import com.nhnacademy.user.UserGet;
import com.nhnacademy.user.UserListGet;
import com.nhnacademy.user.UserPost;
import com.nhnacademy.user.UserPut;
import com.nhnacademy.user.UserRepository;
import java.util.Arrays;
import java.util.Objects;

public enum ControllerUtil implements ControllerConstructable {
    USER_GET(generate("/user.nhn", GET)){
        @Override
        public Communicable construct() {
            return new UserGet(UserRepository.INSTANCE);
        }
    },
    USER_POST(generate("/user.nhn", POST)){
        @Override
        public Communicable construct() {
            return new UserPost(UserRepository.INSTANCE);
        }
    },
    USER_PUT(generate("/user-modify.nhn", POST)){
        @Override
        public Communicable construct() {
            return new UserPut(UserRepository.INSTANCE);
        }
    },
    USER_DELETE(generate("/user-delete.nhn", POST)){
        @Override
        public Communicable construct() {
            return new UserDelete(UserRepository.INSTANCE);
        }
    },
    USER_LIST_GET(generate("/users.nhn", GET)){
        @Override
        public Communicable construct() {
            return new UserListGet(UserRepository.INSTANCE);
        }
    },
    POST_GET(generate("/post.nhn", GET)){
        @Override
        public Communicable construct() {
            return new PostGet(PostRepository.INSTANCE);
        }
    },
    POST_POST(generate("/post.nhn", POST)){
        @Override
        public Communicable construct() {
            return new PostPost(PostRepository.INSTANCE);
        }
    },
    POST_PUT(generate("/post-modify.nhn", POST)){
        @Override
        public Communicable construct() {
            return new PostPut(PostRepository.INSTANCE);
        }
    },
    POST_DELETE(generate("/post-delete.nhn", POST)){
        @Override
        public Communicable construct() {
            return new PostDelete(PostRepository.INSTANCE);
        }
    },
    POST_LIST_GET(generate("/posts.nhn", GET)){
        @Override
        public Communicable construct() {
            return new PostListGet(PostRepository.INSTANCE);
        }
    },
    LOGIN_POST(generate("/login.nhn", POST)){
        @Override
        public Communicable construct() {
            return new LoginPost(UserRepository.INSTANCE);
        }
    };

    private final ControllerProtocol protocol;

    ControllerUtil(ControllerProtocol protocol) {
        this.protocol = protocol;
    }

    public static Communicable getControllerByProtocol(ControllerProtocol protocol){
        ControllerUtil matchController = Arrays.stream(ControllerUtil.values())
            .filter(controllerGenerator -> controllerGenerator.protocol.equals(protocol))
            .findFirst()
            .orElse(null);

        if(Objects.nonNull(matchController)){
            return matchController.construct();
        }

        throw new NotMatchUrlException("페이지가 존재하지 않습니다.");
    }
}
