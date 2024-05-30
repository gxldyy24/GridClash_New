package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.play.InGamePage;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * Grid Clash Play Window This class represents the home window for the Grid
 * Clash game. It extends JFrame and implements Runnable. It creates the home
 * frame in a cardLayout and adds panels to the frame.
 * 
 * @author eheidepriem
 */
public class PlayWindow extends Window {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger for PlayWindow.
	 */
	private static final Logger LOG = Logger.getLogger(PlayWindow.class);


	/**
	 * Our user at the playWindow.
	 */
	@SuppressWarnings("unused")
	private User user;
	
	/**
	 * The requested size of the board.
	 */
	private int size;
	
	/**
	 * The requested difficulty of the board.
	 */
	private int diff;

	/**
	 * Constructor for PlayWindow.
	 */
	public PlayWindow() {
	}

	/**
	 * Constructor for HomeWindow with a user.
	 * 
	 * @param user
	 * @param size the size of the board
	 * @param diff he difficulty of the board codede between 0, 1, and 2
	 */
	public PlayWindow(User user, int size, int diff) {
		this.user = user;
		this.size = size;
		this.diff = diff;
	}
	
	/**
	 * Setter for playWindow user.
	 * @param user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Getter for playWindow user.
	 * 
	 * @return user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Create the frame for the Play window.
	 */
	protected void createWindow() {

		// Initialize JFrame
		setTitle("GRID CLASH - PLAY - " + user.getUsername());
		
		// Set size to full screen
		Dimension screenSize = 
				Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setLocation(0, 0);

//		uncomment these lines to have normal size
//		setSize(1000, 700);
//		setLocation(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create card layout and jpanel that holds card layout
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);

		// Add all panels to card layout
        LOG.info("Here are the stats of the board in PlayWindow size= " 
                    + size + " diff= " + diff);

		cardPanel.add(new InGamePage(this.size, this.diff), "Game");
	    //cardPanel.add(new GamePage() , "GameTest");

		

		// Show play panel first
		cardLayout.show(cardPanel, "Game");

		// Add card panel to the JFrame
		add(cardPanel);

		setVisible(true);
	}
	
	/**
	 * Closes play window, opens login window.
	 * 
	 * @param dispose the play window.
	 */
	public void playToLogin(PlayWindow dispose) {
		Runnable application = new LoginWindow();
		new Thread(application).start();
		dispose.dispose();
	}

	/**
	 * Closes play window, opens home window.
	 * 
	 * @param dispose the play window
	 * @param user the current user
	 */
	public void playToHome(PlayWindow dispose, User user) {
		Runnable application = new HomeWindow(user);
		new Thread(application).start();
		dispose.dispose();
	}

	/**
	 * Handles user exit request.
	 */
	public void handleExitRequest() {
		LOG.info("Application shutting down");
	}
}
