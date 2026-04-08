package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.service.UserService;
import at.elisabeth_tulla.walk_it_off.util.ValidationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * RegisterController connects the RegisterView.fxml with the Models (User, Challenge, Achievement).
 * It hands over the user input from the GUI to the service layer in order to register a new User.
 */

public class RegisterController {

    ValidationManager valid = new ValidationManager();
    UserService userService = new UserService();

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private Text txtEmailMessage;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtPasswordMessage;
    @FXML
    private DatePicker birthdayDate;
    @FXML
    private TextField txtGender;
    @FXML
    private Text txtRegisterMessage;

    public void btnCreateUserClicked(ActionEvent actionEvent) throws IOException {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        LocalDate dateBirthday = birthdayDate.getValue();
        String gender = txtGender.getText();

        //check if email already exists in the DB:
        boolean emailAlreadyExists = valid.checkEmail(email);

        if (emailAlreadyExists) {
            txtEmailMessage.setText("This e-mail is already registered.");
        }

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(password);

        if (!isValidPassword) {
            txtPasswordMessage.setText("Invalid password.");
        }

        if (isValidPassword && !emailAlreadyExists) {

            try {
                userService.registerUser(firstName, lastName, email, password, dateBirthday, gender);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully registered!");
                    alert.setHeaderText("You are now registered and can log in to your account!");
                    alert.showAndWait();

            } catch (RuntimeException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("There was an error registering the user. Contact tulla.elisabeth@gmx.at.");
                alert.showAndWait();
            }

            backToWelcome(actionEvent);
        }
    }

    private void backToWelcome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/WelcomeView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void btnCloseClicked(ActionEvent actionEvent) throws IOException {
        backToWelcome(actionEvent);
    }

}
