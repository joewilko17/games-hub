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
        System.out.println(profiles);
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

    public void updateProfile(Profile profile) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equalsIgnoreCase(profile.getUsername())) {
                profiles.set(i, profile);
                System.out.println("Updated profile: " + profile.getUsername());
                profileStore.saveProfiles(profiles);
                return;
            }
        }
        System.out.println("Profile not found: " + profile.getUsername());
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
