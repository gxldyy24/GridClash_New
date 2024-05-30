package edu.skidmore.cs326.game.sudoku.frontend.login;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertUserData;
import edu.skidmore.cs326.game.sudoku.persistence.users.Users;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertAttributes;

/**
 * Insert User as a seperate thread.
 */
public class InsertUserRunnable implements Runnable {
	
	/**
	 * Parent loginwindow.
	 */
	private LoginWindow loginWindow;

	/**
	 * Current user.
	 */
	private Users user;

	/**
	 * Constructor for myRunnable.
	 * 
	 * @param user
	 */
	public void myRunnable(Users user) {
		this.user = user;
	}
	
	/**
	 * Adds user to database.
	 */
	public void run() {
		// Instantiate an object of the InsertUserDataImpl class
		InsertUserData insert = new InsertAttributes();

		// Call the insertEmailAndUser method of the 
		// InsertUserDataImpl object and pass
		// in the user's email and username as parameters
		try {
            if (insert.insertUser(user)) {

            	JOptionPane.showMessageDialog(
            			loginWindow, "User added to database");

            	// Call switchPage go to AccountVerification
            	// switchPage("Account Verification");
            } else {
            	JOptionPane.showMessageDialog(
            		loginWindow, "Error adding user to database");
            }
        }
        catch (HeadlessException e) {
            e.printStackTrace();
        }
        catch (PersistenceException e) {
            e.getMessage();
        }
	}
}
