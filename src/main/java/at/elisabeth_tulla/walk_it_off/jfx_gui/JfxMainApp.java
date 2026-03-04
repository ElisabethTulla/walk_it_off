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
           // FXMLLoader loader = new FXMLLoader();

            //URL resource = JfxMainApp.class.getResource("/v.fxml");
            //loader.setLocation(resource);
            //VBox rootLayout = loader.load();

            //Label l = new Label("Loading application...");

            // Zeige die Szene an
            Scene scene = new Scene(root, 1000, 800);

            // Fügen Sie hier ggf. die CSS-Datei hinzu, falls Sie eine verwenden
            // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            primaryStage.setTitle("Welcome to walk_it_off");
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
