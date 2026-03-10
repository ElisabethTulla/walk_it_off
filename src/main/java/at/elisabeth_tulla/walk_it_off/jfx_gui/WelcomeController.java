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

public class WelcomeController {

    UserService userService = new UserService();

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtMessage;

    //todo FRAGE: Konstruktor erstellen? (in AccountController inizializen??)

    @FXML
    public void loginButtonClicked(ActionEvent event) throws IOException {

        //String userData = ((Node) event.getSource()).getUserData().toString();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        User user = userService.login(email, password);

        //todo FRAGE: kann ich außer NULL noch einen anderen Wert statt einem echten user hier zurückgeben,
        // damit ich differenzieren kann zwischen WRONG EMAIL und WRONG PASSWORD?

        if (user != null) {

            //todo FRAGE: wie kann ich der neuen Scene den user mit übergeben?

            Parent root = FXMLLoader.load(getClass().getResource("/views/AccountView.fxml"));
            Scene scene = new Scene(root);

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
