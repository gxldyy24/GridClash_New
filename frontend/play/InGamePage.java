package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JToggleButton;

//import us.daveread.edu.graphics.shape.Drawable;

//import us.daveread.edu.graphics.shape.impl.Image;
//import us.daveread.edu.graphics.surface.MainFrame;
//import us.daveread.edu.graphics.surface.Drawingsurface;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.HomeWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.PlayWindow;
import edu.skidmore.cs326.game.sudoku.frontend.home.PlayPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import edu.skidmore.cs326.game.utility.GameController;
import javax.swing.SwingConstants;

/**
 * Grid Clash Find Game page.
 * 
 * @author cdavidson
 */
public class InGamePage extends Page {

	/**
	 * InGame Page.
	 * @param size the size o fthe board
	 * @param diff the requested difficulty of the board
	 */
	public InGamePage(int size, int diff) {
	    this.size = size;
	    this.diff = diff;
	    addInGamePanel();
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger LOG = Logger.getLogger(PlayPage.class);

	/**
	 * variable that determines the dimensions of the
     *  board ex. boardSize = 9, then board will be 9x9.
	 */
	private int size;
	
	/**
	 * The requested difficulty of the board.
	 */
	private int diff;
	
	/**
	 * Panel that holds all the information on the gameboard.
	 */
	private JPanel numPanel;

	/**
	 * Button user presses to send their current move.
	 */
	@SuppressWarnings("unused")
	private ButtonGradient sendMoveButton;

	/**
	 * Button to pull up in-game options.
	 */
	private ButtonGradient optionButton;

	/**
	 * Dimensions of screen.
	 */
	private Dimension screenSize;

	/**
<<<<<<< HEAD
	 * variable that determines the dimensions of the
	 *  board ex. boardSize = 9, then board will be 9x9.
	 */ 
	private int boardSize;
	
	/**
	 * variable that determines the difficulty of the
	 *  board, 1 for easy(1/4 of the numbers hidden), 
	 *  2 for medium(2/4 of the numbers hidden), and 
	 *  3 for hard(3/4 of the numbers hidden.
	 */ 
	private int boardDiff;

	/**
=======
>>>>>>> e7e6bf8baefb4d008e3d0299d1cc209076db685c
	 * This is the board that will be called 
	 * to in the game that is in charge of the logic.
	 */
	@SuppressWarnings("unused")
	private GameController controller;

	/**
	 * This displays whos turn it is.
	 */
	private TurnIndicator turnIndicator;

	/**
	 * This is the code for what the tiles will display.
	 */
	private int selectedNumCode;

	/**
	 * button to send selected move.
	 */
	private SendButton sendButton;

	/**
	 * The board.
	 */
	private SwingBoard theBoard;
	
	/**
	 * The strike counter.
	 */
	private StrikeCounter strikeCounter;
	
	/**
	 * Users this current user.
	 */
	@SuppressWarnings("unused")
	private User user;
	
	
	/**
	 * setup in game page.
	 */
	protected void setup() {
		
	}

	/**
	 * Displaying in-game screen.
	 */
	private void addInGamePanel() {

		this.setLayout(null);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, screenSize.width, screenSize.height);

		setSelectedNumCode(-1);
		
		this.theBoard = new SwingBoard(this.size, this.diff);
		
		addSmallLogo();
		addTurnLabel();
		addSendMoveButton();
		addOptionMenuButton();
		addNumberButtons(this.size);
		addGameBoard();
		addStrikeCounter(this.size);
		

		repaint();
	}

	/**
	 * initialized gameboard.
	 */
	private void addGameBoard() {
		this.theBoard.setup();
		add(theBoard);
	}
	
	/**
	 * Sets current user in JPanel.
	 * 
	 * @param user2
	 */
	public void setUser(User user2) {
		this.user = user2;
	}

	/**
	 * Creates the keypad of numbers on-screen. Only 1 number can be
	 *  selected at a time.
	 * 
	 * @param gameboardSize
	 */
	private void addNumberButtons(int gameboardSize) {
		// int gameboardSize = 9;
		// This would be a parameter
		// eventually, sosize can change
		numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Create a ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();

		for (int i = 1; i <= gameboardSize; i++) {
			JToggleButton button = 
				new JToggleButton(String.valueOf(i));
			button.setBackground(Color.LIGHT_GRAY);
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Monospaced", Font.BOLD, 28));

			// add action listener to each button,
			// so it can return which is
			// being clicked
			button.addActionListener((e) -> {
				String buttonText = 
					((JToggleButton) e.getSource())
					                    .getText();
				System.out.println("Button clicked: " 
					                    + buttonText);
				setSelectedNumCode(
				    Integer.parseInt(buttonText));
				
				SwingTile currentTile = SwingTile
				                        .getCurrentTile();
				if (currentTile == null) {
				    return;
				}
				
				if (!currentTile.isSetInStone()) {
				    currentTile.setText(buttonText);
				}
			});

			buttonGroup.add(button);
			numPanel.add(button);
		}

		// Add the button panel to the main panel.
		add(numPanel);

		// Set the size and position of the button panel.
		numPanel.setBounds(0, 0, screenSize.width, 100);
		int x = (screenSize.width - numPanel.getWidth()) / 2;
		int y = (int) (screenSize.height - numPanel.getHeight() * 1.75);
		numPanel.setLocation(x, y);
	}

