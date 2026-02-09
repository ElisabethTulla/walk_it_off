import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManager {

    public Connection conn = DatabaseConfig.configure();

    public boolean checkEmail(String providedEmail){

        String sql = "SELECT * FROM user_walkitoff WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, providedEmail);

            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validatePassword(String password){

        if (password == null) {
            return false;
        }
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*['*°?/!@#$%^&-+=()])(?=\\S+$).{8,20}$";

        // Compile Regex:
        Pattern p = Pattern.compile(regex);

        // Matcher finds matching Regex - password:
        Matcher m = p.matcher(password);

        // Return true if the password matched the Regex:
        return m.matches();
    }

    //todo profanity check

}
