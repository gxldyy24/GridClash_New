package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.home.PlayPage;

/**
 * @author cdavidson
 */
public class SendButton extends ButtonGradient {
	
	/**
	 * current version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger LOG = Logger.getLogger(PlayPage.class);
	
	/**
	 * this button's width.
	 */
	@SuppressWarnings("unused")
	private int buttonWidth;
	
	/**
	 * this button's height.
	 */
	@SuppressWarnings("unused")
	private int buttonHeight;
	
    /**
     * Reference to the selected tile.
     */
    private SwingTile selectedTile;
	
	/**
	 * This displays whos turn it is.
	 */
	private TurnIndicator turnIndicator;
	
	/**
	 * the constructor.
	 * @param turnIndicator
	 */
	public SendButton(TurnIndicator turnIndicator) {
		this.turnIndicator = turnIndicator;
		this.buttonWidth = 150;
		this.buttonHeight = 50;
		
		//default button setup
		this.setText("Send");
		this.setColor1(new Color(180, 222, 105));
		this.setColor2(new Color(180, 222, 105));

		this.setBounds(100, 100, 152, 53);
		this.setForeground(Color.WHITE);
		this.setFont(new Font("Monospaced", Font.BOLD, 28));
		clearSelectedTile();
		
		if (turnIndicator.isCurrentPlayersTurn()) {
			setColor1(new Color(180, 222, 105));
			setColor2(new Color(180, 222, 105));
		} else {
			setColor1(Color.GRAY);
			setColor2(Color.GRAY);
		}
		repaint();
		
		this.addActionListener((e) -> {
		   this.selectedTile = SwingTile.getCurrentTile();

		   LOG.info("SelectedTile = " + selectedTile);
	       //LOG.info("SelectedTile part 2= " + SwingTile.getCurrentTile());
		      
		   StrikeCounter sC =  ((InGamePage) getParentPage())
		                                       .getStrikeCounter();
		   
		   if (sC.isGameOver()) {
               LOG.info("Game is over and this button cannot be pressed");
		       return;
		   }
		   
           if (selectedTile != null && !selectedTile.isSetInStone()) {
                int selectedNumber = ((InGamePage) getParentPage())
                                                    .getSelectedCode();
                
                if (selectedNumber != -1) {
                    
                    
                    if (!isTileCorrect(((InGamePage) getParentPage())
                                    .getTheBoard().getBoard(),
                                    selectedNumber)) {
                       sC.addStrike();
                       selectedTile.setBackground(Color.RED);

                       
                       if (sC.isGameOver()) {
                           LOG.info("Game is over");
                           ((InGamePage) getParentPage())
                                       .setkEndColor(Color.RED);
                           ((InGamePage) getParentPage())
                                       .setkStartColor(Color.RED);
                           ((InGamePage) getParentPage())
                                       .repaint();

                       }
                       
                    } else {
                        selectedTile.setText(String.valueOf(selectedNumber));
                        selectedTile.setBackground(Color.GREEN);
                        selectedTile.setIsSetInStone(true); 
                        LOG.info("Number " 
                                + selectedNumber 
                                + " added to tile.");
                        ((InGamePage) getParentPage()).clearSelectedCode();
                        //switchButtonState();
                    }
                  
                } else {
                    LOG.warn("No number selected.");
                }
            } else {
                LOG.warn("No tile selected or selected tile is not editable.");
	        }
			//this.switchButtonState();
//*** need another method here that checks for legal move
			LOG.info("Send Button Clicked!");
			repaint();
		});
	}
	
	/**
	 * switches button to be activated or deactivated.
	 */
	@SuppressWarnings("unused")
	private void switchButtonState() {
	    LOG.info("turnIndicator: " + turnIndicator.isCurrentPlayersTurn());
	    if (turnIndicator.isCurrentPlayersTurn()) {
	    	setColor1(Color.GRAY);
	    	setColor2(Color.GRAY);
	    	//this.setEnabled(false); diables button while opponent is going
	    } else {
	    	setColor1(new Color(180, 222, 105));
	    	setColor2(new Color(180, 222, 105));
	    }
	    revalidate();
	    repaint();
	    turnIndicator.switchTurn();
	}
	
    /**
     * Set the selected tile.
     * 
     * @param selectedTile the selected tile
     */
    public void setSelectedTile(SwingTile selectedTile) {
        this.selectedTile = selectedTile;
    }
	
    /**
     * Clear the selected tile.
     */
    public void clearSelectedTile() {
        this.selectedTile = null;
    }
    
    /**
     * This returns the parent window to allow methods to be called on it.
     * Try to avoid this method and have methods be page dependent.
     * 
     * @return Current parent page
     */
    protected Page getParentPage() {
        Page currentWindow = null;
        
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof Page) {
                currentWindow = (Page) parent;
            }
            parent = parent.getParent();
            
        }
        return currentWindow;
    }

    /**
     * This tests if tile is in correct location.
     * @param board the board of correct values.
     * @param selectedNumber the number the user thinks it is.
     * @return true if the number they want is in the correct location.
     */
    private boolean isTileCorrect(int[][] board, int selectedNumber) {
        SwingTile sT = SwingTile.getCurrentTile();
        int xPos = sT.getXCoord();
        int yPos = sT.getYCoord();
        
        return board[xPos][yPos] == selectedNumber 
                || board[xPos][yPos] == -1 * selectedNumber;
    }
    
    
    
    
    
//===== These are not used so don't need for now =====
    
//	/**
//	 * sets this button's width.
//	 * @param newWidth the new width of the button.
//	 */
//	private void setButtonWidth(int newWidth) {
//		this.buttonWidth = newWidth;
//	}
//	
//	/**
//	 * sets this button's height.
//	 * @param newHeight the new height of the button.
//	 */
//	private void setButtonHeight(int newHeight) {
//		this.buttonHeight = newHeight;
//	}
//	
//	/**
//	 * accessor method for button height.
//	 * @return this button's height
//	 */
//	public int getHeight() {
//		return this.buttonHeight;
//	}
//	
//	/**
//	 * accessor method for button width.
//	 * @return this button's width
//	 */
//	public int getWidth() {
//		return this.buttonWidth;
//	}
}
