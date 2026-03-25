package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.AchievementService;
import at.elisabeth_tulla.walk_it_off.service.ChallengeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


public class ChallengeController {

    ChallengeService challengeService = new ChallengeService();
    AchievementService achievementService = new AchievementService();


    private User currentUser;

    //ChallengesTable:
    @FXML
    private TableView<Challenge> allChallengesTable;
    @FXML
    private TableColumn<Challenge, Integer> chaIdColumn;
    @FXML
    private TableColumn<Challenge, String> chaNameColumn;
    @FXML
    private TableColumn<Challenge, Integer> chaReqStepsColumn;
    @FXML
    private TableColumn<Challenge, Double> chaReqKmsColumn;
    @FXML
    private TableColumn<Challenge, Integer> chaGoalStepsColumn;
    @FXML
    private TableColumn<Challenge, Double> chaGoalKmsColumn;
    @FXML
    private TableColumn<Challenge, Timestamp> chaStartColumn;
    @FXML
    private TableColumn<Challenge, Timestamp> chaEndColumn;
    @FXML
    private TableColumn<Challenge, Integer> chaRewardId;

    @FXML
    private TextField txtchallengeId;

    //createChallenge:
    @FXML
    private TextField txtRewardAchievementName;
    @FXML
    private TextField txtAchievementType;
    @FXML
    private TextField txtGoalSteps;
    @FXML
    private TextField txtGoalKms;

    @FXML
    private Button btnLoadRewardAchievementId;
    @FXML
    private TextField txtRewardAchievementId;
    @FXML
    private TextField txtChallengeName;
    @FXML
    private TextField txtMinParticipants;
    @FXML
    private TextField txtMaxParticipants;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField txtLastsForDays;
    @FXML
    private TextField txtRequiredSteps;
    @FXML
    private TextField txtRequiredKms;
    @FXML
    private TextField txtRequiredAchievementId;

    //AchievementsTable:
    @FXML
    private TableView<Achievement> achievementTable;
    @FXML
    private TableColumn<Achievement, Integer> achIdColumn;
    @FXML
    private TableColumn<Achievement, String> achNameColumn;
    @FXML
    private TableColumn<Achievement, Integer> achStepsColumn;
    @FXML
    private TableColumn<Achievement, Double> achKmsColumn;
    @FXML
    private TableColumn<Achievement, String> achTypeColumn;


    public void initData(User user) {

        this.currentUser = user;

        //ChallengeTable:
        chaIdColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Integer>("id"));
        chaNameColumn.setCellValueFactory(new PropertyValueFactory<Challenge, String>("name"));
        chaReqStepsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Integer>("requiredSteps"));
        chaReqKmsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Double>("requiredKm"));
        chaGoalStepsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Integer>("goalSteps"));
        chaGoalKmsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Double>("goalDistanceKm"));
        chaStartColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Timestamp>("startedAt"));
        chaEndColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Timestamp>("endsAt"));
        chaRewardId.setCellValueFactory(new PropertyValueFactory<Challenge, Integer>("rewardAchievementID"));

        List<Challenge> allChallenges = challengeService.showAllChallenges();

        ObservableList<Challenge> obsActiveChallenges = FXCollections.observableArrayList();
        obsActiveChallenges.addAll(allChallenges);

        //fill table with active Challenges:
        allChallengesTable.setItems(obsActiveChallenges);

        //AchievementsTable:
        achIdColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("id"));
        achNameColumn.setCellValueFactory(new PropertyValueFactory<Achievement, String>("name"));
        achStepsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("requiredSteps"));
        achKmsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Double>("requiredKm"));
        achTypeColumn.setCellValueFactory(new PropertyValueFactory<Achievement, String>("type"));

        List<Achievement> allAchievements = achievementService.showAllAchievements();

        ObservableList<Achievement> obsAchievements = FXCollections.observableArrayList();
        obsAchievements.addAll(allAchievements);

        //fill table with all Achievements:
        achievementTable.setItems(obsAchievements);
    }

    public void btnEnterChallengeClicked(ActionEvent actionEvent) throws IOException {

        Integer challengeId = Integer.parseInt(txtchallengeId.getText());

        //check if RequiredAchievement is unlocked:
        String missingAchievement = challengeService.checkReqAchievements(currentUser, challengeId);
        if (missingAchievement != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Achievement");
            alert.setHeaderText("You don't have the required Achievement to enter this Challenge.");
            alert.setContentText("Missing Achievement: " + missingAchievement);
            alert.showAndWait();
        }

        Boolean underMaxParticipants = challengeService.checkParticipantsOutmaxed(challengeId);
        if (!underMaxParticipants) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Challenge maxed out.");
            alert.setHeaderText("This challenge has already reached it's max number of participants");
            alert.showAndWait();
        }

        if (underMaxParticipants && missingAchievement == null) {

            //enter challenge:
            boolean entered = challengeService.enterChallenge(currentUser, challengeId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (!entered) {
                alert.setTitle("Something went wrong");
                alert.setHeaderText("There was an error entering the challenge. Contact tulla.elisabeth@gmx.at");
            } else {
                alert.setTitle("Challenge entered!");
                alert.setHeaderText("You successfully entered the challenge!");
            }
            alert.showAndWait();

            backToAccount(actionEvent);
        }
    }

    private void backToAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AccountView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //hand over user to initData Method in AccountController:
        AccountController accController = loader.getController();
        accController.initData(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void btnCloseClicked(ActionEvent actionEvent) throws IOException {

        backToAccount(actionEvent);
    }

    private Integer rewardAchievementId;

    public void btnLoadRewardAchievementIdClicked(ActionEvent actionEvent) throws IOException {

        String rewardAchievementName = txtRewardAchievementName.getText();
        String type = txtAchievementType.getText();
        Integer goalSteps = Integer.parseInt(txtGoalSteps.getText());
        Integer goalKms = Integer.parseInt(txtGoalKms.getText());

        this.rewardAchievementId = achievementService.createAchievement
                (rewardAchievementName, goalSteps, goalKms, 0, type);
        //txtRewardAchievementId.setText(rewardAchievementId.toString());
        //todo FRAGE Wie kann ich das Textfeld txtRewardAchievement mit der rewardAchievementId befüllen?
        // -> ERROR sagt rewardAchievementId is null...

        btnLoadRewardAchievementId.setDisable(false);
    }

    public void btnCreateChallengeClicked(ActionEvent actionEvent) throws IOException {

        Integer goalSteps = Integer.parseInt(txtGoalSteps.getText());
        Integer goalKms = Integer.parseInt(txtGoalKms.getText());
        String challengeName = txtChallengeName.getText();
        Integer minParticipants = Integer.parseInt(txtMinParticipants.getText());
        Integer maxParticipants = Integer.parseInt(txtMaxParticipants.getText());
        LocalDate start = startDate.getValue();
        Integer lastsFor = Integer.parseInt(txtLastsForDays.getText());
        Integer reqSteps = Integer.parseInt(txtRequiredSteps.getText());
        Double reqKms = Double.parseDouble(txtRequiredKms.getText());
        Integer reqAchievementId = Integer.parseInt(txtRequiredAchievementId.getText());

        challengeService.createChallenge(challengeName, reqSteps, reqKms, reqAchievementId,
                minParticipants, maxParticipants, goalSteps, goalKms, start, lastsFor, rewardAchievementId);

        //todo handle if createChallenge didn't go threw

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Challenge created!");
        alert.setHeaderText("You successfully created a new challenge!");
        alert.showAndWait();

        backToAccount(actionEvent);
    }
}
