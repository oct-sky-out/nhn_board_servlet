package com.nhnacademy.config;

import com.nhnacademy.commnicate.Communicable;
import com.nhnacademy.post.PostCrud;
import com.nhnacademy.post.PostDelete;
import com.nhnacademy.post.PostGet;
import com.nhnacademy.post.PostListGet;
import com.nhnacademy.post.PostPost;
import com.nhnacademy.post.PostPut;
import com.nhnacademy.post.PostRepository;
import com.nhnacademy.profile.ProfileGet;
import com.nhnacademy.profile.ProfilePost;
import com.nhnacademy.user.LoginPost;
import com.nhnacademy.user.Logout;
import com.nhnacademy.user.UserCrud;
import com.nhnacademy.user.UserDelete;
import com.nhnacademy.user.UserGet;
import com.nhnacademy.user.UserListGet;
import com.nhnacademy.user.UserPost;
import com.nhnacademy.user.UserPut;
import com.nhnacademy.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PostCrud postRepository(){
        return new PostRepository();
    }

    @Bean
    public UserCrud userRepository(){
        return new UserRepository();
    }

    @Bean
    public Communicable userGet(UserCrud userCrud){
        return new UserGet(userCrud);
    }

    @Bean
    public Communicable userPost(UserCrud userCrud){
        return new UserPost(userCrud);
    }

    @Bean
    public Communicable userPut(UserCrud userCrud){
        return new UserPut(userCrud);
    }

    @Bean
    public Communicable userDelete(UserCrud userCrud){
        return new UserDelete(userCrud);
    }

    @Bean
    public Communicable userListGet(UserCrud userCrud){
        return new UserListGet(userCrud);
    }

    @Bean
    public Communicable postGet(PostCrud postCrud){
        return new PostGet(postCrud);
    }

    @Bean
    public Communicable postPost(PostCrud postCrud){
        return new PostPost(postCrud);
    }

    @Bean
    public Communicable postPut(PostCrud postCrud){
        return new PostPut(postCrud);
    }

    @Bean
    public Communicable postDelete(PostCrud postCrud){
        return new PostDelete(postCrud);
    }

    @Bean
    public Communicable postListGet(PostCrud postCrud){
        return new PostListGet(postCrud);
    }

    @Bean
    public Communicable loginPost(UserCrud userCrud){
        return new LoginPost(userCrud);
    }

    @Bean
    public Communicable logout(){
        return new Logout();
    }

    @Bean
    public Communicable profileGet(){
        return new ProfileGet();
    }

    @Bean
    public Communicable profilePost(){
        return new ProfilePost();
    }
}
