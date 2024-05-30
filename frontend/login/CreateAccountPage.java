package edu.skidmore.cs326.game.sudoku.frontend.login;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.logic.Login;
import edu.skidmore.cs326.game.sudoku.logic.LoginLogic;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import edu.skidmore.cs326.game.sudoku.persistence.users.Users;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertUserData;
import edu.skidmore.cs326.game.sudoku.persistence.insert.UserValidation;
import edu.skidmore.cs326.game.sudoku.persistence.insert.InsertAttributes;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;

/**
 * Grid Clash create account page.
 * 
 * @author eheidepriem
 */

public class CreateAccountPage extends Page {
	
	/**
	 * Create Account Page.
	 */
	public CreateAccountPage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger LOG =
		Logger.getLogger(CreateAccountPage.class);

	/**
	 * Create Account page password field.
	 */
	private JTextField emailField;

	/**
	 * User input for Create Account page email field.
	 */
	private String userEmail;

	/**
	 * User input for userBirthdayMonth field.
	 */
	private String userBirthdayMonth;

	/**
	 * User input for userBirthdayDay field.
	 */
	private String userBirthdayDay;

	/**
	 * User input for userBirthdayYear field.
	 */
	private String userBirthdayYear;

	/**
	 * User input for Create Account page privacy agreement.
	 */
	private JComboBox<String> monthBox;

	/**
	 * User input for Create Account page privacy agreement.
	 */
	private JComboBox<String> dayBox;

	/**
	 * User input for Create Account page privacy agreement.
	 */
	private JComboBox<String> yearBox;

	/**
	 * Checkbox for privacy agreement.
	 */
	private JCheckBox privacyCheckBox;

	/**
	 * InsertUserData to add user data.
	 */
	@SuppressWarnings("unused")
	private InsertUserData insert = new InsertAttributes();
	
	/**
	 * Current user.
	 */
	private User user;
	
	/**
	 * Interface for create login logic.
	 */
	private Login check;

	/**
	 * Setup create account panel.
	 */
	protected void setup() {
		privacyCheckBox = 
			new JCheckBox("I agree to the Privacy Agreement");
		addCreateAccountPanel();
	}

