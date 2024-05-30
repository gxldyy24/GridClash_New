package edu.skidmore.cs326.game.sudoku.persistence.insert;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.users.User;

/**
 * @author ssignore
 * Class to add wom/lost game to the user_games table.
 */
public class InsertGame implements InsertGameData {

    /**
     * This is where the Logger is stored.
     */
	private static final Logger LOG;
	
	/**
	 * This static block initializes the logger.
	 */
	static {
		LOG = Logger.getLogger(InsertGameData.class);
	}
	
	/**
	 * array of strings with levels of difficulty.
	 */
	private String[] levels = {"easy", "medium", "hard"};
	


	/**
	 * array of Strings with possible outcomes for the came.
	 */
	//private String[] outcomes = {"win", "loss", "draw"};
	
	/**
	 * The attribute a player.
	 */
	private User player1;
	
	/**
	 * This is the user of player2.
	 */
	private User player2;
	
	/**
	 * This is the difficulty of the game.
	 */
	private int difficulty;
	
	/**
	 * This is the outcome of the game. 
	 * 0 means there was a draw,
	 * 1 means player1 won
	 * 2 means player2 won
	 */
	private int outcome;
	
	//TO-DO TIMESTAMP
	
	/**
	 * constructor for game with 2 players.
	 * @param u = player1
	 * @param c = 'competitor' player 2
	 * @param d = difficulty
	 * @param o = outcome
	 */
	public InsertGame(User u, User c, int d, int o) {
		player1 = u;
		player2 = c;
		difficulty = d;
		outcome = o;
	}
	
	
	/**
	 * Method to insert column with won/lost game data .
	 * @return boolean
	 */
	@Override
	public boolean insertGame() throws PersistenceException {
			if (outcome == 0) {
				LOG.info("Adding game ---> {Draw} " 
						+ player1.getUsername() + ", " 
						+ player2.getUsername()
						+ "; {Difficulty} " 
						+ levels[difficulty] 
						+ "; {Timestamp} N/A");
				
				try {
					var connection = 
					MySQLConnection.connect();
					
					String query = "insert into "
						+ "user_games(outcome, "
						+ "player1, "
						+ "player2, difficulty, time) "
						+ "values (?, ?, ?, ?, ?)";
					PreparedStatement stmt =
						connection.
						prepareStatement(query);
					stmt.setString(1, "" + outcome);
					stmt.setString(2, player1.getCode());
					stmt.setString(3, player2.getCode());
					stmt.setString(4, "" + difficulty);
					stmt.setString(5, "{Timestamp}");
					stmt.executeUpdate();
					LOG.info("Board inserted "
						+ "into the user_games table");
				} catch (SQLException e) {
					throw new PersistenceException(
					"Could not insert game data", e);
				}
				
			} else if (outcome == 1 
			            && player1.getUsername() != null) {
				LOG.info("Adding game ---> {Winner} " 
			            + player1.getUsername()
						+ "; {Loser} " 
			            + player2.getUsername() 
						+ "; {Difficulty} " 
			            + levels[difficulty] 
						    + "; {Timestamp} N/A");
				try {
					var connection = MySQLConnection
					                          .connect();
					String query = "insert into "
						+ "user_games(outcome, "
					    + "player1, "
						+ "player2, difficulty, time) "
						+ "values (?, ?, ?, ?, ?)";
					PreparedStatement stmt = 
					        connection
					             .prepareStatement(query);
					stmt.setString(1, "" + outcome);
					stmt.setString(2, player1.getCode());
					stmt.setString(3, player2.getCode());
					stmt.setString(4, levels[difficulty]);
					stmt.setString(5, "{Timestamp}");
					stmt.executeUpdate();
					LOG.info("Board inserted into "
						+ "the user_games table");
					return true;
					
				} catch (SQLException e) {
					throw new PersistenceException(
					      "Could not insert game data", e);
				}
			} else if (outcome == 1 
			    && player2.getUsername() == null) {
			    LOG.info("No code being run in this block");
			    try {
			    	var connection = 
			    		MySQLConnection
			    		.connect();
			    	
			    	LOG.info("Adding game ---> {Winner} " 
				            + player1.getUsername()
							+ "; {Loser} NULL"
							+ "; {Difficulty} " 
				            + levels[difficulty] 
							+ "; {Timestamp} N/A");
			    	
			    	String query = "instert into "
			    		+ "user_games(outcome, "
			    		+ "player1, "
			    		+ "player2, difficulty, time) "
			    		+ "values (?, ?, ?, ?, ?)";
			    	PreparedStatement stmt = 
			    		connection
			    		.prepareStatement(query);
			    	stmt.setString(1, "" + outcome);
			    	stmt.setString(2, player1.getCode());
			    	stmt.setString(3, "null");
			    	stmt.setString(4, "" + difficulty);
			    	stmt.setString(5, "{Timestamp}");
			    	stmt.executeUpdate();
			    	
			    } catch (SQLException e) {
			    	throw new PersistenceException(
			    	"Could not insert game data", e);
			    }
			}
		
		
		return false;
	}
	
}
