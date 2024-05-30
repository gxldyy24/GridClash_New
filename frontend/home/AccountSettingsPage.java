package edu.skidmore.cs326.game.sudoku.frontend.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;
import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.LoginWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;
import edu.skidmore.cs326.game.sudoku.logic.AccountSettingsLogic;

import edu.skidmore.cs326.game.sudoku.persistence.users.User;
import edu.skidmore.cs326.game.sudoku.logic.AccountSettings;




/**
 * Grid Clash account settings page.
 * 
 * @author vzhao
 */
public class AccountSettingsPage extends Page {
	
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The title label for the Account Settings Page.
	 */
	private JLabel title;

	/**
	 * The title label for the Account Settings Page.
	 */
	private JLabel changePasswordLabel;
	/**LOG = 
        Logger.getLogger(AccountSettingsPage.class)
	 * The old password label for Account Settings Page.
	 */
	private JLabel oldLabel;

	/**
	 * The new password label for Account Settings Page.
	 */
	private JLabel newLabel;

	/**
	 * The confirm password label for Account Settings Page.
	 */
	private JLabel confirmLabel;

	/**
	 * The new password text field for Account Settings Page.
	 */
	private JTextField oldPasswordTextField;
	/**
	 * The new password text field for Account Settings Page.
	 */
	private JTextField newPasswordTextField;

	/**
	 * The confirm password text field for the Account Settings Page.
	 */
	private JTextField confirmPasswordTextField;

	/**
	 * The change display name label for the Account Settings Page.
	 */
	private JLabel changeDisplayNameLabel;

	/**
	 * The change display name label for the Account Settings Page.
	 */
	private JTextField changeDisplayNameTextField;

	/**
	 * The change avatar label for Account Settings Page.
	 */
	private JLabel changeAvatarLabel;

	/**
	 * The change name label.
	 */
	private JLabel changeNameLabel;

	/**
	 * The change name text field.
	 */
	private JTextField changeNameTextField;

	/**
	 * The change email label.
	 */
	private JLabel changeEmailLabel;

	/**
	 * The change email text field.
	 */
	private JTextField changeEmailTextField;

	/**
	 * The change cell phone number label.
	 */
	private JLabel changePhoneNumberLabel;

	/**
	 * The change phone number formatted text field.
	 */
	private JPhoneNumberFormattedTextField changePhoneNumberTextField;

	/**
	 * The upload avatar button for Account Settings Page.
	 */
	private ButtonGradient uploadAvatarButton;

	/**
	 * The request random avatar button for the Account Settings Page.
	 */
	private ButtonGradient requestAvatarButton;

	/**
	 * The delete account button for Account Settings Page.
	 */
	private ButtonGradient deleteAccountButton;

	/**
	 * The apply and save changes button for Account Settings Page.
	 */
	private ButtonGradient saveAndApplyButton;

	/**
	 * The request random display name button for Account Settings Page.
	 */
	private ButtonGradient confirmDisplayNameButton;

	/**
	 * The send verification code button for change email.
	 */
	private ButtonGradient emailVerificationButton;

	/**
	 * Button to update user password.
	 */
	private ButtonGradient updatePasswordButton;
	
	/**
	 * Interface for account settings logic.
	 */
	private AccountSettings accountSettings;

	/**
	 * Current signed in user.
	 */
	private User user;
	
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG;
	
	/**
	 * Initializes the logger.
	 */
	static {
	    LOG = Logger.getLogger(AccountSettingsPage.class);
	}
	
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
	 * This constructor assignes the user initally.
	 * 
	 * @param u current user
	 */
	public AccountSettingsPage(User u) {
	    LOG.info("User:" + u 
	        + "| is user null: "
	        + Boolean.toString(u == null));
        this.user = u;
	    
    	accountSettings = new AccountSettingsLogic();
    	accountSettings.getUser(user.getEmail());

        
        setupPart2();
	}

	
	/**
	 * There is an error that occurs if the typical setup is called
	 * due to how user variable needs to be assigned before setup.
	 */
	protected void setup() {
	    return;
	}
	
