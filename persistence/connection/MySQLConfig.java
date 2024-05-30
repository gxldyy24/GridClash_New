package edu.skidmore.cs326.game.sudoku.persistence.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * MySQL database configuration to load the properties containing URL, username,
 * password for the database and creates getters for each.
 * 
 * @author taylort
 */
public class MySQLConfig {

    /**
     * The logger.
     */
    private static final Logger LOG;

    /**
     * The properties.
     */
    private static final Properties DB_PROPERTIES = new Properties();

    /**
     * Attempting to find the config.properties and load it into DB_PROPERTIES.
     */
    static {
        LOG = Logger.getLogger(MySQLConfig.class);

        try (InputStream input =
            MySQLConfig.class.getResourceAsStream("config.properties")) {
            if (input == null) {
                LOG.error("Unable to find config.properties");
                System.exit(1);
            }
            // Load the properties file
            DB_PROPERTIES.load(input);
        }
        catch (IOException e) {
            LOG.error("Error loading config.properties", e);
        }
    }

    /**
     * Constructor method without ability for public use.
     */
    private MySQLConfig() {

    }

    /**
     * Gets the database URL from our database properties.
     * 
     * @return The database URL.
     */
    public static String getDbURL() {
        LOG.debug("Getting database URL");
        return DB_PROPERTIES.getProperty("db.url");
    }

    /**
     * Gets the database username from our database properties.
     * 
     * @return The database username.
     */
    public static String getDbUsername() {
        LOG.debug("Getting database username");
        return DB_PROPERTIES.getProperty("db.username");
    }

    /**
     * Gets the database password from our database properties.
     * 
     * @return The database password.
     */
    public static String getDbPassword() {
         LOG.debug("Getting database password");
         String encryptedPassword =
         DB_PROPERTIES.getProperty("db.encryptedpassword");
        
         try {
             LOG.debug("Got up until .decrypt() in getdbpass");
             String decryptedPassword =
                 PasswordEncryption.decrypt(encryptedPassword);
             return decryptedPassword;
         }
         catch (Exception e) {
             LOG.error("Error decrypting database password", e);
             return null; 
         }
    }
}