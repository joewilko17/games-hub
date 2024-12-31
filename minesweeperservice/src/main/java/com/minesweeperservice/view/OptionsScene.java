package com.minesweeperservice.view;

import com.minesweeperservice.controller.UIEventHelper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class OptionsScene extends BaseScene {

    private VBox containerVBox;
    private VBox optionsMenuContainerVBox;
    private VBox optionsMenuItemsVBox;
    private HBox buttonsContainerHBox;
    private Label validationLabel;
    private Label optionsMenuLabel;
    private CheckBox showHintsCheckBox;
    private Button backButton;
    private Button saveChangesButton;


    public OptionsScene(Stage stage) {

        super(stage);
        initialiseSceneUI();
        initialiseControlEvents(stage);
    }

    @Override
    public void initialiseSceneUI() {
        containerVBox = new VBox(20);
        optionsMenuContainerVBox = new VBox(10);
        optionsMenuItemsVBox = new VBox();
        buttonsContainerHBox = new HBox(20);
        validationLabel = new Label("Your changes have been saved");
        optionsMenuLabel = new Label("Options");
        showHintsCheckBox = new CheckBox("Show Hints");
        backButton = new Button("Back");
        saveChangesButton = new Button("Save Changes");

        containerVBox.getStyleClass().add("options-container");
        containerVBox.setMaxSize(700, 460);
        containerVBox.setAlignment(Pos.CENTER);

        optionsMenuContainerVBox.getStyleClass().add("options-menu-container");
        optionsMenuContainerVBox.setMaxSize(380,300);
        optionsMenuContainerVBox.setAlignment(Pos.CENTER);
        VBox.setMargin(optionsMenuItemsVBox, new Insets(20));

        buttonsContainerHBox.setAlignment(Pos.CENTER);

        validationLabel.getStyleClass().add("validation-label");
        validationLabel.setVisible(false);

        optionsMenuLabel.getStyleClass().add("options-title-label");

        if(profileManager.getPlayer().isShowHints()) {
            showHintsCheckBox.setSelected(true);
        } else {
            showHintsCheckBox.setSelected(false);
        }
        showHintsCheckBox.getStyleClass().add("options-hints-checkbox");

        backButton.getStyleClass().add("menu-button");
        backButton.setPrefSize(180, 27);

        saveChangesButton.getStyleClass().add("menu-button");
        saveChangesButton.setPrefSize(180,27);

        optionsMenuItemsVBox.getChildren().add(showHintsCheckBox);
        optionsMenuContainerVBox.getChildren().addAll(optionsMenuLabel,optionsMenuItemsVBox);
        buttonsContainerHBox.getChildren().addAll(backButton,saveChangesButton);
        containerVBox.getChildren().addAll(validationLabel,optionsMenuContainerVBox,buttonsContainerHBox);
        getcontentBorderPane().setCenter(containerVBox);
    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
       backButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("SplashScene"))); 
       saveChangesButton.setOnAction(_ -> saveOptionChanges());
    }

    private void saveOptionChanges() {
        boolean showHintsChange = showHintsCheckBox.isSelected();
        System.out.println("Is checkbox selected: "  + showHintsCheckBox.isSelected());
        profileManager.getPlayer().setShowHints(showHintsChange);
        validationLabel.setVisible(true);
    }
    
}
