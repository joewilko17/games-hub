package com.gameshub.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SetupScene extends BaseScene {

    private VBox containerVBox;
    private VBox setupConfigContainerVBox;
    private VBox setupDifficultyContainerVBox;
    private HBox buttonsContainerHBox;
    private Label setupMenuLabel;
    private Label chooseDifficultyLabel;
    private ToggleGroup difficultyGroup;
    private RadioButton easyRadioButton;
    private RadioButton mediumRadioButton;
    private RadioButton hardRadioButton;
    private Button backButton;
    private Button startButton;
    
    public SetupScene(Stage stage) {

        super(stage);
        initialiseSceneUI();

    }

    @Override
    public void initialiseSceneUI() {

        containerVBox = new VBox(20);
        setupConfigContainerVBox = new VBox(10);
        setupDifficultyContainerVBox = new VBox();
        buttonsContainerHBox = new HBox(20);
        setupMenuLabel = new Label();
        chooseDifficultyLabel = new Label();
        difficultyGroup = new ToggleGroup();
        easyRadioButton = new RadioButton();
        mediumRadioButton = new RadioButton();
        hardRadioButton = new RadioButton();
        backButton = new Button();
        startButton = new Button();

        containerVBox.getStyleClass().add("setup-container");
        containerVBox.setMaxSize(800, 450);
        containerVBox.setAlignment(Pos.CENTER);

        setupConfigContainerVBox.getStyleClass().add("setup-config-container");
        setupConfigContainerVBox.setMaxSize(380, 300);
        setupConfigContainerVBox.setAlignment(Pos.CENTER);
        VBox.setMargin(setupDifficultyContainerVBox, new Insets(20));
        
        buttonsContainerHBox.setAlignment(Pos.CENTER);

        setupMenuLabel.setText("Setup Game");
        setupMenuLabel.getStyleClass().add("setup-title-label");

        VBox.setMargin(chooseDifficultyLabel, new Insets(0,0,5,0));
        chooseDifficultyLabel.setText("Choose a difficulty:");
        chooseDifficultyLabel.getStyleClass().add("setup-subtitle-label");

        VBox.setMargin(easyRadioButton, new Insets(0,0,3,0));
        easyRadioButton.setText("easy");
        easyRadioButton.getStyleClass().add("setup-radio-button");
        easyRadioButton.setToggleGroup(difficultyGroup);
        easyRadioButton.setSelected(true);

        VBox.setMargin(mediumRadioButton, new Insets(0,0,3,0));
        mediumRadioButton.setText("medium");
        mediumRadioButton.getStyleClass().add("setup-radio-button");
        mediumRadioButton.setToggleGroup(difficultyGroup);

        //VBox.setMargin(hardRadioButton, new Insets(0,0,5,0));
        hardRadioButton.setText("hard");
        hardRadioButton.getStyleClass().add("setup-radio-button");
        hardRadioButton.setToggleGroup(difficultyGroup);

        backButton.getStyleClass().add("menu-button");
        backButton.setText("Back");
        backButton.setPrefSize(180, 27);

        startButton.getStyleClass().add("menu-button");
        startButton.setText("Start");
        startButton.setPrefSize(180, 27);

        setupDifficultyContainerVBox.getChildren().addAll(chooseDifficultyLabel,easyRadioButton,mediumRadioButton,hardRadioButton);
        setupConfigContainerVBox.getChildren().addAll(setupMenuLabel, setupDifficultyContainerVBox);
        buttonsContainerHBox.getChildren().addAll(backButton, startButton);
        containerVBox.getChildren().addAll(setupConfigContainerVBox,buttonsContainerHBox);
        getcontentBorderPane().setCenter(containerVBox);
    }

}
