package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Activity;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;

/***
 * This class is the repository/Data Access Object managing any database operations concerning Activities.
 */

public class ActivityRepository {
    public Connection conn = DatabaseConfig.configure();

    public ActivityRepository() {
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
            throw new RuntimeException(e);
        }
    }
}

