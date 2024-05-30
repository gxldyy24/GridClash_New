package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.Color;
import java.awt.Font;

/**
 * This class is designed to house all the data for all the windows to pull
 * from.
 * 
 * Note: When darkMode is changes this will not automatically switch everything
 * to darkmode it will only pull the darkMode data so a repaint() is needed to
 * regenreate the pages in dark mode.
 * 
 * @author Jack Biggins
 * 
 */
public class WindowData {
	
	/**
	 * Constructor for this class.
	 */
	private WindowData() {
	}

	/**
	 * This stores in the application is it is dark mode.
	 */
	private static boolean isDarkMode;

	/**
	 * Get if the application is in dark mode if 
	 * elements want to be made on the page.
	 * 
	 * @return isDarkMode
	 */
	public static boolean isDarkMode() {
		return isDarkMode;
	}

	/**
	 * This switched the dark mode.
	 */
	public static void switchColor() {
		isDarkMode = !isDarkMode;
	}

	/**
	 * This static block initialized the application 
	 * to not dark mode but has roomto pull from persistance
	 *  if the user want it to be in dark mode 
	 *  (Not implemented).
	 */
	static {

		isDarkMode = false;
		// isDarkMode = getDatabaseDarkMode(); Or something like this
	}

	/**
	 * @return the standard window width.
	 */
	public static int getWindowWidth() {
		return 1000;
	}

	/**
	 * @return The standard window height.
	 */
	public static int getWindowHeight() {
		return 750;
	}

	/**
	 * Getter for background color.
	 * 
	 * @return Color
	 */
	public static Color getBackgroundColor() {
		if (isDarkMode) {
			return Color.darkGray;
		}
		return new Color(67, 110, 167);
	}

	/**
	 * @return the standard text color depending on 
	 *         the dark mode status.
	 */
	public static Color getTextColor() {
		if (isDarkMode) {
			return Color.white;
		}
		return Color.black;
	}

	/**
	 * @return the standard font.
	 */
	public static Font getFont() {
		Font font = new Font("Monospaced", Font.BOLD, 16);
		return font;
	}

}