	/**
	 * add button on left middle.
	 */
	private void addSendMoveButton() {
		sendButton = new SendButton(this.turnIndicator);
		sendButton.setLocation(
		    screenSize.width / 9, screenSize.height / 3);
		add(sendButton);
		sendButton.repaint();
	}
	
	/**
	 * Update the selected tile.
	 * 
	 * @param selectedTile the selected tile
	 */
	public void updateSelectedTile(SwingTile selectedTile) {
	    sendButton.setSelectedTile(selectedTile);
	}
	
    /**
     * Handle selection of a number button.
     * 
     * @param selectedCode the selected number code
     */
    public void setSelectedCode(int selectedCode) {
        this.selectedNumCode = selectedCode;
    }
    

	/**
	 * add option button.
	 */
	private void addOptionMenuButton() {
		int buttonWidth = 125;
		int buttonHeight = 40;

		optionButton = new ButtonGradient();
		optionButton.setText("Return");
		optionButton.setColor1(new Color(208, 208, 203));
		optionButton.setColor2(new Color(208, 208, 203));
		optionButton.setForeground(Color.WHITE);
		optionButton.setBackground(new Color(208, 208, 203));
		// optionButton.setFont(new Font("Monospaced", Font.BOLD, 14));
		optionButton.setBounds((int) 
		          (screenSize.width - buttonWidth * 1.2),
		          buttonHeight, 
		          buttonWidth, 
		          buttonHeight);
		add(optionButton);

		// want to add a side bar pop-up for leaving game
		// also maybe include chat on the pop-up?
		optionButton.addActionListener((e) -> {

			LOG.info("Option Button Clicked");
			PlayWindow playWindow = (PlayWindow) getParentWindow();
			User u = playWindow.getUser();
			HomeWindow back = new HomeWindow(u);
			switchWindow(back);
		});
	}

	/**
	 * add small logo on top left of screen.
	 */
	private void addSmallLogo() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(12, 7, 227, 67);
		add(gridClashLogo);
		ImageIcon logoOG = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game/sudoku"
				  + "/frontend/assets/"
				  + "GridClashLogo.png"));
		Image originalProfile = logoOG.getImage();
		Image rescaledProfile = 
			originalProfile.getScaledInstance(
				gridClashLogo.getWidth(),
				gridClashLogo.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(rescaledProfile);
		gridClashLogo.setIcon(logo);
		add(gridClashLogo);
	}

	/**
	 * Label for whose turn it is.
	 */
	private void addTurnLabel() {

		turnIndicator = new TurnIndicator(true);

		add(turnIndicator);

	}
	
	/**
	 * add the strike counter.
	 * @param size the size of the board
	 */
	private void addStrikeCounter(int size) {
		strikeCounter = new StrikeCounter(
		                   (int) Math.sqrt(size));
		strikeCounter.setBounds((int) (screenSize.width 
				/ 1.25), (int) (screenSize.height 
						/ 3), 100, 300);
		add(strikeCounter);
		
		JLabel lblStrikes = new JLabel("Strikes");
		lblStrikes.setFont(new Font("Monospaced", Font.BOLD, 16));
		lblStrikes.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrikes.setBounds(1536, 333, 100, 15);
		add(lblStrikes);
	}

	/**
	 * Setter for selected code.
	 * 
	 * @param selectedCode
	 */
	private void setSelectedNumCode(int selectedCode) {
		selectedNumCode = selectedCode;
	}

	/**
	 * Getter for selected number in the number bar.
	 * 
	 * @return selectedNumCode
	 */
	public int getSelectedCode() {
		return selectedNumCode;
	}
	
    /**
     * Method to clear selected code.
     */
    public void clearSelectedCode() {
        this.selectedNumCode = -1;
    }
    
    /**
     * Getter for strike counter.
     * @return strikeCounter
     */
    public StrikeCounter getStrikeCounter() {
        return strikeCounter;
    }
    
    /**
     * Getter for swing board.
     * @return theBoard
     */
    public SwingBoard getTheBoard() {
        return theBoard;
    }
}
