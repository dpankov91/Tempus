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
    }
    
    public void createProject(String name, String clientName, String hRate, String description) {
        
        try {
            String sql = "INSERT Project.ProjectName, Project.HourlyRate, Project.Description, Client.ClientName FROM Project INNER JOIN Client ON Project.ClientID=Client.ProjectID VALUES ProjectName = ? AND ClientName = ? AND HourlyRate = ? AND Description =? ";
            
            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, name);
            pstmt.setString(2, clientName);
            pstmt.setString(3, hRate);
            pstmt.setString(4, description);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void deleteProject(Project projectToDelete) {
        try {
            String sql = "DELETE * FROM [dbo].[Project] WHERE id=? ";
            
            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, projectToDelete.getId());

            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Project> getAllProjects() throws SQLException {
        ArrayList<Project> allProjects = new ArrayList<>();
        
        
        String sql = "SELECT [dbo].[Client].[clientName], [dbo].[Project].*"
                   + "FROM [dbo].[Project]"
                   + "JOIN [dbo].[Client] ON [dbo].[Client].[clientID] = [dbo].[Project].[clientID]";
                   
                    
        
        Connection con = connector.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) 
            {
                
                String name = rs.getString("projectName");
                String clientName = rs.getString("clientName");
                int hRate = rs.getInt("hourlyRate");
                String description = rs.getString("description");
                allProjects.add(new Project(name, hRate, clientName, description));
               
            }
                return allProjects;
    }
    
}
