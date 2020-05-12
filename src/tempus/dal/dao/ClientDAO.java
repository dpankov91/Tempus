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
                String city = rs.getString("clientAddress");
                int phone = rs.getInt("clientPhone");
                String email = rs.getString("clientEmail");
                allClientss.add(new Client(id, name, city, phone, email));
            }
           
            return allClientss;
            
            
       
    }

    public void deleteClient(Client selectedClient) {
try {
            String sql = "DELETE  FROM [dbo].[Client] WHERE clientID=?";

            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, selectedClient.getId());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createClient(String name, String city, int phone, String email) {
        
        try {
            Connection con = connector.getConnection();
            
            String sqlClient = "INSERT INTO [Client] "
                    + "VALUES (?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sqlClient);
            
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, phone);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
