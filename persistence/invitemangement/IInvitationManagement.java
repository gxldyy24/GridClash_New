package edu.skidmore.cs326.game.sudoku.persistence.invitemangement;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for managing game invitations.
 * Provides methods for creating, retrieving, 
 * and managing invitations between users.
 * @author Bernardo Sa
 */
public interface IInvitationManagement {

    /**
     * Creates an invitation from one user to another.
     * Ensures that no duplicate pending invitations exist.
     *
     * @param fromUserID the ID of the user sending the invitation.
     * @param toUserID the ID of the user receiving the invitation.
     * @throws SQLException if a database access error occurs.
     * @throws IllegalStateException if a pending invitation already exists.
     */
    void createInvitation(int fromUserID, int toUserID) 
    		throws SQLException, IllegalStateException;

    /**
     * Retrieves an invitation by its unique identifier.
     *
     * @param invitationID the ID of the invitation to retrieve.
     * @return the Invitation object if found, otherwise null.
     * @throws SQLException if a database access error occurs.
     */
    Invitation getInvitationByID(int invitationID) throws SQLException;

    /**
     * Lists all pending invitations for a specified user.
     *
     * @param userID the ID of the user to retrieve invitations for.
     * @return a list of pending Invitation objects.
     * 
     * @throws SQLException if a database access error occurs.
     */
    List<Invitation> listInvitationsByUser(int userID) throws SQLException;

    /**
     * Responds to an invitation, updating its status in the database.
     *
     * @param invitationID the ID of the invitation to respond to.
     * @param response the new status to set (e.g., "accepted" or "rejected").
     * @throws SQLException if a database access error occurs.
     */
    void respondToInvitation(int invitationID, String response) 
    		throws SQLException;

    /**
     * Cancels a specific invitation, setting its status to 
     * 'cancelled' in the database.
     *
     * @param invitationID the unique identifier of the invitation to cancel.
     * @throws SQLException if there is an issue executing 
     * the update in the database.
     */
    void cancelInvitation(int invitationID) throws 
    SQLException;

    /**
     * Removes all pending invitations that are older than 30 days .
     * This method cleans unresolved invitations.
     *
     * @throws SQLException with database problems.
     */
    void cleanupExpiredInvitations() throws SQLException;
}
