/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tempus.be.Client;
import tempus.be.Project;
import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author chris
 */
public class ProjectDAO {

    private final DbConnectionProvider connector;
    private ProjectUserDAO projUsDao;

    public ProjectDAO() throws IOException {
        this.connector = new DbConnectionProvider();
        projUsDao = new ProjectUserDAO();
    }

    public Project createProject(String name, Client client, int hRate, String description) {

        try {
            //we establish a connnection to the database
            Connection con = connector.getConnection();
            //we make a query
            String sqlProject = "INSERT INTO Project (ProjectName, ClientId, HourlyRate, Description) "
                    + "VALUES(?,?,?,?)";
            //preparing a statement and the query as argument
            PreparedStatement pstmt = con.prepareStatement(sqlProject,
                                      Statement.RETURN_GENERATED_KEYS);
            //for each questionmark we have one prepared statement and we set them to the following parameters
            pstmt.setString(1, name);
            pstmt.setInt(2, client.getId());
            pstmt.setInt(3, hRate);
            pstmt.setString(4, description);
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
            // we create a new project and return the newproject and return null
            Project newProjec = new Project(id, name, hRate, client.getId(), description);
            return newProjec;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Project deleteProject(Project projectToDelete) {
        //In here the project is deleted from the database.
        try {
            String sql = "DELETE FROM [dbo].[Project] WHERE projectID=?"; 
        // Sequence statement above delete the selected project from the projecttable.

            Connection con = connector.getConnection(); // sets up connection.
            PreparedStatement pstmt = con.prepareStatement(sql); // Creatss prepared statement.

            pstmt.setInt(1, projectToDelete.getId()); // This string goes to the question mark, projectID value

            pstmt.executeUpdate(); //String is sent to the database and then updates the database, REMOVING the selected project from it.
            return projectToDelete;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Project> getAllProjects() throws SQLException {
        ArrayList<Project> allProjects = new ArrayList<>();

        String sql = "SELECT [dbo].[Client].[clientName], [dbo].[Project].*"
                + "FROM [dbo].[Project]"
                + "JOIN [dbo].[Client] ON [dbo].[Client].[clientID] = [dbo].[Project].[clientID]";

        Connection con = connector.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("projectID");
            String name = rs.getString("projectName");
            String clientName = rs.getString("clientName");
            int hRate = rs.getInt("hourlyRate");
            String description = rs.getString("description");
            List<User> userList = projUsDao.getUserProject(id);
            Project proj = new Project(name, hRate, clientName, description, userList);
            proj.setId(rs.getInt("projectID"));
            allProjects.add(proj);

        }
        return allProjects;
    }
// method that updates the values into database
    public Project editProject(int id, String projectName, String clientName, int hourlyRate, String description) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[Project] SET [projectName] = ?,[hourlyRate] = ?, [description] = ? WHERE projectID=?";

            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, projectName);
            pstmt.setInt(2, hourlyRate);
            pstmt.setString(3, description);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
            Project editedProject = new Project(projectName, hourlyRate, clientName, description, null);
            editedProject.setId(id);
            return editedProject;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
