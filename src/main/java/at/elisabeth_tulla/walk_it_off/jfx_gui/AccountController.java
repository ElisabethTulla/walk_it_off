package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AccountController {

    private User currentUser;

    @FXML
    private Text txtHello;

    public void initData(User user) {
        currentUser = user;
        txtHello.setText("Hello, " + currentUser.getFirstName() + "!");

        //todo fill Ongoing Challenges- and Achievements- table with data from user
    }



}
