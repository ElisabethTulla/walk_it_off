package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;
import at.elisabeth_tulla.walk_it_off.service.AchievementService;
import at.elisabeth_tulla.walk_it_off.service.ChallengeService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AccountController {

    ChallengeService challengeService = new ChallengeService();
    AchievementService achievementService = new AchievementService();

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

    public void initData(User user) {
        this.currentUser = user;
        achievementsRadioButtonGroup = new ToggleGroup();
        this.yourAchievementsRadioButton.setToggleGroup(achievementsRadioButtonGroup);
        this.allAchievementsRadioButton.setToggleGroup(achievementsRadioButtonGroup);

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

    public void radioButtonSelected() {

        achIdColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("id"));
        achNameColumn.setCellValueFactory(new PropertyValueFactory<Achievement, String>("name"));
        achStepsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Integer>("requiredSteps"));
        achKmsColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Double>("requiredKm"));
        unlockedColumn.setCellValueFactory(new PropertyValueFactory<Achievement, Boolean>("unlocked"));
        //todo ERROR cannot read from unreadable property unlocked
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

}
