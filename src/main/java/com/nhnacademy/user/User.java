package com.nhnacademy.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    private String id;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profileName")
    private String profileName;

    public User(String id, String password, String name, String profileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileName = profileName;
    }

    public User(){}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return name;
    }

    public String getProfileName() {
        return this.profileName;
    }
}
