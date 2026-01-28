import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository {

    public Connection conn = DatabaseConfig.configure();

    public AccountRepository(){}

    public boolean changeLastname(User user1){

        String sql = "UPDATE user_walkitoff SET last_name = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, user1.getLastName());
            ps.setInt(2, user1.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean changeEmail(User user1){

        String sql = "UPDATE user_walkitoff SET email = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, user1.getEmail());
            ps.setInt(2, user1.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean changePassword(User user1){

        String sql = "UPDATE user_walkitoff SET password = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, user1.getPassword());
            ps.setInt(2, user1.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUserAccount(User user1){

        String sql = "DELETE FROM user_walkitoff WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, user1.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
