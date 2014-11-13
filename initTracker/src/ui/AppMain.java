package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.TransferHandler;

/**
 * User interface is built here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update November 13nd 2014 Latest update of this file
 * @LatestUpdate Added core ui elements, working on drag and drop/swap
 * 
 */

@SuppressWarnings("serial")
public class AppMain extends JFrame {

	public static void main(String[] args) {
		new AppMain();

	}

	// panel where elements can be put in
	private JPanel panel = new JPanel();
	// panel elements
	// TODO add elements
	JButton loadPresetButton = new JButton("Load a preset");
	JButton savePresetButton = new JButton("Save as preset");
	JButton addPlayerButton = new JButton("Add a player");
	JButton addMonsterButton = new JButton("Add a monster");

	JSeparator topSeperator = new JSeparator();

	JButton sortListButton = new JButton("Sort list");
	JLabel nextRoundLabel = new JLabel(
			"<html>Increase time of <i>all</i> (de)buffs by 1:</html>");
	JButton nextRoundButton = new JButton("Next Round");
	JLabel nextTurnLabel = new JLabel("<html>Next player/monster:</html>");
	JButton nextTurnButton = new JButton("Next turn");

	JSeparator bottomSeperator = new JSeparator();

	// Character panels

	JPanel firstCharacter = new JPanel();
	JButton firstDragButton = new JButton("1");

	JPanel secondCharacter = new JPanel();
	JButton secondDragButton = new JButton("2");

	/**
	 * Constructor to make the interface
	 */
	AppMain() {
		// info about the app
		// title of the window
		super("Iniative Tracker");
		// width and height of the panel (inclusive title bar and borders)
		setSize(1200, 750);
		setLocation(250, 50);
		panel.setLayout(null);
		firstCharacter.setLayout(null);
		secondCharacter.setLayout(null);

		// set pos x, pos y, width and height of each element
		// TODO setbound of elements
		loadPresetButton.setBounds(5, 5, 150, 40);
		savePresetButton.setBounds(200, 5, 150, 40);
		addPlayerButton.setBounds(400, 5, 150, 40);
		addMonsterButton.setBounds(600, 5, 150, 40);

		topSeperator.setBounds(0, 55, 1200, 2);

		sortListButton.setBounds(5, 75, 150, 40);
		nextRoundLabel.setBounds(180, 74, 120, 42);
		nextRoundButton.setBounds(300, 75, 150, 40);
		nextTurnLabel.setBounds(475, 75, 120, 40);
		nextTurnButton.setBounds(600, 75, 150, 40);

		bottomSeperator.setBounds(0, 130, 1200, 2);

		firstCharacter.setBounds(5, 150, 1150, 100);
		firstCharacter.setBackground(Color.black);
		firstDragButton.setBounds(5, 10, 50, 80);

		secondCharacter.setBounds(5, 250, 1150, 100);
		secondCharacter.setBackground(Color.red);
		secondDragButton.setBounds(5, 10, 50, 80);

		// make elements draggable

		MouseListener listener = new DragMouseAdapter();
		firstDragButton.addMouseListener(listener);
		secondDragButton.addMouseListener(listener);

		firstDragButton.setTransferHandler(new TransferHandler("text"));
		secondDragButton.setTransferHandler(new TransferHandler("text"));

		// add elementals to the panel layout
		// TODO add elements to panel

		panel.add(loadPresetButton);
		panel.add(savePresetButton);
		panel.add(addPlayerButton);
		panel.add(addMonsterButton);

		panel.add(topSeperator);

		panel.add(sortListButton);
		panel.add(nextRoundLabel);
		panel.add(nextRoundButton);
		panel.add(nextTurnLabel);
		panel.add(nextTurnButton);

		panel.add(bottomSeperator);

		panel.add(firstCharacter);
		firstCharacter.add(firstDragButton);

		panel.add(secondCharacter);
		secondCharacter.add(secondDragButton);

		// methods needed for default interface behavior
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		interfaceControls();
		
	}

	/**
	 * 
	 * Drag functionality
	 *
	 */
	class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}
	
	

	/**
	 * Behavior of interface elements are defined here
	 */
	public void interfaceControls() {

		
		/**
		 * When user clicks on button, create a new panel
		 */
		// Behavior of addPlayerButton
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//TODO
								
				}
		});	// Behavior of addPlayerButton
	}
}
