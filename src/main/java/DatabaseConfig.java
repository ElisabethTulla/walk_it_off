import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    static String db_url = null;
    static String db_user = null;
    static String db_password = null;

    public static Connection configure() {

        try {

            Properties config = new Properties();

            if (System.getenv("DB_URL") != null /*|| !System.getenv("DB_URL").trim().isEmpty()*/) {
                //Environment Variable:
                db_url = System.getenv("DB_URL");
                db_user = System.getenv("DB_USER");
                db_password = System.getenv("DB_PASSWORD");
            } else if (checkForProperty(config)) {
                //Property-File:
                Path configPath = Path.of("config.properties");

                try (var reader = Files.newBufferedReader(configPath)) {
                    config.load(reader);
                } catch (IOException e) {
                    System.err.println("Fehler beim Laden der Configuration: " + e.getMessage());
                }
                db_url = config.getProperty("DB_URL");
                db_user = config.getProperty("DB_USER");
                db_password = config.getProperty("DB_PASSWORD");

            } else {
                // default-Werte:
                db_url = "jdbc:postgresql://localhost:5432/walkitoff";
                db_user = "postgres";
                db_password = "postgres";
            }

            return DriverManager.getConnection(db_url, db_user, db_password);
        } catch (SQLException e) {
            System.err.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
    public static boolean checkForProperty(Properties config){
        //System.out.println("Checking for Properties...");

        Path configPath = Path.of("config.properties");

        if (!Files.exists(configPath)) {
            //System.out.println("Externe Config-Datei nicht vorhanden: " + configPath);
            return false;
        } else {
            return true;
        }

    }



}
