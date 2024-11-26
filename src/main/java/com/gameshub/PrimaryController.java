package com.gameshub;

//import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
//import javafx.stage.Modality;
//import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    private Circle profileImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image(getClass().getResource("/com/gameshub/images/sample.jpg").toExternalForm());
        profileImage.setFill(new ImagePattern(img));
    }

}
