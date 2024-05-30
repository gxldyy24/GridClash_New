package edu.skidmore.cs326.game.sudoku.logic;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
* User pop up invites for game play.
*/
public class UserInvitePopUp extends JFrame implements 
MouseListener, ActionListener {

	/**
	 * Main function.
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		UserInvitePopUp myFrame = new UserInvitePopUp();
		
	}
 
	private static final long serialVersionUID = 1L;

	/**
	 * setting up the window.
	 */
	public void popUpInvite() {
		
		this.setTitle(getTitle());
		this.setSize(350, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(getLayout());
		
		
		JPopupMenu popupmenu = new JPopupMenu();
		popupmenu.setBounds(150, 200, 100, 150);
		
				
		
		this.setVisible(true);
		this.addMouseListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
} 