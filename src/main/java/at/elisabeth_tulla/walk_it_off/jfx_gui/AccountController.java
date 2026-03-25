package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.AchievementService;
import at.elisabeth_tulla.walk_it_off.service.ChallengeService;
import at.elisabeth_tulla.walk_it_off.service.ComparingService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class AccountController {

    ChallengeService challengeService = new ChallengeService();
    AchievementService achievementService = new AchievementService();
    ComparingService comparingService = new ComparingService();

    private User currentUser;

    @FXML
    private Text txtHello;

    //ActiveChallengesTable:
    @FXML
    private TableView<Challenge> challengesTable;
    @FXML
    private TableColumn<Challenge, String> chaNameColumn;
    @FXML
    private TableColumn<Challenge, Integer> chaStepsColumn;
    @FXML
    private TableColumn<Challenge, Double> chaKmsColumn;
    @FXML
    private TableColumn<Challenge, Timestamp> startedColumn; //todo FRAGE: besser LocalDateTime(auch im Objekt?)
    @FXML
    private TableColumn<Challenge, Timestamp> endsColumn;

    //AchievementsRadioButtons:
    @FXML
    private RadioButton yourAchievementsRadioButton;
    @FXML
    private RadioButton allAchievementsRadioButton;
    @FXML
    private ToggleGroup achievementsRadioButtonGroup;

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
    private TableColumn<Achievement, Boolean> unlockedColumn;
    @FXML
    private TableColumn<Achievement, Timestamp> unlockedAtColumn;

    //ActivityRadioButtons:
    @FXML
    private RadioButton yourStepsRadioButton;
    @FXML
    private RadioButton yourKmsRadioButton;
    @FXML
    private ToggleGroup activityRadioButtonGroup;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    //ActivityTable:
    @FXML
    private TableView<Activity> activityTable;
    @FXML
    private TableColumn<Achievement, Integer> activityStepsColumn;
    @FXML
    private TableColumn<Achievement, Double> activityKmsColumn;
    @FXML
    private TableColumn<Achievement, Timestamp> activityLoggedAtColumn;

    public void initData(User user) {
        this.currentUser = user;
        achievementsRadioButtonGroup = new ToggleGroup();
        this.yourAchievementsRadioButton.setToggleGroup(achievementsRadioButtonGroup);
        this.allAchievementsRadioButton.setToggleGroup(achievementsRadioButtonGroup);
        activityRadioButtonGroup = new ToggleGroup();
        this.yourStepsRadioButton.setToggleGroup(activityRadioButtonGroup);
        this.yourKmsRadioButton.setToggleGroup(activityRadioButtonGroup);

        txtHello.setText("Hello, " + currentUser.getFirstName() + "!");

        //remove ended Challenges from active Challenges:
        challengeService.updateActiveChallenges(currentUser);
        //todo pop-up: Challenge ended

        chaNameColumn.setCellValueFactory(new PropertyValueFactory<Challenge, String>("name"));
        chaStepsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Integer>("goalSteps"));
        chaKmsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Double>("goalDistanceKm"));
        startedColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Timestamp>("startedAt"));
        endsColumn.setCellValueFactory(new PropertyValueFactory<Challenge, Timestamp>("endsAt"));

        List<Challenge> activeChallenges = challengeService.getActiveChallenges(currentUser);

        ObservableList<Challenge> obsActiveChallenges = FXCollections.observableArrayList();
        obsActiveChallenges.addAll(activeChallenges);

        //fill table with active Challenges:
        challengesTable.setItems(obsActiveChallenges);
        //todo if obsActiveChallenges.isEmpty() ...

        //todo select radiobutton "yourAchievements" and fill table with unlocked Achievements.
        // Copy select-method from loggingController!
    }

    //todo Tooltip "Show Progress" when hover over Item in TableView challengesTable
    //todo load new Scene(view+controller) onClick on Item in TableView challengesTable

    public void btnLogActivityClicked(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoggingView.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AccountView.fxml"));
        Scene scene = new Scene(root);

        //hand over user to initData Method in LoggingController:
        LoggingController logController = loader.getController();
        logController.initData(currentUser);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void btnEnterChallengeClicked(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ChallengeView.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("/views/AccountView.fxml"));
        Scene scene = new Scene(root);

        //hand over user to initData Method in ChallengeController:
        ChallengeController challengeController = loader.getController();
        challengeController.initData(currentUser);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void exitMenuItemClicked(){
        Platform.exit();
    }

    public void achievementRadioButtonSelected() {

        achIdColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("id"));
        achNameColumn.setCellValueFactory(new PropertyValueFactory<Achievement, String>("name"));
        achStepsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("requiredSteps"));
        achKmsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Double>("requiredKm"));
        unlockedColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Boolean>("unlocked"));
        unlockedAtColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Timestamp>("unlockedAt"));

        if (yourAchievementsRadioButton.isSelected()) {

            List<Achievement> yourAchievements = achievementService.showUserAchievements(currentUser);

            ObservableList<Achievement> obsYourAchievements = FXCollections.observableArrayList();
            obsYourAchievements.addAll(yourAchievements);

            //fill table with all Achievements:
            achievementTable.setItems(obsYourAchievements);

        } else if (allAchievementsRadioButton.isSelected()) {

            List<Achievement> allAchievements = achievementService.showAllAchievements();

            ObservableList<Achievement> obsAchievements = FXCollections.observableArrayList();
            obsAchievements.addAll(allAchievements);

            //fill table with all Achievements:
            achievementTable.setItems(obsAchievements);
        }
    }

    public void activityRadioButtonSelected() {

        activityLoggedAtColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Timestamp>("loggedAt"));

        if (yourStepsRadioButton.isSelected()) {

            activityStepsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("steps"));

            LocalDate startdate = startDatePicker.getValue();
            LocalDate enddate = endDatePicker.getValue();

            HashMap<LocalDateTime, Integer> yourSteps = comparingService.mapStepsTimeframe(currentUser, startdate, enddate);

            List<Activity> items = yourSteps.keySet().stream()
                    .map(key -> new Activity(key, yourSteps.get(key)))
                    .toList();
/*
            for (Activity item : items) {
                System.out.println(item.toString());
            }
 */

            ObservableList<Activity> obsItems = FXCollections.observableArrayList(items);

            //fill table with ObservableList:
            activityTable.setItems(obsItems);

        } else if (yourKmsRadioButton.isSelected()) {

            activityKmsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Double>("distanceInKm"));

            LocalDate startdate = startDatePicker.getValue();
            LocalDate enddate = endDatePicker.getValue();

            HashMap<LocalDateTime, Double> yourKms = comparingService.mapRunsTimeframe(currentUser, startdate, enddate);

            List<Activity> items = yourKms.keySet().stream()
                    .map(key -> new Activity(key, yourKms.get(key)))
                    .toList();

            ObservableList<Activity> obsItems = FXCollections.observableArrayList(items);

            //fill table with ObservableList:
            activityTable.setItems(obsItems);
        }
    }

}
