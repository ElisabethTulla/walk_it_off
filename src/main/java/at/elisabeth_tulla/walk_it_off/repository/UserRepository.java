package at.elisabeth_tulla.walk_it_off.repository;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;
import at.elisabeth_tulla.walk_it_off.model.User;

import java.sql.*;
import java.time.LocalDate;

/***
 * This class is the repository/Data Access Object managing any database operations
 * concerning creating and fetching a User for registration and login.
 */

public class UserRepository {

    public Connection conn = DatabaseConfig.configure();

    public UserRepository() {
    }

    /***
     * This method inserts the new User into the database.
     * @param newUser User Object
     */
    public void registerNewUser(User newUser) {

        String sql = "INSERT INTO user_walkitoff (first_name, last_name, email, password, gender, birthday) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getGender());
            ps.setDate(6, Date.valueOf(newUser.getBirthdayDate()));

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    newUser.setId(keys.getInt(1));
                }
            }
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Error with insertion to database :" + e.getMessage());
                throw new RuntimeException(e);
        }
    }

    /***
     * This method fetches User Data from the table user_walkitoff from the database and creates User Object.
     * @param email String attribute from User Object
     * @return User Object
     */
    public User getUser(String email) {

        String sql = "SELECT * FROM user_walkitoff WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = mapRow(rs);
                    return u;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method maps the Data from the database to the User Object attributes using the ResultSet.
     * @param rs ResultSet from database
     * @return User Object
     * @throws SQLException
     */
    private User mapRow(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Timestamp created_at = rs.getTimestamp("created_at");
        //Instant createdAt = ts != null ? ts.toInstant() : null;
        boolean active = rs.getBoolean("active");
        LocalDate birthday = rs.getDate("birthday").toLocalDate();
        String gender = rs.getString("gender");

        return new User(id, firstName, lastName, email, password, created_at, active,
                birthday, gender);
    }

    /***
     * This method fetches the User password from the table user_walkitoff and matches it to the String password.
     * @param user1 User Object
     * @param password String input from GUI
     * @return boolean true if the String input matches the password from the database - false if not.
     */
    public boolean checkPassword(User user1, String password) {

        String sql = "SELECT password FROM user_walkitoff WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user1.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return password.equals(rs.getString("password"));
                } else {
                    //todo logger
                    System.out.println("no password found in db..");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
