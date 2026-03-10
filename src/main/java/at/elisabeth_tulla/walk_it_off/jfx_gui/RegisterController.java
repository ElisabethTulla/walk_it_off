package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.util.ValidationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class RegisterController {

    ValidationManager valid = new ValidationManager();

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

    public void btnCreateUserClicked(ActionEvent actionEvent) {

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        LocalDate dateBirthday = birthdayDate.getValue();
        String gender = txtGender.getText();

        //check if email already exists in the DB:
        boolean emailAlreadyExists = valid.checkEmail(email);

        if (emailAlreadyExists){
            txtEmailMessage.setText("This e-mail is already registered.");
        }

        //check if password is valid:
        boolean isValidPassword = valid.validatePassword(password);

        if (!isValidPassword){
            txtPasswordMessage.setText("Invalid password.");
        }



        //todo new user/constructor with LocalDate - NOT integer year, ...
    }


}
