/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tempus.be.Project;

import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author Tienesh
 */
public class UserDAO {

    private final DbConnectionProvider connector;

    public UserDAO() {
        this.connector = new DbConnectionProvider();
    }

    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM [dbo].[User] WHERE email = ? AND password = ? ";

        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        } else {
            int id = rs.getInt("userID");
            String fName = rs.getString("firstName");
            String lName = rs.getString("lastName");
            String email = rs.getString("email");
            String address = rs.getString("address");
            int phone = rs.getInt("phoneNumber");
            int postC = rs.getInt("postcode");
            Boolean isAdmin = rs.getBoolean("isAdmin");
            String photoURL = rs.getString("userPhoto");
            String role;
            if (isAdmin) {
                role = "Admin";
            } else {
                role = "Developer";
            }
            User us = new User(id, fName, lName);
            us.setEmail(email);
            us.setIsAdmin(isAdmin);
            us.setPassword(password);
            us.setRole(role);
            us.setPostcode(postC);
            us.setPhone(phone);
            us.setAddress(address);
            us.setPhotoURL(photoURL);
            return us;

        }

    }

    public User deleteUser(User userToDelete) {
        //In here the user is deleted from the database.
        try {
            String sql = "DELETE  FROM [dbo].[User] WHERE userID=?";
        // Sequence statement above deletes the selected user from the userTable.
            Connection con = connector.getConnection(); // sets up connection.
            PreparedStatement pstmt = con.prepareStatement(sql); // Creatss prepared statement.

            pstmt.setInt(1, userToDelete.getId()); // This string goes to the question mark as their values match, userID value.

            pstmt.executeUpdate(); //String is sent to the database and then updates the database, REMOVING the selected user from it.
            return userToDelete;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        ArrayList<User> allUsers = new ArrayList<>();

        String sql = "SELECT * FROM [dbo].[User]";

        Connection con = connector.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            int id = rs.getInt("userID");
            String fName = rs.getString("firstName");
            String lName = rs.getString("lastName");
            String email = rs.getString("email");
            String address = rs.getString("address");
            int phone = rs.getInt("phoneNumber");
            int postC = rs.getInt("postcode");
            Boolean isAdmin = rs.getBoolean("isAdmin");
            //String photoURL = rs.getString("userPhoto");
            String role;
            if (isAdmin) {
                role = "Admin";
            } else {
                role = "Developer";
            }
            User us = new User(id, fName, lName);
            us.setEmail(email);
            us.setIsAdmin(isAdmin);
            us.setRole(role);
            us.setPostcode(postC);
            us.setPhone(phone);
            us.setAddress(address);
            //us.setPhotoURL(photoURL);
            allUsers.add(us);
        }
        return allUsers;
    }

    public User createUser(String fName, String lName, String hashedPassword, String email, String role, String address, int phone, int postcode) {

        try {
            Connection con = connector.getConnection();
            String sqlUser = "INSERT INTO [User] "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sqlUser,
                    Statement.RETURN_GENERATED_KEYS);
            //INSERT INTO [User] 
            //VALUES (?,'password','firstName','lastname',5555,'address',6700,1,'Admin','')

            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, fName);
            pstmt.setString(4, lName);
            pstmt.setInt(5, phone);
            pstmt.setString(6, address);
            pstmt.setInt(7, postcode);
            pstmt.setInt(8, (role == "Admin" ? 1 : 0));
            pstmt.setString(9, "");
            int id = 0;
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try ( ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            User us = new User(fName, lName, email, role);
            us.setId(id);
            us.setPassword(hashedPassword);

            us.setPostcode(postcode);
            us.setPhone(phone);
            us.setAddress(address);
            return us;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User editUser(int id, String name, String Lname, String email, int realphone, int realpostcode, String address, String imageURL, String password) {
        try {
            String sql = "UPDATE [dbo].[User] SET [email] = ?, [firstName] = ?, [lastName] = ?, [phoneNumber] = ?, [address] = ?, [postcode] = ?, [userPhoto] = ? WHERE [userID] = ?";

            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, Lname);
            pstmt.setInt(4, realphone);
            pstmt.setString(5, address);
            pstmt.setInt(6, realpostcode);
            pstmt.setInt(8, id);
            pstmt.setString(7, imageURL);
            //pstmt.setString(8, password);
            pstmt.executeUpdate();

            User us = new User(id, name, Lname);

            us.setEmail(email);
            us.setPostcode(realpostcode);
            us.setPhone(realphone);
            us.setAddress(address);
            return us;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void assignUsersToProj(Project selectedProject, List<User> usersAssign) throws SQLException {

        String sql = "INSERT INTO UserAndProject (projectID, userID) VALUES (?, ?)";

        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        for (User us : usersAssign) {
            pstmt.setInt(1, selectedProject.getId());
            pstmt.setInt(2, us.getId());
            pstmt.addBatch();

        }
        pstmt.executeBatch();
    }

    public void newPassword(String pswSecond, int userID) throws SQLException {
        // In here, the new password is updated.
        String sql = "UPDATE [dbo].[User] SET [password] = ? WHERE [userID] = ?";
        // Sequence statement above updates the user from the usertable, updating the password value to something new.
        Connection con = connector.getConnection(); // sets up connection.
        PreparedStatement pstmt = con.prepareStatement(sql); // Creates prepared statement
        pstmt.setString(1, pswSecond); // This string goes to the first question mark, the password value
        pstmt.setInt(2, userID); // This string goes to the second question mark, the userID value

        pstmt.executeUpdate(); //String is sent to the database and then updates the database with the new hashpassword.
    }

    public void newPasswordForSelectedUser(String newPassword, int id) throws SQLException {
        String sql = "UPDATE [dbo].[User] SET [password] = ? WHERE [userID] = ?";


        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, newPassword);
        pstmt.setInt(2, id);


        pstmt.executeUpdate();

    }

}
