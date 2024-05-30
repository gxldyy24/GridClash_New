package edu.skidmore.cs326.game.sudoku.logic;
/**
 * Solver class provides methods to solve a Sudoku puzzle
 * and to check if there is a unique solution for the given Sudoku board.
 */
public class Solver {

    /**
     * Global variable to track the number of solutions found.
     */
    private static int solutionCount;
    
    /**
     * To store the solved board.
     */
    private static int[][][] solvedBoard; 

    
    /**
     * This initializes solutionCount.
     */
    static {
        solutionCount = 0;
    }
    
    /**
     * Constuctor to fix checkstyle for utility classes.
     */
    private Solver() {
        
    }

    /**
     * Checks if a number can be placed at the 
     * specified row and column in the Sudoku board.
     *
     * @param board the Sudoku board
     * @param row the row number to check
     * @param col the column number to check
     * @param number the number to place
     * @param size the size of the Sudoku board 
     *       
     * @return true if the number can be placed; 
     *         false otherwise
     */
    private static boolean canPlaceNumber(int[][][] board,
                                            int row, 
                                            int col, 
                                            int number, 
                                            int size) {
     // Calculate the size of the small box
        int boxSize = (int) Math.sqrt(size); 

        // Check row and column for duplicates
        for (int i = 0; i < size; i++) {
            if (board[row][i][0] == number || board[i][col][0] == number) {
                return false;
            }
        }

        // Calculate the start position for the small box
        int boxRowStart = row - row % boxSize;
        int boxColStart = col - col % boxSize;

        // Check the small box for duplicates
        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (board[boxRowStart + r][boxColStart + c][0] == number) {
                    return false;
                }
            }
        }

        return true; // Number can be placed
    }

    /**
     * Solves the Sudoku puzzle using a backtracking 
     * algorithm.
     *
     * @param board the Sudoku board
     * @param row the current row index
     * @param col the current column index
     * @param size the size of the Sudoku board
     */
    private static void solveSudoku(int[][][] board, 
                                    int row, 
                                    int col, 
                                    int size) {
        // Check if we have reached the end of the board
        if (row == size) {
            solutionCount++;  // Increment the solution count
            copyBoard(board, size);
            return;
        }

        // Calculate the next cell's row and column indices
        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col == size - 1) ? 0 : col + 1;

        // If cell is not empty, move to the next cell
        if (board[row][col][0] != 0) {
            solveSudoku(board, nextRow, nextCol, size);
        } else {
            // Try placing each possible number and backtrack if needed
            for (int number = 1; number <= size; number++) {
                if (canPlaceNumber(board, row, col, number, size)) {
                    board[row][col][0] = number; // Place the number
                    solveSudoku(board, nextRow, nextCol, size);
                    board[row][col][0] = 0; // Backtrack
                }
            }
        }
    }

    /**
     * Determines if there is a unique solution for the Sudoku puzzle.
     *
     * @param board the Sudoku board
     * @param size the size of the Sudoku board
     * @return true if there is exactly one solution; false otherwise
     */
    public static boolean isUniqueSolution(int[][][] board, int size) {
        solutionCount = 0;  // Reset the solution count
        solveSudoku(board, 0, 0, size);  // Start solving the Sudoku
        return solutionCount == 1;  // Check if there is exactly one solution
    }
    
    
    /**
     * This copies the board to solved board static variable.
     * @param board 
     * @param size 
     */
    private static void copyBoard(int[][][] board, int size) {
        solvedBoard = new int[size][size][1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                solvedBoard[i][j][0] = board[i][j][0];
            }
        }
    } 
    
    /**
     * @return int[][][] of the board stored in class.
    */
    public static int[][][] getSolvedBoard() {
        return solvedBoard;
    }

    /**
     * Determines if the provided Sudoku board has a unique solution.
     *
     * @param board the Sudoku board r
     * @param size the size of the Sudoku board 
     * @return true if the board has a unique solution,
     */
    public static boolean isUniqueSolution(int[][] board, int size) {
        // TODO
        return false;
    }
    
  /*  public static void main(String[] args) {
        int[][][] sudoku9x9 = {
            {{5}, {0}, {0}, {0}, {7}, {0}, {0}, {0}, {0}},
            {{0}, {0}, {0}, {1}, {9}, {5}, {0}, {0}, {0}},
            {{0}, {9}, {8}, {0}, {0}, {0}, {0}, {6}, {0}},
            {{8}, {0}, {0}, {0}, {6}, {0}, {0}, {0}, {3}},
            {{4}, {0}, {0}, {8}, {0}, {3}, {0}, {0}, {1}},
            {{7}, {0}, {0}, {0}, {2}, {0}, {0}, {0}, {6}},
            {{0}, {6}, {0}, {0}, {0}, {0}, {2}, {8}, {0}},
            {{0}, {0}, {0}, {4}, {1}, {9}, {0}, {0}, {5}},
            {{0}, {0}, {0}, {0}, {8}, {0}, {0}, {7}, {9}}
        };
        int[][][] unsolvablesudoku9x9 = {
            {{5}, {3}, {4}, {6}, {7}, {8}, {9}, {2}, {1}},
            {{0}, {0}, {0}, {1}, {9}, {5}, {0}, {0}, {0}},
            {{0}, {9}, {8}, {0}, {0}, {0}, {0}, {6}, {0}},
            {{8}, {0}, {0}, {0}, {6}, {0}, {0}, {0}, {3}},
            {{4}, {0}, {0}, {8}, {0}, {3}, {0}, {0}, {1}},
            {{7}, {0}, {0}, {0}, {2}, {0}, {0}, {0}, {6}},
            {{0}, {6}, {0}, {0}, {0}, {0}, {2}, {8}, {0}},
            {{0}, {0}, {0}, {4}, {1}, {9}, {0}, {0}, {5}},
            {{0}, {0}, {0}, {0}, {8}, {0}, {0}, {7}, {9}}
        };

        // Complete 4x4 Sudoku board
        int[][][] sudoku4x4 = {
            {{1}, {3}, {4}, {2}},
            {{2}, {0}, {0}, {3}},
            {{3}, {1}, {5}, {4}},
            {{4}, {0}, {4}, {1}}
        };
     

        int[][][] unsolvableBoard4x4 = {
            {{1}, {0}, {4}, {0}},
            {{0}, {0}, {0}, {2}},
            {{9}, {0}, {2}, {1}},
            {{6}, {0}, {0}, {4}}
        };
        

        isUniqueSolution(sudoku4x4,4)){
            System.out.println("size 4: solved");
        }else{
            System.out.println("size 4: unsolved");
        }
        if (isUniqueSolution(unsolvableBoard4x4,4)){
            System.out.println("size unsolvable4: solved");
        }else{
            System.out.println("size unsolvable4: unsolved");
        }
        if (isUniqueSolution(sudoku9x9,9)){
            System.out.println("size 9: solved");
        }else{
            System.out.println("size 9: unsolved");
        }
        if (isUniqueSolution(unsolvablesudoku9x9,9)){
            System.out.println("size unsolv9: solved");
        }else{
            System.out.println("size unsolv9: unsolved");
        }
    }
    */
}