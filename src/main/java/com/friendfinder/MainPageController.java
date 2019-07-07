package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.css.Stylesheet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPageController extends MovableApplication {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPageAnchorPane;

    // -----------------------------> StartPagePane_EN stuff <----------------------------- //

    @FXML
    private Pane startPagePane_EN;

    // -----------------------------> MainMenuPane_EN stuff <----------------------------- //

    @FXML
    private Pane mainMenuPane_EN;

    @FXML
    private JFXComboBox<Label> languageBox;

    @FXML
    private Label languageLabel_EN;

    @FXML
    private Label languageLabel_RU;

    // -----------------------------> MyProfilePane_EN stuff <----------------------------- //

    @FXML
    private Pane myProfilePane_EN;

    @FXML
    private JFXButton myProfileButton;

    @FXML
    private JFXButton myProfilePane_backBtn;

    @FXML
    private ImageView myProfileImage;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField telephoneField;

    @FXML
    private JFXTextField emailField1;

    @FXML
    private JFXTextField telephoneField1;

    @FXML
    private JFXButton changeProfileButton;

    // -----------------------------> EditProfilePane_EN stuff <----------------------------- //

    @FXML
    private Pane editProfilePane_EN;

    @FXML
    private ImageView editProfilePane_avatarImageView;

    @FXML
    private JFXButton editProfilePane_changePhotoBtn;

    @FXML
    private JFXTextField editProfilePane_usernameField;

    @FXML
    private JFXPasswordField editProfilePane_passwordField;

    @FXML
    private JFXTextField editProfilePane_emailField;

    @FXML
    private JFXTextField editProfilePane_telephoneField;

    @FXML
    private JFXTextField editProfilePane_countryField;

    @FXML
    private JFXTextField editProfilePane_cityField;

    @FXML
    private JFXButton editProfilePane_backBtn;

    @FXML
    private JFXButton editProfilePane_saveChangesBtn;


    private String whatPaneWastheLast;

    /*
     *
     * It's ---------> startPagePane_EN <---------
     * All methods are gonna be here
     *
     */

    @FXML
    void setBackToSignInButton(ActionEvent event) throws IOException {

        goToSignInPage();
    }

    /*
     *
     * It's ---------> mainMenuPane_EN <---------
     * All methods are gonna be here
     *
     */

    @FXML
    void setHEADLanguageComboBox(ActionEvent event) {

        ListCell<Label> buttonCell = new ListCell<>() {
            @Override
            protected void updateItem(Label label, boolean isEmpty) {
                super.updateItem(label, isEmpty);

                setText(label == null ? "" : label.getText());

            }
        };
        languageBox.setButtonCell(buttonCell);
    }

    @FXML
    void setHEADMyProfileButton(ActionEvent event) {

        if (myProfileButton.isManaged() && !event.isConsumed()) {
            startPagePane_EN.setVisible(false);
            myProfilePane_EN.setVisible(true);
            editProfilePane_EN.setVisible(false);
            event.consume();
        }
    }

    @FXML
    void setHEADMenuProfileButton(ActionEvent event) {

    }

    @FXML
    void setHEADGoToSettings(ActionEvent event) {

    }

    @FXML
    void setHEADGoToExit(ActionEvent event) {
        System.exit(0);
    }

    /*
     *
     * It's ---------> startPagePane_EN <---------
     * All methods are gonna be here
     *
     */

    @FXML
    void setMyProfilePane_backBtn(ActionEvent event) {

        if (myProfileButton.isManaged() && !event.isConsumed()) {
            startPagePane_EN.setVisible(true);
            myProfilePane_EN.setVisible(false);
            editProfilePane_EN.setVisible(false);
            event.consume();
        }
    }

    @FXML
    void setMyProfilePane_ChangeProfileBtn(ActionEvent event) {

        if (myProfileButton.isManaged() && !event.isConsumed()) {
            startPagePane_EN.setVisible(false);
            myProfilePane_EN.setVisible(false);
            editProfilePane_EN.setVisible(true);
            event.consume();
        }
    }

    /*
     *
     * It's ---------> editProfilePane_EN <---------
     * All methods are gonna be here
     *
     */

    @FXML
    void setEditProfilePane_BackBtn(ActionEvent event) {

        if (editProfilePane_backBtn.isManaged() && !event.isConsumed()) {
            startPagePane_EN.setVisible(false);
            myProfilePane_EN.setVisible(true);
            editProfilePane_EN.setVisible(false);
            event.consume();
        }
    }

    @FXML
    void setEditProfilePane_ChangePhotoBtn(ActionEvent event) {

    }

    @FXML
    void setEditProfilePane_SaveChangesBtn(ActionEvent event) {

    }

    @FXML
    void initialize() {

        languageBox.getSelectionModel().select(0);
        startPagePane_EN.setVisible(true);
        myProfilePane_EN.setVisible(false);
        editProfilePane_EN.setVisible(false);
    }

    private void goToSignInPage() throws IOException {

        Parent signInPage = FXMLLoader.load(getClass().getResource("signInPage.fxml"));
        Scene signInPageScene = new Scene(signInPage);

        Stage window = (Stage) mainPageAnchorPane.getScene().getWindow();

        window.close();
        window.setScene(signInPageScene);
        window.centerOnScreen();
        makeWindowMovable(signInPage, window);
        window.show();
    }
}
