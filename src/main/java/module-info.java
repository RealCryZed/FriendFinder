module com.friendfinder {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.friendfinder to javafx.fxml;
    exports com.friendfinder;
}