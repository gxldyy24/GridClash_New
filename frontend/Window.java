package edu.skidmore.cs326.game.sudoku.frontend;

import javax.swing.JFrame;

/**
 * This simplifies code of a window and allows switching 
 * between pages to be implemented in Page abstract class.
 * 
 * @author Jack Biggins
 */
public abstract class Window extends JFrame implements Runnable {

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    
    /**
     * Run: Calls createWindow when threaded.
     */
    @Override
    public void run() {
        createWindow();
    }

    /**
     * Create the frame for the Play window.
     */
    protected abstract void createWindow();

}
