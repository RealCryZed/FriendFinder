package com.friendfinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void setBackToSignInButton(ActionEvent event) throws IOException {

        Parent signInPage = FXMLLoader.load(getClass().getResource("signInPage.fxml"));
        Scene signInPageScene = new Scene(signInPage);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.close();
        window.setScene(signInPageScene);
        window.show();
    }

    @FXML
    void initialize() {

    }
}
