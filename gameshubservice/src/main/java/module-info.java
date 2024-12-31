module com.gameshubservice {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires spring.security.crypto;
    requires com.minesweeperservice;

    opens com.gameshubservice to javafx.fxml;
    exports com.gameshubservice;
    exports com.gameshubservice.controller;
    opens com.gameshubservice.controller to javafx.fxml;
    opens com.gameshubservice.model to com.fasterxml.jackson.databind;
}
