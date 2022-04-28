package com.nhnacademy.user;

public class User {
    private String id;
    private String password;
    private String name;
    private byte[] image;

    public User(String id, String password, String name, byte[] image) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPassword() {
        return this.password;
    }

    public byte[] getImage() {
        return this.image;
    }
}