	/**
	 * Add the Create Account Panel.
	 */
	private void addCreateAccountPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 748);
		addEmailLabel();
		addEmailTextField();
		addBirthdayLabel();
		addBirthdayBox();
		addCreateAccountButton();
		addPrivacyCheckbox();
		addCreateAccountTitle();
		addBackToLoginButton();
	}

	/**
	 * Add email label to the Create Account panel.
	 */
	private void addEmailLabel() {
		JLabel emailLabel = new JLabel("e-mail:");
		emailLabel.setBounds(305, 302, 50, 50);
		this.add(emailLabel);
		emailLabel.repaint();
	}

	/**
	 * Add email text field to the Create Account panel.
	 */
	private void addEmailTextField() {
		emailField = new JTextField("");
		emailField.setBorder(
			new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailField.setBounds(390, 313, 252, 30);
		this.add(emailField);
		emailField.repaint();
	}

	/**
	 * Add birthday label to the Create Account panel.
	 */
	private void addBirthdayLabel() {
		JLabel passwordLabel = new JLabel("birthday:");
		passwordLabel.setBounds(293, 361, 70, 42);
		this.add(passwordLabel);
		passwordLabel.repaint();
	}

	/**
	 * Add combo boxes to panel. Add months days and years to combo boxes.
	 */
	public void addBirthdayBox() {
		String[] months = { "January", "February", 
				"March", "April", "May", "June",
				"July", "August", "September",
				"October", "November", "December" };

		String[] days = new String[31];
		for (int i = 0; i < 31; i++) {
			days[i] = String.valueOf(i + 1);
		}

		String[] years = new String[101];
		for (int i = 100; i >= 0; i--) {
			years[i] = String.valueOf(2024 - i);
		}

		monthBox = new JComboBox<String>(months);
		monthBox.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		monthBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		monthBox.setBounds(390, 370, 104, 24);
		add(monthBox);

		dayBox = new JComboBox<String>(days);
		dayBox.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dayBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		dayBox.setBounds(506, 370, 50, 24);
		add(dayBox);

		yearBox = new JComboBox<String>(years);
		yearBox.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		yearBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		yearBox.setBounds(572, 370, 70, 24);
		add(yearBox);
	}

	/**
	 * Add privacy agreement checkbox to the Create Account panel.
	 */
	private void addPrivacyCheckbox() {
		privacyCheckBox.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		privacyCheckBox.setOpaque(false);
		privacyCheckBox.setBounds(390, 420, 300, 30);
		this.add(privacyCheckBox);
		privacyCheckBox.repaint();
		privacyCheckBox.addActionListener((e) -> {
			LOG.info("'Privacy Agreement' checkbox clicked");
		});
	}

	/**
	 * Add Create Account button to the Create Account panel.
	 */
	private void addCreateAccountButton() {
		ButtonGradient createAccountButton = new ButtonGradient();
		createAccountButton.setText("Create Account");
		createAccountButton.setColor1(
				new java.awt.Color(61, 110, 167));
		createAccountButton.setColor2(
				new java.awt.Color(61, 110, 167));
		createAccountButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		createAccountButton.setBounds(352, 479, 290, 84);
		createAccountButton.setFont(new Font(
				"Monospaced", Font.BOLD, 25));
		this.add(createAccountButton);
		createAccountButton.repaint();

		createAccountButton.addActionListener((e) -> {
			LOG.info("'Create Account' button clicked");

			// Store email field
			userEmail = emailField.getText();
			// Store birth month field
			userBirthdayMonth = (String) monthBox.getSelectedItem();
			// Store birth day field
			userBirthdayDay = (String) dayBox.getSelectedItem();
			// Store birth year field
			userBirthdayYear = (String) yearBox.getSelectedItem();
			
			check = new LoginLogic();
			boolean v = check.checkCreate(userEmail, 
        			    userBirthdayMonth, 
        			    userBirthdayDay,
        			    userBirthdayYear);

			if (v && privacyCheckBox.isSelected()) {
				LOG.info("User is old enough");
				LOG.info("User email: " + userEmail);

				// Code to verify create account info
				LoginWindow loginWindow =
					(LoginWindow) getParentWindow();

				LOG.info("Checking if email is valid");
				try {
    				// Use Interface - UserValidationInterface
    				if (UserValidation.isValidEmail(userEmail)) {
    					JOptionPane.showMessageDialog(
    					loginWindow, "Email is available");
    					
    					user = new Users(userEmail);
    
    					LOG.info("Attempting to create user "
    						+ "and email code");
    					
    					user.userCreateAccount(userEmail);
    					
    					// Call switchpage
    					switchPage("Account Verification");
    					
    					
    				} else {
    					JOptionPane.showMessageDialog(
    					loginWindow, 
    					"Error adding user to database");
    				}
				} catch (PersistenceException e1) {
				    e1.getMessage();
				}
				// Pass user into account verification
				// Pass user into password creation
				AccountVerificationPage aVP =
					(AccountVerificationPage) loginWindow.
					getAccountVerficationPage();
				PasswordCreationPage pCP = 
					(PasswordCreationPage) loginWindow.
					getPasswordCreationPage();
				aVP.setUser(user);
				pCP.setUser(user);
				aVP.updateWelcome();
				pCP.updateWelcome();
				
				// Reset email field
				emailField.setText("");
				privacyCheckBox.setSelected(false);
				monthBox.setSelectedIndex(0);
				dayBox.setSelectedIndex(0);
				yearBox.setSelectedIndex(0);
			} else if (privacyCheckBox.isSelected()) {
				LOG.info("User is NOT old enough");
				JOptionPane.showMessageDialog(
					this, "User not old enough");
			} else {
				LOG.info("Privacy Agreement not accepted.");
				JOptionPane.showMessageDialog(
				this, "Privacy Agreement not accepted.");
			}
		});
	}

	/**
	 * Add title and grid clash icon to create account panel.
	 */
	private void addCreateAccountTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(196, 76, 575, 141);
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
	 * Add back to login button.
	 */
	private void addBackToLoginButton() {
		JButton backToPlayButton = 
				createBackButton("< Back to Login", "Login");
		add(backToPlayButton);
		//emailField.setText("");
		
	}
}
