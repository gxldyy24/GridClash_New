package edu.skidmore.cs326.game.sudoku.persistence.insert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Inserts user's into the users_game_statistics 
 * table as they are inserted into the UsersTable.
 * 
 * @author zlindewirth
 */
public class UsersGameStatisticsInitializer {
    /**
     * This is a utility class, no objects of 
     * this class should be made. 
     */
    private UsersGameStatisticsInitializer() {
        
    }
    
    /**
     * The logger.
     */
    private static final Logger LOG;
    
    static {
        LOG = Logger.getLogger(UsersGameStatisticsInitializer.class);
    }
    
    /**
     * Adds all User's in UsersTable to the users_game_statistics table 
     * when called.
     * @throws PersistenceException 
     */
    public static void insertUserInUsersGameStatistics() 
        throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();

            String userNotInGameplay = "select id from UsersTable "
                + "where id not in "
                + "(select id from users_game_statistics)";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(userNotInGameplay);
            ResultSet rs = stmt.executeQuery();
            LOG.info("Found user that needs to be added into "
                + "users_game_statistics");

            while (rs.next()) {
                int id = rs.getInt("id");
                String addToGameplay = "insert into "
                    + "users_game_statistics(id)"
                    + " values (?)";
                PreparedStatement insertStatement = 
                    connection.prepareStatement(addToGameplay);
                insertStatement.setInt(1, id);
                insertStatement.executeUpdate();
                LOG.info("added User " + id 
                    + "to users_game_statistics");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Could not insert user into "
                + "users_game_statistics", e);
        }
    }
}
