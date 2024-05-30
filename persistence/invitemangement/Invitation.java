package edu.skidmore.cs326.game.sudoku.persistence.invitemangement;

import java.sql.Timestamp;

/**
 * Represents an invitation with details about the inviter, invitee, and status.
 */
public class Invitation implements InviteI {

    /**
     * Unique identifier for the invitation.
     */
    private int inviteId;

    /**
     * ID of the user who sent the invitation.
     */
    private int inviterId;

    /**
     * ID of the user who received the invitation.
     */
    private int inviteeId;

    /**
     * Status of the invitation (e.g., pending, accepted, rejected).
     */
    private String status;

    /**
     * Time when the invitation was created.
     */
    private Timestamp createdAt;

    /**
     * Time when the invitation was last updated.
     */
    private Timestamp updatedAt;

    /**
     * Constructs an Invitation with the specified details.
     * @param inviteId Unique identifier for the invitation.
     * @param inviterId User ID of the person sending the invitation.
     * @param inviteeId User ID of the person receiving the invitation.
     * @param status Current status of the invitation.
     * @param createdAt Timestamp when the invitation was created.
     * @param updatedAt Timestamp when the invitation was last updated.
     */
    public Invitation(int inviteId, 
    		int inviterId, 
    		int inviteeId, 
    		String status, 
    		Timestamp createdAt, 
    		Timestamp updatedAt) {
        this.inviteId = inviteId;
        this.inviterId = inviterId;
        this.inviteeId = inviteeId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the invitation ID.
     * @return the unique identifier for the invitation.
     */
    public int getInviteId() {
        return inviteId;
    }

    /**
     * Returns the ID of the inviter.
     * @return the user ID of the person who sent the invitation.
     */
    public int getInviterId() {
        return inviterId;
    }

    /**
     * Returns the ID of the invitee.
     * @return the user ID of the person who received the invitation.
     */
    public int getInviteeId() {
        return inviteeId;
    }

    /**
     * Returns the current status of the invitation.
     * @return the status as a string.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the creation timestamp of the invitation.
     * @return the timestamp marking when the invitation was created.
     */
    @Override
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the updated timestamp of the invitation.
     * @return the timestamp marking when the invitation was last updated.
     */
    @Override
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the status of the invitation.
     * @param status the new status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the creation timestamp of the invitation.
     * @param createdAt the new creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Sets the updated timestamp of the invitation.
     * @param updatedAt the new updated timestamp.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
