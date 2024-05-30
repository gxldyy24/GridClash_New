package edu.skidmore.cs326.game.sudoku.logic;
import edu.skidmore.cs326.game.sudoku.model.Tile;
import edu.skidmore.cs326.game.sudoku.model.TileValue;

import java.awt.Color;
import java.awt.Font;

/**
 * The Theme class for visual components
 * background color, text color, and font. 
 */
public class Theme {
    /**
     * Background color of the theme.
     */
    private Color backgroundColor;

    /**
     * Text color of the theme.
     */
    private Color textColor;

    /**
     * Font used in the theme.
     */
    private Font font;

    /**
     * Constructs a new Theme with specified colors and font.
     * 
     * @param backgroundColor the background color of the theme
     * @param textColor the text color of the theme
     * @param font the font of the theme
     */
    public Theme(Color backgroundColor, Color textColor, Font font) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.font = font;
    }

    /**
     * Returns the background color of the theme.
     * 
     * @return the background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color of the theme.
     * 
     * @param backgroundColor the new background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Returns the text color of the theme.
     * 
     * @return the text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Sets the text color of the theme.
     * 
     * @param textColor the new text color
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Returns the font of the theme.
     * 
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets the font of the theme.
     * 
     * @param font the new font
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * Sets the image link.
     * 
     * @param link the URL or path to the image resource
     */
    public void setImg(String link) {
        TileValue tileValue = null; 
        Tile tile = new Tile(tileValue);
        tile.setImgLink(link);
    }
    /**
     * Retrieves the image link used in the theme.
     
     * 
     * @return the URL or path to the image resource
     */
    public String getImgLink() {
    	TileValue tileValue = null; 
        Tile tile = new Tile(tileValue);
    	tile.getImgLink();
        return null;
    }
    /**
     * Retrieves the tile value.
     * 
     * 
     * @return the tile val
     */
    public int getTileVal() {
        return 0;

    }
}
