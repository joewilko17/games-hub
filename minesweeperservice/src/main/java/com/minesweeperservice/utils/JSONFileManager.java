package com.minesweeperservice.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONFileManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File jsonFile;

    public JSONFileManager(String filePath) {
        this.jsonFile = new File(filePath);
    }

    public List<Map<String, Object>> readJson() throws Exception {
        if (!jsonFile.exists()) {
            throw new Exception("File not found: " + jsonFile.getAbsolutePath());
        }
        return objectMapper.readValue(jsonFile, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public void writeJson(List<Map<String, Object>> data) throws Exception {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, data);
    }
}
