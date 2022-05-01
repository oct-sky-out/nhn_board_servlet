package com.nhnacademy.commnicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Communicable {
    String communicate(HttpServletRequest req, HttpServletResponse resp);
}

