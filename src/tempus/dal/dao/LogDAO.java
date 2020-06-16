/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import tempus.dal.DbConnectionProvider;

/**
 * The LogDAO is a class. It can perform CRUD operations on the database 
 * Anything CRUD related happens in here:
 * CRUD = CREATE, READ, UPDATE & DELETE
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class LogDAO {
    
    /**
     *
     */
    public static final String CLIENT_TABLE = "Client";

    /**
     *
     */
    public static final String PROJECT_TABLE = "Project";

    /**
     *
     */
    public static final String TASK_TABLE = "Task";

    /**
     *
     */
    public static final String USER_TABLE = "User";

    /**
     *
     */
    public static final String USER_AND_PROJECT_TABLE = "UserAndProject";
    
    /**
     *
     */
    public static final String CREATE_ACTION = "Create";

    /**
     *
     */
    public static final String UPDATE_ACTION = "Update";

    /**
     *
     */
    public static final String DELETE_ACTION = "Delete";
    
    private DbConnectionProvider provider;
    
    /**
     *
     */
    public LogDAO()
    {
        provider = new DbConnectionProvider();
    }
    // method that inserts log into database

    /**
     *
     * @param table
     * @param changedEntryId
     * @param action
     * @param actionTime
     * @throws SQLException
     */
    public void insertLog(String table, int changedEntryId, String action, Date actionTime) throws SQLException
    {
        System.out.println(table+" "+changedEntryId+" "+action+" "+actionTime);
        String sql = "INSERT INTO [Log] VALUES(?,?,?,?)";
        Connection con = provider.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, table);
        pstmt.setInt(2, changedEntryId);
        pstmt.setString(3, action);
        pstmt.setDate(4, actionTime);
        pstmt.executeUpdate();
    }
    
}
