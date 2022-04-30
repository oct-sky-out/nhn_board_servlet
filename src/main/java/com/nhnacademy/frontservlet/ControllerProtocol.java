package com.nhnacademy.frontservlet;

import java.util.Objects;

public class ControllerProtocol {
    static final String GET = "GET";
    static final String POST = "POST";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ControllerProtocol protocol = (ControllerProtocol) o;
        return path.equals(protocol.path) && method.equals(protocol.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }
}
