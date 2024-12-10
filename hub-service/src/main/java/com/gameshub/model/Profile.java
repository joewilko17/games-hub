package com.gameshub.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    private String username;
    private String passwordHash;
    private String avatarImageURL;
    private Map<String, Object> preferences;

    @JsonCreator
    public Profile(@JsonProperty("username") String username,
            @JsonProperty("passwordHash") String passwordHash,
            @JsonProperty("avatarImageURL") String avatarImageURL,
            @JsonProperty("preferences") Map<String, Object> preferences) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.avatarImageURL = avatarImageURL;
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

    public String getAvatarImageURL() {
        return avatarImageURL;
    }

    public void setAvatarImageURL(String avatar) {
        this.avatarImageURL = avatar;
    }

    public Map<String, Object> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, Object> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "Profile: {username='" + username + "', passwordHash='" + passwordHash + "', avatar='" + avatarImageURL
                + "', preferences='" + preferences + "'}";
    }
}
