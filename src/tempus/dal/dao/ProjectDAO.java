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
import tempus.be.Client;
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
    
    public void createProject(String name, Client client, int hRate, String description) {
        
        try {
            Connection con = connector.getConnection();/*
            String sqlClient = "INSERT INTO Client(clientName) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sqlClient, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, clientName);
            pstmt.executeUpdate();
            
            ResultSet genKeys = pstmt.getGeneratedKeys();
            int clientId = -1;
            if (genKeys.next()) {
                clientId = genKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
            */
            String sqlProject = "INSERT INTO Project (ProjectName, ClientId, HourlyRate, Description) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sqlProject);
            
            pstmt.setString(1, name);
            pstmt.setInt(2, client.getId());
            pstmt.setInt(3, hRate);
            pstmt.setString(4, description);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void deleteProject(Project projectToDelete) {
        try {
            String sql = "DELETE FROM [dbo].[Project] WHERE projectID=? ";
            
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
