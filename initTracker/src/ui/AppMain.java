package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * User interface is built here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update November 2nd 2014 Latest update of this file
 * @LatestUpdate Creation of file, made first panel.
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
	JLabel statusStaticLabel = new JLabel("Status: ");
	JButton aButton = new JButton("Test");

	/**
	 * Constructor to make the interface
	 */
	AppMain() {
		// info about the app
		// title of the window
		super("Iniative Tracker");
		// width and height of the panel (inclusive title bar and borders)
		setSize(800, 600);
		setLocation(250, 50);
		panel.setLayout(null);

		// set pos x, pos y, width and height of each element
		statusStaticLabel.setBounds(5, 5, 50, 20);
		aButton.setBounds(5, 25, 75, 30);

		// add elementals to the panel layout
		panel.add(statusStaticLabel);
		panel.add(aButton);

		// methods needed for default interface behavior
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		interfaceControls();

	}

	/**
	 * Behavior of interface elements are defined here
	 */
	public void interfaceControls() {

		// Behavior of the createUserButton
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});// Behavior of the createUserButton
	}
}
