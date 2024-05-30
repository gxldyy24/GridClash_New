package edu.skidmore.cs326.game.sudoku.frontend.home;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

/**
 * Grid Clash how to play page.
 * 
 * @author cdavidson
 */
public class HowToPlayPage extends Page {
	
	/**
	 * How to Play Page.
	 */
	public HowToPlayPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for HowToPlayPage.
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(HomePage.class);

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
	 * Setup HowToPlayPage.
	 */
	protected void setup() {
		setBackground(new Color(238, 238, 236));
		addHowToPanel();
	}

	/**
	 * add components to panel.
	 */
	private void addHowToPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);

		screenHeight = this.getHeight();
		screenWidth = this.getWidth();

		addBackToHomeButton();
		addTitle();
	}

	/**
	 * add title.
	 */
	private void addTitle() {
		JLabel title = super.createTitle("How to Play");
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
		
		JLabel lblDownloadGridClash = new JLabel(
		    "<HTML>\n    <BR><BR>Choose \"Play Now\" "
    		  + "to be matched with an opponent or invite "
    		  + "friends to join.\n    <BR><BR>Familiarize "
    		  + "yourself with the game interface, "
    		  + "including the sudoku grid and number "
    		  + "selector.\n    <BR><BR>Take turns filling "
    		  + "in numbers on the grid according to sudoku "
    		  + "rules.\n    <BR><BR>Aim to complete the "
    		  + "grid before your opponent to claim victory."
    		  + "\n    <BR><BR>Don't forget to use your "
    		  + "power-ups!\n    <BR><BR>Customize your "
    		  + "in-game icons in Settings -> Options for "
    		  + "fun variety.\n    <BR><BR>Enjoy "
    		  + "challenging gameplay and compete with "
    		  + "friends and players worldwide!\n<HTML>");
		lblDownloadGridClash.setFont(
		    new Font("Monospaced", Font.PLAIN, 20));
		lblDownloadGridClash.setBounds(130, 112, 801, 499);
		add(lblDownloadGridClash);
		
	}

	/**
	 * Add back to home page button.
	 */
	private void addBackToHomeButton() {
		ButtonGradient backToHome = createBackButton("< Home", "Home");
		add(backToHome);
	}
}
