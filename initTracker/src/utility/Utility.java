package utility;

import static utility.FixedNumbers.PANELHEIGHT;
import static utility.FixedNumbers.PANELWIDTH;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
 * @update January 9th 2015 Latest update of this file
 * @LatestUpdate Updated for new Layout, fixed aligment bug
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

	private static ArrayList<PlayerCharacter> arrListTMP = new ArrayList<>();

	/**
	 * Counter used to give each {@link PlayerCharacter} an unique ID Starts at
	 * -1 so first added will be 0 which lines up with the ArrayList
	 */
	private static int playerCharacterCounter = -1;

	/**
	 * Increases the playerCharacterCounter by 1 when a new panel is created
	 */
	public static void increasePlayerCharacterCounter() {
		playerCharacterCounter++;
	}

	/**
	 * Decreases the playerCharacterCounter by 1 when a new panel is removed
	 */
	public void decreasePlayerCharacterCounter() {
		playerCharacterCounter--;
	}
	
	//Same for monsters
	private static int monsterCounter = -1;
	public static void increaseMonsterCounter() {
		monsterCounter++;
	}
	public void decreaseMonsterCounter() {
		monsterCounter--;
	}
	
	// AppMain exclusive methods
	/**
	 * Goes through the arrList from AppMain. Sets position of panels based on
	 * position in the arrList.
	 */
	public void repositionPanels(ArrayList<PlayerCharacter> arrList) {
		for (PlayerCharacter pc : arrList) {
			pc.setBounds(15, getPanelYpos(arrList.indexOf(pc)),
					PANELWIDTH, PANELHEIGHT);
		}
	}

	/**
	 * Saves all PlayerCharacters from arrList into a text file.
	 * 
	 * @param arrList
	 * @param presetFile
	 */
	public void savePreset(ArrayList<PlayerCharacter> arrList, File presetFile) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(presetFile))) {
			for (PlayerCharacter pc : arrList) {

				bw.write("" + pc.getIniative());
				bw.write(",");
				bw.write("" + pc.getName());
				bw.write(",");
				bw.write("" + pc.getNotes());
				bw.write(",");
				bw.write("" + pc.getHp());
				bw.write(",");
				bw.write("" + pc.getDebuffTopLeft());
				bw.write(",");
				bw.write("" + pc.getDebuffTopCenter());
				bw.write(",");
				bw.write("" + pc.getDebuffTopRight());
				bw.write(",");
				bw.write("" + pc.getDebuffBottomLeft());
				bw.write(",");
				bw.write("" + pc.getDebuffBottomCenter());
				bw.write(",");
				bw.write("" + pc.getDebuffBottomRight());
				bw.write(",");
				bw.write("" + pc.getIsMonster());
				bw.newLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reads the file, each line becomes a new PlayerCharacter, each line
	 * contains all needed attributes
	 * 
	 * @param file
	 *            Parameter to determine which file needs to be read
	 * @return ArrayList of PlayerCharacters to be created in AppMain
	 */
	public ArrayList<PlayerCharacter> loadPreset(File presetFile) {
		// Find file
		if (presetFile == null || !presetFile.exists()) {
			System.out.println("File not found");
		} else {
			FileReader fr;
			try {
				fr = new FileReader(presetFile);
				BufferedReader br = new BufferedReader(fr);

				for (String line = br.readLine(); line != null; line = br
						.readLine()) {

					String iniative;
					String name;
					String notes;
					String hp;
					String debuffTopLeft;
					String debuffTopCenter;
					String debuffTopRight;
					String debuffBottomLeft;
					String debuffBottomCenter;
					String debuffBottomRight;
					String isMonster;

					StringTokenizer stringTokenizer = new StringTokenizer(line,
							",");
					iniative = stringTokenizer.nextToken();
					name = stringTokenizer.nextToken();
					notes = stringTokenizer.nextToken();
					hp = stringTokenizer.nextToken();
					debuffTopLeft = stringTokenizer.nextToken();
					debuffTopCenter = stringTokenizer.nextToken();
					debuffTopRight = stringTokenizer.nextToken();
					debuffBottomLeft = stringTokenizer.nextToken();
					debuffBottomCenter = stringTokenizer.nextToken();
					debuffBottomRight = stringTokenizer.nextToken();
					isMonster = stringTokenizer.nextToken();

					PlayerCharacter newPlayerCharacter = new PlayerCharacter(
							Boolean.parseBoolean(isMonster));
					newPlayerCharacter.setIniative(Integer.parseInt(iniative));
					newPlayerCharacter.setName(name);
					newPlayerCharacter.setNotes(notes);
					newPlayerCharacter.setHp(Integer.parseInt(hp));
					newPlayerCharacter.setDebuffTopLeft(Integer
							.parseInt(debuffTopLeft));
					newPlayerCharacter.setDebuffTopCenter(Integer
							.parseInt(debuffTopCenter));
					newPlayerCharacter.setDebuffTopRight(Integer
							.parseInt(debuffTopRight));
					newPlayerCharacter.setDebuffBottomLeft(Integer
							.parseInt(debuffBottomLeft));
					newPlayerCharacter.setDebuffBottomCenter(Integer
							.parseInt(debuffBottomCenter));
					newPlayerCharacter.setDebuffBottomRight(Integer
							.parseInt(debuffBottomRight));

					arrListTMP.add(newPlayerCharacter);

				}

				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arrListTMP;
	}

	/**
	 * Runs updateDebuff on the PlayerCharacter. If a value reaches 0 in that
	 * method it will return as true.
	 * 
	 * @return Returns true or false, true if a value was changed to 0 and a
	 *         message needs to be displayed
	 */
	public boolean nextRound(PlayerCharacter pc) {
		boolean b = false;
		if (pc.updateDebuffs()) {
			b = true;
		}
		return b;
	}

	// PlayerCharacter exclusive methods

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

	// General methods
	/**
	 * Checks tmpCounter to check how many panels exist to determine the y
	 * position of the new instance
	 * 
	 * @param tmpCounter
	 *            Number of instances
	 * @return Y position of the new instance
	 */
	public int getPanelYpos(int tmpCounter) {
		return (PANELHEIGHT * tmpCounter);
	}

	// getters and setters
	public static int getPlayerCharacterCounter() {
		return playerCharacterCounter;
	}
	public static int getMonsterCounter() {
		return monsterCounter;
	}

}
