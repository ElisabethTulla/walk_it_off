package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.LoggingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoggingController {

    LoggingService loggingService = new LoggingService();

    private User currentUser;

    @FXML
    private RadioButton stepsRadioButton;
    @FXML
    private RadioButton kmsRadioButton;
    @FXML
    private ToggleGroup stepsKmToggleGroup;
    @FXML
    private HBox stepsHBox;
    @FXML
    private HBox kmsHBox;
    @FXML
    private TextField stepsTextField;
    @FXML
    private TextField kmsTextField;

    public void initData(User user) {

        this.currentUser = user;
        stepsKmToggleGroup = new ToggleGroup();
        this.stepsRadioButton.setToggleGroup(stepsKmToggleGroup);
        this.kmsRadioButton.setToggleGroup(stepsKmToggleGroup);

        stepsRadioButton.setSelected(true);
    }

    public void radioButtonSelected() {
        if (stepsRadioButton.isSelected()) {
            stepsHBox.setDisable(false);
            kmsHBox.setDisable(true);
        } else if (kmsRadioButton.isSelected()) {
            kmsHBox.setDisable(false);
            stepsHBox.setDisable(true);
        }
    }

    public void btnStepsSubmitClicked(ActionEvent event) throws IOException {

        //todo check if textfield is empty

        Integer steps = Integer.parseInt(stepsTextField.getText());
        List<Achievement> achievements = loggingService.loggWalking(currentUser, "walking", steps); //todo is "activity" necessary?

        checkAchievementAlert(achievements);

        backToAccount(event);
/*
        Parent root = FXMLLoader.load(getClass().getResource("/views/AccountView.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();


 */
    }

    private void checkAchievementAlert(List<Achievement> achievements) {

    //todo FRAGE: WIESO funktioniert es nicht?

        if (!achievements.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achievement unlocked!");
            if (achievements.size() == 1) {
                alert.setHeaderText("Congratulations! You have unlocked an Achievement!");
            } else if (achievements.size() > 1) {
                alert.setHeaderText("Congratulations! You have unlocked " + achievements.size() + " Achievements!");
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (Achievement achievement : achievements) {
                stringBuilder.append(achievement.getName());
                stringBuilder.append("\n");
            }
            alert.setContentText(stringBuilder.toString());

            alert.showAndWait();
        }
    }

    public void btnKmsSubmitClicked(ActionEvent event) throws IOException {

        //todo check if textfield is empty

        Double kms = Double.parseDouble(kmsTextField.getText());
        List<Achievement> achievements = loggingService.loggRunning(currentUser, "running", kms);

        checkAchievementAlert(achievements);

        backToAccount(event);
    }

    private void backToAccount(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccountView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //hand over user to initData Method in AccountController:
        AccountController accController = loader.getController();
        accController.initData(currentUser);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void btnCloseClicked(ActionEvent actionEvent) throws IOException {
        backToAccount(actionEvent);
    }
}
