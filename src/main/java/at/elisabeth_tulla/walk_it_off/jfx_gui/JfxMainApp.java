package at.elisabeth_tulla.walk_it_off.jfx_gui;

import at.elisabeth_tulla.walk_it_off.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * JfxMainApp launches the application and starts the graphical user interface
 * by loading the WelcomeView.fxml into the Scene of the Stage.
 */

public class JfxMainApp extends Application {

    static UserService userService = new UserService();

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomeView.fxml"));

            Scene scene = new Scene(root, 900, 850);

            primaryStage.setTitle("walk_it_off");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

   public static void main(String[] args) {

       if (Arrays.stream(args).anyMatch(s -> s.equalsIgnoreCase("--create-test-users"))) {
           createTestUsers();
       }

       launch();
    }

    /***
     * REGISTER TEST-USERS:
     */
    public static void createTestUsers() {

        userService.registerUser("Elvis", "Tulla", "elvis@tulla.at",
                "Postgres1!", LocalDate.of(2018, 1, 18), "male");
        userService.registerUser("Felix", "Tulla", "felix@tulla.at",
                "Postgres1!", LocalDate.of(2015, 1, 6), "male");
        userService.registerUser("Elisabeth", "Tulla", "tulla.elisabeth@gmx.at",
                "postgres", LocalDate.of(1992, 1, 16), "female");
        userService.registerUser("Oliver", "Tulla", "oliver@tulla.at",
                "Postgres1!", LocalDate.of(1992, 7, 21), "male");
        userService.registerUser("Nachi", "Tulla", "nachi@tulla.at",
                "Postgres1!", LocalDate.of(2016, 6, 3), "male");
    }


    /*** TODO:
     *
     * Enter Challenge -> bug! does not show in "ongoing Challenges" in AccountView
     *
     *      (nice to have) Logger? for login password/email wrong (error/warning/debug)
     *
     * im class diagramm UserController als Überklasse einzeichnen (AccountController, ActivityController,
     *      ChallengeController, MannageAccountController ... extends UserController)
     *
     *      (nice to have) prevent empty textfields in Register-, Logging- und ChallengeController
     *
     * grüne-Sternchen-Kommentare -> für jede Klasse wofür sie da ist und bei den ganz wichtigen Methoden auch wieso sie da sind
     *
     * replace "Fehler beim Einfügen in die Datenbank..." with "Error with insertion to database..."
     *
     *
     * DOCUMENTATION:
     *
     * class diagramm note zwischen achievement und user -> zwischentabelle + user und challenge -> zwischentabelle
     *
     * in der documentation use cases aus dem backlog von github (die features) - eventuell kann man es aus dem project exportieren -> ev AI markdown machen lassen)
     *
     * requirements_and_user_stories: hier die user Stories von github (wenn ich es exportiern kann, wenn Export von github nicht geht, nur exemplarisch hier und auf github verweisen)
     *
     * FEATURES zu den usecasses , STORIES zu requirements_and_user_stories
     *
     * [STORY] compare to group stats	https://github.com/ElisabethTulla/walk_it_off/issues/25
     * [STORY] file IO activity input	https://github.com/ElisabethTulla/walk_it_off/issues/36
     * [FEAT] activity logging	https://github.com/ElisabethTulla/walk_it_off/issues/18
     * [FEAT] gui	https://github.com/ElisabethTulla/walk_it_off/issues/31
     * [FEAT] show and compare stats	https://github.com/ElisabethTulla/walk_it_off/issues/21
     * [STORY] implement database configuration	https://github.com/ElisabethTulla/walk_it_off/issues/10
     * [FEAT] Project Setup and Configuration	https://github.com/ElisabethTulla/walk_it_off/issues/4
     * [STORY] manuel input	https://github.com/ElisabethTulla/walk_it_off/issues/19
     * [STORY] write to database	https://github.com/ElisabethTulla/walk_it_off/issues/20
     * [STORY] Setup Github and initial structure	https://github.com/ElisabethTulla/walk_it_off/issues/5
     * [STORY] create postgres database	https://github.com/ElisabethTulla/walk_it_off/issues/8
     * [STORY] implement database connection	https://github.com/ElisabethTulla/walk_it_off/issues/11
     * [STORY] create database tables and relations	https://github.com/ElisabethTulla/walk_it_off/issues/9
     * [FEAT] database setup	https://github.com/ElisabethTulla/walk_it_off/issues/7
     * [STORY] write to database	https://github.com/ElisabethTulla/walk_it_off/issues/17
     * [STORY] project setup	https://github.com/ElisabethTulla/walk_it_off/issues/6
     * [STORY] user registration	https://github.com/ElisabethTulla/walk_it_off/issues/13
     * [STORY] user account deletion	https://github.com/ElisabethTulla/walk_it_off/issues/16
     * [STORY] user name change	https://github.com/ElisabethTulla/walk_it_off/issues/15
     * [STORY] user login	https://github.com/ElisabethTulla/walk_it_off/issues/14
     * [FEAT] account management	https://github.com/ElisabethTulla/walk_it_off/issues/12
     * [STORY] read from database	https://github.com/ElisabethTulla/walk_it_off/issues/22
     * [STORY] compare stats to own old stats	https://github.com/ElisabethTulla/walk_it_off/issues/23
     * [STORY] compare stats to other user	https://github.com/ElisabethTulla/walk_it_off/issues/24
     * [FEAT] challenges	https://github.com/ElisabethTulla/walk_it_off/issues/26
     * [STORY] enter challenge	https://github.com/ElisabethTulla/walk_it_off/issues/27
     * [STORY] show challenges	https://github.com/ElisabethTulla/walk_it_off/issues/28
     * [STORY] create challenges	https://github.com/ElisabethTulla/walk_it_off/issues/32
     * [STORY] create achievements	https://github.com/ElisabethTulla/walk_it_off/issues/33
     *
     *
     * (libraries used:
     *      * - java.sql
     *      * - java.time
     *      * - java.util
     *      * - java.io
     *      * - javafx
     *      * - Junit)
     *
     *
     * Inhalt der POWERPOINT -> Dokumentation!, refactoring der Achievement-Logik kann erwähnt werden     *
     */

    /*** FUTURE PLANS:
     *
     *  - compare achievements to other user
     *  - compare stats to user-group (eg others in your age group, ...)
     *
     *  - log time with runs (also in db) and compare times per km improvements (add maxTime to createChallenge)
     *
     *  - Tooltip "Show Progress" when hover over Item in TableView challengesTable
     *  - load new Scene(view+controller) onClick on Item in TableView challengesTable
     *
     */
}
