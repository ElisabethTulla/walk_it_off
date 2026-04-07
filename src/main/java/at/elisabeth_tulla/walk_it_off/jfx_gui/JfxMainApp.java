package at.elisabeth_tulla.walk_it_off.jfx_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JfxMainApp launches the application and starts the graphical user interface
 * by loading the WelcomeView.fxml into the Scene of the Stage.
 */

public class JfxMainApp extends Application {

    /**
     * This method loads the WelcomeView into the Scene of the Stage and starts the GUI.
     * @param primaryStage Stage
     */
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
     *
     * fix different sizes in application
     *
     * neues Screencapture (Register new User, Your Achievements, Log 10.000 steps, create new Challenge (run  5K),
     *  enter challenge, log 5km); login felix@tulla.at, show steps timeframe.
     *
     * todo FIX CLASS DIAGRAMM in PowerPoint (n-m relationship between user - achievement!!)
     *
     * DOCUMENTATION:
     *
     * todo: check 03_analysis/test_cases_analysis -> test cases to be accurate! (ai)
     *
     * todo: check 05_testing/test_cases -> test cases to be accurate! (ai)
     */

}
