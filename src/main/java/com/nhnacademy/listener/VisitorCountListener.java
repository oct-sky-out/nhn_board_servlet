package com.nhnacademy.listener;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class VisitorCountListener implements ServletContextListener {
    private static final AtomicInteger visitorCounter = new AtomicInteger(0);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("visitor", visitorCounter.incrementAndGet());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("visitor", visitorCounter.decrementAndGet());
    }
}
