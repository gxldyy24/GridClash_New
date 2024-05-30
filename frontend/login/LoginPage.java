package edu.skidmore.cs326.game.sudoku.frontend.login;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.logic.Login;
import edu.skidmore.cs326.game.sudoku.logic.LoginLogic;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Grid Clash Login main page.
 * 
 * @author cdavidson jbiggins vzhao eheidepriem
 */

public class LoginPage extends Page {
	
	/**
	 * default constructor for login page.
	 */
	public LoginPage() {
		setOpaque(false);
	}
	
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger LOG = 
		Logger.getLogger(LoginPage.class);

	/**
	 * Login page password field.
	 */
	private JTextField emailField;

	/**
	 * Login page password field.
	 */
	private JPasswordField passwordField;

	/**
	 * User input for login page email field.
	 */
	private String userEmail;

	/**
	 * User input for login page password field.
	 */
	private String userPassword;

	/**
	 * Window width.
	 */
	private int w;

	/**
	 * Window height.
	 */
	private int h;

	/**
	 * Variable for width:height ratio.
	 */
	private int r;

	/**
	 * Variable for scalable text size.
	 */
	private int t;
	
	/**
	 * Dimensions of screen.
	 */
	private Dimension screenSize;
	
	/**
	 * width of window.
	 */
	private int width;
	
	/**
	 * height of window.
	 */
	private int height;
	
	/**
	 * TextField Label.
	 */
	private JLabel textField;
	
	/**
	 * TextField1 Label.
	 */
	private JTextField textField1;

//////////////////////////////////////////

	/**
	 * Setup LoginPage.
	 */
	protected void setup() {
		
		setBackground(
			new Color(186, 189, 182));
		w = 1000;
		h = 750;
		r = w / h;
		t = (int) (r * 14);
		addLoginPanel();
	}

/////////////////////////////////////////////////////////////////////////////

	/**
	 * Add the login panel components.
	 */
	private void addLoginPanel() {
		this.setLayout(null);
		this.setBounds(
			(int) (w * 0.35),
			(int) (h * 0.333), w, h);
		screenSize = this.getSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		addEmailLabel();
		addEmailTextField();
		addPasswordLabel();
		addPasswordField();
		addShowPassword();
		addForgotPasswordButton();
		addCreateAccountButton();
		addLoginButton();
		addLoginTitle();
		addLogo();
	}

