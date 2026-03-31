package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageAccountRepository {

    public Connection conn = DatabaseConfig.configure();

    public ManageAccountRepository(){}

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

    public boolean changeFirstname(User user1){

        String sql = "UPDATE user_walkitoff SET first_name = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, user1.getFirstName());
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

    public boolean deleteUserAccount(String email) {

        String sql = "DELETE FROM user_walkitoff WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
