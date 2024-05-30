package edu.skidmore.cs326.game.sudoku.logic;

import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 *  Logic implementation of account settings.
 *  @author vzhao
 */
public interface AccountSettings {
	
	/**
	 * Interface method for getting user.
	 * 
	 * @param userEmail
	 * @return user
	 */
	User getUser(String userEmail);
	
	/**
	 * Interface method for requesting a random display name.
	 * 
	 * @return newDispName
	 */
	String requestDisplayName();
	
	/**
	 * Interface method for saving name/display name changes.
	 * 
	 * @param newDispName
	 * @param userEmail
	 * @return true if successful
	 */
	boolean saveAndApplyChanges(String newDispName, String userEmail);
	
	/**
	 * Interface method for updating password.
	 * @param newPass
	 * @return true if successful
	 */
	boolean updatePassword(String newPass);
	
	/**
	 * Interface method for getting verification code.
	 * 
	 * @param newEmail
	 * @return true if successful
	 */
	String getEmailVeriCode(String newEmail);
	
	/**
	 * Interface method for updating email.
	 * 
	 * @param newEmail
	 * @return true if successful
	 */
	boolean updateEmail(String newEmail);
	
	/**
	 * Interface method for updating name.
	 * 
	 * @param newName
	 * @return true if successful
	 */
	boolean updateName(String newName);
	
	/**
	 * Interface method for updating phone.
	 * 
	 * @param phone
	 * @return true if successful
	 */
	boolean updatePhone(String phone);
	
	/**
	 * Interface method for deleting account.
	 * 
	 * @param email
	 * 
	 * @return true if successful
	 */
	boolean deleteAccount(String email);
}