package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * This class is the repository/Data Access Object managing any database operations concerning changes to Users.
 */

public class ManageAccountRepository {

    public Connection conn = DatabaseConfig.configure();

    public ManageAccountRepository(){}

    /***
     * This method updates the last_name in the table user_walkitoff in the database.
     * @param user1 User Object
     * @return boolean true if the last_name was changed - false if nothing was changed.
     */
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

    /***
     * This method updates the first_name in the table user_walkitoff in the database.
     * @param user1 User Object
     * @return boolean true if the first_name was changed - false if nothing was changed.
     */
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

    /***
     * This method updates the email in the table user_walkitoff in the database.
     * @param user1 User Object
     * @return boolean true if the email was changed - false if nothing was changed.
     */
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

    /***
     * This method updates the password in the table user_walkitoff in the database.
     * @param user1 User Object
     * @return boolean true if the password was changed - false if nothing was changed.
     */
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

    /***
     * This method drops a User from the table user_walkitoff in the database.
     * @param email String User Object attribute
     * @return boolean true if user was deleted - false if user was not deleted.
     */
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
