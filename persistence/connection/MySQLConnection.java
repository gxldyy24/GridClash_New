package edu.skidmore.cs326.game.sudoku.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Establishing a connection to MySQL database. Provides the static connect()
 * method to create a connection using JDBC driver with our database
 * credentials.
 * 
 * @author taylort
 */
public class MySQLConnection {

    /**
     * The logger.
     */
    private static final Logger LOG;

    /**
     * Create the logger.
     */
    static {
        LOG = Logger.getLogger(MySQLConnection.class);
    }

    /**
     * Connects to the MySQL database using JDBC driver with provided database
     * credentials.
     * 
     * @return A connection object representing the established database
     *         connection.
     * @throws SQLException
     *             if a database access error occurs
     */
    public static Connection connect() throws SQLException {

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            var jdbcUrl = MySQLConfig.getDbURL();
            var user = MySQLConfig.getDbUsername();
            var password = MySQLConfig.getDbPassword();

            connection = DriverManager.getConnection(jdbcUrl, user, password);

            LOG.info("Connected to MySQL database successfully.");

        }
        catch (SQLException | ClassNotFoundException e) {
            LOG.error("Failed to connect to MySQL database.", e);
        }
        return connection;

    }

    /**
     * Constructor method for MySQLConnection - set to private.
     */
    private MySQLConnection() {

    }
}
