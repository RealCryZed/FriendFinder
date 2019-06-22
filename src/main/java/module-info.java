module com.friendfinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens com.friendfinder to javafx.fxml;
    exports com.friendfinder;
}