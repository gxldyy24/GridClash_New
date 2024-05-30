package edu.skidmore.cs326.game.sudoku.persistence.security;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Checks checks that the correct password was entered for login.
 * @author Sebastian & zlindewirth 
 */
public class PasswordVerification {

	/**
	 * The logger.
	 */
	private static final Logger LOG = 
	    Logger.getLogger(PasswordVerification.class);

	/**
	 * Retrieves the password for a given email from the database.
	 *
	 * @param email The email of the desired user.
	 * @param typedPassword the password entered
	 * @return The password as a String, or null if not found or on error.
	 **/
	public boolean checkPasswordMatches(String email, String typedPassword) 
	    throws PersistenceException {
		PasswordSecurity security = new SHA512();
		//Can make this method way shorter and faster 
		// using methods from RetrieveFromUsersTable
		String query = "SELECT salt, hashedPassword FROM "
		    + "UsersTable WHERE email = ?";
		try {
    		var connection = MySQLConnection.connect();
    		PreparedStatement pstmt = connection.prepareStatement(query);
    		pstmt.setString(1, email);
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
    			String storedHashedPassword = 
    			    rs.getString("hashedPassword");
    			byte[] storedHPByte = 
    			    Base64.getDecoder().decode(storedHashedPassword);
    			String storedSalt = rs.getString("salt");
    			byte[] storedSaltByte = 
    			    Base64.getDecoder().decode(storedSalt);
    			if ((storedHPByte != null) 
    			    && (storedSaltByte != null)) {
    				if (security.verifyPassword(
                        storedHPByte, 
                        typedPassword, storedSaltByte)) {
                    	LOG.info("User successfully "
                    	    + "logged in");
                    	return true;
                    }
    			}
    		}
		} catch (SQLException e) {
		    throw new PersistenceException("Could not check password",
		                                    e);
		}
		LOG.info("User could not log in");
		return false;
	}
}