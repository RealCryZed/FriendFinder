package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton continueButton;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXButton signUpButton;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void setContinueButton(ActionEvent event) throws SQLException {

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "SELECT * FROM signupinfo WHERE username = ? and password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Please enter correct Username and Password", null, "Failed");
            }else{
                infoBox("Login Successfull", null, "Success" );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void setSignUpButton(ActionEvent event) throws IOException {

        Parent signUpPage = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        Scene signUpScene = new Scene(signUpPage);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(signUpScene);
        window.show();
    }

    @FXML
    void initialize() {


    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
