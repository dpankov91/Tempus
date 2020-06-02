package tempus.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DBConnectionProvider is a class. It provides the connection to the database
 *
 * @author Abdiqafar Mohamud Abas Ahmed
 * @author Christian Hansen
 * @author Dmitri Pankov
 * @author Nebojsa Gutic
 * @author Tienesh Kanagarasan
 */
public class DbConnectionProvider {

    private static final String PROP_FILE = "data/databaseConnectionInfo.properties";
    private SQLServerDataSource ds;

    /**
     *Connects to the database
     */
    public DbConnectionProvider() {

        try {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(PROP_FILE));
            ds = new SQLServerDataSource();
            ds.setServerName(databaseProperties.getProperty("Server"));
            ds.setDatabaseName(databaseProperties.getProperty("Database"));
            ds.setUser(databaseProperties.getProperty("User"));
            ds.setPassword(databaseProperties.getProperty("Password"));
        } catch (IOException ex) {
            Logger.getLogger(DbConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *Retrieves the connection of the database
     * @return
     */
    public Connection getConnection() {

        try {
            return ds.getConnection();
        } catch (SQLServerException ex) {
            Logger.getLogger(DbConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
