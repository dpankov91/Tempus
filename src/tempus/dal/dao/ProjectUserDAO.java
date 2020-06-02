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
import java.util.List;
import tempus.be.User;
import tempus.dal.DbConnectionProvider;

/**
 * The ProjectUserDAO is a class. It can perform CRUD operations on the database 
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class ProjectUserDAO {

    private final DbConnectionProvider connector;

    /**
     *
     * @throws IOException
     */
    public ProjectUserDAO() throws IOException {
        connector = new DbConnectionProvider();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public List<User> getUserProject(int id) throws SQLException {
        List<User> assignedUsers = new ArrayList<>();

        String sql = "SELECT [dbo].[User].[firstName], [dbo].[User].[lastName], [dbo].[UserAndProject].* "
                + "FROM [dbo].[User]\n"
                + "LEFT JOIN [dbo].[UserAndProject] ON [dbo].[UserAndProject].[userID] = [dbo].[User].[userID]\n"
                + "WHERE [dbo].[UserAndProject].[projectID] = ? ORDER BY [dbo].[User].[userID]";
        Connection con = connector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int userId = rs.getInt("userID");
            String fname = rs.getString("firstName");
            String sname = rs.getString("lastName");
            assignedUsers.add(new User(userId, fname, sname));
        }

        return assignedUsers;
    }

}
