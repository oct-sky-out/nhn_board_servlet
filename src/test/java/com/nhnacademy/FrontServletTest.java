package com.nhnacademy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.function.BiConsumer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrontServletTest {
    FrontServlet frontServlet = new FrontServlet();
    HttpServletRequest req;
    HttpServletResponse res;

    @BeforeEach
    void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
    }

    @Test
    void serviceErrorPage() throws Exception {
        MyRequestDispatcher myRequestDispatcher = new MyRequestDispatcher();
        RequestDispatcher dispatcher = myRequestDispatcher.dispatcher;

        when(req.getRequestDispatcher("/error.jsp")).thenReturn(dispatcher);
        when(req.getServletPath()).thenThrow(new NullPointerException());

        frontServlet.service(req, res);

        verify(req, times(1)).getRequestDispatcher("/error.jsp");


    }
}

class MyRequestDispatcher{
    private HttpServletRequest req;
    private HttpServletResponse res;

    RequestDispatcher dispatcher = new RequestDispatcher() {
        @Override
        public void forward(ServletRequest servletRequest, ServletResponse servletResponse)
                throws ServletException, IOException {
            req = (HttpServletRequest) servletRequest;
            res = (HttpServletResponse) servletResponse;
        }

        @Override
        public void include(ServletRequest servletRequest, ServletResponse servletResponse)
                throws ServletException, IOException {
            req = (HttpServletRequest) servletRequest;
            res = (HttpServletResponse) servletResponse;
        }
    };

    void assertDispatcher(BiConsumer<HttpServletRequest, HttpServletResponse> assertAction){
        assertAction.accept(req, res);
    }
}
