package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;


public class ComparingRepository {

    public Connection conn = DatabaseConfig.configure();

    public ComparingRepository() {
    }

    public Integer getStepsSumDateToDate(User user, LocalDateTime startDate, LocalDateTime endDate) {

        String sql = "SELECT SUM(steps_logged) FROM activity WHERE user_id = ? AND logged_at BETWEEN ? AND ?";

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

    public HashMap<LocalDateTime, Double> getRunsDateToDate(User user, LocalDateTime startDate, LocalDateTime endDate) {

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








