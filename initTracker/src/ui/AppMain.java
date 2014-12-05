package ui;

import static utility.EN_res.ADDMONSTER;
import static utility.EN_res.ADDPLAYER;
import static utility.EN_res.DEBUFFEXPIRED;
import static utility.EN_res.LOADPRESET;
import static utility.EN_res.NEXTTURN;
import static utility.EN_res.SAVEPRESET;
import static utility.EN_res.SORTLIST;
import static utility.EN_res.WINDOWNAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import units.PlayerCharacter;
import utility.Utility;

/**
 * User interface is built here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update December 3rd 2014 Latest update of this file
 * @LatestUpdate Removed nextRound, implemented it into nextTurn
 * 
 */

@SuppressWarnings("serial")
public class AppMain extends JFrame {

	public static void main(String[] args) {
		new AppMain();
	}

	public static ArrayList<PlayerCharacter> arrList = new ArrayList<>();

	// panel where elements can be put in
	private JPanel panel = new JPanel();
	// panel elements
	JButton loadPresetButton = new JButton(LOADPRESET);
	JButton savePresetButton = new JButton(SAVEPRESET);
	JButton addPlayerButton = new JButton(ADDPLAYER);
	JButton addMonsterButton = new JButton(ADDMONSTER);

	JSeparator topSeperator = new JSeparator();

	JButton sortListButton = new JButton(SORTLIST);
	JButton nextTurnButton = new JButton(NEXTTURN);

	JSeparator bottomSeperator = new JSeparator();

	/**
	 * Constructor to make the interface
	 */
	AppMain() {
		// info about the app
		// title of the window
		super(WINDOWNAME);
		// width and height of the panel (inclusive title bar and borders)
		setSize(1200, 750);
		setLocation(250, 50);
		panel.setLayout(null);

		// set pos x, pos y, width and height of each element
		loadPresetButton.setBounds(5, 5, 150, 40);
		savePresetButton.setBounds(200, 5, 150, 40);
		addPlayerButton.setBounds(400, 5, 150, 40);
		addMonsterButton.setBounds(600, 5, 150, 40);

		topSeperator.setBounds(0, 55, 1200, 2);

		sortListButton.setBounds(5, 75, 150, 40);
		nextTurnButton.setBounds(600, 75, 150, 40);

		bottomSeperator.setBounds(0, 130, 1200, 2);

		// makes elements draggable
		// TODO
		// MouseListener listener = new DragMouseAdapter();
		// TODO add dynamically added panels to list of dragable objects
		/*
		 * firstDragButton.addMouseListener(listener);
		 * secondDragButton.addMouseListener(listener);
		 * 
		 * firstDragButton.setTransferHandler(new TransferHandler("text"));
		 * secondDragButton.setTransferHandler(new TransferHandler("text"));
		 */

		// add elementals to the panel layout

		panel.add(loadPresetButton);
		panel.add(savePresetButton);
		panel.add(addPlayerButton);
		panel.add(addMonsterButton);

		panel.add(topSeperator);

		panel.add(sortListButton);

		panel.add(nextTurnButton);

		panel.add(bottomSeperator);

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
			// TODO make it a swap location drag and drop
		}
	}

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	/**
	 * Behavior of interface elements are defined here
	 */
	public void interfaceControls() {

		/**
		 * Opens a window to select a previously saved preset
		 */
		// Behavior of loadPresetButton
		loadPresetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO method to handle loadPresetButton

			}

		});
		// Behavior of loadPresetButton

		/**
		 * Opens a window to input name of preset and maybe a place to store it
		 */
		// Behavior of savePresetButton
		savePresetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO method to handle savePresetButton

			}

		});
		// Behavior of savePresetButton

		/**
		 * Sort the list of panels by value of their iniative (descending)
		 */
		// Behavior of sortListButton
		sortListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Collections.sort(arrList);
				repository.repositionPanels();
			}

		});
		// Behavior of sortListButton

		/**
		 * Rearrange panels. Highest becomes lowest. All others move up one
		 * place.
		 */
		// Behavior of nextTurnButton
		nextTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Collections.rotate(arrList.subList(0, arrList.size()), -1);
				repository.repositionPanels();
				if (repository.nextRound(arrList.get(0))){
					JOptionPane.showMessageDialog(panel, DEBUFFEXPIRED);
				}
			}

		});
		// Behavior of nextTurnButton

		/**
		 * When user clicks on button, create a new panel
		 */
		// Behavior of addPlayerButton
		addPlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO extract to utility if possible
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						PlayerCharacter newPlayerCharacter = new PlayerCharacter();
						arrList.add(newPlayerCharacter);
						panel.add(newPlayerCharacter);
						panel.validate();
						panel.repaint();
						
					}

				}); // Behavior of addPlayerButton
			}
		});

	}
}
