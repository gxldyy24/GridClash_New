package edu.skidmore.cs326.game.sudoku.logic;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveFromUsersTable;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveUserAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.insert.BirthdayVerification;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertUserData;
import edu.skidmore.cs326.game.sudoku.persistence.insert.UserValidation;
import edu.skidmore.cs326.game.sudoku.persistence.modify.ChangeAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.modify.ChangeUserData;
import edu.skidmore.cs326.game.sudoku.persistence.security.PasswordVerification;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
/**
 * Logic functionalites for login page.
 * 
 * @author eheidepriem & zlindewirth
 */
public class LoginLogic implements Login {
	
	/**
	 * The logger.
	 */
	private static final Logger LOG =
		Logger.getLogger(LoginLogic.class);

	/**
	 * Interface for retrieve user attributes.
	 */
	private RetrieveUserAttributes fetch = 
			new RetrieveFromUsersTable();
	/**
	 * InsertUserData object.
	 */
	private InsertUserData insert = new InsertAttributes();
	/**
	 * ChangeUserData object. 
	 */
	private ChangeUserData change = new ChangeAttributes();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkLogin(String userEmail, String userPassword) {
		// Code to verify login credentials in database
        // User Interface, not PV class - PasswordSecurity
		PasswordVerification pv = new PasswordVerification();
		try {
			boolean v = pv.checkPasswordMatches(
					userEmail, userPassword);
			int id = fetch.getID(userEmail);
			boolean y = UserValidation.checkUserDeleted(id);
			if (!v) {
			    LOG.info("User's password and username "
			        + "combination was incorrect");
			    
			}
			if (!y) {
			    LOG.info("User can not login "
			        + "because account deleted");
			}
			return (v && y);
		} catch (HeadlessException | PersistenceException e1) {
			e1.getMessage();
			return false;
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(String userEmail) {
		User user = null;
        try {
            user = fetch.getUser(userEmail);
        }
        catch (PersistenceException e1) {
            e1.getMessage();
        }
		return user;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkCreate(String userEmail, 
                        	    String userBirthdayMonth, 
                        	    String userBirthdayDay,
                        	    String userBirthdayYear) {
		switch (userBirthdayMonth) {
		default:
			userBirthdayMonth = "01";
		case "January":
			userBirthdayMonth = "01";
			break;
		case "Febuary":
			userBirthdayMonth = "02";
			break;
		case "March":
			userBirthdayMonth = "03";
			break;
		case "April":
			userBirthdayMonth = "04";
			break;
		case "May":
			userBirthdayMonth = "05";
			break;
		case "June":
			userBirthdayMonth = "06";
			break;
		case "July":
			userBirthdayMonth = "07";
			break;
		case "August":
			userBirthdayMonth = "08";
			break;
		case "September":
			userBirthdayMonth = "09";
			break;
		case "October":
			userBirthdayMonth = "10";
			break;
		case "November":
			userBirthdayMonth = "11";
			break;
		case "December":
			userBirthdayMonth = "12";
			break;
		}
		
		// Use Interface
		BirthdayVerification b = 
			BirthdayVerification.getInstance();
		@SuppressWarnings("unused")
		Date dateofbirth = null;
		try {
			dateofbirth = b.dateFormatter(
				userBirthdayYear, 
				userBirthdayMonth, 
				userBirthdayDay);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkVerify(String userEmail, 
                        	    String userVerificationCode, 
                        	    User user) {
		try {
			// If their acct exists in database,
			// check their database code.
			// If its a new account,
			// check their user interface .code
		    if (!UserValidation.checkEmail(userEmail)) {
    			if (userVerificationCode.equals(
    					fetch.fetchCode(userEmail))) {		
    				return true;
    			}
		    } else {
		        if (userVerificationCode.equals(user.getCode())) {
		            return true;
		        }
		    }
		} catch (PersistenceException  | HeadlessException e1) {
			e1.getMessage();
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean forgotPass(String userEmail, User user) {
		try {
    		if (!UserValidation.isValidEmail(userEmail)) {
    			// Update user.code with new code.
    			// Also sends their email the code		
    			
				change.changeCode(userEmail);
    			return true;	
    		} 
		} catch (PersistenceException e1) {
            e1.getMessage();
        }
		 return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkPassCreate(String userPassword, 
                            	    String userConfirmedPassword, 
                            	    User user) {
        // Use Interface - InsertUserData
		try {
            if (insert.insertUser(user)) {
            	return true;
            }
        }
        catch (PersistenceException e) {
            e.getMessage();
        }
		
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User changeUserCode(User user) {
	    
	    LOG.info("changeUserCode was called");
	    
		try {
			user.setCode(fetch.fetchCode(user.getEmail()));
		} catch (PersistenceException e1) {
			e1.getMessage();
		}
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkForgot(String userEmail) {
		boolean y = false;
		try {
			y = UserValidation.checkEmail(userEmail);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		return y;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkPassReset(String userPassword, 
                            	    String userConfirmedPassword, 
                            	    User user) {
        // Use Interface - InsertUserData
		try {
            if (change.changePassword(user.getEmail(), userPassword)) {
            	return true;
            }
        }
        catch (PersistenceException e) {
            e.getMessage();
        }
		
		return false;
	}
	
	
	
}
