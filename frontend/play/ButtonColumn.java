package edu.skidmore.cs326.game.sudoku.frontend.play;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * The ButtonColumn class provides a renderer and an editor that looks like a
 * JButton. The renderer and editor will then be used for a specified column in
 * the table. The TableModel will contain the String to be displayed on the
 * button.
 *
 * The button can be invoked by a mouse click or by pressing the space bar when
 * the cell has focus. Optionally a mnemonic can be set to invoke the button.
 * When the button is invoked the provided Action is invoked. The source of the
 * Action will be the table. The action command will contain the model row
 * number of the button that was clicked.
 *
 */
public class ButtonColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor,
		ActionListener, MouseListener {
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JTable for the table created.
	 */
	private JTable table;

	/**
	 * Action for when button is clicked.
	 */
	private javax.swing.Action action;

	/**
	 * Mnemonic for the button.
	 */
	private int mnemonic;
	/**
	 * Boarder for default cell.
	 */
	private Border originalBorder;

	/**
	 * Border for when button is in focus.
	 */
	private Border focusBorder;

	/**
	 * Button to be rendered into table cell.
	 */
	private JButton renderButton;

	/**
	 * Button to be edited for table cell.
	 */
	private JButton editButton;

	/**
	 * The value of the cell to be edited.
	 */
	private Object editorValue;

	/**
	 * Boolean for button editor.
	 */
	private boolean isButtonColumnEditor;

	/**
	 * Create the ButtonColumn to be used as a renderer and editor.
	 * The renderer and editor will automatically be installed
	 * on the TableColumn of the specified column.
	 *
	 * @param table      table w button renderer/editor.
	 * @param sendInvite the Action to be invoked
	 * @param column     the column that renderer is added.
	 */
	public ButtonColumn(JTable table,
			javax.swing.Action sendInvite,
			int column) {
		this.table = table;
		this.action = sendInvite;

		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);
		originalBorder = editButton.getBorder();
		setFocusBorder(new LineBorder(Color.BLUE));

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
		table.addMouseListener(this);
	}

	/**
	 * Get foreground color of the button when the cell has focus.
	 *
	 * @return the foreground color.
	 */
	public Border getFocusBorder() {
		return focusBorder;
	}

	/**
	 * The foreground color of the button when the cell has focus.
	 *
	 * @param focusBorder the foreground color.
	 */
	public void setFocusBorder(Border focusBorder) {
		this.focusBorder = focusBorder;
		editButton.setBorder(focusBorder);
	}

	/**
	 * Gets the mnemonic.
	 * 
	 * @return mnemonic
	 */
	public int getMnemonic() {
		return mnemonic;
	}

	/**
	 * The mnemonic to activate the button when the cell has focus.
	 *
	 * @param mnemonic the mnemonic
	 */
	public void setMnemonic(int mnemonic) {
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
/*
 * Action mnemonicAction = new AbstractAction() { public void
 * actionPerformed(ActionEvent e) { ButtonColumn.this.actionPerformed(e); } };
 * 
 * String key = "mnemonicAction"; KeyStroke keyStroke =
 * KeyStroke.getKeyStroke(mnemonic, 0);
 * editButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke,
 * key); editButton.getActionMap().put(key, mnemonicAction);
 */
	}

	/**
	 * Edits the given values and returns the edited button.
	 * 
	 * @return editButton
	 */
	@Override
	public Component getTableCellEditorComponent(
			JTable table, Object value, 
			boolean isSelected, int row, int column) {
		if (value == null) {
			editButton.setText("");
			editButton.setIcon(null);
		} else if (value instanceof Icon) {
			editButton.setText("");
			editButton.setIcon((Icon) value);
		} else {
			editButton.setText(value.toString());
			editButton.setIcon(null);
		}

		this.editorValue = value;
		return editButton;
	}

	/**
	 * Getter for cell edit value.
	 * 
	 * @return editorValue
	 */
	@Override
	public Object getCellEditorValue() {
		return editorValue;
	}

	/**
	 * Implement TableCellRenderer interface.
	 * 
	 * @param table
	 * @param value
	 * @param isSelected
	 * @param hasFocus
	 * @param row
	 * @param column
	 * 
	 * @return renderButton
	 */
	public Component getTableCellRendererComponent(
			JTable table,
			Object value, 
			boolean isSelected, 
			boolean hasFocus,
			int row, 
			int column) {
		
		if (isSelected) {
			renderButton.setForeground(
				table.getSelectionForeground());
			renderButton.setBackground(
				table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(
				UIManager.getColor("Button.background"));
		}

		if (hasFocus) {
			renderButton.setBorder(focusBorder);
		} else {
			renderButton.setBorder(originalBorder);
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null) {
			renderButton.setText("");
			renderButton.setIcon(null);
		} else if (value instanceof Icon) {
			renderButton.setText("");
			renderButton.setIcon((Icon) value);
		} else {
			renderButton.setText(value.toString());
			renderButton.setIcon(null);
		}

		return renderButton;
	}

	/**
	 * Implement ActionListener interface.
	 * The button has been pressed. 
	 * Stop editing and invoke the custom Action.
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		int row = table.convertRowIndexToModel(table.getEditingRow());
		fireEditingStopped();

		// Invoke the Action

		ActionEvent event = 
			new ActionEvent(table, 
				ActionEvent.ACTION_PERFORMED,
				"" + row);
		((ActionListener) action).actionPerformed(event);
	}

	/**
	 * Implement MouseListener interface. When the 
	 * mouse is pressed the editor is invoked. If 
	 * you then then drag the mouse to another cell before releasing it,
	 * the editor is still active. Make sure editing is stopped when 
	 * the mouse isreleased.
	 * 
	 * @param e
	 */
	public void mousePressed(MouseEvent e) {
		if (table.isEditing() && table.getCellEditor() == this) {
			isButtonColumnEditor = true;
		}
	}

	/**
	 * Action for when mouse click is released.
	 * 
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
		if (isButtonColumnEditor && table.isEditing()) {
			table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
		}
	}

	/**
	 * Action for when mouse is clicked.
	 * 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Action for when mouse entered.
	 * 
	 * @param e
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Action for when mouse is exited.
	 * 
	 * @param e
	 */
	public void mouseExited(MouseEvent e) {
	}
}