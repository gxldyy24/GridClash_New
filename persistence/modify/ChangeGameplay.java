package edu.skidmore.cs326.game.sudoku.persistence.modify;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all the methods necessary to modify a users gameplay preferences.
 * 
 * Accesses the user_game_preferences table. 
 * 
 * @author zlindewirth
 */
public interface ChangeGameplay {
    /**
     * Changes the users preferred difficulty level. 
     * @param id User id number in the User table
     * @param difficulty 0-'easy', 1-'medium', 3-'hard'
     * @throws PersistenceException 
     */
	void changeDifficulty(int id, int difficulty) 
	                                      throws PersistenceException;
	/**
	 * Changes the users preferred board size. 
	 * @param id User id number in the UsersTable.  
	 * Can acquire the id with email using 
	 * getID(email) in RetrieveFromUserTable class
	 * @param board 0-'4x4', 1-'9x9', 2-'25x25'
	 * @throws PersistenceException 
	 */
	void changeBoardsize(int id, int board) throws PersistenceException;
}
