package edu.skidmore.cs326.game.sudoku.persistence.insert;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * @author ssignore
 * inserts won/lost game into the user_games table.
 */
public interface InsertGameData {

    /**
     * This inserts the game into the datbase.
     * 
     * @return whether the method failed as boolean.
     * @throws PersistenceException
     */
	boolean insertGame() throws PersistenceException;
	
}
