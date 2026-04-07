package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * WelcomeController connects the WelcomeView.fxml with the Models (User, Challenge, Achievement).
 * It acts as the first contact with the application and hands over the user input in order to log in
 * and lead to the AccountView.fxml or to register via the RegisterView.fxml.
 */

public class WelcomeController {

    UserService userService = new UserService();

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtMessage;

    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {

        String email = txtEmail.getText();
        String password = txtPassword.getText();
        User user = userService.login(email, password);

        if (user != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccountView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            AccountController accController = loader.getController();
            accController.initData(user);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else
            txtMessage.setText("E-mail address and/or password are wrong.");
    }

    @FXML
    public void registerHyperlinkClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/views/RegisterView.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
