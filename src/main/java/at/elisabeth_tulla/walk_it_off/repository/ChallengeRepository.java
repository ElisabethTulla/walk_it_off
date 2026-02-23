package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRepository {

    public Connection conn = DatabaseConfig.configure();

    public ChallengeRepository() {}

    public void createChallenge(Challenge newChallenge) {

        String sql = "INSERT INTO challenge (name, required_steps, required_achievement_id, min_number_participants " +
                "max_number_participants, goal_steps, goal_distance_km, started_at, goal_end, rewards_achievement_id," +
                "required_km) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setString(1, newChallenge.getName());
            ps.setInt(2, newChallenge.getRequiredSteps());
            ps.setInt(3, newChallenge.getRequiredAchievement().getId());
            ps.setInt(4, newChallenge.getMinNumberParticipants());
            ps.setInt(5, newChallenge.getMaxNumberParticipants());
            ps.setInt(6, newChallenge.getGoalSteps());
            ps.setDouble(7, newChallenge.getGoalDistanceKm());
            ps.setTimestamp(8, newChallenge.getStartedAt());
            ps.setTimestamp(9, newChallenge.getEndsAt());
            ps.setInt(10, newChallenge.getRewardAchievement().getId());
            ps.setDouble(11, newChallenge.getRequiredKm());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    newChallenge.setId(keys.getInt(1));
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

    public List<Challenge> getActiveChallenges(User user) {
        //todo

        List<Challenge> activeChallenges = new ArrayList<>();

        return activeChallenges;
    }
}
