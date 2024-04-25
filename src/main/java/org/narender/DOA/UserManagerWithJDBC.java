package org.narender.DOA;

import org.narender.Objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManagerWithJDBC {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerWithJDBC.class.getName());
    private static int userID = 1;

    public static User createUser(User user) {
        user.setUserID(userID);
        userID++;
        return user;
    }

    public static User verifyUser(String username, String password) {

        User user = new User();


        try {
            Connection con = DBConnectionSingleTon.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               user.setUserID(rs.getInt("user_ID"));
               user.setUsername(rs.getString("username"));
               user.setEmail(rs.getString("email_id"));
               user.setPassword(rs.getString("password"));
               user.setFirstName(rs.getString("firstName"));
               user.setLastName(rs.getString("lastName"));
               user.setMobileNo(rs.getString("mobileNo"));
               user.setCreate_timestamp(rs.getTimestamp("create_timestamp"));
               user.setUpdate_timestamp(rs.getTimestamp("update_timestamp"));
               return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
