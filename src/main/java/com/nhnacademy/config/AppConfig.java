package com.nhnacademy.config;

import com.nhnacademy.post.PostCrud;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.user.UserCrud;
import com.nhnacademy.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    PostCrud postRepository(){
        return new PostRepository();
    }

    @Bean
    UserCrud userRepository(){
        return new UserRepository();
    }
}
