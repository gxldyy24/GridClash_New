package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import java.sql.SQLException;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.Users;

/**
 * Contains the methods to get all attributes of User.
 * Accesses UserTable from sudoku.
 * @author zlindewirth
 */
public interface RetrieveUserAttributes {

	/**
	 * Retrieves username from user with email entered.
	 * 
	 * @param email the email of a user
	 * @return String of a user name
	 * @throws SQLException
	 */
	String fetchUsername(String email) throws PersistenceException;

	/**
	 * Retrieves code from user with email entered.
	 * 
	 * @param email the email of a user
	 * @return String of the verification code
	 * @throws SQLException
	 */
	String fetchCode(String email) throws PersistenceException;

	/**
	 * Retrieves salt from user with email entered.
	 * 
	 * @param email the email of a user
	 * @return String salt from UsersTable
	 * @throws SQLException
	 */
	byte[] fetchSalt(String email) throws PersistenceException;

	/**
	 * Retrieves hashedPassword from user with email entered.
	 * 
	 * @param email the email of a user
	 * @return String hashedPassword from UsersTable
	 * @throws SQLException
	 */
	byte[] fetchHashedPassword(String email) throws PersistenceException;

	/**
	 * Retrieves email from user with username entered.
	 * 
	 * @param username the username of a user
	 * @return String email from UsersTable
	 * @throws SQLException
	 */
	String fetchEmail(String username) throws PersistenceException;

	/**
	 * Retrieves inGame from user with email entered.
	 * 
	 * @param email the email of a user
	 * @return int inGame from UsersTable
	 * @throws SQLException
	 */
	int fetchInGame(String email) throws PersistenceException;

	/**
	 * Returns the id number of user.
	 * 
	 * @param email the email of a user
	 * @return the id number of the user or null 0 if not found
	 * @throws SQLException
	 */
	int getID(String email) throws PersistenceException;
	
	/**
	 * Returns a User object. 
	 * @param email the email of the user
	 * @return user object
	 * @throws SQLException
	 */
	Users getUser(String email) throws PersistenceException;

}
