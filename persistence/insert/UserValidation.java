package edu.skidmore.cs326.game.sudoku.persistence.insert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Check's if username or email are already in the database.
 * 
 * @author zlindewirth
 */
public class UserValidation {
    /**
     * This is a utility class, no objects of 
     * this class should be made. 
     */
    private UserValidation() {
        
    }
	/**
	 * The logger.
	 */
	private static final Logger LOG;

	static {
		LOG = Logger.getLogger(UserValidation.class);
	}
	/**
	 * Checks if email is in the UsersTable.
	 * @param email the users email
	 * @return boolean true email not in UsersTable
	 * @throws PersistenceException 
	 */
	public static boolean isValidEmail(String email) 
	    throws PersistenceException {
	    boolean valid = false;
	    valid = (checkEmail(email) && checkBlockedEmails(email));
	    return valid;
	}

	/**
	 * Checks if an email is already in the UsersTable 
	 * in the sudoku database.
	 * 
	 * @param checkEmail the email we are looking for in UsersTable
	 * @return boolean emailInUserTable (True if email is in the UserTable)
	 * @throws PersistenceException 
	 */
	public static boolean checkEmail(String checkEmail) 
	    throws PersistenceException {
		boolean isAvailable = true;
		try {
    		var connection = MySQLConnection.connect();
    
    		String query = "select * from UsersTable where email = ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, checkEmail);
    		ResultSet result = stmt.executeQuery();
    
    		if (result.next()) {
    			isAvailable = false;
    			LOG.info(checkEmail + " is already in the UsersTable");
    		} else {
    			LOG.info(checkEmail + " is not in the UsersTable");
    		}
    
    		return isAvailable;
		} catch (SQLException e) {
		    throw new PersistenceException("Could not check email", e);
		}

	}

	/**
	 * Verify the presence of the username in the UsersTable.
	 * 
	 * @param checkUsername the username we are looking for in UsersTable
	 * @return boolean usernameInUserTable 
	 * (True if username is in UsersTable)
	 * @throws SQLException
	 */

	public static boolean checkUsername(String checkUsername) 
	    throws PersistenceException {
		boolean isAvailable = true;
		try {
    		var connection = MySQLConnection.connect();
    
    		String query = "select * from UsersTable where username = ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, checkUsername);
    		ResultSet result = stmt.executeQuery();
    
    		if (result.next()) {
    		    isAvailable = false;
    			LOG.info(checkUsername 
    			    + " is already in the UsersTable");
    		} else {
    			LOG.info(checkUsername + " is not in the UsersTable");
    		}
    
    		return isAvailable;
		} catch (SQLException e) {
		    throw new PersistenceException(
            		        "Could not check username",
            		        e);
		}

	}
	/**
	 * Check's if email is in the blocked_emails table.
	 * @param checkEmail email to be checked
	 * @return true if email is not in blocked_emails
	 * @throws SQLException
	 */
	public static boolean checkBlockedEmails(String checkEmail) 
	    throws PersistenceException {
		boolean isAvailable = true;
		try {
    		var connection = MySQLConnection.connect();
    
    		String query = 
    		    "select * from blocked_emails where emailBlocked=?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, checkEmail);
    		ResultSet result = stmt.executeQuery();
    
    		if (result.next()) {
    		    isAvailable = false;
    			LOG.info(checkEmail + " is blocked");
    		} else {
    			LOG.info(checkEmail + " is not in blocked_emails");
    		}
    
    		return isAvailable;
		} catch (SQLException e) {
		    throw new PersistenceException("Could not "
		        + "check blocked emails", e);
		}

	}
	/**
	 * Checks if a user has been deleted.
	 * @param id the id of the deleted user
	 * @return false if user has been deleted
	 * @throws PersistenceException
	 */
	public static boolean checkUserDeleted(int id)
	    throws PersistenceException {
	    boolean activeAccount = true;
        try {
            var connection = MySQLConnection.connect();
            String query = 
                "select userDeleted from UsersTable where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int accountStatus = result.getInt("userDeleted");
                if (accountStatus == 1) {
                    activeAccount = false;
                    LOG.info(id + " deleted there account");
                } else {
                    LOG.info(id + " is not deleted");
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("Could not "
                + "check userDeleted column", e);
        }
        return activeAccount;
	}
}
