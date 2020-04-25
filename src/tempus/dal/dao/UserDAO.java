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
import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author Tienesh
 */
public class UserDAO {
    private final DbConnectionProvider connector;
    
    public UserDAO(DbConnectionProvider connector)
    {
        this.connector = connector;
        //connector = new DbConnectionProvider();
    }

    public UserDAO() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM [dbo].[User] WHERE username = ? AND password = ? ";
        
        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if(!rs.next()){
            return null;
        }else{
        int id = rs.getInt("id");
        String firstName = rs.getString("firstname");
        String lastName = rs.getString("lastname");
        boolean isAdmin = rs.getBoolean("admin");
        
        User us = new User(id, firstName, lastName, isAdmin);
        
        return us;
        }    
            
    }
}
