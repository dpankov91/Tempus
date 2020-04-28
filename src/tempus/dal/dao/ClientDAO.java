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

    public ClientDAO() {
        this.connector = new DbConnectionProvider();
    }

    public List<Client> getAllClientNames() throws SQLException {
        
       List<Client> allClientNames = new ArrayList<>();

        String sql = "Select [clientName] From [dbo].[Client]";
        System.out.println("dao");
            Connection con = connector.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("clientName");

                allClientNames.add(new Client(name));
            System.out.println("dao2");
            return allClientNames;
      
    }
            System.out.println("dao3");
        return null;
    }
    
    
    
}
