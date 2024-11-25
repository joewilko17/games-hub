module com.gameshub {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gameshub to javafx.fxml;
    exports com.gameshub;
}
