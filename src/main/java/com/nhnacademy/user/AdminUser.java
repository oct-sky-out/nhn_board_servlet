package com.nhnacademy.user;

public class AdminUser extends User{
    private final boolean isAdmin;

    public AdminUser(String id, String password, String name, String profileName, boolean isAdmin) {
        super(id, password, name, profileName);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
