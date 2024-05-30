package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.utility.Event;
import edu.skidmore.cs326.game.utility.GameController;
import edu.skidmore.cs326.game.utility.Observer;
import edu.skidmore.cs326.game.utility.PopulateEvent;

import edu.skidmore.cs326.game.sudoku.model.Tile;

/**
 * This is a view of the board for java swing in the board MVC system.
 * 
 * @author jackbiggins
 */
public class SwingBoard extends JPanel implements Observer {
	
    
	/**
	 * call populate board.setPayload().
	 * check
	 */
	private int[][] sodukuPuzzle;
	/*= {
	    {-1, 7, 5, -2, 9, -4, -8, -3, 6},
	    {-6, 2, 3, -1, 8, -7, -9, 4, -5},
	    {8, -9, -4, -5, -6, 3, -2, -7, 1},
	    
	    {5, -1, -9, 7, -3, 2, -4, -6, -8},
	    {-3, 4, -7, 8, -5, 6, -1, 2, -9},
	    {-2, -8, -6, 9, -4, 1, -7, -5, 3},
	    
	    {9, -3, -8, 4, -2, -5, -6, -1, 7},
	    {-4, 6, -1, -3, 7, -9, 5, 8, -2},
	    {7, -5, -2, -6, 1, -8, 3, 9, -4}
	    
	};
	*/
	
	/**
	 * completed 4x4 board just for show.
	 
	@SuppressWarnings("unused")
	private int[][] sodukuPuzzle4 = {
	    {-4, -1, -2, 3},
	    {3,   2,  4 -1},
	    {-1,  4,  3, 2},    
	    {2,  -3, -1, -4},
	    
	};
	*/
	
	
    /**
     * This is the added to supress JPanel warning.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * This is the board that is used to be displayed.
     */
    private SwingTile[][] displayBoard;
    
    /**
     * difficulty of the board.
     */
    private int difficulty;
    
    /**
     * This declares the variable for the logger.
     */
    private static final Logger LOG;
    
    //private Gamecontoller
    
    /**
     * This static block initiates the logger.
     */
    static {
        LOG = Logger.getLogger(SwingBoard.class);
    }
    
    /** 
     * @param size of the board (given by axis length).
     * @param diff the difficulty of the game with values
     * 0,1,2 where 0 is the easiest.
     */
    
    public SwingBoard(int size, int diff) {
        difficulty = diff;
        
        LOG.info("Here are the stats of the board... size= " 
                + size + " diff= " + diff);
        this.sodukuPuzzle = GameController.getInstance()
                                        .generateBoard(size, diff);
        
        this.displayBoard = new SwingTile[size][size];
        // Initialize each SwingTile object
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                displayBoard[i][j] = new SwingTile();
            }
        }
        setup();
        
        //gamecontroller = new game
        //puzzleSolve = gamcecontroller.gnerateboard(size , dif
    }
    
    /**
     * This is the main visual setup of the board.
     */
    public void setup() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int size = getBoardSize();

        int boardPixel = (int) (screenSize.height / 1.5);
        double iterator = Math.sqrt((double) size);

        setBorder(new LineBorder(Color.BLACK));

        // Create a JPanel to hold the tiles
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        SwingTile[][] board = getDisplayBoard();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SwingTile square = board[i][j];
                square.setXCoord(i);
                square.setYCoord(j);
                
                //setting all the tile values
                if (sodukuPuzzle[i][j] < 0) {
                	square.setIsSetInStone(false);
                } else {
                	square.setIsSetInStone(true);
                	square.setBackground(Color.LIGHT_GRAY);
                	square.setForeground(Color.WHITE);
                	square.setText(Integer.toString(sodukuPuzzle[i][j]));
                }
                
                //Makes font so it can be seen
                if (size > 9) {
                    square.setFont(new Font("Monospaced", Font.BOLD, 24));
                }
               
                // Add thicker lines
                if ((i + 1) % iterator == 0 && (j + 1) % iterator == 0) {
                    square.setBorder(new MatteBorder(1, 1, 5, 5, Color.BLACK));
                } else if ((i + 1) % iterator == 0) {
                    square.setBorder(new MatteBorder(1, 1, 5, 1, Color.BLACK));
                } else if ((j + 1) % iterator == 0) {
                    square.setBorder(new MatteBorder(1, 1, 1, 5, Color.BLACK));
                }

                // Add thicker lines
                if (i == 0 && (j + 1) % iterator == 0) {
                    square.setBorder(new MatteBorder(5, 1, 1, 5, Color.BLACK));
                } else if (j == 0 && (i + 1) % iterator == 0) {
                    square.setBorder(new MatteBorder(1, 5, 5, 1, Color.BLACK));
                } else if (i == 0 && j == 0) {
                    square.setBorder(new MatteBorder(5, 5, 1, 1, Color.BLACK));
                } else if (i == 0) {
                    square.setBorder(new MatteBorder(5, 1, 1, 1, Color.BLACK));
                } else if (j == 0) {
                    square.setBorder(new MatteBorder(1, 5, 1, 1, Color.BLACK));
                }

                // Add the tile to the board panel
                boardPanel.add(square);
            }
        }

        // Add the board panel to the SwingBoard
        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);

        setBounds((screenSize.width - boardPixel) 
        		/ 2, 120, boardPixel, boardPixel);
    
    }
    
    /**
     * @return This returns the board size.
     */
    public int getBoardSize() {
        return displayBoard.length;
    }
    
    /**
     * @return difficulty of the board
     */
    public int getDifficulty() {
    	return difficulty;
    }
    
    /**
     * @return This returns the display board.
     */
    private SwingTile[][] getDisplayBoard() {
        return displayBoard;
    }
    
    /**
     * This is the update function that is called as an observer.
     * This method will house all the event cases.
     * 
     * @param e the event that occurred.
     */
    public void update(Event e) {

        switch (e.getType()) {
            
            case POPULATE:
                if (e instanceof PopulateEvent) {
                    populateDisplay((PopulateEvent) e);
                } else {
                	LOG.error("Code is unable to populate");
                }
                break;
            default:
                LOG.warn("Could not parse event type");
        }
        
        return;
    }
    
    /**
     * This is the event case for populating the board for the first time.
     * 
     * @param e Population Event Object with its array of Tiles full
     */
    private void populateDisplay(PopulateEvent e) {
        
        setup();
        
        Tile[][] tiles = e.getPayload();
        
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; i++) {
                
                Tile tile = tiles[i][j];
                
                //If its not hidden then its a value we can add
                if (!tile.isHidden()) {
                    displayBoard[i][j].setInStone(tile.getImgLink());
                }
                
            }
            
        }
    }
    
    /**
     * @return returns the int form of the puzzle board.
     */
    public int[][] getBoard() {
        return sodukuPuzzle;
    }

}
