package edu.skidmore.cs326.game.sudoku.frontend.home;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

/**
 * Grid Clash Settings page.
 * 
 * @author cdavidson eheidepriem
 */
public class SettingsPage extends Page {
	
	/**
	 * Settings Page.
	 */
	public SettingsPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Account settings button.
	 */
	private ButtonGradient accountButton;

	/**
	 * accessibility settings button.
	 */
	private ButtonGradient accessibilityButton;

	/**
	 * options settings button.
	 */
	private ButtonGradient optionsButton;

	/**
	 * help button.
	 */
	private JButton helpButton;

	/**
	 * about page button.
	 */
	private JButton aboutButton;

	/**
	 * width of screen.
	 */
	private int screenWidth;

	/**
	 * height of screen.
	 */
	@SuppressWarnings("unused")
	private int screenHeight;

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(SettingsPage.class);


	/**
	 * Setup Settings panel.
	 */
	protected void setup() {
		addSettingsPanel();
	}


	/**
	 * Adds components to settings panel.
	 */
	private void addSettingsPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);

		screenHeight = this.getHeight();
		screenWidth = this.getWidth();

		addTitle();
		addAccountButton();
		addAccessButton();
		addOptionsButton();
		addHelpButton();
		addAboutButton();
		addBackToHomeButton();
	}

	/**
	 * Adds the title to the page.
	 */
	private void addTitle() {
		JLabel title = super.createTitle("Settings");
		add(title);
		
		JLabel pragLogo = new JLabel("Here");
		pragLogo.setBounds(0, 559, 118, 107);
		add(pragLogo);
		ImageIcon logoOG = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game/sudoku"
				  + "/frontend/assets/"
				  + "PragLogo.png"));
		Image originalProfile = logoOG.getImage();
		Image rescaledProfile = 
			originalProfile.getScaledInstance(
				pragLogo.getWidth(),
				pragLogo.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(rescaledProfile);
		pragLogo.setIcon(logo);
		add(pragLogo);

	}

	/**
	 * Adds account button to the page.
	 */
	private void addAccountButton() {
		int accountWidth = 200;
		int accountHeight = 60;

		accountButton = new ButtonGradient();
		accountButton.setText("Account");
		accountButton.setFont(
			new Font("Monospaced", Font.BOLD, 28));
		accountButton.setBounds(
			screenWidth / 2 - accountWidth / 2, 160,
			accountWidth, accountHeight);

		add(accountButton);

		accountButton.addActionListener((e) -> {
			LOG.info("'Account' Settings Button Pressed");

			// Go to account settings panel
			switchPage("Account Settings");
		});
	}

	/**
	 * Adds accessibility button to the page.
	 */
	private void addAccessButton() {
		int accessWidth = 300;
		int accessHeight = 60;

		accessibilityButton = new ButtonGradient();
		accessibilityButton.setText("Accessibility");
		accessibilityButton.setFont(
			new Font("Monospaced", Font.BOLD, 28));
		accessibilityButton.setBounds(
			screenWidth / 2 - accessWidth / 2, 260,
			accessWidth, accessHeight);

		add(accessibilityButton);

		accessibilityButton.addActionListener((e) -> {
			LOG.info("'Accessibility' Settings Button Pressed");

			// Go to accessibility settings panel
			switchPage("Accessibility Settings");
		});
	}

	/**
	 * Adds options button to the page.
	 */
	private void addOptionsButton() {
		int optionWidth = 200;
		int optionHeight = 60;

		optionsButton = new ButtonGradient();
		optionsButton.setText("Options");
		optionsButton.setFont(
			new Font("Monospaced", Font.BOLD, 28));
		optionsButton.setBounds(
			screenWidth / 2 - optionWidth / 2, 360,
			optionWidth, optionHeight);

		add(optionsButton);

		optionsButton.addActionListener((e) -> {
			LOG.info("'Options' Settings Button Pressed");

			// Go to options panel
			switchPage("Options");
		});
	}

	/**
	 * Adds help button to the page.
	 */
	private void addHelpButton() {
		helpButton = new JButton("<HTML><U>Help</U></HTML>");
		helpButton.setBounds(441, 532, 117, 30);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		add(helpButton);

		helpButton.addActionListener((e) -> {
			LOG.info("'Help' Settings Button Pressed");

			// Go to help panel
			switchPage("Help");
		});
	}

	/**
	 * Adds about button to the page.
	 */
	private void addAboutButton() {
		aboutButton = new JButton("<HTML><U>About</U></HTML>");
		aboutButton.setBounds(441, 490, 117, 30);
		aboutButton.setOpaque(false);
		aboutButton.setContentAreaFilled(false);
		aboutButton.setBorderPainted(false);
		add(aboutButton);

		aboutButton.addActionListener((e) -> {
			LOG.info("'About' Settings Button Pressed");

			// Go to about panel
			switchPage("About");
		});
	}

	/**
	 * Adds back button to the page.
	 */
	private void addBackToHomeButton() {
		ButtonGradient backToHome = createBackButton("< Home", "Home");
		add(backToHome);
	}
}
