package com.nhnacademy.user;

public class AdminUser extends User{
    private final boolean isAdmin;

    public AdminUser(String id, String password, String name, byte[] image, boolean isAdmin) {
        super(id, password, name, image);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
