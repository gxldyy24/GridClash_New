package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.Page;

/**
 * This is a purlet visual representation of a (
 * tile in java frontend.
 * 
 * @author jackbiggins
 */
public class SwingTile extends JButton {

    /**
     * Serial Version ID #.
     */
    private static final long serialVersionUID = 1L; 
    
    /**
     * This attribute describes if this space can ba altered.
     */
    private Boolean isSetInStone;
    
    /**
     * Here is the logger that will be used throught this file.
     */
    private static final Logger LOG;
    
    /**
     * This allows buttons to act like radio as 
     * the only one selected will be stored here.
     */
    private static SwingTile currentlySelectedTile;
    
    /**
     * x coordinate of this Tile on the board.
     */
    private int xCoordinate;
    
    /**
     * y coordinate of this Tile on the board.
     */
    private int yCoordinate;
    
    /**
     * The SwingTile constructor.
     */
    public SwingTile() {
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    	
    	
        this.setIsSetInStone(false);
        
        addDefaultVisuals();
        
        addActionListener((e) -> {
            LOG.info("Currently SelectedTile From SwingTile = "
                                    + getCurrentTile());
            
            if (!this.isSetInStone()) {
                 
                
                
                if (getCurrentTile() != null 
                    && getCurrentTile().isSetInStone()) {
                    
                	getCurrentTile().deSelect();
                	LOG.info("here 1");
                	
                } else if (getCurrentTile() != null) {
                	getCurrentTile().deSelect();
                	getCurrentTile().setText("");
                	LOG.info("here 2");
                }
                if (getCurrentTile() != this) {
                    select();
                    InGamePage currentPage = (InGamePage) getCurrentPage();
                    int selectedValue = currentPage.getSelectedCode();
                    if (selectedValue > 0) {
                        setText(String.valueOf(selectedValue));
                    }
                }
            }
        }); 
//        addActionListener((e) -> {
//            if (!this.isSetInStone()) {
//                    
//                    
//                if (getCurrentTile() != null) {
//                    currentlySelectedTile.deSelect();
//                    
//                    //if is visible
//                    //currentlySelectedTilesetText("");
//    
//                }
//                
//                if (getCurrentTile() != this) {
//                    select();                
//                    
//                    InGamePage currentPage = 
//                        (InGamePage) getCurrentPage();
//                    int selectedValue = currentPage.getSelectedCode();
//                    
//                    if (selectedValue > 0) {
//                        //getMapping(curre)
//                        //setForeground(Color.RED); 
//                        // this might change text color
//                        setText(selectedValue + "");
//                    }
//                }
//            }
//           LOG.info("Current Tile's Coords: " 
//                    + this.getXCoord() 
//                    + " " 
//                    + this.getYCoord());
//        });
    }
    
    /**
     * This initialized the logger and the currently selected tile to null.
     */
    static {
        LOG = Logger.getLogger(SwingTile.class);
        currentlySelectedTile = null;
    }
    
    /**
     * @return returns the currently selected tile.
     */
    public static SwingTile getCurrentTile() {
        return SwingTile.currentlySelectedTile;
    }
   
    /**
     * setter method for x value.
     * @param newX new x value for this Tile
     */
    public void setXCoord(int newX) {
    	this.xCoordinate = newX;
    }
    
    /**
     * setter method for y value.
     * @param newY new y value for this Tile
     */
    public void setYCoord(int newY) {
    	this.yCoordinate = newY;
    }
    
    /**
     * accessor method for x coordinate.
     * @return the x coordinate of this Tile
     */
    public int getXCoord() {
    	return this.xCoordinate;
    }
    
    /**
     * accessor method for y coordinate.
     * @return the y coordinate of this Tile
     */
    public int getYCoord() {
    	return this.yCoordinate;
    }
    
    /**
     * Sets the current tile to the currently selected tile.
     */
    private void select() {
        setBackground(Color.CYAN);
        currentlySelectedTile = this;
        repaint();
        LOG.info("New Tile Selected");
    }
    
    /**
     * Makes this tile no longer the currently selected tile.
     */
    private void deSelect() {
        setBackground(Color.WHITE);
        repaint();
        LOG.info("Tile Deselected");
    }
    
    /**
     * Here are the default visuals of the tile.
     */
    private void addDefaultVisuals() {
        
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        setFont(new Font("Monospaced", Font.BOLD, 36));
        
    }
    
    /**
     * This sets the space in stone meaning its value can't be changes.
     * turnIndicator
     * @param img that will be displayed 
     */
    public void setInStone(String img) {
        setText("IS SET IN STONE");
        setIsSetInStone(true);
    }
    
    /**
     * This is the getter method for isSetInStone.
     * 
     * @return If current Swing tile is set in stone.
     */
    public boolean isSetInStone() {
        return this.isSetInStone;
    }
    
    /**
     * Sets isSetInStone to the inputed value.
     * @param isSetInStone boolean
     */
    public void setIsSetInStone(Boolean isSetInStone) {
        this.isSetInStone = isSetInStone;
    }
    
    
    /**
     * This function iterates over pages til it gets the first page object.
     * 
     * Note: Uses casting which might be an issue
     * 
     * @author jackbiggins
     * @return the current page of the tile 
     *         (will typically be an instance of InGamePage)
     */
    private Page getCurrentPage() {
        Page currentPage = null;
        
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof Page) {
                currentPage = (Page) parent;
            }
            parent = parent.getParent();
            
        }
        return currentPage;
    }
    

}
