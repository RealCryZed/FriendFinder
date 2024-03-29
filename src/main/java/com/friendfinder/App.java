package com.friendfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    private MovableApplication movableApplication;
    private SignInController signInController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        movableApplication = new MovableApplication();

        Parent root = FXMLLoader.load((getClass().getResource("signInPage.fxml")));
        Scene scene = new Scene(root);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);

        movableApplication.makeWindowMovable(root, primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}