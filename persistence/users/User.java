package edu.skidmore.cs326.game.sudoku.persistence.users;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Represents a User object. 
 * 
 * Designed to be used mainly to create user, log in, and edit user settings
 * 
 * @author zlindewirth
 */
public interface User {
	/**
     * Returns the user's email.
     * @return String user's email
     */
	String getEmail();
	/**
	 * Returns the user's code.    
	 * @return String code
	 */
	String getCode();
	/**
	 * Returns the user's username.
	 * @return String username
	 */
	String getUsername();
	/**
	 * Returns the user's online status.  
	 * @return int 1 or 0; 1 = in a game, 0 = not in a game
	 */
	int getInGame();
	/**
	 * Returns the user's hashedPassword.
	 * @return byte[] 
	 */
	byte[] getHashedPassword();
	/**
	 * Returns the user's salt.
	 * @return byte[]
	 */
	byte[] getSalt();
	/**
	 * Uses SHA512 to create a hashedPassword for security.
	 * @param password String user's password
	 * @throws PersistenceException 
	 */
    void setPassword(String password) throws PersistenceException;
    /**
     * Called when initializing account.
     * Generates random username, random code, and sends code to email address
     * @param e
     * @throws PersistenceException 
     */
    void userCreateAccount(String e) throws PersistenceException;
    /**
     * Called when the resend code button is hit.
     * @param email the email the new code should be sent to
     * @throws PersistenceException 
     */
    void setCode(String email);
    
	/**
     * Sets the user's email.
     * @param email
     */
    void setEmail(String email);
}
