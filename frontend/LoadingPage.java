package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

import java.awt.Color;
import javax.swing.JProgressBar;

/**
 * Class loading page.
 */
public class LoadingPage extends KGradientPanel {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(LoadingPage.class);

	/**
	 * Percent label.
	 */
	private JLabel label;

	/**
	 * Timer for progress.
	 */
	private Timer timer;


	/**
	 * Constructor for CreateAccountPage.
	 */
	public LoadingPage() {
		setup();
	}

	/**
	 * Setup create account panel.
	 */
	private void setup() {

		// Set the layout of the panel
		setLayout(null);

		setkEndColor(Color.white);
		setkStartColor(Color.gray);
		setBackground(new Color(186, 189, 182));

		// Create the label
		label = new JLabel("Loading...");
		label.setForeground(new Color(17, 92, 168));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(341, 541, 321, 56);
		label.setFont(new Font("Arial", Font.ITALIC, 30));

		// Add the label to the panel
		add(label);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(173, 127, 168));
		progressBar.setBounds(0, 615, 1000, 55);
		progressBar.setStringPainted(true);
		add(progressBar);
		
		JLabel pragLogo = new JLabel("Here");
		pragLogo.setBounds(175, 0, 656, 597);
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

		// Create the timer
		timer = new Timer(500, new ActionListener() {
			private int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 5) {
					timer.stop();
					label.setText("Done!");
					// Set progress to 100%
					progressBar.setValue(100);
					setVisible(false);
					count = 0;
					timer.restart();
				} else {
					// Increment progress by 25% each time
					progressBar.setValue(count * 24);
				}
			}
		});

		// Start the timer
		timer.start();
	}
}