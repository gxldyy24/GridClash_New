/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseMotionListener;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author oXCToo
 */
public class KGradientPanel extends JPanel {
	
	/**
	 * The serial version id.
	 */
    private static final long serialVersionUID = 1L;
    
    /**
     * The start color for the gradient.
     */
	private Color kStartColor = Color.gray;
	
	/**
	 * The end color for the gradient.
	 */
    private Color kEndColor = Color.white;
    
    /**
     * Boolean for gradient transparency. 
     */
    private boolean kTransparentControls = true;
    
    /**
     * The focus number for the gradient.
     */
    private int kGradientFocus = 500;

    /**
     * Gets start color.
     * 
     * @return kStartColor
     */
    public Color getkStartColor() {
        return kStartColor;
    }
    
    /**
     * Sets start color.
     * @param kStartColor
     */
    public void setkStartColor(Color kStartColor) {
        this.kStartColor = kStartColor;
    }
    
    /**
     * Gets the end color.
     * @return kEndColor
     */
    public Color getkEndColor() {
        return kEndColor;
    }
    
    /**
     * Sets the end color.
     * @param kEndColor
     */
    public void setkEndColor(Color kEndColor) {
        this.kEndColor = kEndColor;
    }
    
    /**
     * Boolean for gradient transparency.
     * @return True if transparent, false otherwise.
     */
    public boolean iskTransparentControls() {
        return kTransparentControls;
    }
    
    /**
     * Sets the boolean for transparency.
     * @param kTransparentControls
     */
    public void setkTransparentControls(boolean kTransparentControls) {
        this.kTransparentControls = kTransparentControls;
    }
    
    /**
     * Gets the gradient focus.
     * @return kGradientFocus
     */
    public int getkGradientFocus() {
        return kGradientFocus;
    }
    
    /**
     * Sets the gradient focus.
     * @param kGradientFocus
     */
    public void setkGradientFocus(int kGradientFocus) {
        this.kGradientFocus = kGradientFocus;
    }
  
    /**
     * Constructor for the class.
     */
    public KGradientPanel() {

        if (kTransparentControls) {
            setBg(true);
        } else {
            setBg(false);
        }

    }
    
    /**
     * Adds mouse motion listener.
     */
    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
    	//To change body of generated methods, choose Tools | Templates.
        super.addMouseMotionListener(l);
    }
    
    /**
     * Paints the page with the graphics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
        		RenderingHints.KEY_RENDERING, 
        		RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();

        GradientPaint gp = new GradientPaint(
        		0, 0, kStartColor, kGradientFocus, h, kEndColor);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        //g2d.dispose();
    }
    
    /**
     * Sets the background of page.
     * @param isOpaque
     */
    private void setBg(boolean isOpaque) {
        Component[] components = this.getComponents();
        for (Component component : components) {

            ((JLabel) component).setOpaque(isOpaque);
            ((JCheckBox) component).setOpaque(isOpaque);
            ((JTextField) component).setOpaque(isOpaque);
            ((JPasswordField) component).setOpaque(isOpaque);
            ((JFormattedTextField) component).setOpaque(isOpaque);
            ((JToolBar) component).setOpaque(isOpaque);
            ((JRadioButton) component).setOpaque(isOpaque);

        }
    }

}
