package edu.skidmore.cs326.game.sudoku.persistence.modify;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import edu.skidmore.cs326.game.sudoku.persistence.connection.MySQLConnection;
import edu.skidmore.cs326.game.sudoku.persistence.exception.PersistenceException;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.RetrieveUsersGameStatistics;
import edu.skidmore.cs326.game.sudoku.persistence.fetching.FetchUsersGameStatistics;

/**
 * Updates data in users_game_statistics.
 * @author zlindewirth
 */
public class UpdateGameStatistics implements ChangeGameStatistics {
    /**
     * The logger.
     */
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(UpdateGameStatistics.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int updateWins(int id) throws PersistenceException {
        RetrieveUsersGameStatistics stats = new FetchUsersGameStatistics();
        try {
            var connection = MySQLConnection.connect();
            String query = "update users_game_statistics set "
                + "wins = ? where id = ";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            int wins = stats.fetchWins(id);
            int newWins = wins + 1;
            stmt.setInt(2, newWins);
            stmt.executeUpdate();
            LOG.info("Updated user " + id 
                + " win total");
        } catch (SQLException e) {
            LOG.info("Unable to update");
            throw new PersistenceException("Could not change win"
                                           + " total", e);
        }
        return 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int updateLosses(int id) throws PersistenceException {
        RetrieveUsersGameStatistics stats = new FetchUsersGameStatistics();
        try {
            var connection = MySQLConnection.connect();
            String query = "update users_game_statistics set "
                + "losses = ? where id = ";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            int losses = stats.fetchLosses(id);
            int newLosses = losses + 1;
            stmt.setInt(2, newLosses);
            stmt.executeUpdate();
            LOG.info("Updated user " + id 
                + " loss total");
        } catch (SQLException e) {
            LOG.info("Unable to update");
            throw new PersistenceException("Could not change loss"
                                           + " total", e);
        }
        return 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int updateDraw(int id) throws PersistenceException {
        RetrieveUsersGameStatistics stats = new FetchUsersGameStatistics();
        try {
            var connection = MySQLConnection.connect();
            String query = "update users_game_statistics set "
                + "draws = ? where id = ";
            PreparedStatement stmt = 
                connection.prepareStatement(query);
            stmt.setInt(1, id);
            int draws = stats.fetchDraws(id);
            int newDraws = draws + 1;
            stmt.setInt(2, newDraws);
            stmt.executeUpdate();
            LOG.info("Updated user " + id 
                + " draw total");
        } catch (SQLException e) {
            LOG.info("Unable to update");
            throw new PersistenceException("Could not change draw"
                                           + " total", e);
        }
        return 0;
    }

}
