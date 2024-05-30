package edu.skidmore.cs326.game.sudoku.frontend;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * A custom JButton class with gradient and rounded buttons.
 *
 * This class extends JButton and provides buttons with 
 * gradient and rounded edges.
 *
 * Original source: GitHub
 */
public class ButtonGradient extends JButton {

    /** Serial version ID. */
    private static final long serialVersionUID = 1L;

    /** The first color of the gradient. */
    private Color color1 = new Color(208, 208, 203);

    /** The second color of the gradient. */
    private Color color2 = new Color(208, 208, 203);

    /** A timer for the mouse over effect. */
    private final Timer timer;

    /** A timer for the pressed effect. */
    private final Timer timerPressed;

    /** The transparency level of the button. */
    private float alpha = 0.3f;

    /** Whether the mouse is over the button. */
    private boolean mouseOver;

    /** Whether the button is pressed. */
    private boolean pressed;

    /** The location where the button was pressed. */
    private Point pressedLocation;

    /** The size of the pressed effect. */
    private float pressedSize;

    /** The speed at which the pressed effect grows. */
    private float sizeSpeed = 1f;

    /** The transparency level of the pressed effect. */
    private float alphaPressed = 0.5f;

    /**
     * Creates a new `ButtonGradient` with default settings.
     */
    public ButtonGradient() {
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                pressedSize = 0;
                alphaPressed = 0.5f;
                pressed = true;
                pressedLocation = me.getPoint();
                timerPressed.setDelay(0);
                timerPressed.start();
            }
        });

        timer = new Timer(40, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mouseOver) {
                    if (alpha < 0.6f) {
                        alpha += 0.05f;
                        repaint();
                    } else {
                        alpha = 0.6f;
                        timer.stop();
                        repaint();
                    }
                } else {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.3f;
                        timer.stop();
                        repaint();
                    }
                }
            }
});

        timerPressed = new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                pressedSize += getSizeSpeed();
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }

    /**
     * Paints the component.
     *
     * @param grphcs the Graphics object to draw on
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, 
        		BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        		RenderingHints.VALUE_ANTIALIAS_ON);

        // Create Gradients Color
        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gra);

        g2.fillRoundRect(0, 0, width, height, height, height);

        // Add Style
        createStyle(g2);

        if (pressed) {
            paintPressed(g2);
        }

        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

    /**
     * Creates the style for the button.
     *
     * @param g2 the Graphics2D object to draw on
     */
    private void createStyle(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(
        		AlphaComposite.SRC_ATOP, alpha));
        int width = getWidth();
        int height = getHeight();
        GradientPaint gra = new GradientPaint(
        		0, 0, Color.WHITE, 0, height,
                new Color(255, 255, 255, 60));
        g2.setPaint(gra);
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, 0);
        int controll = height + height / 2;
        f.curveTo(0, 0, width / 2, controll, width, 0);
        g2.fill(f);
    }

    /**
     * Paints the button when pressed.
     *
     * @param g2 the Graphics2D object to draw on
     */
    private void paintPressed(Graphics2D g2) {
        if (pressedLocation.x - (pressedSize / 2) < 0
                && pressedLocation.x + (pressedSize / 2) 
                > getWidth()) {
            timerPressed.setDelay(20);
            alphaPressed -= 0.05f;
            if (alphaPressed < 0) {
                alphaPressed = 0;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(
        		AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
        g2.fillOval((int) x, (int) y, (int) pressedSize, 
        		(int) pressedSize);
    }

    /**
     * Getter for size speed.
     *
     * @return the size speed
     */
    public float getSizeSpeed() {
        return sizeSpeed;
    }

    /**
     * Setter for size speed.
     *
     * @param sizeSpeed the size speed to set
     */
    public void setSizeSpeed(float sizeSpeed) {
        this.sizeSpeed = sizeSpeed;
    }

    /**
     * Getter for color1.
     *
     * @return color1
     */
    public Color getColor1() {
        return color1;
   }

    /**
     * Setter for color1.
     *
     * @param color1 the color1 to set
     */
    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    /**
     * Getter for color2.
     *
     * @return color2
     */
    public Color getColor2() {
        return color2;
    }

    /**
     * Setter for color2.
     *
     * @param color2 the color2 to set
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

}