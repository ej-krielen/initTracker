package utility;

import static units.PlayerCharacter.PANELHEIGHT;
import static units.PlayerCharacter.PANELWIDTH;
import static units.PlayerCharacter.PANEL_X;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

import units.PlayerCharacter;

/**
 * 
 * This is were all user actions are handled
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update December 1st 2014 Latest update of this file
 * @LatestUpdate Changed getPanelYPos to also be usable for sortList
 * 
 */

public class Utility {

	private static Utility INSTANCE;
	static {
		INSTANCE = new Utility();
	}

	public static Utility getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Utility();
		}
		return INSTANCE;
	}

	/**
	 * Counter used to give each {@link PlayerCharacter} an unique ID
	 */
	private static int playerCharacterCounter;

	/**
	 * Increases the playerCharacterCounter by 1 when a new panel is created
	 */
	public static void increasePlayerCharacterCounter() {
		playerCharacterCounter++;
	}

	/**
	 * Decreases the playerCharacterCounter by 1 when a new panel is created
	 */
	public static void decreasePlayerCharacterCounter() {
		playerCharacterCounter--;
	}
	
	//AppMain exclusive methods
	/**
	 * Goes through the arrList from AppMain. Sets positions and colors of
	 * panels based on position in the arrList.
	 */
	public void repositionPanels() {
		for (PlayerCharacter pc : ui.AppMain.arrList) {
			int newListPosition = ui.AppMain.arrList.indexOf(pc);
			pc.setBounds(PANEL_X, getPanelYpos(newListPosition), PANELWIDTH,
					PANELHEIGHT);
			if (newListPosition % 2 <= 0) {
				pc.setBackground(Color.GRAY);
			} else {
				pc.setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	/**
	 * Goes through the arrList from AppMain. Runs method increaseDebuffs on all
	 * panels. If something was changed sets boolean to true.
	 * 
	 * @return True if a change was made, false if no changes were made.
	 */
	public boolean nextRound() {
		boolean b = false;
		for (PlayerCharacter pc : ui.AppMain.arrList) {
			if (pc.increaseDebuffs()) {
				b = true;
			}
		}
		return b;
	}
	
	//PlayerCharacter exclusive methods
	
	/**
	 * Gets the textField (through the editor (from JComponent)) and centers the
	 * text in the spinner. Also sets the color depending on which spinner was
	 * entered.
	 * 
	 * @param spinner
	 *            The JSpinner to alter
	 * @return The TextField of the JSpinner
	 */
	public JFormattedTextField getSpinnerTextField(JSpinner spinner) {
		JComponent editor = spinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			return ((JSpinner.DefaultEditor) editor).getTextField();
		} else {
			System.err.println("Unexpected editor type: "
					+ spinner.getEditor().getClass()
					+ " isn't a descendant of DefaultEditor");
			return null;
		}
	}
	
	
	//General methods
	/**
	 * Checks tmpCounter to check how many panels exist to determine the y
	 * position of the new instance
	 * 
	 * @param tmpCounter
	 *            Number of instances
	 * @return Y position of the new instance
	 */
	public int getPanelYpos(int tmpCounter) {
		if (tmpCounter == 0) {
			return 150;
		} else {
			return (150 + (100 * (tmpCounter)));
		}
	}

	

	

	// getters and setters
	public static int getPlayerCharacterCounter() {
		return playerCharacterCounter;
	}

}
