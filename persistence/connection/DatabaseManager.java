package edu.skidmore.cs326.game.sudoku.persistence.connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


//This was being used in the original first sprint, this import may change.
//import edu.skidmore.cs326.game.sudoku.persistence.users.Users;


    /**
     * Utility class for managing database operations.
     * @author Bernardo Sa
     */

    public class DatabaseManager {

        /**
         * Logger for logging database operations.
         */
        private static final Logger LOG = 
        		Logger.getLogger(DatabaseManager.class.getName());

        /**
         * Obtains a connection to the MySQL database.
         * <p>
         * This method establishes a connection to the database using predefined
         * connection parameters.
         * </p>
         *
         * @return A {@link Connection} object 
         * representing the database connection.
         * @throws SQLException If a database access error
         *  occurs or the url is {@code null}.
         */
        public static Connection getDbConnection() throws SQLException {
            return MySQLConnection.connect();
        }

        /**
         * Closes the given database connection.
         * <p>
         * This method safely closes the database connection.
         * If the connection cannot be closed, the error is logged.
         * </p>
         *
         * @param connection The database {@link Connection} to be closed.
         */
        public static void dbDisconnect(Connection connection) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } 
            	catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    



    /**
     * Deletes a user from the database.
     * It is a soft deletion, it will mark them as deleted.
     * Not very important to the final project but could be useful.
     * 
     * 
     * @param userName The username of the user to be deleted.
     */
        /**
         * Marks a user as deleted in the database.
         * 
         * @param userName The username of the user to be marked as deleted.
         */
        public static void deleteUser(String userName) {
            // SQL command to mark a user as deleted
            String deleteSQL = "UPDATE UsersTable "
            		+ "SET userDeleted = 1 "
            		+ "WHERE username = ?";

            try (Connection conn = getDbConnection(); // Get database connection
                 PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                
                // Set the username in the prepared statement
                pstmt.setString(1, userName);
                
                // Execute the update operation
                int affectedRows = pstmt.executeUpdate();
                
                // Check if the user was marked as deleted
                if (affectedRows > 0) {
                    System.out.println("User marked as deleted: " + userName);
                } else {
                    System.out.println("Username inexistent: " + userName);
                }
            } catch (SQLException e) {
                System.err.println("Database Failure: " + e.getMessage());
            }
        }

//  public static void deleteUser(String userName) {
//    // SQL command to delete a user by username
//    String deleteSQL = "DELETE FROM UsersTable WHERE username = ?";
//    
//    try (Connection conn = getDbConnection(); // Get database connection
//         PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
//        
//        // Set the username in the prepared statement
//        pstmt.setString(1, userName);
//        
//        // Execute the delete operation
//        int affectedRows = pstmt.executeUpdate();
//        
//        // Check if a row was deleted
//        if (affectedRows > 0) {
//            System.out.println("User deleted successfully: " + userName);
//        } else {
//            System.out.println("No user found with username: " + userName);
//        }
//    } catch (SQLException e) {
//        System.err.println("Database Interaction Failure: " + e.getMessage());
//    } 
//}

    /**
     * Retrieves the password for a given username from the database.
     * 
     * @param username The username of the desired user.
     * @return The password as a String, or null if not found or on error.
     */
    public static String getPassword(String username) {
        String storedPassword = null;
        String query = "SELECT password FROM users WHERE username = ?";

		try (Connection conn = getDbConnection(); 
				PreparedStatement pstmt = 
						conn.prepareStatement(query)) {

			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					storedPassword = 
					rs.getString("password");
				} else {
					LOG.info("No user "
					+ "found with username: " 
				+ username);
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.ALL, "Getting password didn't work: ", e);
		}

		return storedPassword;
	}



    /**
     * Blocks a specified email address for a user.
     * This method assumes it's being called internally 
     * and does not check for pre-existing blocks.
     * 
     * @param userId The ID of the user who wants to block the email.
     * @param emailToBlock The email address to be blocked.
     * @return true if the email was successfully blocked, false otherwise.
     */

public boolean blockEmail(int userId, String emailToBlock) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    boolean success = false;

    try {
        conn = getDbConnection(); // Get a connection to the database
        
        // Create the blocked_emails table if it doesn't already exist
        createBlockedEmailsTable(conn);
        
        // Prepare the SQL statement to insert a new block entry
        String sql = "INSERT INTO blocked_emails "
        		+ "(id, email, blocked_at) "
        		+ "VALUES (?, ?, CURRENT_TIMESTAMP)";
        pstmt = conn.prepareStatement(sql);
        
        // Set the prepared statement parameters
        pstmt.setInt(1, userId);
        pstmt.setString(2, emailToBlock);
        
        // Execute the insert
        int affectedRows = pstmt.executeUpdate();
        
        // If the insert was successful, one row will be affected
        success = affectedRows > 0;
        
        if (success) {
            LOG.info("Email " + emailToBlock 
            		+ " successfully blocked by user ID: " + userId);
        } else {
            LOG.warn("Failed to block email: " 
        + emailToBlock + " for user ID: " + userId);
        }
    } catch (SQLException e) {
        LOG.warn("SQLException while attempting to block email: " 
    + e.getMessage());
    } finally {
        // Closing resources
        try {
            if (pstmt != null) { 
            	pstmt.close();
            dbDisconnect(conn); 
            } // Ensure connection is closed
        } catch (SQLException e) {
            LOG.warn("SQLException on close: " + e.getMessage());
        }
    }
    
    return success;
}


