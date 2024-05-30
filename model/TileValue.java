package edu.skidmore.cs326.game.sudoku.model;

/**
 * This is the TileValue enum since there are
 * a fixed amount of values each Tile can have.
 * 
 * @author jackbiggins, cdavidson
 */
public enum TileValue {
    //Here are all the possible values from 1 - 25.

    /**
     * Values 0-5.
     */
    EMPTY(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    
    /**
     * Values 6 - 10.
     */
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    
    /**
     * Values 11 - 15.
     */
    ELEVEN(11), TWELVE(12), THIRTEEN(13), FOURTEEN(14), FIFTEEN(15),
    
    /**
     * Values 16 - 20.
     */
    SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18), NINETEEN(19), TWENTY(20),
    
    /**
     * Values 21 - 23.
     */
    TWENTY_ONE(21), TWENTY_TWO(22), TWENTY_THREE(23), 
    
    /**
     * Values 24 , 25.
     */
    TWENTY_FOUR(24), TWENTY_FIVE(25);
	
	/**
	 * integer value of this enum.
	 */
	private final int value;
	
	
	//----------------------------------------------------------------------
	
	
	/**
	 * constructor for tile value enum.
	 * @param value
	 */
    TileValue(int value) {
        this.value = value;
    }

    /**
     * Getter method to retrieve the integer value of each enum constant.
     * @return the integer value
     */
    public int getValue() {
        return this.value;
    }
}