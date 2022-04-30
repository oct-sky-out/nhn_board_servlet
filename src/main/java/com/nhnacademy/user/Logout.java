package com.nhnacademy.user;

import com.nhnacademy.commnicate.Communicable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Communicable {
    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {

        return "/";
    }
}
