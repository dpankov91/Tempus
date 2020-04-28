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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tempus.be.Project;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author chris
 */
public class ProjectDAO {

    private final DbConnectionProvider connector;
    
    public ProjectDAO()
    {
        this.connector = new DbConnectionProvider();
        //connector = new DbConnectionProvider();
    }
    
    public void createProject(String projectName, String clientName, String hourlyRate, String description) {
        
        try {
            String sql = "INSERT * FROM [dbo].[Project] VALUES ProjectName = ? AND ClientName = ? AND HourlyRate = ? AND Description =? ";
            
            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, projectName);
            pstmt.setString(2, clientName);
            pstmt.setString(3, hourlyRate);
            pstmt.setString(4, description);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void deleteProject(String name, String client, String rate, String description) {
        try {
            String sql = "DELETE * FROM [dbo].[Project] VALUES ProjectName = ? AND ClientName = ? AND HourlyRate = ? AND Description =? ";
            
            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, name);
            pstmt.setString(2, client);
            pstmt.setString(3, rate);
            pstmt.setString(4, description);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Project> getAllProjects() throws SQLException {
        ArrayList<Project> allProjects = new ArrayList<>();
        
        String sql = "SELECT * FROM [dbo].[Project]";
        
        Connection con = connector.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) 
            {
                
                String projectName = rs.getString("projectName");
                Integer projectID = rs.getInt("projectID");
                Integer hourlyRate = rs.getInt("hourlyRate");
                Integer clientID = rs.getInt("clientID");
                String description = rs.getString("description");
               
               
            }
                return allProjects;
    }
    
}
