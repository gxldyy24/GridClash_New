package edu.skidmore.cs326.game.sudoku.persistence.exception;

/**
 * Should be thrown for any persistence error. 
 * 
 * @author zlindewirth
 */
@SuppressWarnings("serial")
public class PersistenceException extends Exception {
    /**
     * The exception causing the problem.
     */
    private Throwable cause;
    /**
     * The Exception that needs to be thrown for persistence 
     * issues. 
     * @param message the error message
     * @param cause the name of the exception causing the issue
     */
    public PersistenceException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }
    /**
     * Method to get the cause of the issue
     * from the DAO object. 
     * @return Throwable that causes the problem
     */
    public Throwable getCauseException() {
        return cause;
    }
}
