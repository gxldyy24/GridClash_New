package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.Users;

/**
 * Contains the methods to get all attributes of User.
 * Accesses UserTable from sudoku.
 * @author zlindewirth
 */
public class RetrieveFromUsersTable implements RetrieveUserAttributes {

	/**
	 * The logger.
	 */
	private static final Logger LOG;

	static {
		LOG = Logger.getLogger(RetrieveFromUsersTable.class);
	}
	
	/**
     * Retrieves username from user with email entered.
     * 
     * @param email the email of a user
     * @return String of a user name
     * @throws SQLException
     */
	@Override
	public String fetchUsername(String email) throws PersistenceException {
		try {
	        var connection = MySQLConnection.connect();
    		String query = "select username from "
    		    + "UsersTable where email = ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			String username = result.getString("username");
    			LOG.info("Retrieved " + username 
    			    + " from user " + email);
    			return username;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "username from the email " + email);
    		}
		} catch (SQLException e) {
		    throw new PersistenceException(
        		        "Could not retrieve username",
        		        e);
		}

		return null;
	}
	
	/**
     * Retrieves code from user with email entered.
     * 
     * @param email the email of a user
     * @return String of the verification code
     * @throws SQLException
     */
	@Override
	public String fetchCode(String email) throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select code from UsersTable where email = ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			String code = result.getString("code");
    			LOG.info("Retrieved " + code + " from user " + email);
    			return code;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "code from the email " + email);
    		}
	    } catch (SQLException e) {
	        throw new PersistenceException("Could not retrieve code", e);
	    }

		return null;
	}
	
	/**
     * Retrieves salt from user with email entered.
     * 
     * @param email the email of a user
     * @return String salt from UsersTable
     * @throws SQLException
     */
	@Override
	public byte[] fetchSalt(String email) throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select * from UsersTable where email= ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    
    		if (result.next()) {
    			String salt = result.getString("salt");
    			byte[] saltByte = Base64.getDecoder().decode(salt);
    			LOG.info("Retrieved " + salt + " from user " + email);
    			return saltByte;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "salt from the email " + email);
    			return null;
    		}
	    } catch (SQLException e) {
	        throw new PersistenceException("Could not retrieve salt", e);
	    }

	}
	
	/**
     * Retrieves hashedPassword from user with email entered.
     * 
     * @param email the email of a user
     * @return String hashedPassword from UsersTable
     * @throws SQLException
     */
	@Override
	public byte[] fetchHashedPassword(String email) 
	                        throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select hashedPassword from "
    		    + "UsersTable where email= ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			String hashedPassword = 
    			    result.getString("hashedPassword");
    			byte[] hashedPasswordByte = 
    			    Base64.getDecoder().decode(hashedPassword);
    			LOG.info("Retrieved " + hashedPassword 
    			    + " from user " + email);
    			return hashedPasswordByte;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "hashedPassword from the email " + email);
    		}
	    } catch (SQLException e) {
	        throw new PersistenceException(
        	            "Could not retrieve hashed password",
        	            e);
	    }

		return null;
	}
	
	/**
     * Retrieves email from user with username entered.
     * 
     * @param username the username of a user
     * @return String email from UsersTable
     * @throws SQLException
     */
	@Override
	public String fetchEmail(String username) throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select email from UsersTable where username= ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, username);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			String email = result.getString("email");
    			LOG.info("Retrieved " 
    			    + email + " from user " + username);
    			return email;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "email from the username " + username);
    		}
	    } catch (SQLException e) {
	        throw new PersistenceException("Could not retrieve email", e);
	    }

		return null;
	}
	
	/**
     * Retrieves inGame from user with email entered.
     * 
     * @param email the email of a user
     * @return int inGame from UsersTable
     * @throws SQLException
     */
	@Override
	public int fetchInGame(String email) throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select inGame from UsersTable where email= ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			int inGame = result.getInt("inGame");
    			LOG.info("Retrieved " + inGame + " from user " + email);
    			return inGame;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "inGame from the email " + email);
    		}
	    } catch (SQLException e) {
	        throw new PersistenceException("Could not retrieve inGame", e);
	    }

		return 0;
	}
	
	/**
     * Returns the id number of user.
     * 
     * @param email the email of a user
     * @return the id number of the user or null 0 if not found
     * @throws SQLException
     */
	@Override
	public int getID(String email) throws PersistenceException {
	    try {
    		var connection = MySQLConnection.connect();
    		String query = "select id from UsersTable where email= ?";
    		PreparedStatement stmt = connection.prepareStatement(query);
    		stmt.setString(1, email);
    		ResultSet result = stmt.executeQuery();
    		if (result.next()) {
    			int id = result.getInt("id");
    			LOG.info("Retrieved " + id + " from user " + email);
    			return id;
    		} else {
    			LOG.info("Could not retrieve "
    			    + "ID from the email " + email);
    		}
	    } catch (SQLException e) {
		    throw new PersistenceException("Could not retrieve id", e);
		}
    
    		return -1;
	}
	
	/**
     * Returns a User object. 
     * @param email the email of the user
     * @return user object
     * @throws SQLException
     */
	@Override
	public Users getUser(String email) throws PersistenceException {
	    try {
	        var connection = MySQLConnection.connect();
	        String query = "select * from UsersTable where email= ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, email);
	        ResultSet result = stmt.executeQuery();
	        if (result.next()) {
	            String username = result.getString("username");
	            String code = result.getString("code");
	            byte[] salt = 
	                Base64.getDecoder().decode(result.getString("salt"));
	            byte[] hashedPassword = 
	                Base64.getDecoder()
	                .decode(result.getString("hashedPassword"));
	            Users user = 
	                new Users(email, username, code, salt, hashedPassword);
	            LOG.info("Retrieved user attributes from user " + email);
	            return user;
	        } else {
	            LOG.info("Could not retrieve user from the email " + email);
	        }
	    } catch (SQLException e) {
	        throw new PersistenceException("Could not retrieve User", e);
	    }
	    return null;

	}

}
