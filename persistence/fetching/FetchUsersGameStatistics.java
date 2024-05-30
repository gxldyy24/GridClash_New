package edu.skidmore.cs326.game.sudoku.persistence.fetching;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
/**
 * Getting data from users_game_statisitics.
 * @author zlindewirth
 */
public class FetchUsersGameStatistics implements RetrieveUsersGameStatistics {
    /**
     * The logger.
     */
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(FetchUsersGameStatistics.class);
    }
    /**
     * {@inheritDoc}
     * @throws PersistenceException 
     */
    @Override
    public int fetchWins(int id) throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            String query = "select wins from "
                + "users_game_statistics where id=?";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int wins = 
                    rs.getInt("wins");
                LOG.info("Got " + id 
                    + " total wins");
                return wins;
            } else {
                LOG.info("Unable to get total wins");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error trying "
                + "to retrieve user's total "
                + "wins", e);
        }
        return 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int fetchLosses(int id) throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            String query = "select losses from "
                + "users_game_statistics where id=?";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int losses = 
                    rs.getInt("losses");
                LOG.info("Got " + id 
                    + " total losses");
                return losses;
            } else {
                LOG.info("Unable to get total losses");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error trying "
                + "to retrieve user's total "
                + "losses", e);
        }
        return 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int fetchDraws(int id) throws PersistenceException {
        try {
            var connection = MySQLConnection.connect();
            String query = "select draws from "
                + "users_game_statistics where id=?";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int draws = 
                    rs.getInt("draws");
                LOG.info("Got " + id 
                    + " total draws");
                return draws;
            } else {
                LOG.info("Unable to get total draws");
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error trying "
                + "to retrieve user's total "
                + "draws", e);
        }
        return 0;
    }
    
}
