package com.nhnacademy.frontservlet;

public class ControllerProtocol {
    static final String USER_PATH = "user.nhn";
    static final String USER_LIST_PATH = "users.nhn";
    static final String POST_PATH = "post.nhn";
    static final String POST_LIST_PATH = "users.nhn";
    static final String GET = "GET";
    static final String POST = "POST";
    static final String PUT= "PUT";
    static final String DELETE = "DELETE";

    private final String path;
    private final String method;

    ControllerProtocol(String path, String method) {
        this.path = path;
        this.method = method;
    }

    public static ControllerProtocol generate(String path, String method) {
        return new ControllerProtocol(path, method);
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }
}
