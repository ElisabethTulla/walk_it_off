package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChallengeRepository {

    public Connection conn = DatabaseConfig.configure();

    public ChallengeRepository() {}

    public void createChallenge(Challenge newChallenge) {

        String sql = "INSERT INTO challenge (name, required_steps, required_achievement_id, min_number_participants, " +
                "max_number_participants, goal_steps, goal_distance_km, started_at, goal_end, rewards_achievement_id," +
                "required_km) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setString(1, newChallenge.getName());
            ps.setInt(2, newChallenge.getRequiredSteps());
            ps.setInt(3, newChallenge.getRequiredAchievementID());
            ps.setInt(4, newChallenge.getMinNumberParticipants());
            ps.setInt(5, newChallenge.getMaxNumberParticipants());
            ps.setInt(6, newChallenge.getGoalSteps());
            ps.setDouble(7, newChallenge.getGoalDistanceKm());
            ps.setTimestamp(8, newChallenge.getStartedAt());
            ps.setTimestamp(9, newChallenge.getEndsAt());
            ps.setInt(10, newChallenge.getRewardAchievementID());
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
                conn.rollback(); //todo throw new RuntimeException(e);
            } catch (SQLException ex) {
                System.err.println("Fehler beim rollback:" + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }

    public HashMap<LocalDateTime, Integer> getOngoingChallenges(User user) {

            String sql = "SELECT * FROM user_challenge JOIN challenge " +
                    "ON challenge.id = user_challenge.challenge_id WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());

            try (ResultSet rs = ps.executeQuery()) {
                HashMap<LocalDateTime, Integer> activeChallenges = new HashMap<>();

                while (rs.next()) {
                    if (rs.getTimestamp("goal_end").toLocalDateTime().isAfter(LocalDateTime.now())) {
                        activeChallenges.put(rs.getTimestamp("entered_at").toLocalDateTime(),
                                rs.getInt("challenge_id"));
                    }
                }
                return activeChallenges;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Challenge> getAllChallenges() {

        String sql = "SELECT * FROM challenge";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            try (ResultSet rs = ps.executeQuery()) {
                List<Challenge> allChallenges = new ArrayList<>();

                while (rs.next()) {
                    Challenge c = mapRows(rs);
                    allChallenges.add(c);
                }
                return allChallenges;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Challenge mapRows(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        int requiredSteps = rs.getInt("required_steps");
        int requiredAchievementID = rs.getInt("required_achievement_id");
        int minNumberParticipants = rs.getInt("min_number_participants");
        int maxNumberParticipants = rs.getInt("max_number_participants");
        int goalSteps = rs.getInt("goal_steps");
        int goalDistanceKm = rs.getInt("goal_distance_km");
        Timestamp startedAt = rs.getTimestamp("started_at");
        Timestamp endsAt = rs.getTimestamp("goal_end");
        int rewardAchievementID = rs.getInt("rewards_achievement_id");
        int requiredKm = rs.getInt("required_km");

        return new Challenge(id, name, requiredSteps, requiredAchievementID, minNumberParticipants,
                maxNumberParticipants, goalSteps, goalDistanceKm, startedAt, endsAt, rewardAchievementID, requiredKm);
    }

    public Challenge getChallenge(Integer challengeID) {

        String sql = "SELECT * FROM challenge WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, challengeID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Challenge c = mapRows(rs);
                    return c;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean enterChallenge(User user, Challenge currentChallenge) {

        String sql = "INSERT INTO user_challenge (user_id, challenge_id) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setInt(2, currentChallenge.getId());


            ps.executeUpdate();
            conn.commit();

            return true;

        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen in die Datenbank :" + e.getMessage());
            try {
                conn.rollback();
                return false; //todo throw new RuntimeException(e);
            } catch (SQLException ex) {
                System.err.println("Fehler beim rollback:" + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }

    public Integer getParticipantsCount(Challenge currentChallenge) {

        String sql = "SELECT count(*) FROM user_challenge WHERE challenge_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, currentChallenge.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deactivateChallenge(User user, Challenge challenge) {

        String sql = "UPDATE user_challenge SET active = false WHERE user_id = ? AND challenge_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setInt(2, challenge.getId());

            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Updaten der Datenbank :" + e.getMessage());
            try {
                conn.rollback(); //todo throw new RuntimeException(e);
            } catch (SQLException ex) {
                System.err.println("Fehler beim rollback:" + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }

    }

    public List<Challenge> getActiveChallenges(User user) {

        String sql = "SELECT * FROM user_challenge JOIN challenge " +
                "ON challenge.id = user_challenge.challenge_id WHERE user_id = ? AND active = true";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());

            try (ResultSet rs = ps.executeQuery()) {
                List<Challenge> activeChallenges = new ArrayList<>();

                while (rs.next()) {
                    Challenge c = mapRows(rs);
                    activeChallenges.add(c);
                }
                return activeChallenges;

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
