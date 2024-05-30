package edu.skidmore.cs326.game.sudoku.persistence.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Contains all the methods required to create and 
 * use SHA512 for password security. 
 * 
 * @author zlindewirth
 */
public class SHA512 implements PasswordSecurity {
    /**
     * The length of the array storing the salt.
     */
	private static int saltSize = 10;
	
	/**
     * The logger.
     */
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(SHA512.class);
    }
	
    /**
     * {@inheritDoc}
     */
	@Override
	public byte[] salt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[saltSize];
		random.nextBytes(salt);
		LOG.info("Generated new Salt");
		return salt;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public byte[] createHash(String password, byte[] salt) 
	    throws PersistenceException {
	    try {
    		MessageDigest messageDigest = 
    		    MessageDigest.getInstance("SHA-512");
    		messageDigest.reset();
    
    		byte[] bytePassword = password.getBytes(StandardCharsets.UTF_8);
    		byte[] passwordWithSalt = 
    		    new byte[bytePassword.length + salt.length];
    		System.arraycopy(bytePassword, 0, passwordWithSalt, 
    		    0, bytePassword.length);
    		System.arraycopy(salt, 0, passwordWithSalt, 
    		    bytePassword.length, salt.length);
    
    		messageDigest.update(passwordWithSalt);
    		byte[] hash = messageDigest.digest();
    		for (int i = 0; i < 1000; i++) {
    			messageDigest.reset();
    			hash = messageDigest.digest(hash);
    		}
    		LOG.info("Generating new hash");
    		return hash;
	    } catch (NoSuchAlgorithmException e) {
	        throw new PersistenceException("Can not create new hash", e);
	    }
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public boolean verifyPassword(
	    byte[] originalHash, String password, byte[] salt) 
	        throws PersistenceException {
	    byte[] compare = createHash(password, salt);
        if (originalHash.length != compare.length) {
            LOG.info("Password incorrect");
        	return false;
        }
   
        for (int i = 0; i < originalHash.length; i++) {
        	if (originalHash[i] != compare[i]) {
        	    LOG.info("Password incorrect");
        		return false;
        	}
        }
        LOG.info("Password Correct");
        return true;
	}

}
