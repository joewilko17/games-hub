module com.gameshub {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires spring.security.crypto;

    opens com.gameshub to javafx.fxml;
    exports com.gameshub;
    exports com.gameshub.controller;
    opens com.gameshub.controller to javafx.fxml;
    opens com.gameshub.model to com.fasterxml.jackson.databind;
}
