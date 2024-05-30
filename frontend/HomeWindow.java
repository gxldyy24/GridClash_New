package edu.skidmore.cs326.game.sudoku.frontend;

import edu.skidmore.cs326.game.sudoku.frontend.home.HomePage;
import edu.skidmore.cs326.game.sudoku.frontend.home.AboutPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.AccessibilitySettingsPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.AccountSettingsPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.FindGamePage;
import edu.skidmore.cs326.game.sudoku.frontend.home.HelpPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.HowToPlayPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.OptionsPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.PlayPage;
import edu.skidmore.cs326.game.sudoku.frontend.home.SettingsPage;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Grid Clash Home Window This class represents the home window for the Grid
 * Clash game. It extends JFrame and implements Runnable. It creates the home
 * frame in a cardLayout and adds panels to the frame
 * 
 * @author eheidepriem
 */
public class HomeWindow extends Window {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger for LoginWindow.
	 */
	private static final Logger LOG = Logger.getLogger(LoginWindow.class);

	/**
	 * The user in the current window.
	 */
	private User user;
	
	/**
	 * The default size of the board.
	 */
	private int defaultSize;
	
	/**
	 * The default difficulty.
	 */
	private int defaultDifficulty;

	/**
	 * Constructor for HomeWindow.
	 */
	public HomeWindow() {
	}

	/**
	 * Constructor for HomeWindow with a user.
	 * 
	 * @param user2 the user passed.
	 */
	public HomeWindow(User user2) {
		this.user = user2;
		this.setDefaultSize(9);
		this.setDefaultDifficulty(1);
		
	}

	/**
	 * Create the frame for the Home window.
	 */
	protected void createWindow() {

		// Initialize JFrame
		//////////////////////////////////////////// ??ERROR HERE
	    
	    setTitle("GRID CLASH - HOME - " + getUser().getUsername());
		

		setSize(1000, 700);
		setLocation(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create card layout and jpanel that holds card layout
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);
		
		LOG.info("User:" + getUser() 
		    + "| is user null: " + Boolean.toString(getUser() == null)
		    + "| is username : " + getUser().getUsername());

		// Add all panels to card layout
		cardPanel.add(new HomePage(), "Home");
		cardPanel.add(new SettingsPage(), "Settings");		
		cardPanel.add(new AccountSettingsPage(getUser()), 
		        "Account Settings");
		cardPanel.add(new AccessibilitySettingsPage(), 
				"Accessibility Settings");
		cardPanel.add(new OptionsPage(), "Options");
		cardPanel.add(new HelpPage(), "Help");
		cardPanel.add(new AboutPage(), "About");
		cardPanel.add(new HowToPlayPage(), "How to Play");
		cardPanel.add(new PlayPage(), "Play");
		cardPanel.add(new FindGamePage(), "Find a Game");
		// Show home panel first
		cardLayout.show(cardPanel, "Home");

		// Add card panel to the JFrame
		add(cardPanel);
		setVisible(true);
	}

	/**
	 * Closes home window, opens login window.
	 * 
	 * @param dispose the home window.
	 */
	public void homeToLogin(HomeWindow dispose) {
		Runnable application = new LoginWindow();
		new Thread(application).start();
		dispose.dispose();
	}

	/**
	 * Closes home window, opens play window.
	 * 
	 * @param dispose the home window.
	 */
	public void homeToPlay(HomeWindow dispose) {
		Runnable application = new PlayWindow();
		new Thread(application).start();
		dispose.dispose();
	}

	/**
	 * Handles user exit request.
	 */
	public void handleExitRequest() {
		LOG.info("Application shutting down");
	}
	
	/**
	 * This gets the User object attribute.
	 * 
	 * @return current user.
	 */
	public User getUser() {
	    return this.user;
	}
	
	/**
	 * setter method for user.
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
		
	}
	
	/**
	 * @return the currently selected size of board.
	 */
    public int getDefaultSize() {
        return defaultSize;
    }
    
    /**
     * This sets the size of the board.
     * @param defaultSize the size requested
     */
    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }
    
    /**
     * @return the currently selected difficulty of board.
     */
    public int getDefaultDifficulty() {
        return defaultDifficulty;
    }
    
    /**
     * This sets the difficulat of the board.
     * @param defaultDifficulty the suggested difficulty
     */
    public void setDefaultDifficulty(int defaultDifficulty) {
        this.defaultDifficulty = defaultDifficulty;
    }
}
