package edu.skidmore.cs326.game.sudoku.logic;

/**
 * Represents a power-up in a game of Sudoku.
 * A power-up can either be permanent or 
 * temporary and has various effects on the game play,
 * such as affecting the turn order or modifying player capabilities.
 */
public class PowerUp {
	/**
	 * Power up name.
	 */
    private String name;
    /**
     * isPermanent check.
     */
    private boolean isPermanent;

    /**
     * Constructs a new PowerUp with a specified name and permanence.
     * 
     *
     * @param name the name of the power-up, 
     * indicating what it does or represents (e.g., "Skip Turn").
     * 
     * @param isPermanent a boolean indicating whether
     *  the power-up has permanent effects or can be consumed.
     */
    public PowerUp(String name, boolean isPermanent) {
        this.name = name;
        this.isPermanent = isPermanent;
    }

    /**
     * Returns the name of the power-up.
     *
     * @return the name of this power-up.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if this power-up is permanent.
     *
     * @return true if this power-up is permanent, otherwise false.
     */
    public boolean isPermanent() {
        return isPermanent;
    }

    //powerup to be choose who takes the first turn

    //implent logic so you can only use powerup during your turn

    //if a player forfits it the winning 
    //player gets the contents of their perminant inventory

    //skip oppents turn con only use once every 5 turns

    // other power ups Lose a turn; lose 
    //a permanent inventory item; lose permanent points; 
    //extra turn (choose second cell); add permanent points

    
}
