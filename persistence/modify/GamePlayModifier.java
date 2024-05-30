package edu.skidmore.cs326.game.sudoku.persistence.modify;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all the methods necessary to modify a users gameplay preferences.
 * 
 * Accesses the user_game_preferences table. 
 * 
 * @author zlindewirth
 */
public class GamePlayModifier implements ChangeGameplay {
    
    /**
     * The logger.
     */
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(GamePlayModifier.class);
    }
    
    /**
     * Changes the users preferred difficulty level. 
     * @param id id number in the UsersTable.  
     * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class
     * @param difficulty 0-'easy', 1-'medium', 3-'hard'
     * @throws PersistenceException 
     */
	@Override
	public void changeDifficulty(int id, int difficulty) 
	                                    throws PersistenceException {
		String[] difficultyLevel = { "easy", "medium", "hard" };
		try {
			var connection = MySQLConnection.connect();
			String query = "update user_game_preferences set "
			    + "difficulty_level = ? where id = ";
			PreparedStatement stmt = 
			    connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, difficultyLevel[difficulty]);
			stmt.executeUpdate();
			LOG.info("Updated user" + id 
			    + " preferred difficulty level");
		} catch (SQLException e) {
		    LOG.info("Unable to update");
		    throw new PersistenceException("Could not change difficulty"
                                           + " preference", e);
		}

	}
	
	/**
     * Changes the users preferred board size.
     * @param id User id number in the UsersTable.  
     * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class.
     * @param board 0-'4x4', 1-'9x9', 2-'25x25'
	 * @throws PersistenceException 
     */
	@Override
	public void changeBoardsize(int id, int board) 
	                                    throws PersistenceException {
		String[] size = { "4x4", "9x9", "25x25" };
		try {
			var connection = MySQLConnection.connect();
			String query = "update user_game_preferences set "
			    + "board_size = ? where id = ";
			PreparedStatement stmt = 
			    connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, size[board]);
			stmt.executeUpdate();
			LOG.info("Updated user" + id + " preferred board size");
		} catch (SQLException e) {
		    LOG.info("Unable to update");
			throw new PersistenceException("Could not change "
			                        + "board size preference", e);
		}

	}

}
