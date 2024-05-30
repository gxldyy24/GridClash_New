
package edu.skidmore.cs326.game.sudoku.frontend.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.Page;

import edu.skidmore.cs326.game.sudoku.logic.Login;
import edu.skidmore.cs326.game.sudoku.logic.LoginLogic;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.modify.ChangeAttributes;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * Grid Clash account verification page.
 * 
 * @author vzhao
 */
public class AccountVerificationPage extends Page {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User input for verification code text field.
	 */
	private String userVerificationCode;

	/**
	 * Account verification page verification code field.
	 */
	private JTextField verificationCodeField;

	/**
	 * Account verification page verification label.
	 */
	private JLabel verificationLabel;

	/**
	 * Account verification page send new code button.
	 */
	private JButton sendNewCodeButton;

	/**
	 * Account verification page enter button.
	 */
	private JButton enterButton;

	/**
	 * The logger.
	 */
	private static final Logger LOG =
		Logger.getLogger(AccountVerificationPage.class);

	/**
	 * Label for grid clash icon.
	 */
	private JLabel logo;

	/**
	 * User email string.
	 */
	private String email;

	/**
	 * User username string.
	 */
	private String username;
	
	/**
	 * User code string.
	 */
	@SuppressWarnings("unused")
	private String code;

	/**
	 * Label for user identification.
	 */
	private JLabel lblWelcome;
	
	/**
	 * Users this current user.
	 */
	private User user;
	
	/**
	 *Interface to check verification.
	 */
	private Login check;

	/**
	 * Set up account verification panel.
	 */
	protected void setup() {
		addAccountVerificationPanel();
	}

	/**
	 * Sets current user in JPanel.
	 * 
	 * @param user2
	 */
	public void setUser(User user2) {
		this.user = user2;
		this.email = user2.getEmail();
		this.username = user2.getUsername();
		code = user2.getCode();
	}

	/**
	 * Adds the Account Verification Page.
	 */
	private void addAccountVerificationPanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 748);
		addTitle();
		addLogo();
		addVerificationLabel();
		addVerificationCodeTextField();
		addsendNewCodeButton();
		addEnterButton();
		addWelcome();
	}

	/**
	 * Adds enter button to Account Verification Page.
	 */
	private void addEnterButton() {
		enterButton = new JButton("ENTER");
		enterButton.setCursor(Cursor.getPredefinedCursor(
				Cursor.HAND_CURSOR));
		enterButton.setBackground(
			new Color(61, 110, 167));
		enterButton.setForeground(Color.white);
		enterButton.setBounds(350, 406, 293, 79);
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		this.add(enterButton);

		enterButton.repaint();
		enterButton.addActionListener((e) -> {
			LOG.info("'ENTER' button selected");
			userVerificationCode = verificationCodeField.getText();
			LOG.info("User verification code: " 
			+ userVerificationCode);
			
			check = new LoginLogic();
			boolean v = check.checkVerify(this.email, 
        			    userVerificationCode, 
        			    this.user);
			
			boolean forgot = check.checkForgot(this.email);		
			
			if (!forgot) {
				if (v) {
					switchPage("Password Reset");
				} else {
					JOptionPane.showMessageDialog(
							this, "Code Incorrect");
				}
			} else {
				if (v) {
					switchPage("Password Creation");
				} else {
					JOptionPane.showMessageDialog(
							this, "Code Incorrect");
				}
			}

			verificationCodeField.setText("");
		});
	}

	/**
	 * Adds the Grid Clash Title to Account Verification Page.
	 */
	private void addTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(227, 157, 528, 71);
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
	private void addVerificationLabel() {
		verificationLabel = new JLabel("enter verification code:");
		verificationLabel.setBounds(299, 328, 165, 15);
		this.add(verificationLabel);
		verificationLabel.repaint();
	}

	/**
	 * Adds verification code text field to Account Verification Page.
	 */
	private void addVerificationCodeTextField() {
		verificationCodeField = new JTextField("");
		verificationCodeField.setBorder(
			new EtchedBorder(EtchedBorder.LOWERED, null, null));
		verificationCodeField.setBounds(474, 321, 200, 30);
		this.add(verificationCodeField);
		verificationCodeField.repaint();
	}

	/**
	 * Adds send new code button to Account Verification Page.
	 */
	private void addsendNewCodeButton() {
		sendNewCodeButton = new JButton("send new code");
		sendNewCodeButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendNewCodeButton.setBounds(500, 348, 141, 25);
		sendNewCodeButton.setOpaque(false);
		sendNewCodeButton.setContentAreaFilled(false);
		sendNewCodeButton.setBorderPainted(false);
		this.add(sendNewCodeButton);
		sendNewCodeButton.repaint();

		sendNewCodeButton.addActionListener((e) -> {
			LOG.info("'send new code' button selected");
			
			try {
    			user.setCode(email);
    			
    			ChangeAttributes change = new ChangeAttributes();
    			
    			change.changeCode(email);
			} catch (PersistenceException e1) {
			    e1.getMessage();
			}
			// If user input code = database code 
			// --> Email Validated
			// Allow for 3 attempts and 
			// deny account creation request if attempt 3 is wrong

			JOptionPane.showMessageDialog(null,
				"We've sent you a new code "
				+ "to your email. "
				+ "Your old code is no longer valid.",
				"New Verification Code",
				JOptionPane.OK_CANCEL_OPTION);
			verificationCodeField.setText("");
		});
	}

	/**
	 * Adds grid clash icon to panel.
	 */
	private void addLogo() {
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(
			AccountVerificationPage.class.getResource(
				"/edu/skidmore/cs326/game/sudoku"
				+ "/frontend/assets/logo.png")));
		logo.setBounds(426, 125, 135, 134);
		add(logo);
		logo.repaint();
	}

	/**
	 * Adds welcome label.
	 */
	private void addWelcome() {
		lblWelcome = new JLabel(
				this.username 
				+ ", we've sent an email to '" 
				+ this.email 
				+ "' with your verification code.");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcome.setBounds(142, 240, 698, 76);
		lblWelcome.setAlignmentX(CENTER_ALIGNMENT);
		add(lblWelcome);
		lblWelcome.repaint();
	}

	/**
	 * Updates welcome text for specific user.
	 */
	public void updateWelcome() {
		lblWelcome.setText(this.username 
				+ ", we've sent an email to " 
				+ this.email 
				+ " with your verification code");
		lblWelcome.repaint();
	}
}
