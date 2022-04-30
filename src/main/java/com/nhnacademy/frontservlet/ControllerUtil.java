package com.nhnacademy.frontservlet;

import static com.nhnacademy.frontservlet.ControllerProtocol.DELETE;
import static com.nhnacademy.frontservlet.ControllerProtocol.GET;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST_LIST_PATH;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST_PATH;
import static com.nhnacademy.frontservlet.ControllerProtocol.PUT;
import static com.nhnacademy.frontservlet.ControllerProtocol.USER_LIST_PATH;
import static com.nhnacademy.frontservlet.ControllerProtocol.USER_PATH;
import static com.nhnacademy.frontservlet.ControllerProtocol.generate;

import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.post.PostDelete;
import com.nhnacademy.post.PostGet;
import com.nhnacademy.post.PostListGet;
import com.nhnacademy.post.PostPost;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.user.UserDelete;
import com.nhnacademy.user.UserGet;
import com.nhnacademy.user.UserListGet;
import com.nhnacademy.user.UserPost;
import com.nhnacademy.user.UserPut;
import com.nhnacademy.user.UserRepository;
import java.util.Arrays;
import java.util.Objects;

public enum ControllerUtil implements ControllerConstructable {
    USER_GET(generate(USER_PATH, GET)){
        @Override
        public Communicable construct() {
            return new UserGet(UserRepository.INSTANCE);
        }
    },
    USER_POST(generate(USER_PATH, POST)){
        @Override
        public Communicable construct() {
            return new UserPost(UserRepository.INSTANCE);
        }
    },
    USER_PUT(generate(USER_PATH, PUT)){
        @Override
        public Communicable construct() {
            return new UserPut(UserRepository.INSTANCE);
        }
    },
    USER_DELETE(generate(USER_PATH, DELETE)){
        @Override
        public Communicable construct() {
            return new UserDelete(UserRepository.INSTANCE);
        }
    },
    USER_LIST_GET(generate(USER_LIST_PATH, GET)){
        @Override
        public Communicable construct() {
            return new UserListGet(UserRepository.INSTANCE);
        }
    },
    POST_GET(generate(POST_PATH, GET)){
        @Override
        public Communicable construct() {
            return new PostGet(PostRepository.INSTANCE);
        }
    },
    POST_POST(generate(POST_PATH, POST)){
        @Override
        public Communicable construct() {
            return new PostPost(PostRepository.INSTANCE);
        }
    },
    POST_PUT(generate(POST_PATH, PUT)){
        @Override
        public Communicable construct() {
            return new PostPost(PostRepository.INSTANCE);
        }
    },
    POST_DELETE(generate(POST_PATH, DELETE)){
        @Override
        public Communicable construct() {
            return new PostDelete(PostRepository.INSTANCE);
        }
    },
    POST_LIST_GET(generate(POST_LIST_PATH, GET)){
        @Override
        public Communicable construct() {
            return new PostListGet(PostRepository.INSTANCE);
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
