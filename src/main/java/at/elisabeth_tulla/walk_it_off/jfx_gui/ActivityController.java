package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.ActivityService;
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

/**
 * ActivityController connects the ActivityView.fxml with the Models (User, Challenge, Achievement, Activity).
 * It hands over the user input from the GUI to the service layer
 * and therefor enables the logging of Activity.
 */

public class ActivityController extends UserController {

    ActivityService activityService = new ActivityService();

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

    @Override
    public void initData(User user) {

        super.initData(user);
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

        Integer steps = Integer.parseInt(stepsTextField.getText());
        try {
            List<Achievement> achievements = activityService.loggWalking(currentUser, "walking", steps);
            checkAchievementAlert(achievements);
        } catch (RuntimeException e) {
            triggerErrorAlert();
        }

        backToAccount(event);
    }

    private void triggerErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("There was an error logging the activity. Contact tulla.elisabeth@gmx.at.");
        alert.showAndWait();
    }

    private void checkAchievementAlert(List<Achievement> achievements) {

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

        Double kms = Double.parseDouble(kmsTextField.getText());

        try {
            List<Achievement> achievements = activityService.loggRunning(currentUser, "running", kms);

            checkAchievementAlert(achievements);
        } catch (RuntimeException e) {
            triggerErrorAlert();
        }

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
