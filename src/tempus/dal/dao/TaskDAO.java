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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tempus.be.Project;
import tempus.be.Task;
import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author dpank
 */
public class TaskDAO {
    
    private final DbConnectionProvider connector;

    
    public TaskDAO() throws IOException
    {
        this.connector = new DbConnectionProvider();
    }

    public List<Task> getAllTasksOverview() throws SQLException 
    {
         ArrayList<Task> allTasksOverview = new ArrayList<>();
         
             String sql = "SELECT [dbo].[Project].[projectName], [dbo].[User].[lastName], [dbo].[User].[firstName], "
                   + "[dbo].[Task].*"
                     + "FROM [dbo].[Task]"
                      + "JOIN [dbo].[User] ON [dbo].[User].[userID] = [dbo].[Task].[userID]"
                        + "JOIN [dbo].[Project] ON [dbo].[Project].[projectID] = [dbo].[Task].[projectID]";
        
        Connection con = connector.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) 
            {
                String projName = rs.getString("projectName");
                String userLastName = rs.getString("lastName");
                String userFirstName = rs.getString("firstName");
                String taskName = rs.getString("task");
                String note = rs.getString("note");
                LocalDateTime startTime = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
                int spentTime = rs.getInt("spentTime");
                
                Task task = new Task(projName, userLastName, userFirstName,  taskName, note, startTime, endTime, spentTime);

                allTasksOverview.add(task);
            }
             
                return allTasksOverview;
    }
    
    /*
    public void saveTime(Project selectedItem, User loggedInUser, LocalDateTime startTime, LocalDateTime endTime, long totalSeconds, String note, String text) throws SQLException {

        String sql = "INSERT INTO [Task] VALUES (?,?,?,?,?,?,?,?,?)";
        
        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        //ResultSet rs = stmt.executeQuery();
        
        pstmt.setInt(1, selectedItem.getId());

        long millis = startTime.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
        Date date = new Date(millis);

        
        java.sql.Date sqlDate = java.sql.Date.valueOf(date.getTime() );
        
        pstmt.setDate(3, date);
        pstmt.setString(4, endTime);
        pstmt.setInt(5, phone);
        pstmt.setString(6, address);
        pstmt.setInt(7, postcode);
    }
    
    */

    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
