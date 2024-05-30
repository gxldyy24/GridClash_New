package edu.skidmore.cs326.game.sudoku.frontend.home;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.WindowData;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 * This will house the Accessibility Settings page.
 * 
 * @author jbiggins
 */
public class AccessibilitySettingsPage extends Page {
	
	/**
	 * Accessibility page.
	 */
	public AccessibilitySettingsPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for accessibility settings panel.
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = 
		Logger.getLogger(AccessibilitySettingsPage.class);

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
	 * Setup accessibility settings panel.
	 */
	protected void setup() {
		addAboutPanel();
	}

	/**
	 * Add about panel.
	 */
	private void addAboutPanel() {
		this.setLayout(null);
		this.setBounds(350, 250,
			WindowData.getWindowWidth(),
			WindowData.getWindowHeight());
		this.setLayout(null);

		screenHeight = this.getHeight();
		screenWidth = this.getWidth();

		addTitle();
		addBackToSettingsButton();
		addTextSizeOption();
	}

	/**
	 * Add title to accessibility settings panel.
	 */
	private void addTitle() {
		JLabel title = super.createTitle("Accessibility");
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
	 * Add back to settings button to accessibility settings panel.
	 */
	private void addBackToSettingsButton() {
		ButtonGradient backToHome =
			createBackButton("< Settings", "Settings");
		add(backToHome);
	}

	/**
	 * Add text size scrollbar to accessibility settings panel.
	 */
	private void addTextSizeOption() {
		JSlider slider = new JSlider();
		slider.setBounds(445, 194, 279, 16);
		add(slider);

		JLabel txtSizeLabel = new JLabel("Text Size");
		txtSizeLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtSizeLabel.setBounds(239, 159, 182, 75);
		add(txtSizeLabel);

		JLabel littleALabel = new JLabel("A");
		littleALabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
		littleALabel.setBounds(454, 167, 45, 15);
		add(littleALabel);

		JLabel mediumALabel = new JLabel("A");
		mediumALabel.setForeground(new Color(51, 51, 51));
		mediumALabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		mediumALabel.setBounds(568, 167, 45, 15);
		add(mediumALabel);

		JLabel bigALabel = new JLabel("A");
		bigALabel.setFont(new Font("Monospaced", Font.PLAIN, 21));
		bigALabel.setBounds(699, 167, 45, 15);
		add(bigALabel);

		JLabel lblColorBlindnessMode =
				new JLabel("Color Blindness Mode");
		lblColorBlindnessMode.setFont(
			new Font("Monospaced", Font.PLAIN, 25));
		lblColorBlindnessMode.setBounds(121, 120, 300, 30);
		add(lblColorBlindnessMode);

		JCheckBox checkBoxColorBlindness = new JCheckBox("");
		checkBoxColorBlindness.setBounds(556, 120, 21, 21);
		add(checkBoxColorBlindness);
	}
}
