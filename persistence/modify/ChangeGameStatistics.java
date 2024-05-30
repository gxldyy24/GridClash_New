package edu.skidmore.cs326.game.sudoku.persistence.modify;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all methods to change user game statisitics.
 * @author zlindewirth
 */
public interface ChangeGameStatistics {
    /**
     * Returns the number of wins this user has.
     * @param id id of user
     * @return int wins
     * @throws PersistenceException
     */
    int updateWins(int id) throws PersistenceException;
    /**
     * Returns the number of losses this user has. 
     * @param id id of the user
     * @return int draws
     * @throws PersistenceException
     */
    int updateLosses(int id) throws PersistenceException;
    /**
     * Returns the number of draws this user has.
     * @param id id of the user
     * @return int draws
     * @throws PersistenceException
     */
    int updateDraw(int id) throws PersistenceException;
}
