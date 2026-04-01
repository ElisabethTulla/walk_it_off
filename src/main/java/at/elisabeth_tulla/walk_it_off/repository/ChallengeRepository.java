package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.Challenge;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * This class is the repository/Data Access Object managing any database operations concerning Challenges.
 */

public class ChallengeRepository {

    public Connection conn = DatabaseConfig.configure();

    public ChallengeRepository() {}

    /***
     * This method inserts a new Challenge into the table challenge in the database.
     * @param newChallenge Challenge Object
     */
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
            System.err.println("Error with insertion into database :" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /***
     * This method fetches all ongoing Challenges of user and puts them into a HashMap with LocalDateTime (entered_at)
     * and Integer (challenge_id) if they have not ended yet.
     * @param user User Object
     * @return HashMap with LocalDateTime and Integer of all ongoing Challenges of user
     */
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

    /***
     * This method fetches all data from the table challenge from the database, creates Challenges
     * and puts them into a List of Challenges.
     * @return List of Challenges
     */
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

    /***
     * This method maps the Data from the database to the Challenge Object attributes using the ResultSet.
     * @param rs ResultSet from database
     * @return Challenge Object
     * @throws SQLException
     */
    private Challenge mapRows(ResultSet rs) throws SQLException {

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

    /***
     * This method fetches the data of the entry with the matching challengeID from the table challenge
     * and creates a Challenge Object.
     * @param challengeID Integer
     * @return Challenge Object
     */
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

    /***
     * This method inserts a user_id and challenge_id into the table user_challenge in the database.
     * @param user User Object
     * @param currentChallenge Challenge Object
     */
    public void enterChallenge(User user, Challenge currentChallenge) {

        String sql = "INSERT INTO user_challenge (user_id, challenge_id) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setInt(2, currentChallenge.getId());

            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Error with insertion into database :" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /***
     * This method counts all entries from table user_challenge where the challenge_id matches the currentChallenge
     * that are still marked as active.
     * @param currentChallenge Challenge Object
     * @return Integer value of counted entries(users)
     */
    public Integer getParticipantsCount(Challenge currentChallenge) {

        String sql = "SELECT count(*) FROM user_challenge WHERE challenge_id = ? AND active = true";

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

    /***
     * This method changes the status active from true to false where the user_id and challenge_id match
     * the user and challenge.
     * @param user User Object
     * @param challenge Challenge Object
     */
    public void deactivateChallenge(User user, Challenge challenge) {

        String sql = "UPDATE user_challenge SET active = false WHERE user_id = ? AND challenge_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ps.setInt(1, user.getId());
            ps.setInt(2, challenge.getId());

            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Error with update of database :" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /***
     * This method fetches all active Challenges from the table user_challenge with a joined table challenge
     * where user_id matches the user. It then puts them into a List of Challenges.
     * @param user User Object
     * @return List of active Challenges from user
     */
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
