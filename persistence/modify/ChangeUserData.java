package edu.skidmore.cs326.game.sudoku.persistence.modify;

import java.sql.SQLException;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Used to modify Users attributes stored in the UsersTable.
 * 
 * @author zlindewirth
 */
public interface ChangeUserData {
	/**
	 * Changes the user's email and sends verification code to 
	 * the new email address.
	 * 
	 * @param oldEmail the email the user wishes to change
	 * @param newEmail the new email the user wishes to use
	 * @throws SQLException
	 */
	void changeEmail(String oldEmail, String newEmail) 
	    throws PersistenceException;

	/**
	 * Changes the user's verification code and sends email with 
	 * this  new verification code.
	 * 
	 * @param email the email of the user
	 * @throws SQLException
	 */
	void changeCode(String email) 
	    throws PersistenceException;

	/**
	 * Changes the user's playing status.
	 * 
	 * @param email the email of the user that is either entering 
	 * or exiting a game
	 * @throws SQLException
	 */
	void changeInGame(String email) 
	    throws PersistenceException;

	/**
	 * changes the users password by assigning new salt and hashedPassword 
	 * to the UsersTable.
	 * 
	 * @param email the email of the user
	 * @param newPassword the new password
	 * @return boolean if the password was changed, true
	 * @throws SQLException
	 */
	boolean changePassword(String email, String newPassword) 
	    throws PersistenceException;
	/**
	 * Changes the users username in the UsersTable.
	 * @param email the email of the user
	 * @param newUsername the new username
	 * @throws SQLException
	 */
	void changeUsername(String email, String newUsername) 
	    throws PersistenceException;
	/**
	 * Add's or edits the users name in the sudoku database.
	 * @param email the email of the user
	 * @param newName the users name
	 * @throws PersistenceException
	 */
	void changeName(String email, String newName) 
	    throws PersistenceException;
	/**
	 * Add's or edits the users phone number in the sudoku database.
	 * @param email the email of the user
	 * @param newPhoneNumber the user's phone number
	 * @throws PersistenceException
	 */
	void changePhoneNumber(String email, String newPhoneNumber) 
	    throws PersistenceException;
	/**
	 * Delete's a user from User's table.
	 * @param email the user's email
	 * @throws PersistenceException
	 */
	void deleteUser(String email) throws PersistenceException;
}
