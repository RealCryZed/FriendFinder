package com.friendfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        String fxml = "signInPage.fxml";
        Parent root = FXMLLoader.load((getClass().getResource(fxml)));
        Scene scene = new Scene(root);

        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/pictures/friendship.png")));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}