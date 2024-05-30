package edu.skidmore.cs326.game.sudoku.frontend.home;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.HomeWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import org.apache.log4j.Logger;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * Grid Clash Login main page.
 * 
 * @author eheidepriem vzhao
 */
public class PlayPage extends Page {
	
	/**
	 * PlayPage constructor.
	 * 
	 * @author eheidepriem vzhao
	 */
	public PlayPage() {
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
	 * Setup play panel.
	 */
	public void setup() {
		addPlayPanel();
	}

	////////////////////////////////////////////////////////////////////

	/**
	 * Add components to play panel.
	 */
	private void addPlayPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1290, 750);
		addWaitingRoomButton();
		addTitleAndLogo();
		addBackToHomeButton();
		addSelectDifficulty();
		addSelectBoardSize();

	}

	/**
	 * Add find game button to the play panel.
	 */
	private void addWaitingRoomButton() {
		ButtonGradient findGameButton = new ButtonGradient();
		findGameButton.setText("Find A Game");
		findGameButton.setBounds(335, 465, 293, 79);
		findGameButton.setColor1(
			new Color(61, 110, 167));
		findGameButton.setColor2(
			new Color(61, 110, 167));
		findGameButton.setFont(
			new Font("Monospaced", Font.BOLD, 28));

		this.add(findGameButton);
		findGameButton.repaint();
		findGameButton.addActionListener((e) -> {
			LOG.info("'Find a Game' button selected");

			// Go to find game panel
			switchPage("Find a Game");
		});
	}

	/**
	 * Add title and grid clash icon to the play panel.
	 */
	private void addTitleAndLogo() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(212, 24, 575, 141);
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
	 * Add back to home button to the play panel.
	 */
	private void addBackToHomeButton() {		
		JButton backToHome = 
				createBackButton("< Home", "Home");
		add(backToHome);

		backToHome.addActionListener((e) -> {
			LOG.info("'Back to Home' button clicked");
			
			switchPage("Home");
			// this.playWindow.playToHome(this.playWindow);
		});

	}

	/**
	 * Adds difficulty checkboxes and labels.
	 */
	private void addSelectDifficulty() {
	    
	    
	    
	    ButtonGroup difficultyButtonGroup 
	                        = new ButtonGroup();
	    
	    
	    
		JLabel selectDifficultyLabel = 
			new JLabel("select difficulty:");
		selectDifficultyLabel.setBounds(127, 206, 324, 36);
		selectDifficultyLabel.setFont(
				new Font("Monospaced", Font.PLAIN, 30));
		this.add(selectDifficultyLabel);
		selectDifficultyLabel.repaint();

		JRadioButton easyCheckbox = new JRadioButton("easy");
		easyCheckbox.setLocation(234, 278);
		easyCheckbox.setSize(59, 23);
		easyCheckbox.addActionListener((e) -> {
			LOG.info("'easy' checkbox selected.");
	         HomeWindow homeWindow = (HomeWindow) getParentWindow();
	         homeWindow.setDefaultDifficulty(0);

		});
		this.add(easyCheckbox);
		difficultyButtonGroup.add(easyCheckbox);

		JRadioButton medCheckbox = new JRadioButton("medium");
		medCheckbox.setLocation(234, 317);
		medCheckbox.setSize(102, 23);
		medCheckbox.addActionListener((e) -> {
			LOG.info("'medium' checkbox selected.");
	          HomeWindow homeWindow = (HomeWindow) getParentWindow();
	          homeWindow.setDefaultDifficulty(1);
		});
		this.add(medCheckbox);
		difficultyButtonGroup.add(medCheckbox);
		medCheckbox.setSelected(true);

		JRadioButton hardCheckbox = new JRadioButton("hard");
		hardCheckbox.setLocation(235, 359);
		hardCheckbox.setSize(58, 23);
		hardCheckbox.addActionListener((e) -> {
			LOG.info("'hard' checkbox selected.");
	        HomeWindow homeWindow = (HomeWindow) getParentWindow();
	        homeWindow.setDefaultDifficulty(2);
		});
		this.add(hardCheckbox);
		difficultyButtonGroup.add(hardCheckbox);

	}

	/**
	 * Adds board size checkboxes and labels.
	 */
	private void addSelectBoardSize() {
	    
	    
	    ButtonGroup sizeButtonGroup = new ButtonGroup();
	    
		JLabel selectBoardSizeLabel = 
			new JLabel("select board size:");
		selectBoardSizeLabel.setBounds(520, 206, 324, 36);
		selectBoardSizeLabel.setFont(
				new Font("Monospaced", Font.PLAIN, 30));
		this.add(selectBoardSizeLabel);
		selectBoardSizeLabel.repaint();

		JRadioButton fourByFourCheckbox = new JRadioButton("4x4");
		fourByFourCheckbox.setLocation(648, 278);
		fourByFourCheckbox.setSize(59, 23);
		fourByFourCheckbox.addActionListener((e) -> {
			LOG.info("'4x4' checkbox selected.");
	        HomeWindow homeWindow = (HomeWindow) getParentWindow();
	        homeWindow.setDefaultSize(4);
		});
		this.add(fourByFourCheckbox);
		sizeButtonGroup.add(fourByFourCheckbox);

		JRadioButton nineByNineCheckbox = new JRadioButton("9x9");
		nineByNineCheckbox.setLocation(648, 317);
		nineByNineCheckbox.setSize(48, 23);
		nineByNineCheckbox.addActionListener((e) -> {
			LOG.info("'9x9' checkbox selected.");
	        HomeWindow homeWindow = (HomeWindow) getParentWindow();
	        homeWindow.setDefaultSize(9);
		});
		this.add(nineByNineCheckbox);
		sizeButtonGroup.add(nineByNineCheckbox);
		nineByNineCheckbox.setSelected(true);
		
		JRadioButton sixteenXSixteen = new JRadioButton("16x16");
		sixteenXSixteen.setLocation(648, 359);
		sixteenXSixteen.setSize(64, 23);
		sixteenXSixteen.addActionListener((e) -> {
            LOG.info("'25x25' checkbox selected.");
            HomeWindow homeWindow = (HomeWindow) getParentWindow();
            homeWindow.setDefaultSize(16);
        });
        this.add(sixteenXSixteen);
        sizeButtonGroup.add(sixteenXSixteen);
		
		

		JRadioButton twentyfiveByTwentyfiveCheckbox = 
				new JRadioButton("25x25");
		twentyfiveByTwentyfiveCheckbox.setLocation(648, 402);
		twentyfiveByTwentyfiveCheckbox.setSize(64, 23);
		twentyfiveByTwentyfiveCheckbox.addActionListener((e) -> {
			LOG.info("'25x25' checkbox selected.");
	        HomeWindow homeWindow = (HomeWindow) getParentWindow();
	        homeWindow.setDefaultSize(25);
		});
		this.add(twentyfiveByTwentyfiveCheckbox);
		sizeButtonGroup.add(twentyfiveByTwentyfiveCheckbox);
	}
}
