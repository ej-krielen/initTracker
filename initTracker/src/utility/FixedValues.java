package utility;

import java.awt.Color;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * All constant that are not numbers, that are not displayed in the GUI, are contained here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since January 7th 2015 Creation of this file
 * @update January 8th 2015 Latest update of this file
 * @LatestUpdate added startinghp
 * 
 */
public class FixedValues {

  // AppMain

  // Utility

  // PlayerCharacter
  public static final Color BACKGROUND_MONSTER = Color.GRAY;
  public static final Color BACKGROUND_PC = Color.LIGHT_GRAY;

  // Spinnermodels to determine values (initial value, min, max, step increase)
  public static final SpinnerNumberModel DEBUFF_SPINNER_MODEL = new SpinnerNumberModel(0, 0, 9999,
      1);
  public static final SpinnerModel DEFAULT_SPINNER = new SpinnerNumberModel(0, -999, 999, 1);

}