	/**
	 * Add email label to the login panel.
	 */
	private void addEmailLabel() {
		JLabel emailLabel = new JLabel("");
		emailLabel.setFont(
			new Font("Monospaced", Font.BOLD, t));
		emailLabel.setBounds(341, 215, 30, 30);

		ImageIcon profileOG = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game"
					+ "/sudoku/frontend/assets/"
					+ "user.png"));
		Image originalProfile = profileOG.getImage();
		Image rescaledProfile = 
			originalProfile.getScaledInstance(
				emailLabel.getWidth(),
				emailLabel.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon user = new ImageIcon(rescaledProfile);
		emailLabel.setIcon(user);

		this.add(emailLabel);
		emailLabel.repaint();
	}

	/**
	 * Add email text field to the login panel.
	 */
	private void addEmailTextField() {
		emailField = new JTextField("");
		emailField.setOpaque(false);
		emailField.setBorder(
			new EmptyBorder(0, 0, 0, 0));
		emailField.setBackground(
			new Color(255, 255, 255));
		emailField.setBounds(389, 215,
			(int) (w * 0.222), (int) (h * 0.04));
		this.add(emailField);
		emailField.repaint();
	}

	/**
	 * Add password label to the login panel.
	 */
	private void addPasswordLabel() {
		JLabel passwordLabel = new JLabel("password:");
		passwordLabel.setFont(
			new Font("Monospaced", Font.BOLD, t));
		passwordLabel.setBounds(341, 257, 30, 30);

		ImageIcon padlockOG = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game/"
					+ "sudoku/frontend/assets/"
					+ "padlock.png"));
		Image originalPadlock = padlockOG.getImage();
		Image rescaledProfile = 
			originalPadlock.getScaledInstance(
				passwordLabel.getWidth(),
				passwordLabel.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon password = new ImageIcon(rescaledProfile);
		passwordLabel.setIcon(password);

		this.add(passwordLabel);
		passwordLabel.repaint();
	}

	/**
	 * Add password text field to the login panel.
	 */
	private void addPasswordField() {
		passwordField = new JPasswordField("");
		passwordField.setOpaque(false);
		passwordField.setBorder(
			new EmptyBorder(0, 0, 0, 0));
		passwordField.setBounds(389, 258,
			(int) (w * 0.222), (int) (h * 0.04));
		this.add(passwordField);
		passwordField.repaint();
	}

	/**
	 * Add forgot password button to the login panel.
	 */
	private void addForgotPasswordButton() {
		JButton forgotPassButton = 
			new JButton("Forgot Password?");
		forgotPassButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassButton.setFont(
			new Font("Monospaced", Font.BOLD, t));
		forgotPassButton.setBounds(
			(int) (w * 0.412), (int) (h * 0.415),
			(int) (w * 0.17), (int) (h * 0.02));
		forgotPassButton.setOpaque(false);
		forgotPassButton.setContentAreaFilled(false);
		forgotPassButton.setBorderPainted(false);
		this.add(forgotPassButton);
		forgotPassButton.repaint();
		forgotPassButton.addActionListener((e) -> {

			LOG.info("'Forgot Password?' button clicked");

			switchPage("Forgot Password");
		});
	}

	/**
	 * Add create account button to the login window.
	 */
	private void addCreateAccountButton() {

		ButtonGradient createAccountButton = new ButtonGradient();
		createAccountButton.setText("Create Account");
		createAccountButton.setColor1(new java.awt.Color(61, 110, 167));
		createAccountButton.setColor2(new java.awt.Color(61, 110, 167));

		createAccountButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccountButton.setBackground(
			new Color(61, 110, 167));
		createAccountButton.setForeground(Color.white);
		createAccountButton.setFont(
			new Font("Monospaced", Font.BOLD, t));
		createAccountButton.setBounds(
			(int) (w * 0.401), (int) (h * 0.605),
			(int) (w * 0.181), (int) (h * 0.055));
		this.add(createAccountButton);
		createAccountButton.repaint();
		createAccountButton.addActionListener((e) -> {
			LOG.info("'Create Account' Button");

			switchPage("Create Account");
		});
	}

	/**
	 * Add login button to the login window.
	 */
	@SuppressWarnings("deprecation")
	private void addLoginButton() {

		// Code for gradient button

		ButtonGradient loginButton = new ButtonGradient();
		loginButton.setText("Login");
		loginButton.setColor1(
			new java.awt.Color(61, 110, 167));
		loginButton.setColor2(
			new java.awt.Color(61, 110, 167));

		loginButton.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBackground(
			new Color(61, 110, 167));
		loginButton.setForeground(Color.white);
		loginButton.setBounds(
			(int) (w * 0.347), (int) (h * 0.484),
			(int) (w * 0.293), (int) (h * 0.105));
		loginButton.setFont(new Font("Monospaced", Font.BOLD, 28));
		this.add(loginButton);
		loginButton.repaint();
		
		KeyStroke enterKeyStroke = KeyStroke.
				getKeyStroke(KeyEvent.VK_ENTER, 0);
		loginButton.getInputMap(JComponent.
				WHEN_IN_FOCUSED_WINDOW).
		put(enterKeyStroke, "login");
		loginButton.getActionMap().put("login", new AbstractAction() {
		    private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
				// Simulate a click on the login button
		        loginButton.doClick(); 
		    }
		});
		loginButton.addActionListener((e) -> {

			// Store user inputs for email, password
			LOG.info("'LOGIN' button selected");
			
			userEmail = emailField.getText();
			LOG.info("User email: " + userEmail);

			userPassword = passwordField.getText();

			LOG.info("User password entered");
			
			Login l = new LoginLogic();
			boolean v = l.checkLogin(userEmail, userPassword);
			User user = l.getUser(userEmail);
			if (v) {
				((LoginWindow) getParentWindow()).
				loginToHome(((LoginWindow) 
					getParentWindow()), user);
			} else {
				JOptionPane.showMessageDialog(
					this, "Email or "
					+ "Password Incorrect");
			}

			// Reset password field
			passwordField.setText("");
		});
	}

	/**
	 * Add title to login panel.
	 */
	private void addLoginTitle() {
		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds((width) / 2 - 300,
				height / 11, 600, 120);
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
	 * Add grid clash icon to login panel.
	 */
	private void addLogo() {
		textField = new JLabel(
			"____________________________________");
		textField.setOpaque(false);
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBackground(Color.WHITE);
		textField.setBounds(389, 219, 222, 30);
		add(textField);

		textField1 = new JTextField(
			"____________________________________");
		textField1.setOpaque(false);
		textField1.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField1.setBackground(Color.WHITE);
		textField1.setBounds(389, 263, 222, 30);
		add(textField1);
	}

	/**
	 * Add components login panel.
	 */
	private void addShowPassword() {

		JLabel open = new JLabel();
		JLabel closed = new JLabel();

		// Set cursor to hand cursor when hovering buttons
		open.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closed.setCursor(
			Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		open.setBorder(null);
		open.setBounds(629, 257, 30, 30);
		closed.setBounds(629, 257, 30, 30);

		// Scale the open eye image to the button size
		ImageIcon openEyeOG = new ImageIcon(
			LoginPage.class.getResource(
				"/edu/skidmore/cs326/game"
				+ "/sudoku/frontend/assets/show.png"));
		Image originalOpen = openEyeOG.getImage();
		LOG.info("The dimentions of image are : " 
		+ open.getWidth() + " , " + open.getHeight());
		Image rescaledOpen = 
				originalOpen.getScaledInstance(
				open.getWidth(),
				open.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon openEye = new ImageIcon(rescaledOpen);
		open.setIcon(openEye);

		// Scale the open eye image to the button size
		ImageIcon closedEyeOG = new ImageIcon(
				LoginPage.class.getResource(
					"/edu/skidmore/cs326/game"
					+ "/sudoku/frontend/assets/hide.png"));
		Image originalClosed = closedEyeOG.getImage();
		Image rescaled = originalClosed.getScaledInstance(
			closed.getWidth(),
			closed.getHeight(), 
			Image.SCALE_SMOOTH);
		ImageIcon closedEye = new ImageIcon(rescaled);
		closed.setIcon(closedEye);

		// Add both buttons and set the open eye to be visible first
		add(open);
		add(closed);
		closed.setVisible(false);
		closed.repaint();

		// Add label for show/hide password button
		JLabel showTxt = new JLabel("Show Password");
		showTxt.setFont(new Font("Monospaced", Font.BOLD, 14));
		showTxt.setSize((int) (w * 0.137), (int) (h * 0.027));
		showTxt.setLocation(671, 262);
		add(showTxt);

		open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LOG.info("'Show Password' Button clicked");
				passwordField.setEchoChar((char) (0));

				// Show the password
				open.setVisible(false);
				closed.setVisible(true);
				closed.repaint();
				showTxt.setText("Hide Password");
			}
		});

		closed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LOG.info("'Hide Password' Button clicked");
				passwordField.setEchoChar('*');

				// Show the password
				open.setVisible(true);
				closed.setVisible(false);
				open.repaint();
				showTxt.setText("Show Password");
			}
		});
	}
}
