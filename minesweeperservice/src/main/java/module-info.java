module com.minesweeperservice {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens com.minesweeperservice to javafx.fxml;
    exports com.minesweeperservice;
}
