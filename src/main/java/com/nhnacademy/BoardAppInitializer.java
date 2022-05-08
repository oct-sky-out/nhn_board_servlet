package com.nhnacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.exception.IntilaizeFailureException;
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
import org.springframework.beans.factory.BeanCreationException;
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
            setController(context, servletContext);

            AdminUser adminUser = new AdminUser("admin", "1234", "관리자", null, true);
            userRepository.add(adminUser);

            servletContext.setAttribute("admin", adminUser);
            servletContext.setAttribute("logged", 0);
            servletContext.setAttribute("userRepository", userRepository);
            servletContext.setAttribute("postRepository", postRepository);
        } catch (BeanCreationException e) {
            throw new BeanCreationException("빈 생성에 실패했습니다.");
        } catch (Exception e) {
            throw new IntilaizeFailureException("서블릿 초기화에 실패했습니다." +
                "message : " + e);
        }
    }

    private void setController(AnnotationConfigApplicationContext context,
                               ServletContext servletContext) {
        servletContext.setAttribute("userGet", context.getBean("userGet", Communicable.class));
        servletContext.setAttribute("userPost", context.getBean("userPost", Communicable.class));
        servletContext.setAttribute("userPut", context.getBean("userPut", Communicable.class));
        servletContext.setAttribute("userDelete", context.getBean("userDelete", Communicable.class));
        servletContext.setAttribute("userListGet", context.getBean("userListGet", Communicable.class));
        servletContext.setAttribute("postGet", context.getBean("postGet", Communicable.class));
        servletContext.setAttribute("postPost", context.getBean("postPost", Communicable.class));
        servletContext.setAttribute("postPut", context.getBean("postPut", Communicable.class));
        servletContext.setAttribute("postDelete", context.getBean("postDelete", Communicable.class));
        servletContext.setAttribute("postListGet", context.getBean("postListGet", Communicable.class));
        servletContext.setAttribute("loginPost", context.getBean("loginPost", Communicable.class));
        servletContext.setAttribute("logout", context.getBean("logout", Communicable.class));
        servletContext.setAttribute("profileGet", context.getBean("profileGet", Communicable.class));
        servletContext.setAttribute("profilePost", context.getBean("profilePost", Communicable.class));
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
