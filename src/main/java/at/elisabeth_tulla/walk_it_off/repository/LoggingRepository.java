package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;

public class LoggingRepository {
    public Connection conn = DatabaseConfig.configure();

    public LoggingRepository() {
    }

    public void loggActivity(User user, Activity activity) {

        String sql = "INSERT INTO activity (user_id, activity_name, steps_logged, distance_logged_km)" +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setString(2, activity.getActivityName());
            ps.setInt(3, activity.getSteps());
            ps.setDouble(4, activity.getDistanceInKm());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    activity.setId(keys.getInt(1));
                }
            }

            conn.commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen in die Datenbank :" + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Fehler beim rollback:" + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }


    public void loggStepsToChallenge(User user, Challenge challenge, Integer steps) {

        //todo  INSERT INTO user_challenge
    }

    public void loggKmToChallenge(User user, Challenge challenge, double distanceInKm) {

        //todo
    }
}
