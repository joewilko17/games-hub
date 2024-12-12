module com.gameshub {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.gameshub to javafx.fxml;
    exports com.gameshub;
}
