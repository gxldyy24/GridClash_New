package edu.skidmore.cs326.game.sudoku.persistence.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for database operations.
 */
public interface IDatabaseManager {

    /**
     * Gets database connection.
     * 
     * @return Connection object.
     * @throws SQLException For connection errors.
     */
    Connection getDbConnection() throws SQLException;

    /**
     * Closes database connection.
     * 
     * @param connection Connection to close.
     */
    void dbDisconnect(Connection connection);

    /**
     * Deletes user by username.
     * 
     * @param userName User's name.
     */
    void deleteUser(String userName);

    /**
     * Gets user password.
     * 
     * @param username User's name.
     * @return Password string.
     */
    String getPassword(String username);

    /**
     * Blocks an email for a user.
     * 
     * @param userId User ID.
     * @param emailToBlock Email to block.
     * @return True if blocked.
     */
    boolean blockEmail(int userId, String emailToBlock);

    /**
     * Checks if email is blocked.
     * 
     * @param userId User ID.
     * @param emailToCheck Email to check.
     * @return True if blocked.
     */
    boolean isEmailBlocked(int userId, String emailToCheck);

    /**
     * Unblocks an email.
     * 
     * @param userId User ID.
     * @param emailToRemove Email to unblock.
     */
    void removeBlockedEmail(int userId, String emailToRemove);

    /**
     * Fetches user ID by username.
     * 
     * @param username User's name.
     * @return User ID.
     */
    int fetchUserIdByUsername(String username);
}
