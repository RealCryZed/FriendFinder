package com.friendfinder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
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
    private JFXButton myProfileButton;

    @FXML
    private ImageView myProfileImage;

    // -----------------------------> MyProfilePane_EN stuff <----------------------------- //

    @FXML
    private Pane myProfilePane_EN;

    @FXML
    private JFXButton myProfilePane_backBtn;

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
    private JFXComboBox<String> countryField = new JFXComboBox<String>();

    @FXML
    private JFXComboBox<String> cityField = new JFXComboBox<String>();

    @FXML
    private JFXButton changeProfileButton;

    // -----------------------------> EditProfilePane_EN stuff <----------------------------- //

    @FXML
    private Pane editProfilePane_EN;

    @FXML
    private ImageView editProfilePane_avatarImageView;

    @FXML
    private JFXTextField editProfilePane_usernameField;

    @FXML
    private JFXPasswordField editProfilePane_passwordField;

    @FXML
    private JFXTextField editProfilePane_emailField;

    @FXML
    private JFXTextField editProfilePane_telephoneField;

    @FXML
    private JFXComboBox<String> editProfilePane_countryField;

    @FXML
    private JFXComboBox<String> editProfilePane_cityField;

    @FXML
    private JFXButton editProfilePane_backBtn;

    @FXML
    private JFXButton editProfilePane_saveChangesBtn;

    // -----------------------------> Other stuff <----------------------------- //

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private SignInController signInController = new SignInController();
    private SignUpController signUpController = new SignUpController();

    private String usernameFromSignIn;
    private String passwordFromSignIn;

    private Set<String> countrySet = new TreeSet<>();
    private Set<String> citySet = new TreeSet<>();

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
    void setHEADMyProfileButton(ActionEvent event) {

        if (myProfileButton.isManaged() && !event.isConsumed()) {

            usernameField.requestFocus();
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
    void setCityBoxToEnable(ActionEvent event) {

        boolean isMyComboBoxPressed = editProfilePane_countryField.isManaged();

        if (!isMyComboBoxPressed) {
            checkIfCountryFieldIsEmpty();
        } else {
            editProfilePane_cityField.getSelectionModel().clearSelection();
            editProfilePane_cityField.getItems().clear();
            checkIfCountryFieldIsEmpty();
        }
    }

    @FXML
    void setCountryFieldWhenItsChanged(InputMethodEvent event) {

        editProfilePane_cityField.setDisable(true);
        editProfilePane_cityField.getSelectionModel().clearSelection();
        editProfilePane_cityField.setValue(null);

        checkIfCountryFieldIsEmpty();
    }

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
    void setEditProfilePane_SaveChangesBtn(ActionEvent event) {

        if (checkIfProfileInfoWasChanged()) {
            saveChangesInMyProfile();
        }
    }

    @FXML
    void initialize() {

        readSignInLogs();
        setDefaultParamsForMyProfile(usernameFromSignIn, passwordFromSignIn);

        setCountries();
        addCountries();

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

    public void setDefaultParamsForMyProfile(String username, String password) {

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "SELECT username, password, email, phonenumber, country, city " +
                "FROM signupinfo WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                usernameField.setText(resultSet.getString(1));
                passwordField.setText(resultSet.getString(2));
                emailField.setText(resultSet.getString(3));
                telephoneField.setText(resultSet.getString(4));
                countryField.setPromptText(resultSet.getString(5));
                cityField.setPromptText(resultSet.getString(6));

                editProfilePane_usernameField.setText(resultSet.getString(1));
                editProfilePane_passwordField.setText(resultSet.getString(2));
                editProfilePane_emailField.setText(resultSet.getString(3));
                editProfilePane_telephoneField.setText(resultSet.getString(4));
                editProfilePane_countryField.setValue(resultSet.getString(5));
                editProfilePane_cityField.setPromptText(resultSet.getString(6));
            } else {
                System.err.println("Account doesn't exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readSignInLogs() {

        try {
            File file = new File("logs/signInInfo.log");
            Date date = new Date();
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                if (scan.nextLine().equals("--------------------") & scan.nextLine().equals(date.toString())) {
                    usernameFromSignIn = scan.nextLine();
                    passwordFromSignIn = scan.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setCountries() {

        editProfilePane_cityField.setDisable(true);

        for (int i = 0; i < signUpController.countries.length; i++) {
            String country = signUpController.countries[i];
            String[] citiesList = signUpController.cities[i];

            for (String city : citiesList) {
                citySet.add(city);
            }

            countrySet.add(country);
        }
    }

    private void addCountries() {

        editProfilePane_countryField.getItems().add("Country");

        for (String country : countrySet) {
            editProfilePane_countryField.getItems().add(country);
        }
    }

    private void checkIfCountryFieldIsEmpty() {

        boolean isMyComboBoxEmpty = editProfilePane_countryField.getSelectionModel().isEmpty();
        String comboBoxValue = editProfilePane_countryField.getValue();

        if (!isMyComboBoxEmpty && !(comboBoxValue == signUpController.emptyBlock)) {

            editProfilePane_cityField.setPromptText("City");
            for (int i = 0; i < signUpController.countries.length; i++) {
                if (signUpController.countries[i] == editProfilePane_countryField.getValue()) {

                    String[] cityList = signUpController.cities[i];

                    for (String city : cityList) {
                        editProfilePane_cityField.getItems().add(city);
                    }
                }
            }

            isMyComboBoxEmpty = false;
            editProfilePane_cityField.setDisable(false);
        } else {
            editProfilePane_cityField.setDisable(true);
            editProfilePane_cityField.getSelectionModel().clearSelection();
            editProfilePane_cityField.getItems().clear();
        }
    }

    private static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private boolean checkIfProfileInfoWasChanged() {

        boolean isMyProfileInfoWasChanged = false;

        if (editProfilePane_usernameField.getText().equals(usernameField.getText()) &
                editProfilePane_passwordField.getText().equals(passwordField.getText()) &
                editProfilePane_emailField.getText().equals(emailField.getText()) &
                editProfilePane_telephoneField.getText().equals(telephoneField.getText()) &
                (editProfilePane_countryField.getValue().equals(countryField.getPromptText()) || editProfilePane_cityField.getValue() == null) &
                editProfilePane_cityField.getValue() == null
        ) {
            infoBox("You didn't change anything", null, "Failed");
        } else {
            isMyProfileInfoWasChanged = true;
        }

        return isMyProfileInfoWasChanged;
    }

    private void saveChangesInMyProfile() {

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.getConnection();

        String sql = "UPDATE signupinfo SET username = '" + editProfilePane_usernameField.getText() +
                "', password = '" + editProfilePane_passwordField.getText() +
                "', email = '" + editProfilePane_emailField.getText() +
                "', phonenumber = '" + editProfilePane_telephoneField.getText() +
                "', country = '" + editProfilePane_countryField.getSelectionModel().getSelectedItem() +
                "', city = '" + editProfilePane_cityField.getSelectionModel().getSelectedItem() +
                "' WHERE username = '" + usernameFromSignIn +
                "' and password = '" + passwordFromSignIn +"';";

        try {
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            infoBox("All changes saved", null, "Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
