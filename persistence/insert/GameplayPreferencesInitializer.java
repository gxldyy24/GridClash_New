package edu.skidmore.cs326.game.sudoku.persistence.insert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Adds all User's in UsersTable to the user_game_preferences table when called.
 * 
 * @author zlindewirth
 */
public class GameplayPreferencesInitializer {
    /**
     * This is a utility class, no objects of 
     * this class should be made. 
     */
    private GameplayPreferencesInitializer() {
        
    }
    
    /**
     * The logger.
     */
    private static final Logger LOG;
    
    static {
        LOG = Logger.getLogger(GameplayPreferencesInitializer.class);
    }
    
    /**
     * Adds all User's in UsersTable to the user_game_preferences table 
     * when called.
     * @throws PersistenceException 
     */
	public static void insertUserInGameplayPreferences() 
	    throws PersistenceException {
		try {
			var connection = MySQLConnection.connect();

			String userNotInGameplay = "select id from UsersTable "
			    + "where id not in "
			    + "(select id from user_game_preferences)";
			PreparedStatement stmt;
			stmt = connection.prepareStatement(userNotInGameplay);
			ResultSet rs = stmt.executeQuery();
			LOG.info("Found user that needs to be added into "
			    + "user_game_preferences");

			while (rs.next()) {
				int id = rs.getInt("id");
				String addToGameplay = "insert into "
				    + "user_game_preferences(id, "
				    + "difficulty_level, board_size)"
				    + " values (?, 'easy', '4x4')";
				PreparedStatement insertStatement = 
				    connection.prepareStatement(addToGameplay);
				insertStatement.setInt(1, id);
				insertStatement.executeUpdate();
				LOG.info("added User " + id 
				    + "to user_game_preferences");
			}
		} catch (SQLException e) {
		    throw new PersistenceException("Could not insert user into "
		        + "user_game_preferences", e);
		}
	}
}
