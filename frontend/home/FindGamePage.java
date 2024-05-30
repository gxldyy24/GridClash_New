package edu.skidmore.cs326.game.sudoku.frontend.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.frontend.ButtonGradient;
import edu.skidmore.cs326.game.sudoku.frontend.HomeWindow;
import edu.skidmore.cs326.game.sudoku.frontend.Page;
import edu.skidmore.cs326.game.sudoku.frontend.PlayWindow;
import edu.skidmore.cs326.game.sudoku.frontend.login.LoginPage;
import edu.skidmore.cs326.game.sudoku.persistence.onlinestatoperations.OnlineStatusOperations;
import edu.skidmore.cs326.game.sudoku.persistence.onlinestatoperations.OnlineStatus;
import edu.skidmore.cs326.game.sudoku.frontend.play.ButtonColumn;

import javax.swing.ImageIcon;
import javax.swing.AbstractAction;

/**
 * Grid Clash Find Game page.
 * 
 * @author eheidepriem vzhao
 */


public class FindGamePage extends Page {
	
	/**
	 * Find Game Page.
	 */
	public FindGamePage() {
	}

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * JButton for play solo button.
	 */
	private ButtonGradient playSoloButton;
	
	/**
	 * The logger.
	 */
	private static final Logger LOG = Logger.getLogger(PlayPage.class);
	
	/**
	 * Interface for online status operations.
	 */
	private OnlineStatus statusOperations;

	/**
	 * Setup find game panel.
	 */
	protected void setup() {
	    statusOperations = new OnlineStatusOperations();
		addFindGamePanel();
		addBackToPlayButton();
		// shortcutToGamePage();
	}

	/**
	 * Add components to find game Panel.
	 */
	private void addFindGamePanel() {
		this.setLayout(null);
		this.setBounds(350, 250, 1001, 700);
		addTitleAndLogo();
		addPlaySoloButton();
		addUserTable();
	}

	/**
	 * Add title and grid clash icon to the find game panel.
	 */
	private void addTitleAndLogo() {

		JLabel gridClashLogo = new JLabel("Here");
		gridClashLogo.setBounds(213, 40, 575, 141);
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
		
		JLabel lblWaitingRoom = new JLabel("Waiting Room");
		lblWaitingRoom.setFont(new Font("Monospaced", Font.BOLD, 26));
		lblWaitingRoom.setBounds(404, 212, 192, 32);
		add(lblWaitingRoom);
	}
	
	/**
	 * Adds play solo button.
	 */
	private void addPlaySoloButton() {
		playSoloButton = new ButtonGradient();
		playSoloButton.setText("Play Solo");
		playSoloButton.setBounds(351, 517, 293, 79);
		playSoloButton.setColor1(
			new Color(61, 110, 167));
		playSoloButton.setColor2(
			new Color(61, 110, 167));
		playSoloButton.setFont(
			new Font("Monospaced", Font.BOLD, 28));

		this.add(playSoloButton);
		playSoloButton.repaint();
		playSoloButton.addActionListener((e) -> {
			LOG.info("'Play Solo' button selected");

			// Go to find game panel
			HomeWindow homeWindow = (HomeWindow) getParentWindow();
			
			switchWindow(new PlayWindow(homeWindow.getUser(), 
			    homeWindow.getDefaultSize(), 
			    homeWindow.getDefaultDifficulty()));
		});
	}
	

	/**
	 * Add back to play button to find game panel.
	 */
	private void addBackToPlayButton() {
		JButton backToPlayButton = 
				createBackButton("< Back to Selection", "Play");
		add(backToPlayButton);
	}

	/**
	 * JTable for user data.
	 */
	private void addUserTable() {
		String[] columnNames = { "Display Name", "In Game",
				"Ratio W/L/D", "Board Size", "Difficulty",
				"Last Logged In", "Invite" };
//		try {
//			statusOperations.setUserOffline(1234234235);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//	List<Integer> onlineUserIDs = statusOperations.listOnlineUsers();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
		
		
		try {
		    List<Map<String, Object>> onlineUsers = 
		    statusOperations.getAllUsersWithPreferencesAndStats();
		    String[][] userData = new String[onlineUsers.size()][7];

		    SimpleDateFormat dateFormat = new 
		    		SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    for (int i = 0; i < onlineUsers.size(); i++) {
		        Map<String, Object> user = onlineUsers.get(i);
		        String displayName = (String) user.get("username");
		        boolean inGame = false;
		        int wins = (int) user.getOrDefault("wins", 0);
		        int losses = (int) user.getOrDefault("losses", 0);
		        int draws = (int) user.getOrDefault("draws", 0);
		        String ratio = wins + "/" + losses + "/" + draws;
		        String boardSize = (String) 
		        		user.getOrDefault("board_size", "N/A");
		        String difficulty = (String) 
		        		user.getOrDefault(
		        			"difficulty_level", "N/A");
		        Timestamp lastOnline = (Timestamp) 
		        		user.get("last_online");
		        String lastLoggedIn = lastOnline != null 
		        		? 
		        		dateFormat.format(lastOnline) : "N/A";
		        String invite = "Invite"; 

		        userData[i][0] = displayName;
		        userData[i][1] = inGame ? "Yes" : "No";
		        userData[i][2] = ratio;
		        userData[i][3] = boardSize;
		        userData[i][4] = difficulty;
		        userData[i][5] = lastLoggedIn;
		        userData[i][6] = invite;
		    }

		    // Create a DefaultTableModel with the data and column names
		    DefaultTableModel tableModel = new 
		    		DefaultTableModel(userData, columnNames) {
		        private static final long serialVersionUID = 1L;

				@Override
		        public boolean isCellEditable(int row, int column) {
		            // all cells false except invite column
		            return column == getColumnCount() - 1;
		        }
		    };

		    // Create a JTable using the DefaultTableModel
		    JTable table = new JTable(tableModel);

		    // Add the table to a JScrollPane and 
		    // add the scroll pane to the panel
		    JScrollPane scrollPane = new JScrollPane(table);

		    AbstractAction sendInvite = new AbstractAction() {
		        private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e) {
		            // action to send invite
		            JOptionPane.showMessageDialog(null, 
		            		"Invite Sent to user.");
		            LOG.info("Invite button pressed.");
		        }
		    };

		    // Add a button column for invites
		    @SuppressWarnings("unused")
			ButtonColumn buttonColumn = new 
			ButtonColumn(table, sendInvite, 6);

		    // Add the scroll pane to the panel
		    scrollPane.setLocation(0, 256);
		    scrollPane.setSize(1000, 249);
		    table.getColumnModel().
		    getColumn(0).setPreferredWidth(170);		    

		    add(scrollPane);
		} catch (SQLException e) {
		    // Handle SQL exception
		    e.printStackTrace();
		}
	}
}
