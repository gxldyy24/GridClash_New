package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.CardLayout;
import java.awt.Color;
//import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

//import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;

/**
 * This class is used to simplify pages in the application.
 * 
 * @author Jack Biggins, cdavidson
 */
public abstract class Page extends KGradientPanel {
    
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static final Logger LOG = Logger.getLogger(Page.class);
    
	/**
     * This allows setup method to essentially by the constructor.
     */
    public Page() {
        setup();
    }
    
    /**
     * Contains the page layout details.
     */
    protected abstract void setup();    
    
    /**
     * This allows pages to switch between other pages.
     * 
     * @param pageName
     */
    protected void switchPage(String pageName) {
        
        Container parentPanel =  getParent();
        CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
        
        cardLayout.show(parentPanel, pageName);       
     } 
    
   
    /**
     * This will kill the current window and start whatever window is passed.
     * 
     * @param window
     */
    protected void switchWindow(Window window) {
        Runnable application = window;
        new Thread(application).start();

        Window currentWindow = null;
            
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof JFrame) {
                currentWindow = (Window) parent;
            }
            parent = parent.getParent();
            
        }
        currentWindow.dispose();

    }
    
    /**
     * This returns the parent window to allow methods to be called on it.
     * Try to avoid this method and have methods be page dependent.
     * 
     * @return Current parent Window
     */
    protected Window getParentWindow() {
        Window currentWindow = null;
        
        Container parent = getParent();
        while (parent != null) {
            if (parent instanceof JFrame) {
                currentWindow = (Window) parent;
            }
            parent = parent.getParent();
            
        }
        return currentWindow;
    }
    
    /**
     * Method used to add a back button to any screen.
     * @param buttonText is a string of the text to be displayed on the button
     * @param windowText is a string that tells which page to be sent to
     * @return a Button Gradient Object that is set to the top left 
     * 		   of the screen
     */
    public ButtonGradient createBackButton(
    		String buttonText, String windowText) {
    	
		ButtonGradient backButton = new ButtonGradient();
		backButton.setText(buttonText);
		backButton.setFont(new Font("Monospaced", Font.BOLD, 14));
		
	    Dimension textSize = backButton.getPreferredSize();
	    int buttonWidth = textSize.width + 20;
	    
	    backButton.setBounds(12, 37, buttonWidth, 40);
		backButton.addActionListener((e) -> {
			switchPage(windowText);
			LOG.info(windowText + " button clicked");
		});
		return backButton;
    }
    
    /**
     * Create a JLabel title.
     * @param titleText
     * @return a JLabel
     */
    public JLabel createTitle(String titleText) {

		    
		JLabel title = new JLabel(titleText);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Monospaced", Font.BOLD, 70));
		
    	int titleWidth = title.getPreferredSize().width;
		int titleHeight = 80;
		int verticalCenter = (getWidth() - titleWidth) / 2;
		
		title.setBounds(verticalCenter, 20, titleWidth, titleHeight);
		
		return title;
    }
    
    /**
     * Creates the main Grid Clash Title logos.
     * 
     * @return a JPanel that has the correct sizing of the logo
     */
    public JPanel createTitleAndLogo() {
    	// Use null layout for absolute positioning
        JPanel titleAndLogoPanel = new JPanel(null); 

        // Title label
        JLabel loginTitle = new JLabel("GRID    CLASH");
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setFont(new Font("Monospaced", Font.BOLD, 60));
        loginTitle.setForeground(new Color(61, 110, 167));
        loginTitle.setBounds(157, 42, 685, 141);

        // Logo label
        JLabel clashLogo = new JLabel("");
        clashLogo.setIcon(
        		new ImageIcon(LoginPage.class.getResource(
        				"/edu/skidmore/cs326/game/"
        				+ "sudoku/frontend/assets/logo.png")));
        clashLogo.setBounds(436, 57, 127, 122);
//        titleAndLogoPanel.add(clashLogo);
//        titleAndLogoPanel.add(loginTitle);
        add(clashLogo);
        add(loginTitle);
        return titleAndLogoPanel;
    }
    
    /**
     * Creates Logo component.
     * 
     * @return clashLogo
     */
    public JLabel createLogo() {
    	JLabel clashLogo = new JLabel("");
        clashLogo.setIcon(
        		new ImageIcon(LoginPage.class.getResource(
        				"/edu/skidmore/cs326/game/"
        				+ "sudoku/frontend/assets/logo.png")));
        clashLogo.setBounds(436, 57, 127, 122);
        //add(clashLogo);
        return clashLogo;
    }

    

}