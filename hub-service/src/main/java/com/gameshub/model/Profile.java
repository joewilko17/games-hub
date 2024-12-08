package com.gameshub.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    private String username;
    private String passwordHash;
    private String avatarBase64;
    private String preferences;

    @JsonCreator
    public Profile(@JsonProperty("username") String username,
            @JsonProperty("passwordHash") String passwordHash,
            @JsonProperty("avatarBase64") String avatarBase64,
            @JsonProperty("preferences") String preferences) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.avatarBase64 = avatarBase64;
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

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatar) {
        this.avatarBase64 = avatar;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "Profile: {username='" + username + "', passwordHash='" + passwordHash + "', avatar='" + avatarBase64
                + "', preferences='" + preferences + "'}";
    }
}
