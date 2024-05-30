package edu.skidmore.cs326.game.sudoku.frontend.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.logic.Login;
import edu.skidmore.cs326.game.sudoku.logic.LoginLogic;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;

/**
 * Grid clash password created page.
 * 
 * @author eheidepriem vzhao
 */
public class PasswordResetPage extends Page {
	
	/**
	 * The constructor for this class.
	 */
	public PasswordResetPage() {
	}
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for PasswordCreationPage.
	 */
	private static final Logger LOG = 
			Logger.getLogger(PasswordResetPage.class);

	/**
	 * Create Password page password field.
	 */
	private JPasswordField passwordField;

	/**
	 * Create Password page password confirmation field.
	 */
	private JPasswordField passwordConfirmationField;

	/**
	 * User input for new password field.
	 */
	private String userPassword;

	/**
	 * User input for confirm password field.
	 */
	private String userConfirmationPassword;

	/**
	 * Label for new password.
	 */
	private JLabel newPasswordLabel;

	/**
	 * Label for confirm password.
	 */
	private JLabel confirmPasswordLabel;

	/**
	 * Label for password length..
	 */
	private JLabel passLengthLabel;
	/**
	 * Check box for show password.
	 */
	private JCheckBox chckbxShowPassword;

	/**
	 * Current user.
	 */
	private User user;

	/**
	 * Label for user identification.
	 */
	private JLabel lblWelcome;
	
	/**
	 *Interface to check verification.
	 */
	private Login check;

	/**
	 * Set up password creation panel.
	 */
	protected void setup() {
		addCreateAccountPanel();
	}

	/**
	 * Sets current user in JPanel.
	 * 
	 * @param thisUser
	 */
	public void setUser(User thisUser) {
		this.user = thisUser;
	}

