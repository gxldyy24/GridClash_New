package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all methods needed to retrieve values from the 
 * users_game_statistics table.
 * 
 * @author zlindewirth
 */
public interface RetrieveUsersGameStatistics {
    /**
     * fetch's a user's total wins.
     * @param id the id associated with the user
     * @return int wins
     * @throws PersistenceException 
     */
    int fetchWins(int id) throws PersistenceException;
    /**
     * fetch's a user's total losses.
     * @param id the id associated with the user
     * @return int losses
     * @throws PersistenceException
     */
    int fetchLosses(int id) throws PersistenceException;
    /**
     * fetch's a user's total draws.
     * @param id the id associated with the user
     * @return int draws
     * @throws PersistenceException
     */
    int fetchDraws(int id) throws PersistenceException;
}
