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

    public ClientDAO() {
        this.connector = new DbConnectionProvider();
    }

    public List<Client> getAllClientss() throws SQLException {

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

    public Client deleteClient(Client selectedClient) {
        //In here the user is deleted from the database.
        try {
            String sql = "DELETE  FROM [dbo].[Client] WHERE clientID=?";
        // Sequence statement above deletes the selected client from the clientIDTable.
            Connection con = connector.getConnection(); // sets up connection.
            PreparedStatement pstmt = con.prepareStatement(sql); // Creatss prepared statement.

            pstmt.setInt(1, selectedClient.getId()); // This string goes to the question mark as their values match, cæientID value.

            pstmt.executeUpdate(); //String is sent to the database and then updates the database, REMOVING the selected client from it.
            return selectedClient;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Client createClient(String name, String city, int phone, String email) {

        try {
            Connection con = connector.getConnection();

            String sqlClient = "INSERT INTO [Client] "
                    + "VALUES (?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sqlClient,
                                      Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, phone);
            pstmt.setString(4, email);
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
            Client client = new Client(id, name, city, phone, email);
            return client;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Client editClient(int id, String name, String city, int phone, String email) {
        try {
            String sql = "UPDATE [dbo].[Client] SET [clientName] = ?, [clientAddress] = ?, [clientPhone] = ?, [clientEmail] = ? WHERE clientID=?";

            Connection con = connector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, phone);
            pstmt.setString(4, email);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();
            Client client = new Client(id, name, city, phone, email);
            return client;
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
