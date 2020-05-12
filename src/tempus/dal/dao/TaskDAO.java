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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tempus.be.Project;
import tempus.be.Task;
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
         
        String sql = "SELECT [dbo].[Project].[projectName], [dbo].[User].[lastName], "
                   + "[dbo].[Task].[task], [dbo].[Task].[date], [dbo].[Task].[spentTime]"
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
                String taskName = rs.getString("task");
                Date createdDate = rs.getDate("date");
                int spentTime = rs.getInt("spentTime");
                
                Task task = new Task(projName, userLastName, taskName, createdDate, spentTime);
                allTasksOverview.add(task);
            }
             
                return allTasksOverview;
    }
    
}
