package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import org.apache.log4j.Logger;

/**
 * This class is used to count the number of strikes
 * a player has.
 * @author Jack Biggins
 */
public class StrikeCounter extends JPanel {
    
    /**
     * This is used to supress a JPanel error.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * This is where all the x's are stored.
     */
    private JLabel[] slots;
    
    /**
     * This is the nuber of strikes the user has.
     */
    private int numberStrikes;
    
    /**
     * This is the variable for the Logger.
     */
    private static final Logger LOG;
    
    /**
     * This static block initializes the logger.
     */
    static {
        LOG = Logger.getLogger(StrikeCounter.class);
    }
    
    /**
     * This constructor initializes the looks.
     * @param attempts the number of attemps the user has. 
     *              Example: 3 strikes
     */
    public StrikeCounter(int attempts) {
        
        super(new GridLayout(attempts, 1));
        numberStrikes = 0;
        
        
        
        slots = new JLabel[attempts];
        
        for (int i = 0; i < slots.length; i++) {
            
            JLabel slot = new JLabel();
            slot.setText("");
            slot.setBackground(Color.WHITE);
            slot.setForeground(Color.RED);
            slot.setPreferredSize(new Dimension(20, 20));
            slot.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
            slots[i] = slot;
            add(slot);
            
        }
        
        
        
    }
    
    /**
     * This adds a strike.
     */
    public void addStrike() {
        
        if (isGameOver()) {
            return;
        }
        
        
      
        numberStrikes++;
        LOG.info("Numebr of strikes = " + numberStrikes);
        //LOG.info("HELP ME = " + slots[numberStrikes - 1]);
        slots[numberStrikes - 1].setFont(new Font("Monospaced", Font.BOLD, 36));
        slots[numberStrikes - 1].setText("X");
        slots[numberStrikes - 1].setHorizontalAlignment(SwingConstants.CENTER);
        

    }

    /**
     * @return true if game is over.
     */
    public boolean isGameOver() {
        return numberStrikes >= slots.length;
    }
    
    

}
