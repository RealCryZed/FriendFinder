module com.friendfinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
//    requires kotlin.stdlib;
    requires java.sql;

    opens com.friendfinder to javafx.fxml;
    exports com.friendfinder;
}