package edu.skidmore.cs326.game.sudoku.persistence.insert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Check to see if user is old enough to create an account.
 * 
 * @author zlindewirth
 */
public class BirthdayVerification {
    
    /**
     * The logger.
     */
    private static final Logger LOG;
    
    static {
        LOG = Logger.getLogger(BirthdayVerification.class);
    }
    /**
     * Stored to track to the number of BirthdayVerification calls.
     */
	private static BirthdayVerification instance;
	
	/**
	 * Constructor.
	 */
	private BirthdayVerification() {
	}
	
	/**
	 * Checks to see if BirthdayVerification has been called before.
	 * Can only be called once.
	 * @return BirthdayVerification object
	 */
	public static BirthdayVerification getInstance() {
		if (instance == null) {
			instance = new BirthdayVerification();
		}
		return instance;
	}
	
	/**
	 * Formats year, month, day textbox responses into a date object.
	 * 
	 * @param year birth year
	 * @param month birth month
	 * @param day birthday
	 * @return date object of user birthday
	 * @throws ParseException
	 */
	public Date dateFormatter(String year, String month, String day) 
	    throws ParseException {

		String dateString = year + "-" + month 
		    + "-" + day;
		SimpleDateFormat dateFormat = 
		    new SimpleDateFormat("yyyy-MM-dd");
		Date userBirthday = dateFormat.parse(dateString);
		return userBirthday;

	}

	/**
	 * Returns a boolean that determines whether or not 
	 * the player is old enough to create an account 
	 * (True = old enough to play).
	 * 
	 * @param dateOfBirth date object of user birthday 
	 * @return a boolean oldEnough which is true if the user is old enough 
	 * to create account
	 */
	public boolean userOfAge(Date dateOfBirth) {

		boolean oldEnough = false;

		LocalDateTime potentialBirthday = 
		    LocalDateTime.ofInstant(dateOfBirth.toInstant(),
				java.time.ZoneId.systemDefault());
		LocalDateTime currentDate = LocalDateTime.now();
		Duration age = Duration.between(potentialBirthday, currentDate);
		if ((age.toDays() / 365) >= 14) {
			oldEnough = true;
			LOG.info("User is old enough to create account");
		} else {
			LOG.info("User is not old enough to create an account");
		}
		
		return oldEnough;
	}
}
