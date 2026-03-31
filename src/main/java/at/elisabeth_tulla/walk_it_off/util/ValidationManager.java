package at.elisabeth_tulla.walk_it_off.util;

import at.elisabeth_tulla.walk_it_off.config.DatabaseConfig;

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

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*['*°?/!@#$%^&-+=()])(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

}
