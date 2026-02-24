package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Achievement;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementRepository {

    public Connection conn = DatabaseConfig.configure();

    public AchievementRepository() {
    }

    public void createAchievement(Achievement newAchievement) {

        String sql = "INSERT INTO achievement (name, required_steps, required_days_active, achievement_type," +
                "required_km) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setString(1, newAchievement.getName());
            ps.setInt(2, newAchievement.getRequiredSteps());
            ps.setInt(3, newAchievement.getRequiredDaysActive());
            ps.setString(4, newAchievement.getType());
            ps.setDouble(5, newAchievement.getRequiredKm());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    newAchievement.setId(keys.getInt(1));
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

    public void unlockAchievement(User user, Integer achievementID) {

        String sql = "INSERT INTO user_achievement (user_id, achievement_id, unlocked) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setInt(2, achievementID);
            ps.setBoolean(3, true);

            ps.executeUpdate();
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

    public List<Achievement> getUserAchievements(User user) {

        String sql = "SELECT * FROM user_achievement JOIN achievement " +
                "ON achievement.id = user_achievement.achievement_id " +
                "WHERE user_id = ? ";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user.getId());

            try (ResultSet rs = ps.executeQuery()) {
                List<Achievement> unlockedAchievements = new ArrayList<>();

                while (rs.next()) {
                    Achievement a = extendedMapRows(rs);
                    unlockedAchievements.add(a);
                }
                return unlockedAchievements;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Achievement extendedMapRows(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("achievement_id");
        String name = rs.getString("name");
        Integer requiredSteps = rs.getInt("required_steps");
        double requiredKm = rs.getDouble("required_km");
        Integer requiredDaysActive = rs.getInt("required_days_active");
        String type = rs.getString("achievement_type");
        Boolean unlocked = rs.getBoolean("unlocked");
        Timestamp unlockedAt = rs.getTimestamp("unlocked_at");

        return new Achievement(id, name, requiredSteps, requiredKm, requiredDaysActive, type, unlocked, unlockedAt);
    }

    public List<Achievement> getAllAchievements() {

        String sql = "SELECT * FROM achievement";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            try (ResultSet rs = ps.executeQuery()) {
                List<Achievement> allAchievements = new ArrayList<>();

                while (rs.next()) {
                    Achievement a = mapRows(rs);
                    allAchievements.add(a);
                }
                return allAchievements;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Achievement mapRows(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Integer requiredSteps = rs.getInt("required_steps");
        double requiredKm = rs.getDouble("required_km");
        Integer requiredDaysActive = rs.getInt("required_days_active");
        String type = rs.getString("achievement_type");

        return new Achievement(id, name, requiredSteps, requiredKm, requiredDaysActive, type);
    }
}
