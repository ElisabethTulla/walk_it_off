import java.sql.*;


public class UserRepository {

    public Connection conn = DatabaseConfig.configure();

    public UserRepository() {
    }

    public void registerNewUser(User newUser) {

        String sql = "INSERT INTO user_walkitoff (first_name, last_name, email, password, age, gender, " +
                "birth_year, birth_month, birth_day) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getPassword());
            ps.setInt(5, newUser.getAge());
            ps.setString(6, newUser.getGender());
            ps.setInt(7, newUser.getBirthYear());
            ps.setInt(8, newUser.getBirthMonth());
            ps.setInt(9, newUser.getBirthDay());

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    newUser.setId(keys.getInt(1));
                }
            }
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen in die Datenbank :" + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Fehler beim rollback:" + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }

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

    private User mapRow(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Timestamp created_at = rs.getTimestamp("created_at");
        //Instant createdAt = ts != null ? ts.toInstant() : null;
        boolean active = rs.getBoolean("active");
        Integer birthYear = rs.getInt("birth_year");
        Integer birthMonth = rs.getInt("birth_month");
        Integer birthDay = rs.getInt("birth_day");
        Integer age = rs.getInt("age");
        String gender = rs.getString("gender");

        return new User(id, firstName, lastName, email, password, created_at, active,
                birthYear, birthMonth, birthDay, age, gender);
    }

    public boolean checkPassword(User user1, String password) {

        String sql = "SELECT password FROM user_walkitoff WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user1.getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return password.equals(rs.getString("password"));
                } else {
                    System.out.println("no password found in db..");
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
