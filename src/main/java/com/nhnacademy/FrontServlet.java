package com.nhnacademy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.nhn")
public class FrontServlet extends HttpServlet {
    private static final String ENCODE_TYPE = "UTF-8";
    private static final String CONTENT_TYPE = "text/html";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding(ENCODE_TYPE);
        resp.setCharacterEncoding(ENCODE_TYPE);
        resp.setContentType(CONTENT_TYPE);
        try {
            String path = req.getServletPath();

        }catch (Exception e) {
            log.error("", e);
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error.jsp")
                .forward(req, resp);
        }
    }
}
