package edu.skidmore.cs326.game.sudoku.persistence.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * Inserts User into the UsersTable.
 */
public class InsertAttributes implements InsertUserData {
	/**
	 * The logger.
	 */
	private static final Logger LOG;
	
    static {
        LOG = Logger.getLogger(InsertUserData.class);
    }
    
    /**
     * Inserts user into the UsersTable.
     * @param user user object
     * @return true if inserted
     * @throws PersistenceException 
     */
    @Override
    public boolean insertUser(User user) throws PersistenceException {
        boolean added = false;
        try {
            if ((user.getEmail() != null) 
                && (user.getUsername() != null) 
                && (user.getCode() != null) 
                && (user.getSalt() != null) 
                && (user.getHashedPassword() != null)) { 
                if ((UserValidation.checkEmail(user.getEmail()) 
                    && UserValidation.checkUsername(user.getUsername())) 
                    && UserValidation.checkBlockedEmails(user.getEmail())) {
                    var connection = MySQLConnection.connect();
                    String saltString = 
                        Base64.getEncoder().encodeToString(user.getSalt());
                    String hashedPasswordString = 
                        Base64.getEncoder()
                        .encodeToString(user.getHashedPassword());
                    String query = "insert into "
                        + "UsersTable(email, username, code, "
                        + "salt, hashedPassword) "
                        + "values (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, user.getEmail());
                    stmt.setString(2, user.getUsername());
                    stmt.setString(3, user.getCode());
                    stmt.setString(4, saltString);
                    stmt.setString(5, hashedPasswordString);
                    stmt.executeUpdate();
                    LOG.info("Able to insert " + user.getEmail() 
                    + "into the UsersTable");
                    GameplayPreferencesInitializer
                    .insertUserInGameplayPreferences();
                    UsersGameStatisticsInitializer
                    .insertUserInUsersGameStatistics();
                    added = true;
                    
                } else {
                    LOG.info("Not able to insert " + user.getEmail());
                }
            }
        } 
        catch (SQLException e) {
            throw new PersistenceException("Could not insert user", e);
        }
        return added;
    }
}
