package com.gameshub.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javafx.scene.image.Image;

public class ImageConversionService {

    private static ImageConversionService instance;

    public static ImageConversionService getInstance() {
        if (instance == null) {
            instance = new ImageConversionService();
        }
        return instance;
    }

    // Method to convert a JPEG image to a Base64 String. Takes in imageURL (within
    // resources folder) as parameter
    public String convertToBase64(String imageURL) {
        try {
            // Use getClass().getResource() to correctly access the resource path
            System.out.println(imageURL);
            URL imageUrl = getClass().getResource(imageURL);
            System.out.println(imageUrl);
            if (imageUrl == null) {
                throw new FileNotFoundException("Image not found at: " + imageURL);
            }

            // Convert the Image object to Base64
            //Image image = new Image(imageUrl.toString()); // JavaFX Image
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] imageBytes = imageUrl.openStream().readAllBytes(); // Read the image as bytes
            byteArrayOutputStream.write(imageBytes);

            // Convert the byte array to Base64
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if image loading or conversion fails
        }
    }

    // Method to convert a Base64 String to a JavaFX image object
    public Image convertToImage(String base64String) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            return new Image(byteArrayInputStream);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