	/**
	 * Add components to CreateAccountPanel.
	 */
	private void addCreateAccountPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 748);
		addTitle();
		addNewPasswordField();
		addPasswordConfirmationField();
		addConfirmButton();
		addShowPasswordCheckbox();
		addLightDarkToggle();
		addWelcome();
	}

	/**
	 * Add Create Account button to the Create Account panel.
	 */
	@SuppressWarnings("deprecation")
    private void addConfirmButton() {
		JButton createAccountButton = new JButton("Reset Password");
		createAccountButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccountButton.setBounds(353, 400, 290, 84);
		createAccountButton.setOpaque(true);
		createAccountButton.setContentAreaFilled(true);
		createAccountButton.setBorderPainted(true);
		createAccountButton.setBackground(new Color(61, 110, 167));
		createAccountButton.setForeground(Color.white);
		createAccountButton.setFont(
			new Font("Monospaced", Font.BOLD, 25));
		this.add(createAccountButton);

		createAccountButton.repaint();
		createAccountButton.addActionListener((e) -> {
			LOG.info("'Create Account' button clicked");
			
			try {
			    this.userPassword = passwordField.getText();
                this.userConfirmationPassword = 
                		passwordConfirmationField.getText();
    			if (matchPassword() && isValidPassword(userPassword)) {
                    this.user.setPassword(userConfirmationPassword);
                    
        			check = new LoginLogic();
        			boolean v = check.checkPassReset(userPassword, 
                			    userConfirmationPassword, 
                			    this.user);
    
                    if (v) {
                        JOptionPane.showMessageDialog(
                        	this, "Password changed, "
                        		+ "you may now login.");
                        switchPage("Login");
                        passwordField.setText("");
                        passwordConfirmationField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(
                        	this, "Account not Created, "
                        		+ "could not add password.");
                    }
    			} else if (!isValidPassword(userPassword)) {
    				LOG.info("Passwords do not match");
    				JOptionPane.showMessageDialog(null,
    					"Invalid Password. \n"
    					+ "The password must be at least "
    					+ "10 characters long.\n"
    					+ "The password must contain at "
    					+ "least one letter and one digit.\n"
    					+ "The password can contain "
    					+ "the following special "
    					+ "characters: "
    					+ "!@#$%^&*()-_+={}[]|;:,.<>?\n",
    					"Invalid Passwords",
    					 JOptionPane.OK_CANCEL_OPTION);
    				passwordField.setText("");
    				passwordConfirmationField.setText("");
    			} else if (!matchPassword()) {
    				LOG.info("Passwords do not match");
    				JOptionPane.showMessageDialog(
    						null, "Passwords do not match.",
    						"Invalid Passwords",
    						JOptionPane.OK_CANCEL_OPTION);
    				passwordField.setText("");
    				passwordConfirmationField.setText("");
    			} else {
    				passwordField.setText("");
    				passwordConfirmationField.setText("");
    			}
			} catch (PersistenceException e1) {
			    e1.getMessage();
			}
		});
	}

	/**
	 * Add new password field to the create account panel.
	 */
	private void addNewPasswordField() {
		passwordField = new JPasswordField("");
		passwordField.setBounds(412, 316, 200, 30);
		this.add(passwordField);
		passwordField.repaint();

		newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setFont(
			new Font("Monospaced", Font.BOLD, 14));
		newPasswordLabel.setBounds(265, 264, 117, 23);
		add(newPasswordLabel);
	}

	/**
	 * Add password confirmation field.
	 */
	private void addPasswordConfirmationField() {
		passwordConfirmationField = new JPasswordField("");
		passwordConfirmationField.setBounds(412, 261, 200, 30);
		this.add(passwordConfirmationField);
		passwordConfirmationField.repaint();

		confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setFont(
			new Font("Monospaced", Font.BOLD, 14));
		confirmPasswordLabel.setBounds(265, 319, 144, 23);
		add(confirmPasswordLabel);

		passLengthLabel = 
			new JLabel("Password must be at"
				+ " least 10 characters long");
		passLengthLabel.setFont(
			new Font("Monospaced", Font.BOLD, 11));
		passLengthLabel.setBounds(412, 354, 352, 15);
		add(passLengthLabel);
	}

	/**
	 * Add show password check box to the create account panel.
	 */
	private void addShowPasswordCheckbox() {
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setContentAreaFilled(false);
		chckbxShowPassword.setBorder(
			new EmptyBorder(0, 0, 0, 0));
		chckbxShowPassword.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxShowPassword.setFont(
			new Font("Monospaced", Font.BOLD, 13));
		chckbxShowPassword.setBounds(637, 264, 159, 23);
		add(chckbxShowPassword);

		chckbxShowPassword.addActionListener((e) -> {
			LOG.info("'Show Password' checkbox clicked");
			if (chckbxShowPassword.isSelected()) {
				passwordField.setEchoChar((char) (0));
				passwordConfirmationField.setEchoChar(
					(char) (0));
			} else {
				passwordField.setEchoChar('*');
				passwordConfirmationField.setEchoChar('*');
			}
		});
	}

	/**
	 * Add light/dark mode toggle button.
	 */
	private void addLightDarkToggle() {
		JToggleButton lightDarkToggle = 
			new JToggleButton("Light/Dark Mode");
		lightDarkToggle.setBounds(390, 584, 167, 25);
		lightDarkToggle.setBackground(Color.LIGHT_GRAY);
		lightDarkToggle.setForeground(Color.white);
		add(lightDarkToggle);

		lightDarkToggle.addActionListener((e) -> {
			LOG.info("'Light/Dark Mode' toggle clicked");

			// If they press light/dark button
			if (lightDarkToggle.isSelected()) {

				// Apply dark background
				this.setBackground(Color.darkGray);
				passLengthLabel.setForeground(Color.white);
				newPasswordLabel.setForeground(Color.white);
				confirmPasswordLabel.setForeground(Color.white);
				chckbxShowPassword.setForeground(Color.white);

			} else {
				// Apply light background
				this.setBackground(Color.white);
				passLengthLabel.setForeground(Color.black);
				newPasswordLabel.setForeground(Color.black);
				confirmPasswordLabel.setForeground(Color.black);
				chckbxShowPassword.setForeground(Color.black);
			}
		});
	}

	/**
	 * Add title to panel.
	 */
	private void addTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(221, 116, 528, 71);
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
	 * Adds grid clash icon to panel.
	 */
	private void addWelcome() {
		lblWelcome = new JLabel(", create an account password.");
		lblWelcome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(278, 226, 442, 15);
		add(lblWelcome);
		lblWelcome.repaint();
	}

	/**
	 * Adds grid clash icon to panel.
	 */
	public void updateWelcome() {
		lblWelcome.setText(this.user.getUsername() 
			+ ", create an account password.");
		lblWelcome.repaint();
	}

	/**
	 * Validates user's requested password with confirmed password.
	 * 
	 * @return true (passwords match) and false (passwords do not match)
	 */
	@SuppressWarnings("deprecation")
	public boolean matchPassword() {
		String newPass = passwordField.getText();
		String confirmPass = passwordConfirmationField.getText();

		if (newPass.equals(confirmPass)) {
			return true;
		} else {
			JOptionPane.showMessageDialog(
				this, "The passwords do not match");
			return false;
		}
	}

	/**
	 * Validates user's requested password.
	 * 
	 * @param password
	 * 
	 * @return true (valid password) and false (invalid password)
	 */
	public static boolean isValidPassword(String password) {

		// The password must be at least 8 characters long
		if (password.length() < 10) {
			return false;
		}

		// The password must contain at least one letter and one digit
		if (!password.matches(".*[a-z].*") 
			|| !password.matches(".*[0-9].*")) {
			return false;
		}

		// The password can contain the following special characters:
		// !@#$%^&*()-_+={}[]|;:,.<>?
		if (!password.matches(
				".*[!@#$%^&*()\\-_+={}\\[\\]|;:,.<>?].*")) {
			return false;
		}

		// The password can contain any characters 
		// in addition to the special characters
		// listed above
		return true;
	}

}