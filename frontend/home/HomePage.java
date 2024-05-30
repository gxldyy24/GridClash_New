package edu.skidmore.cs326.game.sudoku.frontend.home;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;

/**
 * Grid Clash Home main page.
 * 
 * @author cdavidson
 */

public class HomePage extends Page {
	
	/**
	 * Home Page.
	 */
	public HomePage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dimensions of screen.
	 */
	private Dimension screenSize;
	
	/**
	 * width of window.
	 */
	private int width;
	
	/**
	 * height of window.
	 */
	private int height;
	
	/**
	 * Logger for HomePage.
	 */
	private static final Logger LOG =
		Logger.getLogger(HomePage.class);

//////////////////////////////////////////////////

	/**
	 * Adds the home panel to window.
	 */
	protected void setup() {
		addHomePanel();
	}

//////////////////////////////////////////////////////

	/**
	 * Add components to home panel.
	 */
	private void addHomePanel() {
		LOG.info("height: " + height + " width: "
		+ width);
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);
		screenSize = super.getSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		this.setLayout(null);
		addTitle();
		addPlayButton();
		addSettingsButton();
		addLogoutButton();
		addHowToPlay();
	}

	/**
	 * Add title and grid clash icon to home panel.
	 */
	private void addTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds((width - 600) / 2,
			height / 11, 600, 120);
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
		
		JLabel pragLogo = new JLabel("Here");
		pragLogo.setBounds(0, 559, 118, 107);
		add(pragLogo);
		ImageIcon logoOG2 = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game/sudoku"
				  + "/frontend/assets/"
				  + "PragLogo.png"));
		Image originalProfile2 = logoOG2.getImage();
		Image rescaledProfile2 = 
			originalProfile2.getScaledInstance(
				pragLogo.getWidth(),
				pragLogo.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon logo2 = new ImageIcon(rescaledProfile2);
		pragLogo.setIcon(logo2);
		add(pragLogo);
	}

	/**
	 * Add play button to home panel.
	 */
	private void addPlayButton() {
		ButtonGradient playButton = new ButtonGradient();
		playButton.setText("Play");
		playButton.setBounds(353, 235, 293, 79);
		playButton.setBackground(new Color(61, 110, 167));
		playButton.setColor1(new Color(61, 110, 167));
		playButton.setColor2(new Color(61, 110, 167));
		playButton.setFont(new Font("Monospaced", Font.BOLD, 40));
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		add(playButton);
		playButton.addActionListener((e) -> {
			LOG.info("'PLAY' button clicked");

			// Go to play window
			switchPage("Play");
		});
	}

	/**
	 * Add settings button to home panel.
	 */
	private void addSettingsButton() {
		ButtonGradient settingsButton = new ButtonGradient();
		settingsButton.setText("Settings");
		settingsButton.setFont(new Font("Monospaced", Font.BOLD, 16));
		settingsButton.setBounds(431, 350, 137, 42);

		add(settingsButton);

		settingsButton.addActionListener((e) -> {
			LOG.info("'Settings' button clicked");

			// Go back to settings panel
			switchPage("Settings");
		});
	}

	/**
	 * Add logout button to home panel.
	 */
	private void addLogoutButton() {
		ButtonGradient logoutButton = new ButtonGradient();
		logoutButton.setText("Logout");
		logoutButton.setFont(new Font("Monospaced", Font.BOLD, 18));
		logoutButton.setBounds(806, 575, 162, 42);

		add(logoutButton);

		logoutButton.addActionListener((e) -> {
			LOG.info("'Logout' button clicked");

			// Go back to login window
			switchWindow(new LoginWindow());
		});
	}

	/**
	 * Add how to play button to home panel.
	 */
	private void addHowToPlay() {
		JButton howToPlay = new JButton("How to Play?");
		howToPlay.setBounds(420, 575, 150, 25);
		howToPlay.setOpaque(false);
		howToPlay.setContentAreaFilled(false);
		howToPlay.setBorderPainted(false);
		howToPlay.addActionListener((e) -> {
			LOG.info("'How to play' button clicked");
			switchPage("How to Play");
		});
		add(howToPlay);
	}
}
