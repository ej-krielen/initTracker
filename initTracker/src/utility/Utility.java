package utility;

import static units.PlayerCharacter.PANELHEIGHT;
import static units.PlayerCharacter.PANELWIDTH;
import static units.PlayerCharacter.PANEL_X;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
 * @update December 5th 2014 Latest update of this file
 * @LatestUpdate Added methods savePreset, createNewPresetFile, and getPresetFile
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
	
	private static final String BASE_DIRECTORY = "E:/peon/java workspaces/pathfinder";
	
	/**
	 * Keeps track how many presets have been saved
	 */
	private static int presetCounter;

	/**
	 * Counter used to give each {@link PlayerCharacter} an unique ID
	 * Starts at -1 so first added will be 0 which lines up with the ArrayList
	 */
	private static int playerCharacterCounter = -1;

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
	public void repositionPanels(ArrayList<PlayerCharacter> arrList) {
		for (PlayerCharacter pc : arrList) {
			int newListPosition = arrList.indexOf(pc);
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
	 * Saves all PlayerCharacters from arrList into a text file. Creating a new file is handled by createNewPresetFile
	 * @param arrList
	 */
	public void savePreset(ArrayList<PlayerCharacter> arrList) {
		File presetFile = createNewPresetFile();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(presetFile))) {
			for (PlayerCharacter pc : arrList){
			
				bw.write("" + pc.getIniative());
				bw.write(", ");
				bw.write("" + pc.getName());
				bw.write(", ");
				bw.write("" + pc.getNotes());
				bw.write(", ");
				bw.write("" + pc.getHp());
				bw.write(", ");
				bw.write("" + pc.getDebuffTopLeft());
				bw.write(", ");
				bw.write("" + pc.getDebuffTopCenter());
				bw.write(", ");
				bw.write("" + pc.getDebuffTopRight());
				bw.write(", ");
				bw.write("" + pc.getDebuffBottomLeft());
				bw.write(", ");
				bw.write("" + pc.getDebuffBottomCenter());
				bw.write(", ");
				bw.write("" + pc.getDebuffBottomRight());
				bw.newLine();
				
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Checks all files in directory so it doesn't overwrite previous files
	 * @return A new file created by getPresetFile
	 */
	private File createNewPresetFile() {
		File presetFile = getPresetFile(presetCounter);
		while (presetFile.exists()) {
			presetCounter++;
			presetFile = getPresetFile(presetCounter);
		}

		return presetFile;
	}
	
	/**
	 * Method used by createNewPresetFile
	 * @param presetcounter
	 * @return File named preset + presetCounter + .txt
	 */
	private static File getPresetFile(int presetcounter) {
		return new File(BASE_DIRECTORY, "preset"+ presetCounter + ".txt");
	}

	/**
	 * Runs updateDebuff on the PlayerCharacter. If a value reaches 0 in that method it will return as true.
	 * 
	 * @return Returns true or false, true if a value was changed to 0 and a message needs to be displayed
	 */
	public boolean nextRound(PlayerCharacter pc) {
		boolean b = false;
			if (pc.updateDebuffs()) {
				b = true;
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