	/**
	 * Sets up Account Settings Panel (width, height) 
	 * calls addAccountSettingsPanel.
	 */
	protected void setupPart2() {
	    LOG.info("Setup method called");
        
	    
		try {
			addAccountSettingsPanel();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds the components to account settings panel.
	 * 
	 * @throws ParseException
	 */
	private void addAccountSettingsPanel() throws ParseException {
		this.setLayout(null);
		this.setBounds(350, 250, 1000, 750);

		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		addTitle();
		addChangePasswordLabel();
		addNewLabel();
		addConfirmLabel();
		addNewPasswordTextField();
		addConfirmPasswordTextField();
		addChangeDisplayNameLabel();
		addChangeDisplayNameTextField();
		addRequestDisplayNameButton();
		addChangeAvatarLabel();
		addUploadAvatarButton();
		addRequestAvatarButton();
		addDeleteAccountButton();
		addSaveAndApplyButton();
		addBackToSettingsButton();
		addOldLabel();
		addOldPasswordTextField();
		addChangeNameLabelAndTextField();
		addChangeEmailLabelAndTextField();
		addChangeNumberLabelAndTextField();
		addNumberVerificationButton();
		addEmailVerificationButton();
		addUpdatePassButton();
	}

	/**
	 * Adds the title to the account settings page.
	 */
	private void addTitle() {
		title = super.createTitle("My Account");
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
	 * Adds the change password label to account settings page.
	 */
	private void addChangePasswordLabel() {
		changePasswordLabel = new JLabel(
			"change password:");
		changePasswordLabel.setBounds(125, 301, 306, 40);
		changePasswordLabel.setFont(
			new Font("Monospaced", Font.PLAIN, 28));
		this.add(changePasswordLabel);
		changePasswordLabel.repaint();
	}

	/**
	 * Adds the new password label to account settings page.
	 */
	private void addNewLabel() {
		newLabel = new JLabel("new:");
		newLabel.setBounds(415, 358, 54, 37);
		newLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		this.add(newLabel);
		newLabel.repaint();
	}

	/**
	 * Adds the old password label to account settings page.
	 */
	private void addOldLabel() {
		oldLabel = new JLabel("old:");
		oldLabel.setBounds(415, 308, 44, 37);
		oldLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		this.add(oldLabel);
		oldLabel.repaint();
	}

	/**
	 * Adds the confirm password label to account settings page.
	 */
	private void addConfirmLabel() {
		confirmLabel = new JLabel("confirm:");
		confirmLabel.setBounds(415, 407, 102, 37);
		confirmLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		this.add(confirmLabel);
		confirmLabel.repaint();
	}

	/**
	 * Adds the new password text field to account settings page.
	 */
	private void addOldPasswordTextField() {
		oldPasswordTextField = new JTextField("");
		oldPasswordTextField.setBounds(505, 318, 200, 30);
		this.add(oldPasswordTextField);
		oldPasswordTextField.repaint();
	}

	/**
	 * Adds the new password text field to account settings page.
	 */
	private void addNewPasswordTextField() {
		newPasswordTextField = new JTextField("");
		newPasswordTextField.setBounds(505, 367, 200, 30);
		this.add(newPasswordTextField);
		newPasswordTextField.repaint();
	}

	/**
	 * import javax.swing.JFrame;
	 * 
	 * Adds the confirm password text field to account settings page.
	 */
	private void addConfirmPasswordTextField() {
		confirmPasswordTextField = new JTextField("");
		confirmPasswordTextField.setBounds(505, 411, 200, 30);
		this.add(confirmPasswordTextField);
		confirmPasswordTextField.repaint();
	}

	/**
	 * Adds the change display name label to account settings page.
	 */
	private void addChangeDisplayNameLabel() {
		changeDisplayNameLabel = new JLabel(
			"change display name:");
		changeDisplayNameLabel.setBounds(125, 453, 350, 40);
		changeDisplayNameLabel.setFont(
			new Font("Monospaced", Font.PLAIN, 28));
		this.add(changeDisplayNameLabel);
		changeDisplayNameLabel.repaint();
	}

	/**
	 * Adds the change display name text field to account settings page.
	 */
	private void addChangeDisplayNameTextField() {
	    LOG.info("User.getUsername(): " + user.getUsername());
		changeDisplayNameTextField = new JTextField(user.getUsername());
		changeDisplayNameTextField.setBounds(505, 465, 200, 30);
		this.add(changeDisplayNameTextField);
		changeDisplayNameTextField.repaint();
	}

	/**
	 * Adds the request random display name button to account settings page.
	 */
	private void addRequestDisplayNameButton() {
		confirmDisplayNameButton = new ButtonGradient();
		confirmDisplayNameButton.setColor2(new Color(114, 159, 207));
		confirmDisplayNameButton.setColor1(new Color(52, 101, 164));
		confirmDisplayNameButton.setText("Generate");
		confirmDisplayNameButton.setBounds(735, 463, 129, 32);
		confirmDisplayNameButton.setBackground(Color.LIGHT_GRAY);
		confirmDisplayNameButton.setForeground(Color.white);
		this.add(confirmDisplayNameButton);
		confirmDisplayNameButton.repaint();

		confirmDisplayNameButton.addActionListener((e) -> {
			changeDisplayNameTextField.setText(
			accountSettings.requestDisplayName());
			LOG.info("'Confirm' button selected for display name.");
		});
	}

	/**
	 * Adds the change avatar label to account settings page.
	 */
	private void addChangeAvatarLabel() {
		changeAvatarLabel = new JLabel("change avatar:");
		changeAvatarLabel.setBounds(125, 513, 238, 33);
		changeAvatarLabel.setFont(
			new Font("Monospaced", Font.PLAIN, 28));
		this.add(changeAvatarLabel);
		changeAvatarLabel.repaint();
	}

	/**
	 * Adds the upload avatar button to account settings page.
	 */
	private void addUploadAvatarButton() {
		uploadAvatarButton = new ButtonGradient();
		uploadAvatarButton.setColor2(new Color(114, 159, 207));
		uploadAvatarButton.setColor1(new Color(52, 101, 164));
		uploadAvatarButton.setText("Upload");
		uploadAvatarButton.setBounds(483, 518, 108, 32);
		uploadAvatarButton.setFont(
			new Font("Monospaced", Font.BOLD, 14));
		this.add(uploadAvatarButton);
		uploadAvatarButton.repaint();

		uploadAvatarButton.addActionListener((e) -> {
			LOG.info("'Upload' button selected for avatar.");

		});
	}

	/**
	 * Adds the request random avatar button to account settings page.
	 */
	private void addRequestAvatarButton() {
		requestAvatarButton = new ButtonGradient();
		requestAvatarButton.setColor2(new Color(114, 159, 207));
		requestAvatarButton.setColor1(new Color(52, 101, 164));
		requestAvatarButton.setText("Request");
		requestAvatarButton.setBounds(619, 518, 110, 32);
		requestAvatarButton.setFont(
			new Font("Monospaced", Font.BOLD, 14));

		this.add(requestAvatarButton);
		requestAvatarButton.repaint();

		requestAvatarButton.addActionListener((e) -> {
			LOG.info("'Request' button selected for avatar.");
		});
	}

	/**
	 * Adds the delete account button to account settings page.
	 */
	private void addDeleteAccountButton() {
		deleteAccountButton = new ButtonGradient();
		deleteAccountButton.setColor2(Color.RED);
		deleteAccountButton.setColor1(Color.RED);
		deleteAccountButton.setText("Delete Account");
		deleteAccountButton.setBounds(125, 598, 155, 35);
		deleteAccountButton.setFont(
			new Font("Monospaced", Font.BOLD, 14));

		this.add(deleteAccountButton);
		deleteAccountButton.repaint();

		deleteAccountButton.addActionListener((e) -> {
			LOG.info("'Delete Account' button selected.");
			int g = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to "
					+ "delete your acoount?",
				"Delete Account", JOptionPane.YES_NO_OPTION);
			if (g == JOptionPane.YES_OPTION) {
				accountSettings.deleteAccount(user.getEmail());
				JOptionPane.showMessageDialog(null,
						"Account to be deleted "
						+ "within 24hrs");
				switchWindow(new LoginWindow());
			} 
		});
	}

	/**
	 * Adds the save and apply changes button to account settings page.
	 */
	private void addSaveAndApplyButton() {
		saveAndApplyButton = new ButtonGradient();
		saveAndApplyButton.setColor2(Color.GREEN);
		saveAndApplyButton.setColor1(Color.GREEN);
		saveAndApplyButton.setText("Save and Apply");
		saveAndApplyButton.setBounds(716, 598, 155, 35);
		saveAndApplyButton.setFont(
			new Font("Monospaced", Font.BOLD, 14));

		this.add(saveAndApplyButton);
		saveAndApplyButton.repaint();

		saveAndApplyButton.addActionListener((e) -> {
			LOG.info("'Save and Apply' button selected.");
			

			
			if (!changeDisplayNameTextField.getText()
				.equals(user.getUsername())) {
					if (accountSettings.
					saveAndApplyChanges(
					changeDisplayNameTextField.
					getText(), user.getEmail())) {
						JOptionPane.
						showMessageDialog(
					this, "All changes "
						+ "saved "
						+ "sucessfully.");
					} else {
						JOptionPane.
						showMessageDialog(
						this, "Changes "
					+ "not saved, "
						+ "please try again.");
					}
			} 
			// change name 
		});
	}

	/**
	 * Adds the update password button.
	 */
	private void addUpdatePassButton() {
		updatePasswordButton = new ButtonGradient();
		updatePasswordButton.setColor2(new Color(114, 159, 207));
		updatePasswordButton.setColor1(new Color(52, 101, 164));
		updatePasswordButton.setText("Update");
		updatePasswordButton.setBounds(735, 409, 129, 32);
		updatePasswordButton.setBackground(Color.LIGHT_GRAY);
		updatePasswordButton.setForeground(Color.white);
		this.add(updatePasswordButton);

		updatePasswordButton.addActionListener((e) -> {
			 // compare passwords here
			if (newPasswordTextField.getText().equals(
					confirmPasswordTextField
					.getText())
					) {
				if (accountSettings.updatePassword(
						newPasswordTextField.
						getText())) {
					JOptionPane.showMessageDialog(
						this, 
						"Password changed "
						+ "sucessfully.");
				} else {
					JOptionPane.showMessageDialog(
					this, "Password change unsucessful."
					+ " Please try again");
				}
			} else {
				JOptionPane.showMessageDialog(
					this, "New or old "
					+ "passwords don't match,"
					+ " please try again.");
			}
			LOG.info("'Update' button selected for user password.");
		});
	}

	/**
	 * Adds the change name label and text field.
	 */
	private void addChangeNameLabelAndTextField() {
		changeNameLabel = new JLabel("change name:");
		changeNameLabel.setBounds(125, 233, 250, 43);
		changeNameLabel.setFont(new Font("Monospaced", Font.PLAIN, 28));
		changeNameTextField = new JTextField();
		changeNameTextField.setBounds(505, 248, 200, 30);
		this.add(changeNameLabel);
		this.add(changeNameTextField);
		changeNameLabel.repaint();
		changeNameTextField.repaint();
		
		ButtonGradient confirmNameBtn = new ButtonGradient();
		confirmNameBtn.setColor2(new Color(115, 210, 22));
		confirmNameBtn.setBackground(Color.LIGHT_GRAY);
		confirmNameBtn.setColor1(new Color(78, 154, 6));
		confirmNameBtn.setText("Confirm");
		confirmNameBtn.setBounds(735, 244, 129, 32);
		add(confirmNameBtn);
		confirmNameBtn.repaint();
		
		confirmNameBtn.addActionListener((e) -> {
			LOG.info("'Confirm' button "
					+ "selected for change name.");
				
			boolean x = accountSettings.updateName(
				changeNameTextField.getText());
			if (x) {
				JOptionPane.showMessageDialog(
					this, "Changed name.");
				
			} else {
				JOptionPane.showMessageDialog(
					this, "Unsuccessful change name.");
			}
			});
	}

	/**
	 * Adds the change email label and text field.
	 */
	private void addChangeEmailLabelAndTextField() {
		changeEmailLabel = new JLabel("change email:");
		changeEmailLabel.setBounds(125, 185, 370, 43);
		changeEmailLabel.setFont(
				new Font("Monospaced", Font.PLAIN, 28));
		changeEmailTextField = new JTextField(user.getEmail());
		changeEmailTextField.setBounds(505, 197, 200, 30);
		this.add(changeEmailLabel);
		this.add(changeEmailTextField);
		changeEmailLabel.repaint();
		changeEmailTextField.repaint();
	}

	/**
	 * Adds the change phone number label and text field.
	 * 
	 * @throws ParseException
	 */
	private void addChangeNumberLabelAndTextField() throws ParseException {
		changePhoneNumberLabel = new JLabel("change phone number:");
		changePhoneNumberLabel.setBounds(125, 135, 370, 43);
		changePhoneNumberLabel.setFont(
				new Font("Monospaced", Font.PLAIN, 28));
		changePhoneNumberTextField =
				new JPhoneNumberFormattedTextField();
		changePhoneNumberTextField.setBounds(505, 150, 200, 30);
		this.add(changePhoneNumberLabel);
		this.add(changePhoneNumberTextField);
		changePhoneNumberLabel.repaint();
		changePhoneNumberTextField.repaint();
	}

	/**
	 * A class for a formatted text field that takes in a phone number.
	 */
	private static final class JPhoneNumberFormattedTextField 
		extends JFormattedTextField {

		/**
		 * Serial version id.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Formatted text field.
		 */
		private JPhoneNumberFormattedTextField()
				throws ParseException {
			super(new MaskFormatter("###-###-####"));
			setColumns(8);
		}
	}

	/**
	 * Adds send verification code button for change phone number.
	 */
	private void addNumberVerificationButton() {
		ButtonGradient confirmPhoneBtn = new ButtonGradient();
		confirmPhoneBtn.setColor2(new Color(115, 210, 22));
		confirmPhoneBtn.setText("Confirm");
		confirmPhoneBtn.setColor1(new Color(78, 154, 6));
		confirmPhoneBtn.setBackground(Color.LIGHT_GRAY);
		confirmPhoneBtn.setBounds(735, 145, 129, 32);
		add(confirmPhoneBtn);
		confirmPhoneBtn.addActionListener((e) -> {
			if (accountSettings.updatePhone(
					changePhoneNumberTextField.getText())) {
				JOptionPane.showMessageDialog(
					this, "Phone # changed.");
			} else {
				JOptionPane.showMessageDialog(
					this, "Unsuccessful phone change.");
			}			
		});
	}

	/**
	 * Adds send verification code button for change email.
	 */
	private void addEmailVerificationButton() {
		emailVerificationButton = new ButtonGradient();
		emailVerificationButton.setColor2(new Color(114, 159, 207));
		emailVerificationButton.setColor1(new Color(52, 101, 164));
		emailVerificationButton.setText("Send Code");
		emailVerificationButton.setBounds(735, 196, 129, 32);
		this.add(emailVerificationButton);
		emailVerificationButton.repaint();

		emailVerificationButton.addActionListener((e) -> {
			LOG.info("'Send Code' button "
					+ "selected for change email.");
			
			// only sends code if email entered is not current email
			if (changeEmailTextField.getText() 
					!= null 
					&& changeEmailTextField.getText()
					.compareToIgnoreCase(
							user.getEmail()) != 0) {
				String newCode = accountSettings.
						getEmailVeriCode(
						changeEmailTextField.getText());
				String userInput = 
						JOptionPane.
						showInputDialog(null,
					"We've sent you a verification code "
					+ "to your email. Please enter "
					+ "it here.", "Verify New Email",
					JOptionPane.OK_CANCEL_OPTION);		
				
				   if (userInput.compareToIgnoreCase(
							newCode) == 0) {
					   if (accountSettings.
							updateEmail(
							changeEmailTextField.
							getText())) {
							JOptionPane.
							showMessageDialog(
							this,
							"Email changed "
							+ "sucessfully.");
							this.user.setEmail(
							changeEmailTextField.
							getText());
					   } else {
							JOptionPane.
							showMessageDialog(
							this, 
							"Email validation "
							+ "unsucessful."
							+ " Please try again.");
					   }
				   } else {
						JOptionPane.
						showMessageDialog(
						this, 
						"Code incorrect,"
						+ " please try"
						+ " again.");
				   }
				} else {
					JOptionPane.
					showMessageDialog(
					this, 
					"Email provided is"
					+ " the same as the"
					+ " current email.");
				}
		});
	}

	/**
	 * Adds the back to settings button to account settings page.
	 */
	private void addBackToSettingsButton() {
		ButtonGradient backToSettings =
			createBackButton("< Settings", "Settings");
		add(backToSettings);
	}
}
