import java.sql.*;


public class ComparingRepository {

    public Connection conn = DatabaseConfig.configure();

    public ComparingRepository() {
    }

    public Integer getSteps(User user, Date startDate, Date endDate) {

        String sql = "SELECT COUNT(*) FROM steps WHERE user_id = ? AND logged_at >= ? AND logged_at <= ?";
                                                                        //BUT ??  BETWEEN ??

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user.getId());
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);

            ResultSet rs = ps.executeQuery();



        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Integer steps = 0;
        return steps;
    }
}
