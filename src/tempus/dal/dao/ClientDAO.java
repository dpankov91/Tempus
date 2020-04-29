/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempus.dal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tempus.be.Client;
import tempus.dal.DbConnectionProvider;

/**
 *
 * @author dpank
 */
public class ClientDAO {
    
    private final DbConnectionProvider connector;

    public ClientDAO() 
    {
        this.connector = new DbConnectionProvider();
    }


    public List<Client> getAllClientss() throws SQLException 
    {

        List<Client> allClientss = new ArrayList<>();

        String sql = "Select * From [dbo].[Client]";
        
            Connection con = connector.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("clientID");
                String name = rs.getString("clientName");
                String city = rs.getString("clientCity");
                int phone = rs.getInt("clientPhone");
                allClientss.add(new Client(id, name, city, phone));
            }
           
            return allClientss;
            
            
       
    }
    
    
    
}
