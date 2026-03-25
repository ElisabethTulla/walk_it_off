package at.elisabeth_tulla.walk_it_off.jfx_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JfxMainApp extends Application {

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

        launch(); //todo launch(args);

    }


    /*** TODO:
     * class diagarmm mit relationsships und kardinalitäten ( ein user kann x acchievements haben 1 -n , .....)
     *
     *      (nice tot have) class diagramm für db?
     *
     * Enter Challenge -> bug! does not show in "ongoing Challenges" in AccountView
     *
     * Check if Pop up alert für achievements (in loggingController) funktioniert
     *
     * compare Options in Gui eventuell entfernen? Oder vll disablen
     *
     *      (nice to have) Logger? for login password/email wrong (error/warning/debug)
     *
     * UNIT Test für Methode, die nicht direkt auf die db zugreift
     *
     * try-catch in Controller IO Exception
     *
     * ChallengeView make challenge- and achievement tables longer!!
     *
     * in ChallengeView load AchievementID in textfield (oder change to text -> *.setText(..))
     *
     * todo FRAGE: documentation/01_project_overview ->ganz unten Jira link ??;
     * /02_project_management/02_stakeholders -> Risks and Assumptions;
     * /02_project_management/03 !?!?;
     * 03_analysis (use-case-diagram)
     *
     * todo FRAGE: JFXMain im jfx_gui package lassen?
     *
     * todo FRAGE: toString() der Object-Klassen wird nie verwendet... notwendig?
     *
     * todo FRAGE: grüne-Sternchen-Kommentare für alle/manche Methoden machen?
     *
     * todo FRAGE: keine ENUMS... (possibilities: gender, type of achievement, type of activity)
     *
     */

    /*** FURURE PLANS:
     *
     *   FEAT show and compare stats #21:
     *  - compare achievements to other user
     *  - compare stats to user-group (eg others in your age group, ...)
     *
     *  - log time with runs (also in db) and compare times per km improvements (add maxTime to createChallenge)
     *
     */
}
