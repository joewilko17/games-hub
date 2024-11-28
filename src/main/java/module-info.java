module com.gameshub {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.gameshub to javafx.fxml;
    exports com.gameshub;
    exports com.gameshub.controller;
    opens com.gameshub.controller to javafx.fxml;
}
