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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tempus.be.Project;
import tempus.be.Task;
import tempus.be.User;
import tempus.dal.DbConnectionProvider;
import java.time.LocalDateTime;

/**
 *The TaskDAO is a class. It can perform CRUD operations on the database
 * Anything CRUD related happens in here:
 * CRUD = CREATE, READ, UPDATE & DELETE
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class TaskDAO {

    private final DbConnectionProvider connector;

    /**
     *
     * @throws IOException
     */
    public TaskDAO() throws IOException {
        this.connector = new DbConnectionProvider();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Task> getAllTasksOverview() throws SQLException {
        ArrayList<Task> allTasksOverview = new ArrayList<>();

        String sql = "SELECT [dbo].[Project].[projectName], [dbo].[User].[lastName], [dbo].[User].[firstName], "
                + "[dbo].[Task].*"
                + "FROM [dbo].[Task]"
                + "JOIN [dbo].[User] ON [dbo].[User].[userID] = [dbo].[Task].[userID]"
                + "JOIN [dbo].[Project] ON [dbo].[Project].[projectID] = [dbo].[Task].[projectID]";

        Connection con = connector.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String projName = rs.getString("projectName");
            String userLastName = rs.getString("lastName");
            String userFirstName = rs.getString("firstName");
            String taskName = rs.getString("task");
            String note = rs.getString("note");
            LocalDateTime startTime = rs.getTimestamp("startTime").toLocalDateTime();
            LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
            int spentTime = rs.getInt("spentTime");

            Task task = new Task(projName, userLastName, userFirstName, taskName, note, startTime, endTime, spentTime);
task.setId(id);
            allTasksOverview.add(task);
        }

        return allTasksOverview;
    }
//method that updates new data into database

    /**
     *
     * @param id
     * @param name
     * @param startTime
     * @param endTime
     * @param note
     * @param spentTime
     * @throws SQLException
     */
    public void editTask(int id, String name, LocalDateTime startTime, LocalDateTime endTime, String note, double spentTime) throws SQLException {

        String sql = "UPDATE [dbo].[Task] SET [task] = ?, [startTime] = ?, [endTime] = ?, [note] = ?, [spentTime] = ? WHERE id=?";

        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        pstmt.setTimestamp(2, startTimeStamp);
        Timestamp endTimeStamp = Timestamp.valueOf(endTime);
        pstmt.setTimestamp(3, endTimeStamp);
        pstmt.setString(4, note);
        pstmt.setDouble(5, spentTime);
        pstmt.setInt(6, id);

        pstmt.executeUpdate();

    }

    /**
     *
     * @param selectedProject
     * @param taskName
     * @param note
     * @param loggedUser
     * @param startTime
     * @param endTime
     * @param spentSeconds
     * @throws SQLException
     */
    public void saveStoppedTask(Project selectedProject, String taskName, String note, User loggedUser, LocalDateTime startTime, LocalDateTime endTime, long spentSeconds) throws SQLException {
        String sql = "INSERT INTO [Task] VALUES (?,?,?,?,?,?,?)";

        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, selectedProject.getId());
        pstmt.setString(2, taskName);
        pstmt.setString(3, note);
        Timestamp startTimeStamp = Timestamp.valueOf(startTime);
        pstmt.setTimestamp(4, startTimeStamp);
        Timestamp endTimeStamp = Timestamp.valueOf(endTime);
        pstmt.setTimestamp(5, endTimeStamp);
        pstmt.setFloat(6, spentSeconds);
        pstmt.setInt(7, loggedUser.getId());

        pstmt.executeUpdate();
    }

    /**
     *
     * @param selectedTask
     * @throws SQLException
     */
    public void deleteClient(Task selectedTask) throws SQLException {
            String sql = "DELETE  FROM [dbo].[Task] WHERE id=?";

            System.out.println(selectedTask.getId());
            
            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, selectedTask.getId());

            pstmt.executeUpdate();
    }
        
}
