package com.nhnacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.post.Post;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.user.AdminUser;
import com.nhnacademy.user.User;
import com.nhnacademy.user.UserRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardAppInitializer implements ServletContainerInitializer {
    private final UserRepository userRepository = UserRepository.INSTANCE;
    private final PostRepository postRepository = PostRepository.INSTANCE;

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        storedUsersRead();
        storedPostsRead();

        AdminUser adminUser = new AdminUser("admin", "1234", "관리자", null, true);
        servletContext.setAttribute("admin", adminUser);
        servletContext.setAttribute("logged", 0);
        UserRepository.INSTANCE.add(adminUser);
    }

    private void storedUsersRead() {
        try (InputStream input = new FileInputStream(
            BoardAppInitializer.class.getClassLoader().getResource("/users/users.json").getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            List<User> result = mapper
                .reader()
                .forType(new TypeReference<List<User>>() {})
                .readValue(input);

            result.forEach(userRepository::add);

        } catch (IOException e) {
            log.error("", e);
        }
    }

    private void storedPostsRead() {
        try (InputStream input = new FileInputStream(
            BoardAppInitializer.class.getClassLoader().getResource("/posts/posts.json").getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            List<Post> result = mapper
                .reader()
                .forType(new TypeReference<List<Post>>() {})
                .readValue(input);

            result.forEach(postRepository::register);

        } catch (IOException e) {
            log.error("", e);
        }
    }
}
