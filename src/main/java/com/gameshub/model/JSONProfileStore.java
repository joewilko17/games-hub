package com.gameshub.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;

public class JSONProfileStore {
    private static final String FILE_PATH = "src/main/java/com/gameshub/data/profiles.json";
    private ObjectMapper objectMapper;

    public JSONProfileStore() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<Profile> loadProfiles() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Profile.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveProfiles(List<Profile> profiles) {
        try {
            File file = new File(FILE_PATH);
            System.out.println("Saving profiles to file: " + file.getAbsolutePath());
            if (file.exists()) {
                System.out.println("File exists: " + file.length() + " bytes");
            }
            objectMapper.writeValue(file, profiles);
            System.out.println("Profiles saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}