package edu.skidmore.cs326.game.sudoku.persistence.insert;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
/**
 * Inserts User into the UsersTable.
 */
public interface InsertUserData {
    /**
     * Inserts user into the UsersTable.
     * @param user user object
     * @return true if inserted
     * @throws PersistenceException 
     */
    boolean insertUser(User user) throws PersistenceException;
}
