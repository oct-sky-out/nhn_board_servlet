package com.nhnacademy.frontservlet;

import static com.nhnacademy.frontservlet.ControllerProtocol.GET;
import static com.nhnacademy.frontservlet.ControllerProtocol.POST;
import static com.nhnacademy.frontservlet.ControllerProtocol.generate;

import com.nhnacademy.commnicate.Communicable;
import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ControllerUtil implements ControllerConstructable {
    USER_GET(generate("/user.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("userGet");
        }
    },
    USER_POST(generate("/user.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("userPost");
        }
    },
    USER_PUT(generate("/user-modify.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("userPut");
        }
    },
    USER_DELETE(generate("/user-delete.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("userDelete");
        }
    },
    USER_LIST_GET(generate("/users.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("userListGet");
        }
    },
    POST_GET(generate("/post.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("postGet");
        }
    },
    POST_POST(generate("/post.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("postPost");
        }
    },
    POST_PUT(generate("/post-modify.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("postPut");
        }
    },
    POST_DELETE(generate("/post-delete.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("postDelete");
        }
    },
    POST_LIST_GET(generate("/posts.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("postListGet");
        }
    },
    LOGIN_POST(generate("/login.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("loginPost");
        }
    },
    LOGOUT_GET(generate("/logout.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("logout");
        }
    },
    PROFILE_GET(generate("/profile.nhn", GET)) {
        @Override
        public Communicable construct() {
            return createController("profileGet");
        }
    },
    PROFILE_POST(generate("/profile.nhn", POST)) {
        @Override
        public Communicable construct() {
            return createController("profilePost");
        }
    };


    private final ControllerProtocol protocol;
    private static HttpServletRequest request;

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

    private static Communicable createController(String beanName){
        return (Communicable) request.getServletContext().getAttribute(beanName);
    }

    public static void setRequest(HttpServletRequest request) {
        ControllerUtil.request = request;
    }
}
