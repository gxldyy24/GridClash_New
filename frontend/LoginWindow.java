package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.login.AccountVerificationPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.CreateAccountPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.ForgotPasswordPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.PasswordCreationPage;
import edu.skidmore.cs326.game.sudoku.frontend.login.PasswordResetPage;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * Grid Clash Login Window This class represents the login window for the Grid
 * Clash game. It extends JFrame and implements Runnable. It creates the login
 * window in a cardLayout and adds panels to the frame
 * 
 * @author cdavidson jbiggins vzhao eheidepriem
 */
public class LoginWindow extends Window {

    
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;
    
    
	/**
	 * The logger for LoginWindow.
	 */
	private static final Logger LOG = Logger.getLogger(LoginWindow.class);

	/**
	 * The acct verification page.
	 */
	private Page accountVerificationPage;
	
	/**
	 * The pass create page.
	 */
	private Page passwordCreationPage;
	
	/**
	 * The pass reset page.
	 */
	private Page passwordResetPage;
	
	
	/**
	 * Getter for pass reset page.
	 * 
	 * @return password reset page.
	 */
	public Page getPasswordResetPage() {
	    return this.passwordResetPage;
	}
	/**
	 * Getter for acct verif. page.
	 * 
	 * @return acct verif. page.
	 */
	public Page getAccountVerficationPage() {
	    return this.accountVerificationPage;
	}
	
	/**
	 * Getter for pass create page.
	 * 
	 * @return pass create page.
	 */
	public Page getPasswordCreationPage() {
	    return this.passwordCreationPage;
	}
	
	/**
	 * Constructor for LoginWindow.
	 */
	public LoginWindow() {
	}

	
	/**
	 * Create the main frame for the login window.
	 */
	protected void createWindow() {

		// Initialize JFrame
		setTitle("GRID CLASH");
		setSize(1000, 700);

		setLocation(400, 100);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create card layout and jpanel that holds card layout
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);


		accountVerificationPage = new AccountVerificationPage();
		passwordCreationPage = new PasswordCreationPage();
		passwordResetPage = new PasswordResetPage();
		
		// Add all panels to card layout
		cardPanel.add(new LoginPage(), "Login");
		cardPanel.add(new CreateAccountPage(), "Create Account");
		cardPanel.add(passwordCreationPage, "Password Creation");
		cardPanel.add(accountVerificationPage, "Account Verification");
		cardPanel.add(new LoadingPage(), "Loading");
		cardPanel.add(new ForgotPasswordPage(), "Forgot Password");
		cardPanel.add(passwordResetPage, "Password Reset");

		// Show login panel first
		cardLayout.show(cardPanel, "Login");
		cardLayout.show(cardPanel, "Loading");

		// Add card panel to the JFrame
		add(cardPanel);

		setVisible(true);

		add(new LoadingPage());
	}



	/**
	 * Closes login window, opens home window.
	 * 
	 * @param dispose
	 * @param user
	 */
	public void loginToHome(LoginWindow dispose, User user) {
		Runnable application = new HomeWindow(user);
		new Thread(application).start();
		dispose.dispose();
	}

	/**
	 * Adds title and grid clash icon to panel.
	 * 
	 * @param panel
	 */
	public void addTitleAndLogo(JPanel panel) {
		JLabel loginTitle = new JLabel("GRID    CLASH");
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setFont(new Font("Monospaced", Font.BOLD, 70));
		loginTitle.setForeground(new Color(61, 110, 167));
		loginTitle.setBounds(201, 51, 575, 188);
		panel.add(loginTitle);

		JLabel clashLogo = new JLabel("");
		clashLogo.setIcon(
				new ImageIcon(LoginPage.class.getResource(
				"/edu/skidmore/cs326/game/sudoku/frontend"
				+ "/assets/logo.png")));
		clashLogo.setBounds(425, 75, 127, 163);
		panel.add(clashLogo);
	}

	/**
	 * Handles user exit.
	 */
	public void handleExitRequest() {
		LOG.info("Application shutting down");
	}
}
