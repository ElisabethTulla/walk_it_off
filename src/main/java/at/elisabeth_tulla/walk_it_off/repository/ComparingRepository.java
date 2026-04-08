package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;

/***
 * This class is the repository/Data Access Object managing any database operations
 * concerning Calculations and Comparisons of User Data.
 */

public class ComparingRepository {

    public Connection conn = DatabaseConfig.configure();

    public ComparingRepository() {
    }

    /***
     * This method summarizes all values from column steps_logged in table activity from the given User Object
     * between the LocalDateTime startDate and endDate.
     * @param user User Object
     * @param startDate LocalDateTime that marks the start of timeframe
     * @param endDate LocalDateTime that marks the end of timeframe
     * @return Integer sum of STEPS
     */
    public Integer getStepsSumDateToDate
    (User user, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT SUM(steps_logged) FROM activity " +
                "WHERE user_id = ? AND logged_at BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, user.getId());
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

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
     * This method summarizes all values from column steps_logged in table activity from the given User Object.
     * @param user User Object
     * @return Integer sum of all STEPS
     */
    public Integer getStepsSumAll(User user) {

        String sql = "SELECT SUM(steps_logged) FROM activity WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()){
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
     * This method creates a HashMap with LocalDateTime and Integer of all STEPS (Activity) logged
     * between LocalDateTime startDate and endDate from the table activity.
     * @param user User Object
     * @param startDate LocalDateTime marks start of timeframe
     * @param endDate LocalDateTime marks end of timeframe
     * @return HashMap with LocalDateTime and Integer
     */
    public HashMap<LocalDateTime, Integer> getStepsDateToDate(User user, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT steps_logged, logged_at FROM activity WHERE user_id = ? AND logged_at BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                HashMap<LocalDateTime, Integer> stepsMap = new HashMap<>();

                    while (rs.next()) {
                        if (rs.getInt("steps_logged") >= 1) {
                            stepsMap.put(rs.getTimestamp("logged_at").toLocalDateTime(),
                                    rs.getInt("steps_logged"));
                        }
                    }
                    return stepsMap;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method summarizes all values from column distance_logged_km in table activity from the given User Object
     * between the LocalDateTime startDate and endDate.
     * @param user User Object
     * @param startDate LocalDateTime marks start of timeframe
     * @param endDate LocalDateTime marks end of timeframe
     * @return double sum of all logged KILOMETER in timeframe
     */
    public double getKmSumDateToDate(User user, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT SUM(distance_logged_km) FROM activity WHERE user_id = ? AND logged_at BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
                return 0;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method summarizes all values from column distance_logged_km in table activity from the given User Object.
     * @param user User Object
     * @return double sum of all logged KILOMETER from user
     */
    public double getKmSumAll(User user) {

        String sql = "SELECT SUM(distance_logged_km) FROM activity WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
                return 0;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method creates a HashMap with LocalDateTime and Double of all KILOMETER (Activity) logged
     * between LocalDateTime startDate and endDate from the table activity.
     * @param user User Object
     * @param startDate LocalDateTime marks start of timeframe
     * @param endDate LocalDateTime marks end of timeframe
     * @return HashMap with LocalDateTime and Double of KILOMETER logged in timeframe
     */
    public HashMap<LocalDateTime, Double> getKmsDateToDate(User user, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT distance_logged_km, logged_at FROM activity WHERE user_id = ? AND logged_at BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                HashMap<LocalDateTime, Double> runsMap = new HashMap<>();

                while (rs.next()) {
                    if (rs.getDouble("distance_logged_km") > 0) {
                        runsMap.put(rs.getTimestamp("logged_at").toLocalDateTime(),
                                rs.getDouble("distance_logged_km"));
                    }
                }
                return runsMap;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method counts all entries in table activity from user between startDate and endDate.
     * @param user User Object
     * @param activity String activity name (walking/running)
     * @param startDate LocalDateTime marks start of timeframe
     * @param endDate LocalDateTime marks end of timeframe
     * @return Integer value of counted entries
     */
    public Integer getActivityCount(User user, String activity, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT COUNT(*) FROM activity WHERE user_id =? AND activity_name =? AND logged_at BETWEEN ? AND ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            ps.setString(2, activity);
            ps.setTimestamp(3, Timestamp.valueOf(startDate));
            ps.setTimestamp(4, Timestamp.valueOf(endDate));

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
}
