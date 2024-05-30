package edu.skidmore.cs326.game.sudoku.frontend.home;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Font;
import java.awt.Image;

/**
 * Grid Clash About page.
 * 
 * @author eheidepriem
 */
public class AboutPage extends Page {
	
	/**
	 * About Page.
	 */
	public AboutPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for about page.
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SettingsPage.class);

	/**
	 * width of screen.
	 */
	@SuppressWarnings("unused")
	private int screenWidth;

	/**
	 * height of screen.
	 */
	@SuppressWarnings("unused")
	private int screenHeight;

	/**
	 * Setup about panel.
	 */
	protected void setup() {
		addAboutPanel();
	}

	/**
	 * Adds the about panel components.
	 */
	private void addAboutPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);
		this.setLayout(null);

		screenHeight = this.getHeight();
		screenWidth = this.getWidth();

		addTitle();
		addBackToSettingsButton();
	}

	/**
	 * Adds the title label to the panel.
	 */
	private void addTitle() {
		JLabel title = super.createTitle("About");
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
		
		JLabel lblDiscoverTheWorld = new JLabel(
		    "<HTML>Discover the world of Grid Clash! Developed "
			+ "by Pragmatic Pioneers Group, Grid Clash is a "
			+ "thrilling 1v1, turn-based online sudoku game that "
			+ "puts your strategic thinking to the test. "
			+ "<BR> <BR>\nAt Pragmatic Pioneers Group, "
			+ "we're dedicated to creating innovative and engaging "
			+ "gaming experiences that challenge and entertain "
			+ "players worldwide. <BR><BR>\nWith Grid Clash, we " 
			+ "aim to redefine the sudoku genre and deliver "
			+ "an exciting new gaming experience. Join us on this "
			+ "journey and see why Grid Clash is the ultimate "
			+ "sudoku showdown.<HTML>");
		

		lblDiscoverTheWorld.setFont(
		            new Font("Monospaced", Font.PLAIN, 20));
		lblDiscoverTheWorld.setBounds(116, 142, 801, 421);
		add(lblDiscoverTheWorld);
	}

	/**
	 * Adds the back to settings button to the panel.
	 */
	private void addBackToSettingsButton() {
		ButtonGradient backToSettings = 
				createBackButton("< Settings", "Settings");
		add(backToSettings);
	}
}
