/**

 * The package for the Grid Clash Sudoku game.

 */
package edu.skidmore.cs326.game.sudoku;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;

/**
 * Grid Clash Application. Runnable thread started here with LoginWindow
 * 
 * @author cdavidson jbiggins vzhao eheidepriem
 */

@SuppressWarnings("unused")
public class Sudoku {
	/**
	 * The logger.
	 */
	private static final Logger LOG;

	/**
	 * Create the logger.
	 */
	static {
		LOG = Logger.getLogger(Sudoku.class);
	}

	/**
	 * This class serves to launch the application. 
	 * No instances of it should be created.
	 */
	private Sudoku() {

	}

	/**
	 * Create the application's window and launch it.
	 * 
	 * @param args Command line, not used
	 */
	public static void main(String[] args) {
		LOG.info("Starting the placeholder Grid Clash application");

		Runnable application = new LoginWindow();
		new Thread(application).start();
	}
}
