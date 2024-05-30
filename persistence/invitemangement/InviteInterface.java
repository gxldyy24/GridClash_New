package edu.skidmore.cs326.game.sudoku.persistence.invitemangement;

import java.sql.Timestamp;

/**
 * 
 * Is supposed to represent an invitation status between users.
 * 
 */
public interface InviteInterface {

	/**
	 * 
	 * Gets the unique ID of the invitation.
	 * 
	 * @return the invitation ID.
	 * 
	 */

	int getInviteId();

	/**
	 * 
	 * Gets the ID of the user who created the invitation.
	 * 
	 * @return the inviter's user ID.
	 * 
	 */

	int getInviterId();

	/**
	 * 
	 * Gets the ID of the user who is invited.
	 * 
	 * @return the invitee's user ID.
	 * 
	 */

	int getInviteeId();

	/**
	 * 
	 * Gets the current status of the invitation.
	 * 
	 * @return the status as a String.
	 * 
	 */

	String getStatus();

	/**
	 * 
	 * Sets the current status of the invitation.
	 * 
	 * @param status the new status of the invitation.
	 * 
	 */

	void setStatus(String status);

	/**
	 * 
	 * Gets the timestamp of when the invitation was created.
	 * 
	 * @return the creation timestamp.
	 * 
	 */

	Timestamp getCreatedAt();

	/**
	 * 
	 * Sets the timestamp of when the invitation was created.
	 * 
	 * @param createdAt the new creation timestamp.
	 * 
	 */

	void setCreatedAt(Timestamp createdAt);

	/**
	 * 
	 * Gets the timestamp of the last update to the invitation.
	 * 
	 * @return the last update timestamp.
	 * 
	 */

	Timestamp getUpdatedAt();

	/**
	 * 
	 * Sets the timestamp of the last update to the invitation.
	 * 
	 * @param updatedAt the new last update timestamp.
	 * 
	 */

	void setUpdatedAt(Timestamp updatedAt);
}