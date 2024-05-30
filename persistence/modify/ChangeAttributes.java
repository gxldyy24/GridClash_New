package edu.skidmore.cs326.game.sudoku.persistence.modify;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveFromUsersTable;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveUserAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.insert.UserValidation;
import edu.skidmore.cs326.game.sudoku.persistence.security.PasswordSecurity;
import edu.skidmore.cs326.game.sudoku.persistence.security.SHA512;
import edu.skidmore.cs326.game.utility.EmailUtility;

/**
 * Contains methods that change a users attributes in the UsersTable.
 * 
 * @author zlindewirth
 */
public class ChangeAttributes implements ChangeUserData {
	/**
	 * The logger.
	 */
	private static final Logger LOG;

	static {
		LOG = Logger.getLogger(ChangeAttributes.class);
	}
    /**
     * Changes a users email.
     * @param oldEmail the email that must be changed
     * @param newEmail the email that the user wishes to use
     * @throws SQLException 
     */
    @Override
    public void changeEmail(String oldEmail, String newEmail) 
        throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            if (UserValidation.checkEmail(newEmail)) {
                String query  = "update UsersTable set email = ? " 
                                + "where email = ?";  
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                preparedStatement.setString(1, newEmail);
                preparedStatement.setString(2, oldEmail);
                preparedStatement.executeUpdate();
                LOG.info(oldEmail + " changed their email from " + oldEmail 
                    + " to " + newEmail);
            } else {
                LOG.info(oldEmail + " tried changing account's email but "
                    + "the new email was the same as the old email");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error changing email", e);
        }
        
    }
    
    /**
     * Changes a users username.
     * @param email the email associated with the user that wants to 
     * change their username
     * @param newUsername the username that the user wishes to use
     * @throws SQLException 
     */
    @Override
    public void changeUsername(String email, String newUsername) 
        throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            
            if (UserValidation.checkUsername(newUsername)) {
                String query = "update UsersTable set username = ? "
                                + "where email = ?";
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
                LOG.info(email + " changed their username to " + newUsername);
            } else {
                LOG.info(email + " tried changing account's username but "
                    + "the new username was the same as the old username");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error changing username", e);
        }
    }
    
    /**
     * Changes a users Password.
     * @param email the email associated with the user that wants 
     * to change their password
     * @param newPassword the password that the user wishes to use
     * @throws SQLException 
     */
   @Override
    public boolean changePassword(String email, String newPassword) 
        throws PersistenceException {
       boolean changed = false;
        PasswordSecurity security = new SHA512();
        RetrieveUserAttributes user = new RetrieveFromUsersTable();
        
        try {
            var connection = MySQLConnection.connect();
            if (!security.verifyPassword(user.fetchHashedPassword(email), 
                newPassword, user.fetchSalt(email))) {
                String query = "update UsersTable set hashedPassword = ?, "
                    + "salt = ? where email = ?";
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                
                byte[] newSalt = security.salt();
                String newSaltString = 
                    Base64.getEncoder().encodeToString(newSalt);
                byte[] newHashedPassword = null;
                String newHashedPasswordString = null;
                newHashedPassword = 
                    security.createHash(newPassword, newSalt);
                newHashedPasswordString = 
                    Base64.getEncoder().encodeToString(newHashedPassword);
                preparedStatement.setString(1, newHashedPasswordString);
                preparedStatement.setString(2, newSaltString);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                LOG.info(email + " changed their password");
                changed = true;
            } else {
                LOG.info(email + " tried changing account's password "
                    + "but the new password was the same as the old password");
            }
        }
        catch (SQLException e) {
            throw new PersistenceException("Error changing password", e);
        }
        return changed;
    }
    
   /**
    * Changes the user's playing status.
    * 
    * @param email the email of the user that is either entering 
    * or exiting a game
    * @throws SQLException
    */
    @Override
    public void changeInGame(String email) throws PersistenceException {
        RetrieveUserAttributes user = new RetrieveFromUsersTable();
        try {
            var connection = MySQLConnection.connect();
            if (user.fetchInGame(email) == 1) {
                String query = "update UsersTable set inGame = ? "
                                + "where email = ?";
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
                LOG.info(email + " is no longer in a game");
            }
            
            if (user.fetchInGame(email) == 0) {
                String query = "update UsersTable set inGame = ? " 
                                + "where email = ?";
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
                LOG.info(email + " is now in a game");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error changing inGame", e);
        }
        
        
    }
    
    /**
     * Changes the user's verification code and sends email with 
     * this  new verification code.
     * 
     * @param email the email of the user 
     * @throws SQLException
     */
    @Override
    public void changeCode(String email) throws PersistenceException {
        try {
            String newCode = EmailUtility.generateOTP();
            var connection = MySQLConnection.connect();
            String query = "update UsersTable set code = ? where email = ?";
            PreparedStatement preparedStatement = 
                connection.prepareStatement(query);
            preparedStatement.setString(1, newCode);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            EmailUtility.sendOTP(email, newCode);
            LOG.info(email + " code is now " + newCode);
        } catch (SQLException e) {
            throw new PersistenceException("Error changing code", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void changeName(String email, String newName)
        throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            String query = "update UsersTable set name = ? where email = ?";
            PreparedStatement preparedStatement = 
                connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            LOG.info(email + " changed name to " + newName);
        } catch (SQLException e) {
            throw new PersistenceException("Error changing code", e);
        }
        
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void changePhoneNumber(String email, String newPhoneNumber)
        throws PersistenceException {
        if (isValidPhoneNumber(newPhoneNumber)) {
            try {
                var connection = MySQLConnection.connect();
                String query = "update UsersTable set phoneNumber = ? "
                    + "where email = ?";
                PreparedStatement preparedStatement = 
                    connection.prepareStatement(query);
                preparedStatement.setString(1, newPhoneNumber);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
                LOG.info(email + " changed phone number to " + newPhoneNumber);
            } catch (SQLException e) {
                throw new PersistenceException("Error changing "
                    + "phone number ", e);
            }
        } else {
            LOG.info(newPhoneNumber + " is not of the correct format.  "
                + "Must be XXX-XXX-XXXX");
        }
    }
    /**
     * Checks that a phone number is of the format XXX-XXX-XXXX.
     * @param phoneNumber the phone number to be checked
     * @return if the phone number is valid
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\d{3}-\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(String email) throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            String query = "update UsersTable set userDeleted = 1 "
                + "where email = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
            LOG.info("Deleted a user with email " + email);
        } catch (SQLException e) {
            LOG.info("Could not delete user");
            throw new PersistenceException("Could not delete user", e);
        }
        
    }
    
    

}
