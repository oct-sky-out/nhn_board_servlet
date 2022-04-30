package com.nhnacademy;

import com.nhnacademy.user.AdminUser;
import com.nhnacademy.user.UserRepository;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class BoardAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        AdminUser adminUser = new AdminUser("admin", "1234", "관리자", null, true);
        servletContext.setAttribute("admin", adminUser);
        servletContext.setAttribute("logged", 0);
        UserRepository.INSTANCE.add(adminUser);
    }
}
