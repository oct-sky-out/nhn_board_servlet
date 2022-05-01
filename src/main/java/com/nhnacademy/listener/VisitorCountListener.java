package com.nhnacademy.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.user.User;
import com.nhnacademy.user.UserRepository;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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

        storeUsers();
        storedPosts();
    }

    private void storedPosts() {
        try (OutputStream out = new FileOutputStream(
            VisitorCountListener.class.getClassLoader().getResource("/users/users.json").getPath())) {
            ObjectMapper mapper = new ObjectMapper();
            User admin  = UserRepository.INSTANCE.getUserById("admin");
            UserRepository.INSTANCE.remove(admin);
            mapper.writerWithDefaultPrettyPrinter()
                .writeValue(out, UserRepository.INSTANCE.getAllUser());
        } catch (IOException e) {
            log.error("", e);
        }
    }

    private void storeUsers() {
        try (OutputStream out = new FileOutputStream(
            VisitorCountListener.class.getClassLoader().getResource("/posts/posts.json").getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter().writeValue(out, PostRepository.INSTANCE.getPosts());
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
