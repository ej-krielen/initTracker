package controls;

import characters.PlayerCharacter;

/**
 * 
 * This is were all user actions are handled
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update November 13nd 2014 Latest update of this file
 * @LatestUpdate Added static instance, tested if methods can be accesed from
 *               AppMain succesfully
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

	public void addPlayerCharacter() {
		String test = new PlayerCharacter().getName();
		System.out.println(test);

	}

}
