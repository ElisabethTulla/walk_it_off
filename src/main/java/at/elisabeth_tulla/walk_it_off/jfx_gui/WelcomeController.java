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

    //todo FRAGE: implements Initializable ?

    UserService userService = new UserService();

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtMessage;

    //todo FRAGE: Konstruktor erstellen?

    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {

        //String userData = ((Node) event.getSource()).getUserData().toString();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        User user = userService.login(email, password);

        //todo FRAGE: kann ich außer NULL noch einen anderen Wert statt einem echten user hier zurückgeben,
        // damit ich differenzieren kann zwischen WRONG EMAIL und WRONG PASSWORD?

        if (user != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccountView.fxml"));
            Parent root = loader.load();
            //Parent root = FXMLLoader.load(getClass().getResource("/views/AccountView.fxml"));
            Scene scene = new Scene(root);

            //hand over user to initData Method in AccountController:
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
