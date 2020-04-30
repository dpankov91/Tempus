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

import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author Tienesh
 */
public class UserDAO {
    private final DbConnectionProvider connector;
    
    public UserDAO()
    {
        this.connector = new DbConnectionProvider();
    }

    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM [dbo].[User] WHERE email = ? AND password = ? ";
        
        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if(!rs.next()){
            return null;
        }else{
        int id = rs.getInt("userID");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        boolean isAdmin = rs.getBoolean("isAdmin");
        
        User us = new User(id, firstName, lastName, isAdmin);
        
        return us;
        
    
        }    
            
    }


    public void deleteUser(User userToDelete) {
            try{
                String sql = "DELETE  FROM [dbo].[User] WHERE userID=? ";
            
                Connection con = connector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setInt(1, userToDelete.getId());

                ResultSet rs = pstmt.executeQuery();
                
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getAllUsers() throws SQLException {
         ArrayList<User> allUsers = new ArrayList<>();
        
        
        String sql = "SELECT * FROM [dbo].[User]";
        
        Connection con = connector.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            
                
                String fName = rs.getString("firstName");
                String lName = rs.getString("lastName");
                String email = rs.getString("email");
                Boolean isAdmin = rs.getBoolean("isAdmin");
                String role;
                if(isAdmin){
                    role="Admin";
                        }else{
                     role="Developer";}
                
               
               allUsers.add(new User(fName, lName, email, role));
               
            }
                return allUsers;
    }
    
}
