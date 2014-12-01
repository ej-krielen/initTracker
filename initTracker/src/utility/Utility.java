package utility;

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
	
	/**
	 * Checks tmpCounter to check how many panels exist to determine the y position of the new instance
	 * @param tmpCounter Number of instances
	 * @return Y position of the new instance
	 */
	public int getPanelYpos(int tmpCounter) {
		if (tmpCounter == 0) {
			return 150;
		} else {
			return (150 + (100 * (tmpCounter)));
		}
	}
	
		
	//getters and setters
	public static int getPlayerCharacterCounter() {
		return playerCharacterCounter;
	}


}