/**
 * Creates a table named "blocked_emails" in the database 
 * if it doesn't already exist. This table is used to store 
 * information about blocked email addresses. 
 * The table structure includes an 'id' as an INT, 'email' as a VARCHAR 
 * with a maximum length of 255 characters, and 'blocked_at' as a 
 * TIMESTAMP indicating when the email was blocked. 
 * Both 'id' and 'email' together constitute the primary key for this table, 
 * ensuring that entries are unique based 
 * on these two fields.
 *
 * @param conn The database connection object used to execute the 
 * SQL statement for creating the table. This connection 
 *             should be active and capable of executing SQL statements.
 * @throws SQLException If an SQL error occurs while executing 
 * the statement to create the table. 
 */
private void createBlockedEmailsTable(Connection conn) throws SQLException {
    String createTableSql = "CREATE TABLE IF NOT EXISTS "
    		+ "blocked_emails (id INT, email VARCHAR(255), "
    		+ "blocked_at TIMESTAMP, PRIMARY KEY (id, email))";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(createTableSql);
    }
}


    

    /**
     * Ensures that an email address is blocked by a specific user, 
     * identified by the user's email address.
     * If the email address is not already blocked by the user, 
     * this method adds a new record to the database to block it.
     * If the email address is already blocked, no action is taken, 
     * but the operation is still considered successful.
     *
     * @param userId The ID of the user performing the block operation.
     * @param emailToCheck The email address to be blocked.
     * @return true if the email is successfully blocked or already blocked, 
     * false if the operation fails.
     */

 
   public static boolean isEmailBlocked(int userId, String emailToCheck) {
	    try (Connection conn = getDbConnection();
	         PreparedStatement pstmt = conn.prepareStatement(
	        		 "SELECT id FROM blocked_emails "
	        		 + "WHERE id = ? AND email = ?")) {
	        
	        pstmt.setInt(1, userId);
	        pstmt.setString(2, emailToCheck);

	        try (ResultSet rs = pstmt.executeQuery()) {

	        	//Result with one row gets email blocked
	            return rs.next();
	        }
	    } catch (SQLException e) {
	        LOG.warn("SQLException while "
	        		+ "checking if email is blocked: "
	        		+ e.getMessage());
	        return false;
	    }

	}
   
   
   /**
    * Removes an email from the blocked list of a user.
    *
    * @param userId The ID of the user whose blocked list is being modified.
    * @param emailToRemove The email address to remove from the blocked list.
    */
   public static void removeBlockedEmail(int userId, String emailToRemove) {
       // SQL command to remove an email from the blocked list
       String deleteSQL = "DELETE FROM blocked_emails "
       		+ "WHERE id = ? AND email = ?";
       
       try (Connection conn = getDbConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
           
           // Set parameters in the prepared statement
           pstmt.setInt(1, userId);
           pstmt.setString(2, emailToRemove);
           
           // Execute the delete operation
           int affectedRows = pstmt.executeUpdate();
           
           // Check if a row was deleted
           if (affectedRows > 0) {
               System.out.println("Email removed successfully "
               		+ "from blocked list for user ID: " + userId);
           } else {
               System.out.println("No matching email found"
               		+ " in the blocked list for user ID: " + userId);
           }
       } catch (SQLException e) {
           System.err.println("Database Interaction Failure: " 
       + e.getMessage());
       }
   }
   
   
   /**
    * Fetches the user ID for a given username from the UsersTable.
    * Will return -1 if the user is not found or an error occurs.
    * @param username The username of the user whose ID is being retrieved.
    * @return The user ID as an integer.
    */
   public static int fetchUserIdByUsername(String username) {
       int userId = -1; // Default to -1 to indicate not found or error

       String query = "SELECT id FROM UsersTable WHERE username = ?";
    // Assumes getDbConnection() is a method that connects.
       try (Connection conn = getDbConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {
           
           pstmt.setString(1, username);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   userId = rs.getInt("id");
               } else {
                   LOG.info("No user found with username: " + username);
               }
           }
       } catch (SQLException e) {
           LOG.error("SQLException while fetching user "
           		+ "ID by username: " + username, e);
       }

       return userId;
   }
   


}
