package com.gameshub.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    private String username;
    private String passwordHash;
    private String avatar;
    private String preferences;

    @JsonCreator
    public Profile(@JsonProperty("username") String username,
            @JsonProperty("passwordHash") String passwordHash,
            @JsonProperty("avatar") String avatar,
            @JsonProperty("preferences") String preferences) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.avatar = avatar;
        this.preferences = preferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "Profile{username='" + username + "', passwordHash='" + passwordHash + "', avatar='" + avatar
                + "', preferences='" + preferences + "'}";
    }
}
