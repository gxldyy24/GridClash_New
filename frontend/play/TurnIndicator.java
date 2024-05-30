package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;

/**
 * This class is currently incomplete but should be use as the Turn indicator on
 * the game page to display whose turn it is.
 * 
 * @author Jack Biggins
 */
public class TurnIndicator extends JLabel {

	/**
	 * Serial Version ID.
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Boolean for self turn.
	 */
	private boolean isCurrentPlayerTurn;

	/**
	 * Indicates whose turn it is.
	 * 
	 * @param isCurrentPlayerTurn
	 */
	public TurnIndicator(boolean isCurrentPlayerTurn) {	    
		Dimension screenSize =
			Toolkit.getDefaultToolkit().
			getScreenSize();

		
	    setFont(
              new Font("Monospaced", Font.BOLD, 28));
	    setBackground(
	            new Color(238, 238, 238));
	    setBounds((screenSize.width - 220) / 2, 25,
	                screenSize.width / 2, 30);

		setIsCurrentPlayersTurn(!isCurrentPlayerTurn);
		switchTurn();

	}

	/**
	 * This sets if it is the current players turn.
	 * 
	 * @param isCurrentPlayerTurn
	 */
	private void setIsCurrentPlayersTurn(boolean isCurrentPlayerTurn) {
		this.isCurrentPlayerTurn = isCurrentPlayerTurn;
	}

	/**
	 * getter method for isCurrentPlayersTurn.
	 * 
	 * @return isCurrentPlayerTurn
	 */
	public boolean isCurrentPlayersTurn() {
		return this.isCurrentPlayerTurn;
	}

	/**
	 * Switches player turn.
	 * 
	 * @author Jack Biggins.
	 */
	public void switchTurn() {
		setIsCurrentPlayersTurn(!isCurrentPlayersTurn());

		if (isCurrentPlayersTurn()) {

		    setText("your turn ...");
		    setForeground(
	            new Color(180, 222, 105));
		} else {
		    
		    setText("opponent's turn ...");
		    setForeground(
                new Color(184, 39, 20));
		    
		}
		repaint();
	}
}
