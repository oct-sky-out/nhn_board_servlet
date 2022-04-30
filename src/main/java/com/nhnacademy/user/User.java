package com.nhnacademy.user;

public class User {
    private String id;
    private String password;
    private String name;
    private String profileName;

    public User(String id, String password, String name, String profileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileName = profileName;
    }

    public String getId() {
        return this.id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getProfileName() {
        return this.profileName;
    }
}
