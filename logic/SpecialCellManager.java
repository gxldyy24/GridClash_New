package edu.skidmore.cs326.game.sudoku.logic;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.skidmore.cs326.game.sudoku.model.Tile;

/**
 * @author gabekoff
 * 
 * Manages special cells and their points within the game board.
 */
public class SpecialCellManager {
    
    /**
     * This is the board of Tile.
     */
    private final Tile[][] board;
    
    /**
     * THis is the size of the board.
     */
    private final int size;
    
    /**
     * This holds the random class.
     */
    private final Random rand = new Random();

    /**
     * Constructor for SpecialCellManager.
     * @param board The game board array of Tile.
     * @param size The size of one dimension of the square ]
     *        game board.
     */
    public SpecialCellManager(Tile[][] board, int size) {
        this.board = board;
        this.size = size;
    }

    /**
     * Initializes the board with special cells and assigns 
     * points to some of them.
     */
    public void initializeSpecialCellsAndPoints() {
        int totalCells = size * size;
        // Randomly decide between 20% and 30% of total cells to be special
        int specialCellsCount = (int) (totalCells * (
                                        (rand.nextInt(11) + 20) / 100.0));
        // Of the special cells, randomly decide 
        //between 50% and 70% to assign points
        int pointsCellsCount = (int) (specialCellsCount * (
                                        (rand.nextInt(21) + 50) / 100.0));

        List<Integer> cellIndices = new ArrayList<>();
        for (int i = 0; i < totalCells; i++) {
            cellIndices.add(i);
        }
        Collections.shuffle(cellIndices);

        for (int i = 0; i < specialCellsCount; i++) {
            int row = cellIndices.get(i) / size;
            int col = cellIndices.get(i) % size;
            board[row][col].setSpecial(true);
            if (i < pointsCellsCount) {
                // Assign random points between 1 and 10
                board[row][col].setPoints(rand.nextInt(10) + 1);
            }
        }
    }
}