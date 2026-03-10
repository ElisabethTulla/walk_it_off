package at.elisabeth_tulla.walk_it_off.jfx_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JfxMainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomeView.fxml"));

            // Zeige die Szene an
            Scene scene = new Scene(root, 1400, 1200);

            // CSS-Datei hinzufügen ODER in der fxml file verknüpfen...:
            // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

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


}
