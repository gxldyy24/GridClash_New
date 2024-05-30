package edu.skidmore.cs326.game.sudoku.model;

/**
 * The tile data for one game tile.
 * includes image link, hidden or not, x and y positions, and its value
 * @author cdavidson jackbiggins
 */
public class Tile {

    /**
     * This is the image link associated with the tile when its confirmed.
     */
    private String imgLink;

    /**
     * This describes if the value should be hidden to the user.
     */
    private boolean isHidden;
    
    /**
     * These two might not be necessary but describe the x and y coordinates.
     */
    private TileValue xPos;
    
    /**
     * This is the yPosition (might not be needed.
     */
    private TileValue yPos;
    
    /**
     * integer and enum value of this tile.
     */
	private TileValue value;
	
	/**
	 * The points of the tile.
	 */
	private int points;
    
    /**
     * whether or not a cell is special.
     * ***i know this isn't in the uml but i feel its 
     * better here than in another class/file***
     * This constructor assigns the value.
     * 
     * @param value the value contained in this tile
     */
    private boolean isSpecial;
    
    //--------------------------------------------------------------------------
    
    
    
    /**
     * This constructor assigns the enum value.
     * 
     * @param value --> the value 
     * Hidden is false at default.
     */
    public Tile(TileValue value) {
        this.value = value;
    }
    
//    /**
//     * accessor method for Tile Enum.
//     * don't know if we actually need this...?
//     * @return the tile value enum
//     */
//    public TileValue getTile() {
//    	return this.value;
//    }
    
    /**
     * accessor method for the image link.
     * @return the String image link.
     */
    public String getImgLink() {
        return imgLink;
    }

    /**
     * setter method for the image link.
     * @param imgLink the String image link
     */
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    /**
     * accessor method for the x position of this Tile.
     * @return the X position
     */
    public int getRow() {
        return xPos.getValue();
    }

    /**
     * setter method for the X position.
     * @param xPos the X position
     */
    public void setRow(TileValue xPos) {
        this.xPos = xPos;
    }

    /**
     * accessor method for the y position of this Tile.
     * @return the Y position
     */
    public int getColumn() {
        return yPos.getValue();
    }

    /**
     * setter method for the Y position.
     * @param yPos the Y position
     */
    public void setColumn(TileValue yPos) {
        this.yPos = yPos;
    }

    /**
     * accessor method to see if this Tile is hidden.
     * @return true if the value is hidden to the user, false otherwise
     */
    public boolean isHidden() {
        return isHidden;
    }

    /**
     * Sets whether the value is hidden to the user.
     * @param isHidden true if the value should be hidden, false otherwise
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    
    
    
    
    //just thought this would maybe come in handy at some point?
    /**
     * setter method for special tile assignment.
     * @param b
     */
	public void setSpecial(boolean b) {
		this.isSpecial = b;
	}
	
	/**
	 * getter Method for special tile assignment.
	 * 
	 * @return boolean isSpecial
	 */
	public boolean getSpecial() {
		return this.isSpecial;
	}
	
	/**
	 * getter method for the tile's value.
	 * @return the value of the tile
	 */
	public int getValue() {
		return this.value.getValue();
	}
	
	/**
	 * setter method for the tile's value.
	 * 
	 * @param value
	 */
	public void setValue(TileValue value) {
		this.value = value;
	}
	
	/**
	 * Copy constructor.
	 * 
	 * @param sourceTile 
	 */
	public Tile(Tile sourceTile) {
		this.value = sourceTile.value;
		this.isHidden = sourceTile.isHidden;
	}
	
	/**
	 * The setter of the points.
	 * @param points the points of the tile.
	 */
	public void setPoints(int points) {
	    this.points = points;
	}
	
	/**
	 * @return the points of the tile.
	 */
	public int getPoints() {
	    return this.points;
	}
	

}
