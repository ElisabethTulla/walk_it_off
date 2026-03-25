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

        launch();

    }


    /***
     *  class diagarmm mit relationsships und kardinalitäten ( ein user kann x acchievements haben 1 -n , .....)
     *
     * Enter Challenge -> bug! does not show in "ongoing Challenges" in AccountView
     *
     * compare Options in Gui eventuell entfernen? Oder vll disablen
     *
     * Clean up Souts !!!!
     *
     * UNIT Test
     *
     * try-catch in Controller IO Exception
     *
     * ChallengeView make challenge- and achievement tables longer!!
     *
     * todo FRAGE: documentation/01_project_overview ->ganz unten Jira link ??;
     * /02_project_management/02_stakeholders -> Risks and Assumptions;
     * /02_project_management/03 !?!?;
     * 03_analysis (use-case-diagram)
     *
     * todo FRAGE: JFXMain im jfx_gui package lassen?
     *
     */
}
