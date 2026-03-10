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

        //todo check if TextField is !null/empty

        //check if email already exists in the DB:
        boolean emailAlreadyExists = valid.checkEmail(email);

        if (emailAlreadyExists){
            txtEmailMessage.setText("This e-mail is already registered.");
        }

        //todo check it email is valid (has @ in it)

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(password);

        if (!isValidPassword){
            txtPasswordMessage.setText("Invalid password.");
        }

        if (isValidPassword && !emailAlreadyExists){
            boolean registered = userService.registerUser(firstName, lastName, email, password, dateBirthday, gender);
            if (!registered){
                txtRegisterMessage.setText("There was an error registering the user. Contact tulla.elisabeth@gmx.at.");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully registered!");
                alert.setHeaderText("You are now registered and can log in to your account!");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomeView.fxml"));
                Scene scene = new Scene(root);

                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            }
        }


    }


}
