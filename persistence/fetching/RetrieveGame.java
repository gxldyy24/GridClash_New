package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * interface that contains all methods to retrieve data from user_games.
 * 
 * @author ssignore
 */
public interface RetrieveGame {
	
	/**
	 * method that returns a list of games won by a specific user.
	 * @param user
	 * @return String[]
	 * @throws PersistenceException 
	 */
	String[] fetchWins(int user) throws PersistenceException;
	
	/**
	 * method that returns array of games won by a specific user.
	 * @param user
	 * @return smth
	 * @throws PersistenceException 
	 */
	String[] fetchLosses(int user) throws PersistenceException;
	
	
}
