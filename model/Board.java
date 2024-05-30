package edu.skidmore.cs326.game.sudoku.model;

import java.util.Random;

import edu.skidmore.cs326.game.utility.Event;
import edu.skidmore.cs326.game.utility.EventType;
import edu.skidmore.cs326.game.utility.PopulateEvent;
import edu.skidmore.cs326.game.utility.Subject;

/**
 * This is the model board in the MVC control.
 * 
 * @author gabekoff, jackbiggins
 */
public class Board extends Subject implements BoardModelInterface {
    
    /**
     * This is the board class is the official data.
     */
    private Tile[][] tiles;
    /**
     * The boardsize chosen.
     */
    private int boardSize;
    
    /**
     * Board initialization.
     * @param boardSize the dimension of the square board.
     */
    public Board(int boardSize) {
    	this.boardSize = boardSize;
    	tiles = new Tile[this.boardSize][this.boardSize];
    	initializeEmptyBoard();
    }
    
    /**
     * Initializes an empty board.
     */
    private void initializeEmptyBoard() {
    	for (int i = 0; i < boardSize; i++) {
    		for (int j = 0; j < boardSize; j++) {
    			tiles[i][j] = new Tile(TileValue.EMPTY);
    		}
    	}
    }
    

    /**
     * the setter method for filling out the board.
     * @param payload   the points system implemented
     */
    private void setBoard(Tile[][] payload) {

    	if (payload.length != tiles.length 
    	    || payload[0].length != tiles[0].length) {
            throw new IllegalArgumentException(
                    "Invalid board dimensions provided.");
        }

        for (int i = 0; i < payload.length; i++) {
            for (int j = 0; j < payload[i].length; j++) {
                if (tiles[i][j] == null) {
                	/**
                	 * Create a new tile with the value from payload
                	 */
                    tiles[i][j] = new Tile(payload[i][j]);
                } 
                //else:
                //tiles[i][j].setValue(payload[i][j]);

            }
        }		
	}
    
    /**
     * This method as defined by the interface is called when.
     * wanting to initialize the board in a game.
     * 
     * @param p The Population event
     */
    public void populate(PopulateEvent p) {
        this.setBoard(p.getPayload());
        notifyObservers(p);
    }
    
