package edu.skidmore.cs326.game.sudoku.frontend.home;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Grid Clash Help page.
 * 
 * @author cdavidson
 */
public class HelpPage extends Page {
	
	/**
	 * Help Page.
	 */
	public HelpPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for help page.
	 */
	private static final Logger LOG = Logger.getLogger(HelpPage.class);

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
	 * Setup help panel.
	 */
	protected void setup() {
		addHelpPanel();
	}

	/**
	 * Add components to help panel.
	 */
	private void addHelpPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);
		this.setLayout(null);

		screenHeight = this.getHeight();
		screenWidth = this.getWidth();

		addTitle();
		addBackToSettingsButton();
	}

	/**
	 * Add title to help panel.
	 */
	private void addTitle() {
		JLabel title = createTitle("Help");
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
		
		JLabel lblHelo = new JLabel(
		    "<HTML>\n<BR>FAQs: Browse through frequently "
		    + "asked questions to find quick answers to "
		    + "common queries.\n<BR><BR>Community Support: "
		    + "Join our community forums to connect with "
		    + "other players, share tips and strategies, "
		    + "and get help from experienced players.\n<BR>"
		    + "<BR>Feedback: Have suggestions or ideas to "
		    + "improve Grid Clash? We'd love to hear from "
		    + "you! Share your feedback with us - "
		    + "feedback@gridclash.io\n<BR><BR>Contact Us: "
		    + "Can't find what you're looking for? Reach "
		    + "out to our support team directly for "
		    + "personalized assistance - "
		    + "support@gridclash.io\n\n\n");
		lblHelo.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblHelo.setBounds(152, 85, 696, 536);
		add(lblHelo);
	}

	/**
	 * Add back to settings button.
	 */
	private void addBackToSettingsButton() {
		ButtonGradient backToSettingsButton = new ButtonGradient();
		backToSettingsButton.setText("< Settings");
		backToSettingsButton.setFont(
			new Font("Monospaced", Font.BOLD, 14));
		backToSettingsButton.setBounds(12, 37, 120, 40);

		add(backToSettingsButton);

		backToSettingsButton.addActionListener((e) -> {
			LOG.info("'Back to settings' Button Pressed");

			// Go back to settings
			switchPage("Settings");
		});
	}
}
