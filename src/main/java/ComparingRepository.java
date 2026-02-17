import java.sql.*;
import java.time.LocalDateTime;


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
}
