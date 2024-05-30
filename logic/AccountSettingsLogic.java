package edu.skidmore.cs326.game.sudoku.logic;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.home.RandomDisplayNameGenerator;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveFromUsersTable;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveUserAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.modify.ChangeAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.modify.ChangeUserData;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import edu.skidmore.cs326.game.utility.EmailUtility;

/**
 * Account settings functionalities for account settings page.
 * 
 * @author vzhao
 */
public class AccountSettingsLogic implements AccountSettings {

	/**
	 * The logger.
	 */
	private static final Logger LOG = 
			Logger.getLogger(AccountSettingsLogic.class);

	/**
	 * Interface for changing user data.
	 */
	private ChangeUserData userDataChanger = new ChangeAttributes();

	/**
	 * Interface for retrieving user attributes.
	 */
	private RetrieveUserAttributes fetch = new RetrieveFromUsersTable();

	/**
	 * Current signed in user.
	 */
	private User user;

	/**
	 * {@inheritDoc}
	 */
	public User getUser(String userEmail) {
		user = null;
		try {
			user = fetch.getUser(userEmail);
		} catch (PersistenceException e1) {
			LOG.error(e1.getMessage());
		}
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public String requestDisplayName() {

		String newDispName = null;
		try {
			newDispName = RandomDisplayNameGenerator.
				generateRandomDisplayName();
		} catch (PersistenceException e1) {
			LOG.error(e1.getMessage());
		} return newDispName;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param userEmail
	 * @param newDispName
	 * @return true if changed successfully
	 */
	public boolean saveAndApplyChanges(
        	    String newDispName, String userEmail) {
	    
		try {
			userDataChanger.changeUsername(
				user.getEmail(), newDispName);
			user = fetch.getUser(userEmail);
			if (newDispName.equalsIgnoreCase(user.getUsername())) {
				return true;
			}
		} catch (PersistenceException e1) {
			LOG.error(e1.getMessage());
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param newPass
	 * @return true if changed successfully
	 */
	public boolean updatePassword(String newPass) {
		try {
			return userDataChanger.changePassword(
					user.getEmail(), newPass);
		} catch (PersistenceException e1) {
			LOG.error(e1.getMessage());
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param newEmail
	 * @return newCode
	 */
	public String getEmailVeriCode(String newEmail) {
		String newCode = EmailUtility.generateOTP();
		EmailUtility.sendOTP(newEmail, newCode);
		return newCode;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param newEmail
	 * @return 
	 */
	public boolean updateEmail(String newEmail) {
		try {
			userDataChanger.changeEmail(user.getEmail(), newEmail);
			user = fetch.getUser(newEmail);
			if (newEmail.equalsIgnoreCase(user.getEmail())) {
				return true;
			}

		} catch (PersistenceException e1) {
			LOG.error(e1.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateName(String newName) {
		try {
			userDataChanger.changeName(user.getEmail(), newName);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePhone(String phone) {
		try {
			userDataChanger.changePhoneNumber(
				user.getEmail(), phone);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccount(String email) {
		try {
			userDataChanger.deleteUser(email);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
}
