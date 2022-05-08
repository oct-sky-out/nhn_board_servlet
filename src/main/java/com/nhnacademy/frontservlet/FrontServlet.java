package com.nhnacademy.frontservlet;

import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.post.PostCrud;
import com.nhnacademy.user.UserCrud;
import com.nhnacademy.user.UserRepository;
import java.io.IOException;
import java.util.Objects;
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
    private static final String REDIRECT_TYPE = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding(ENCODE_TYPE);
        resp.setCharacterEncoding(ENCODE_TYPE);
        resp.setContentType(CONTENT_TYPE);
        try {
            String path = req.getServletPath();
            String method = req.getMethod();
            ControllerProtocol protocol = new ControllerProtocol(path, method);
            
            ControllerUtil.setRequest(req);
            Communicable controller = ControllerUtil.getControllerByProtocol(protocol);

            String view = controller.communicate(req, resp);

            if(Objects.isNull(view)){
                return;
            }

            if (view.startsWith(REDIRECT_TYPE)){
                resp.sendRedirect(view.substring(REDIRECT_TYPE.length()));
            } else if(view.startsWith("forward:")){
                req.getRequestDispatcher(view.substring("forward:".length())).forward(req, resp);
            } else {
                req.getRequestDispatcher(view).include(req, resp);
            }
        }catch (Exception e) {
            log.error("", e);
            req.setAttribute("errorMsg", e);
            req.getRequestDispatcher("/error.jsp")
                .forward(req, resp);
        }
    }
}
