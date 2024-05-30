package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all the methods to fetch a Users Gameplay preferences.
 * 
 *  Accesses the user_game_preferences in the sudoku database.
 * 
 * @author zlindewirth
 */
public interface RetrieveGameplay {
    /**
     * Get's the user's preferred difficulty level.
     * 
     * @param id User id number in the UsersTable.  
     * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class.
     * @return the user's preferred difficulty level. 
     * @throws PersistenceException 
     */
	String fetchDifficulty(int id) throws PersistenceException;
	/**
	 * Get's the user's preferred board size.
	 * @param id User id number in the UsersTable.  
	 * Can acquire the id with email using 
     * getID(email) in RetrieveFromUserTable class.
	 * @return the user's preferred board size.
	 * @throws PersistenceException 
	 */
	String fetchBoardsize(int id) throws PersistenceException;
}
