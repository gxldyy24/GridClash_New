package edu.skidmore.cs326.game.sudoku.frontend.home;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import javax.swing.JRadioButton;
import java.awt.Color;

/**
 * Grid Clash Options page.
 * 
 * @author eheidepriem
 */

public class OptionsPage extends Page {
	
	/**
	 * Options Page.
	 */
	public OptionsPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for options panel.
	 */
	private static final Logger LOG = Logger.getLogger(SettingsPage.class);

	/**
	 * screen height.
	 */
	private int screenHeight;

	/**
	 * screen width.
	 */
	private int screenWidth;
	
	/**
	 * light theme btn.
	 */
	private JRadioButton rdbtnLightoriginal;
	
	/**
	 * dark theme btn.
	 */
	private JRadioButton rdbtnDark;
	
	/**
	 * electric blue btn.
	 */
	private JRadioButton rdbtnElectricBlue;
	
	/**
	 * apply btn.
	 */
	private ButtonGradient btngrdntApply;
	
	/**
	 * title.
	 */
	private JLabel title;

	/**
	 * Setup options panel.
	 */
	protected void setup() {
		addSettingsPanel();
	}

/////////////////////////////////////////////////

	/**
	 * Add components to settings panel.
	 */
	private void addSettingsPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);
		this.setLayout(null);

		setScreenWidth(this.getWidth());
		setScreenHeight(this.getHeight());

		addTitle();
		addBackToSettingsButton();
		addThemes();
	}

	/**
	 * Add title to options panel.
	 */
	private void addTitle() {
		title = super.createTitle("Options");
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
	 * Add themes label to options panel.
	 */
	private void addThemes() {
		rdbtnLightoriginal = new JRadioButton("Light (Original)");
		rdbtnLightoriginal.setFont(
		    new Font("Monospaced", Font.BOLD, 20));
		rdbtnLightoriginal.setBounds(132, 165, 246, 23);
		add(rdbtnLightoriginal);
		
		rdbtnDark = new JRadioButton("Dark");
		rdbtnDark.setFont(new Font("Monospaced", Font.BOLD, 20));
		rdbtnDark.setBounds(459, 165, 149, 23);
		add(rdbtnDark);
		
		rdbtnElectricBlue = new JRadioButton("Electric Blue\n");
		rdbtnElectricBlue.setFont(
		    new Font("Monospaced", Font.BOLD, 20));
		rdbtnElectricBlue.setBounds(677, 165, 198, 23);
		add(rdbtnElectricBlue);
		
		btngrdntApply = new ButtonGradient();
		btngrdntApply.setText("Apply");
		btngrdntApply.setForeground(Color.WHITE);
		btngrdntApply.setFont(new Font("Monospaced", Font.BOLD, 28));
		btngrdntApply.setColor2(new Color(61, 110, 167));
		btngrdntApply.setColor1(new Color(61, 110, 167));
		btngrdntApply.setBackground(new Color(61, 110, 167));
		btngrdntApply.setBounds(353, 524, 293, 78);
		add(btngrdntApply);
		
		JRadioButton rdbtnNumbersoriginal = 
			new JRadioButton("Numbers (Original)");
		rdbtnNumbersoriginal.setFont(
			new Font("Monospaced", Font.BOLD, 20));
		rdbtnNumbersoriginal.setBounds(132, 355, 246, 23);
		add(rdbtnNumbersoriginal);
		
		JRadioButton rdbtnShapes = new JRadioButton("Shapes");
		rdbtnShapes.setFont(new Font("Monospaced", Font.BOLD, 20));
		rdbtnShapes.setBounds(459, 355, 118, 23);
		add(rdbtnShapes);
		
		JRadioButton rdbtnCustomupload = 
			new JRadioButton("Custom (Upload)");
		rdbtnCustomupload.setFont(
			new Font("Monospaced", Font.BOLD, 20));
		rdbtnCustomupload.setBounds(677, 355, 246, 23);
		add(rdbtnCustomupload);
		
		ButtonGradient btngrdntUploadFromThis = new ButtonGradient();
		btngrdntUploadFromThis.setText("Upload");
		btngrdntUploadFromThis.setForeground(Color.WHITE);
		btngrdntUploadFromThis.setFont(
			new Font("Monospaced", Font.BOLD, 20));
		btngrdntUploadFromThis.setColor2(new Color(61, 110, 167));
		btngrdntUploadFromThis.setColor1(new Color(61, 110, 167));
		btngrdntUploadFromThis.setBackground(new Color(61, 110, 167));
		btngrdntUploadFromThis.setBounds(687, 386, 193, 53);
		add(btngrdntUploadFromThis);
		
		// Apply btn action listener
		btngrdntApply.addActionListener((e) -> {
			LOG.info("'Apply theme' Button Pressed");
			if (rdbtnLightoriginal.isSelected()) {
				LOG.info("Light theme selected");
				setLight();
			} else if (rdbtnDark.isSelected()) {
				LOG.info("Dark theme selected");
				setDark();
			} else if (rdbtnElectricBlue.isSelected()) {
				LOG.info("Electric Blue theme selected");
				setElectricBlue();
				
			} else {
				LOG.info("No theme selected");
			}

		});
		
		
		// Light theme action listener
		rdbtnLightoriginal.addActionListener((e) -> {
			LOG.info("'Light Theme' Button Pressed");
			rdbtnDark.setSelected(false);
			rdbtnElectricBlue.setSelected(false);

		});
		
		// Dark theme action listener
		rdbtnDark.addActionListener((e) -> {
			LOG.info("'Dark Theme' Button Pressed");
			rdbtnLightoriginal.setSelected(false);
			rdbtnElectricBlue.setSelected(false);

		});
		
		// Electric blue action listener
		rdbtnElectricBlue.addActionListener((e) -> {
			LOG.info("'Electric Blue Theme' Button Pressed");
			rdbtnDark.setSelected(false);
			rdbtnLightoriginal.setSelected(false);

		});
	}

	/**
	 * Add back to settings label to options panel.
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

			// Go to settings
			switchPage("Settings");
		});
	}

	/**
	 * 
	 * @return screenHeight
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * 
	 * @param screenHeight
	 */
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
	 * 
	 * @return screenWidth
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * 
	 * @param screenWidth
	 */
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	/**
	 * Sets theme to Light.
	 */
	public void setLight() {
		rdbtnLightoriginal.setFont(
		    new Font("Monospaced", Font.BOLD, 20));
		btngrdntApply.setColor2(new Color(61, 110, 167));
		btngrdntApply.setColor1(new Color(61, 110, 167));
		btngrdntApply.setBackground(new Color(61, 110, 167));
		title.setForeground(Color.black);
		this.setBackground(Color.white);
	}
	
	/**
	 * Sets theme to Light.
	 */
	public void setDark() {
		rdbtnLightoriginal.setForeground(Color.white);
		btngrdntApply.setColor2(Color.white);
		btngrdntApply.setColor1(Color.white);
		btngrdntApply.setBackground(Color.white);
		title.setForeground(Color.white);
		this.setBackground(Color.black);
	}
	
	/**
	 * Sets theme to Light.
	 */
	public void setElectricBlue() {
		rdbtnLightoriginal.setFont(
		    new Font("Monospaced", Font.BOLD, 20));
		btngrdntApply.setColor2(new Color(61, 110, 167));
		btngrdntApply.setColor1(new Color(61, 110, 167));
		btngrdntApply.setBackground(new Color(61, 110, 167));
		title.setForeground(Color.white);
		this.setBackground(Color.blue);
	}
}
