package edu.skidmore.cs326.game.sudoku.model;

import edu.skidmore.cs326.game.utility.PopulateEvent;

/**
 * This is the interface of the board model.
 * 
 * @author jackbiggins
 */
public interface BoardModelInterface {

    /**
     * This will populate the BoardModel overriding all values.
     * 
     * @param p the population event which will hold specifics
     */
    void populate(PopulateEvent p);
    
    /**
     * This will add a value a given location.
     * 
     * @param row the row number
     * @param col the column number
     * @param value the desired value
     */
    void setTile(int row, int col, TileValue value);
    
}
