package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class to retrieve wins and losses.
 * 
 * @author ssignore
 */
public class RetrieveFromGames implements RetrieveGame {
	
	/**
	 * method to retrieve wins.
	 */
	@Override
	public String[] fetchWins(int user) throws PersistenceException {
		
		String[] wins;
		try {
			ArrayList<String> winsList = new ArrayList<>();
			var connection = MySQLConnection.connect();
			String query = "SELECT * FROM "
					+ "user_games WHERE player1_id = ? "
					+ "AND outcome = ?";
			PreparedStatement stmt = 
				connection.prepareStatement(query);
			stmt.setString(1, "" + user);
			stmt.setString(2, "1");
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				winsList.add("{Opponent} " 
				+ result.getInt("player2_id") 
				+ " {Difficulty} " 
				+ result.getString("difficulty_level")
				+ " {Timestamp} " 
				+ result.getString("finished_at"));
			}
			
			wins = new String[winsList.size()  - 1];
			for (int x = 0; x < winsList.size(); x++) {
				wins[x] = winsList.get(x);
			}
			
			return wins;
			
		} catch (SQLException e) {
			throw new PersistenceException(
				"Could not retrieve username", e);
		}
	}

	@Override
	public String[] fetchLosses(int user) throws PersistenceException {
		String[] losses;
		try {
			ArrayList<String> lossList = new ArrayList<>();
			var connection = MySQLConnection.connect();
			String query = "SELECT * FROM "
					+ "user_games WHERE player2_id = ? "
					+ "AND outcome = ?";
			PreparedStatement stmt = 
				connection.prepareStatement(query);
			stmt.setString(1, "" + user);
			stmt.setString(2, "1");
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				lossList.add("{Opponent} " 
				+ result.getInt("player2_id") 
				+ " {Difficulty} " 
				+ result.getString("difficulty_level")
				+ " {Timestamp} " 
				+ result.getString("finished_at"));
			}
			
			losses = new String[lossList.size() - 1];
			for (int x = 0; x < lossList.size(); x++) {
				losses[x] = lossList.get(x);
			}
			
			return losses;
			
		} catch (SQLException e) {
			throw new PersistenceException(
				"Could not retrieve username", e);
			
		}
	}

}
