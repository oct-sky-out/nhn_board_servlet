package com.nhnacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.post.Post;
import com.nhnacademy.post.PostCrud;
import com.nhnacademy.user.AdminUser;
import com.nhnacademy.user.User;
import com.nhnacademy.user.UserCrud;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class BoardAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            "com.nhnacademy")) {
            UserCrud userRepository = context.getBean("userRepository", UserCrud.class);
            PostCrud postRepository = context.getBean("postRepository", PostCrud.class);

            storedUsersRead(userRepository);
            storedPostsRead(postRepository);

            AdminUser adminUser = new AdminUser("admin", "1234", "관리자", null, true);
            userRepository.add(adminUser);

            servletContext.setAttribute("admin", adminUser);
            servletContext.setAttribute("logged", 0);
            servletContext.setAttribute("userRepository", userRepository);
            servletContext.setAttribute("postRepository", postRepository);

        }
    }

    private void storedUsersRead(UserCrud userRepository) {
        try (InputStream input = new FileInputStream(
            BoardAppInitializer.class.getClassLoader().getResource("/users/users.json")
                .getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            List<User> result = mapper
                .reader()
                .forType(new TypeReference<List<User>>() {
                })
                .readValue(input);

            result.forEach(userRepository::add);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    private void storedPostsRead(PostCrud postRepository) {
        try (InputStream input = new FileInputStream(
            BoardAppInitializer.class.getClassLoader().getResource("/posts/posts.json")
                .getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            List<Post> result = mapper
                .reader()
                .forType(new TypeReference<List<Post>>() {
                })
                .readValue(input);

            result.forEach(postRepository::register);
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
