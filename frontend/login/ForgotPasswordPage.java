
package edu.skidmore.cs326.game.sudoku.frontend.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.logic.Login;
import edu.skidmore.cs326.game.sudoku.logic.LoginLogic;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * Grid Clash forgot password window.
 * 
 * @author eheidepriem
 */
public class ForgotPasswordPage extends Page {
	
	/**
	 * Forgot Password Page.
	 */
	public ForgotPasswordPage() {
		
	}
	
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User input for email code text field.
	 */
	private String userEmail;

	/**
	 * Forgot Password page email field.
	 */
	private JTextField emailField;

	/**
	 * Forgot Password page verification label.
	 */
	private JLabel emailLabel;

	/**
	 * Forgot Password page Enter button.
	 */
	private JButton enterButton;

	/**
	 * The logger.
	 */
	private static final Logger LOG =
		Logger.getLogger(ForgotPasswordPage.class);
	
	/**
	 * Current User.
	 */
	private User thisUser;
	
	/**
	 *Interface to check verification.
	 */
	private Login check;

	/**
	 * Sets up Account Verification Panel.
	 * Calls addAccountVerificationPanel.
	 */
	protected void setup() {
		addForgotPasswordPanel();
		LOG.info("Account verification window created.");
	}

	/**
	 * Adds the Account Verification Page.
	 */
	private void addForgotPasswordPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 748);
		addTitle();
		addEmailLabel();
		addEmailField();
		addEnterButton();
		LOG.info("Buttons, Labels. and TextFields added to window.");
	}

	/**
	 * Adds the Grid Clash Title to Account Verification Page.
	 */
	private void addTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(230, 164, 528, 71);
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
	 * Adds verification label to Account Verification Page.
	 */
	private void addEmailLabel() {
		emailLabel = new JLabel("email:");
		emailLabel.setBounds(352, 306, 165, 15);
		this.add(emailLabel);
		emailLabel.repaint();
	}

	/**
	 * Adds verification code text field to Account Verification Page.
	 */
	private void addEmailField() {
		emailField = new JTextField("");
		emailField.setBounds(411, 299, 243, 30);
		this.add(emailField);
		emailField.repaint();
	}

	/**
	 * Adds enter button to Account Verification Page.
	 */
	private void addEnterButton() {
		enterButton = new JButton("ENTER");
		enterButton.setBounds(350, 406, 293, 79);
		enterButton.setBackground(new Color(61, 110, 167));
		enterButton.setForeground(Color.white);
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		this.add(enterButton);
		enterButton.repaint();
		enterButton.addActionListener((e) -> {
			LOG.info("'ENTER' button selected");
			userEmail = emailField.getText();
			LOG.info("User email code: " + userEmail);		

			// forgotInterface check = new forgotLogic(userEmail);
			// check.checkForgot);
			
			LoginWindow loginWindow =
					(LoginWindow) getParentWindow();
			
			// See if email is in the database.	
			// If Yes -->
			
			// Use Interface
			check = new LoginLogic();
			thisUser = check.getUser(userEmail);
			boolean v = check.forgotPass(userEmail, thisUser);
			
			if (v) {
				JOptionPane.showMessageDialog(
				loginWindow, 
				"Email found");
				
			thisUser = check.changeUserCode(thisUser);

			// Send to code verification page	.		
			AccountVerificationPage aVP =
				(AccountVerificationPage) loginWindow.
				getAccountVerficationPage();
			PasswordResetPage pRP = 
				(PasswordResetPage) loginWindow.
				getPasswordResetPage();
				
			aVP.setUser(thisUser);
			pRP.setUser(thisUser);
			aVP.updateWelcome();
			pRP.updateWelcome();
			switchPage("Account Verification");
				
				// If No -->.
				// Pop up message saying can't find email
				// Clear email text field
			} else {
				JOptionPane.showMessageDialog(
						loginWindow, "Email not found");
				emailField.setText("");
			}
			
		});
	}

}
