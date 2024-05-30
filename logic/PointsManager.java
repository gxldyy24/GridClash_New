package edu.skidmore.cs326.game.sudoku.logic;

/**
 * @author gabekoff
 * 
 * Manages points for players in the Sudoku game.
 * Points are awarded based on game outcomes and 
 * can be used to purchase extra cells.
 */
public class PointsManager {
    
    /**
     * This is the total points.
     */
    private int points;

    /**
     * Constructs a PointsManager with initial points set to 0.
     */
    public PointsManager() {
        this.points = 0;
    }

    /**
     * Awards points to the player for winning a game.
     */
    public void awardPointsForWin() {
        points += 70;
    }

    /**
     * Awards points to the player for losing a game.
     */
    public void awardPointsForLoss() {
        points += 30;
    }

    /**
     * Awards points to the player for a draw.
     */
    public void awardPointsForDraw() {
        points += 50;
    }

    /**
     * Attempts to purchase an extra cell for the player.
     * Can only be purchased once every two turns.
     * @param currentTurn The current turn number in the game.
     * @return true if the purchase is successful, false otherwise.
     */
    public boolean purchaseExtraCell(int currentTurn) {
        if (currentTurn % 2 == 0 && points >= 50) {
            points -= 50; // Assume 50 points cost for an extra cell
            return true;
        }
        return false;
    }

    /**
     * Returns the current points of the player.
     * @return the points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points for the player.
     * @param points the points to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