    /**
<<<<<<< HEAD
     * Generates an initial board setup using a backtracking 
     *       to ensure a solvable Sudoku puzzle.
=======
     * Generates an initial board setup using a 
     * backtracking algorithm to ensure a solvable Sudoku puzzle.
>>>>>>> f9fc979682d0dcf1e06d3efb41f6197ed57dabfe
     *
     * @return a 2D array of Tile objects representing the 
     * initial setup.
     */
    public Tile[][] generateInitialBoardSetup() {

        Tile[][] setup = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setup[i][j] = new Tile(TileValue.EMPTY);
            }
        }
        if (!solveSudoku(setup, 0, 0)) {
            throw new IllegalStateException("Failed to "
            		+ "generate a solvable puzzle.");
        }
        return setup;
    }

    /**
     * Solves the Sudoku puzzle using a backtracking algorithm.
     *
     * @param tiles the board's tiles
     * @param row   the current row to check
     * @param col   the current column to check
     * @return true if the puzzle is successfully solved, 
     *              false if backtracking is needed.

     */
    public boolean solveSudoku(Tile[][] tiles, int row, int col) {
        if (row == 9) {
            row = 0;
            if (++col == 9) {
            	/**
            	 * puzzle solved.
            	 */
                return true;
            }
        } else {
        	
        	tiles = generateInitialBoardSetup();
        	solveSudoku(tiles, row, col);
        }

        /**
         * initialize with empty.
         */
        tiles[row][col] = new Tile(TileValue.EMPTY);

        Random rand = new Random();
        /**
         * Generate random numbers 1-9.
         */
        int[] numbers = rand.ints(1, 10).distinct().limit(9).toArray(); 
        for (int num : numbers) {
            if (isValidPlacement(tiles, row, col, TileValue.values()[num])) {
                tiles[row][col] = new Tile(TileValue.values()[num]);
                if (solveSudoku(tiles, row + 1, col)) {
                    return true;
                }
            }
        }

        /**
         * Reset on backtrack
         */
        tiles[row][col] = new Tile(TileValue.EMPTY);
        return false;
    }

    /**
     * Validates whether placing a specific TileValue at a 
     * specified row and column follows Sudoku rules.
     *
     * @param tiles the board's tiles
     * @param row   the row index
     * @param col   the column index
     * @param value the TileValue to place
     * @return true if the placement is valid, false otherwise.
     */
    private boolean isValidPlacement(Tile[][] tiles, 
    		int row, int col, TileValue value) {
        return checkRow(tiles, row, value) 
        		&& checkCol(tiles, col, value) 
        		&& checkBox(tiles, row, col, value);
    }
    
    /**
     * Checks if a value can be placed 
     * in a specific row without violating Sudoku rules.
     *
     * @param tiles the board's tiles
     * @param row   the row to check
     * @param value the value to check
     * @return true if the placement is valid, false otherwise
     */
    private boolean checkRow(Tile[][] tiles, int row, TileValue value) {
        for (int col = 0; col < 9; col++) {
            if (tiles[row][col] != null 
                && tiles[row][col].getValue() == value.getValue()) {

                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a value can be placed in a specific column 
     * without violating Sudoku rules.
     *
     * @param tiles the board's tiles
     * @param col   the column to check
     * @param value the value to check
     * @return true if the placement is valid, false otherwise
     */
    private boolean checkCol(Tile[][] tiles, int col, TileValue value) {

        for (int row = 0; row < 9; row++) {
            if (tiles[row][col] != null 
                && tiles[row][col].getValue() == value.getValue()) {
                

                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a value can be placed in a specific 3x3 box 
     * without violating Sudoku rules.
     *
     * @param tiles the board's tiles
     * @param row   the starting row of the box
     * @param col   the starting column of the box
     * @param value the value to check
     * @return true if the placement is valid, false otherwise
     */
    private boolean checkBox(Tile[][] tiles, 
                                    int row, 
                                    int col, 
                                    TileValue value) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (tiles[r][c] != null 
                    && tiles[r][c].getValue() == value.getValue()) {

                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Ensure the position is within bounds and the tile
     * can be placed according to our rules.
     * @param row The row index where the tile is to be placed.
     * @param col The column index where the tile is to be placed.
     * @param value The value of the tile to place.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValidMove(int row, int col, TileValue value) {
    	return isInBounds(row, col) 
    	    && !isTileSet(row, col) 
    	    && isValidPlacement(tiles, row, col, value);
    }
    
    /**
     * Checks if the specified position is within the board 
     * bounds.
     * 
     * @param row The row index to check.
     * @param col The column index to check.
     * @return true if the position is within bounds, false 
     * otherwise.
     */
    private boolean isInBounds(int row, int col) {
    	return row >= 0 
    	    && row < tiles.length 
    	    && col >= 0 
    	    && col < tiles[0].length;
    }
    
    /**
     * Checks if a tile at a specified position is already 
     * set with a non empty value.
     * 
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @return true if the tile is already set, false 
     * otherwise.
     */
    private boolean isTileSet(int row, int col) {

    	return tiles[row][col] != null 
    	    && tiles[row][col].getValue() != TileValue.EMPTY.getValue();
    }

	/**
     * This is used when a tile have been confirmed to be 
     * placed in a specific place.
     * 
     * @param row the row number 
     * @param col the column number
     * @param value the TileValue that is desired

     */
    public void setTile(int row, int col, TileValue value) {
        if (isInBounds(row, col)) {
        	tiles[row][col] = new Tile(value);
        	notifyObservers(new Event(EventType.MOVE_MADE));
        }
        
    }
}
