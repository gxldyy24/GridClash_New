package edu.skidmore.cs326.game.sudoku.logic;

import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * Logic implementation of login.
 */
public interface Login {
	
	/**
	 * Interface method for loginPage login btn.
	 * 
	 * @param userEmail the users email
	 * @param userPassword the users password
	 * 
	 * @return v
	 */
	boolean checkLogin(String userEmail, String userPassword);
	
	/**
	 * Interface method for switching page.
	 * 
	 * @param userEmail
	 * 
	 * @return user
	 */
	User getUser(String userEmail);
	
	/**
	 * Interface method for loginPage login btn.
	 * 
	 * @param userEmail
	 * @param userBirthdayMonth
	 * @param userBirthdayDay
	 * @param userBirthdayYear
	 * 
	 * @return v
	 */
	boolean checkCreate(String userEmail, String userBirthdayMonth, 
		String userBirthdayDay, String userBirthdayYear);
	
	/**
	 * Interface method for acct verification.
	 * 
	 * @param userEmail
	 * @param userVerificationCode
	 * @param user the user object
	 * 
	 * @return v
	 */
	boolean checkVerify(String userEmail, String userVerificationCode,
	    User user);
	
	/**
	 * Interface method for forgoPass logic.
	 * 
	 * @param userEmail
	 * @param user
	 * 
	 * @return v
	 */
	boolean forgotPass(String userEmail, User user);
	
	/**
	 * Interface method for forgot password.
	 * 
	 * @param userPassword
	 * @param userConfirmedPassword
	 * @param user
	 * 
	 * @return v
	 */
	boolean checkPassCreate(String userPassword, 
                    	    String userConfirmedPassword, 
                    	    User user);
	
	/**
	 * Interface method for updating code.
	 * 
	 * @param user
	 * 
	 * @return user
	 */
	User changeUserCode(User user);
	
	/**
	 * Interface method for acct verification.
	 * 
	 * @param userEmail
	 * 
	 * @return v
	 */
	boolean checkForgot(String userEmail);

	/**
	 * Interface method for acct verification.
	 * 
	 * @param userPassword
	 * @param userConfirmedPassword
	 * @param user
	 * 
	 * @return v
	 */
	boolean checkPassReset(
	    String userPassword, 
	    String userConfirmedPassword, 
	    User user);
}
