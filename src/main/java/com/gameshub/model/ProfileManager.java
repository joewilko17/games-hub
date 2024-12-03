package com.gameshub.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileManager {
    private static ProfileManager instance;
    private List<Profile> profiles;
    private JSONProfileStore profileStore;
    private Profile activeProfile;

    private ProfileManager() {
        profileStore = new JSONProfileStore();
        profiles = profileStore.loadProfiles();
        if (profiles == null) {
            profiles = new ArrayList<>();
        }
    }

    public static ProfileManager getInstance() {
        if (instance == null) {
            instance = new ProfileManager();
        }
        return instance;
    }

    public Profile getProfileByUsername(String username) {
        return profiles.stream()
                .filter(profile -> profile.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public void addProfile(Profile profile) {
        profiles.add(profile);
        System.out.println("Added profile: " + profile.getUsername());
        profileStore.saveProfiles(profiles);
    }

    public Profile getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(Profile activeProfile) {
        this.activeProfile = activeProfile;
    }

    public void clearActiveProfile() {
        this.activeProfile = null;
    }
}
