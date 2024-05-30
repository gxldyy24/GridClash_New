package edu.skidmore.cs326.game.sudoku.persistence.security;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;

/**
 * Contains all the methods required to create and use SHA512 
 * for password security. 
 * 
 * @author zlindewirth
 */
public interface PasswordSecurity {
    /**
     * Returns a random byte array to be used as a salt. 
     * @return byte[] salt
     */
	byte[] salt();
	/**
	 * Creates a hashed password for String password 
	 * using a byte array salt.
	 * @param password the password that will be hashed
	 * @param salt the salt used to hash the password
	 * @return byte[] hashedPassword
	 * @throws PersistenceException 
	 */
	byte[] createHash(String password, byte[] salt) 
	    throws PersistenceException;
	/**
	 * Creates a hashed password using the entered password 
	 * and entered salt and compares 
	 * the created hashed password to the entered password.
	 * If the they are equal then the method returns true.  
	 * @param originalHash byte[] used to compare to generated hash
	 * @param password password being tested and used to create new hash
	 * @param salt byte[] used to generate new hash
	 * @return true if old hash is equal to new hash, 
	 * false if old hash is not equal to new hash
	 * @throws PersistenceException 
	 */
	boolean verifyPassword(
	    byte[] originalHash, String password, byte[] salt) 
	        throws PersistenceException;
}
