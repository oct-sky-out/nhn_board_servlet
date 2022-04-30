package com.nhnacademy.filter;


import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isWhiteList(request)) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/loginForm.jsp");
                rd.forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(HttpServletRequest request) {
        return !request.getRequestURI().equals("/changeLang.nhn") &&
            !request.getRequestURI().equals("/logout.nhn") &&
            !request.getRequestURI().equals("/logout.jsp") &&
            !request.getRequestURI().equals("/index.jsp") &&
            !request.getRequestURI().equals("/posts.nhn") &&
            !request.getRequestURI().equals("/loginForm.jsp") &&
            !request.getRequestURI().contains("/img") &&
            !request.getRequestURI().equals("/login.nhn");
    }
}
