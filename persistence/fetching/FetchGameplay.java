package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all the methods to fetch a Users Gameplay preferences.
 * 
 *  Accesses the user_game_preferences in the sudoku database.
 * 
 * @author zlindewirth
 */
public class FetchGameplay implements RetrieveGameplay {
    
    /**
     * The logger.
     */
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(FetchGameplay.class);
    }
    
    /**
     * Get's the user's preferred difficulty level.
     * 
     * @param id User id number in the UsersTable.  
     * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class.
     * @return the user's preferred difficulty level. 
     * @throws PersistenceException 
     */
	@Override
	public String fetchDifficulty(int id) throws PersistenceException {
		try {
			var connection = MySQLConnection.connect();
			String query = "select difficulty_level from "
			    + "user_game_preferences where id=?";
			PreparedStatement stmt = 
			    connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String difficulty = 
				    rs.getNString("difficulty_level");
				LOG.info("Got " + id 
				    + "preffered difficulty level");
				return difficulty;
			} else {
			    LOG.info("Unable to get board size");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error trying "
			    + "to retrieve user's prefferred "
			    + "difficulty level", e);
		}
		return null;

	}
	
	/**
     * Get's the user's preferred board size.
     * @param id User id number in the UsersTable.  
     * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class.
     * @return the user's preferred board size.
	 * @throws PersistenceException 
     */
	@Override
	public String fetchBoardsize(int id) throws PersistenceException {
		try {
			var connection = MySQLConnection.connect();
			String query = "select board_size from "
			    + "user_game_preferences where id=?";
			PreparedStatement stmt = 
			    connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String size = rs.getNString("board_size");
				LOG.info("Got " + id + "preffered board size");
				return size;
			} else {
			    LOG.info("Unable to get board size");
			}
		} catch (SQLException e) {
			throw new PersistenceException("Error trying "
			    + "to retrieve user's preffered "
			    + "board size", e);
		}
		return null;

	}

}
