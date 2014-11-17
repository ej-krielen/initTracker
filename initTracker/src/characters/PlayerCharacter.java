package characters;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controls.Utility;

/**
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update November 17th 2014 Latest update of this file
 * @LatestUpdate Adjusted getters and setters for Swing
 * 
 * */

@SuppressWarnings("serial")
public class PlayerCharacter extends JPanel {

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	// variables used

	// variables of the instance of the panel
	private static final int PANEL_X = 5;
	private static final int PANELWIDTH = 1150;
	private static final int PANELHEIGHT = 100;

	// variables used to position elements inside the panel
	private static final int TOP_Y = 5;
	private static final int LABEL_Y = 80;
	private static final int LABELHEIGHT = 15;
	private static final int TEXTAREABOX = 60;
	private static final int TOP_DEBUFF = 5;
	private static final int BOTTOM_DEBUFF = 45;
	private static final int DEBUFFWIDTH = 50;
	private static final int DEBUFFHEIGHT = 35;

	private String name;
	private int iniative;
	private String notes;
	private int hp;
	private int debuffTopLeft;
	private int debuffTopCenter;
	private int debuffTopRight;
	private int debuffBottomLeft;
	private int debuffBottomCenter;
	private int debuffBottomRight;

	// variable used to determine the y pos of the instance of this panel
	private int panel_Y_pos;

	// Strings of the labels
	private static final String INITIATIVELABELTEXT = "INIT";
	private static final String NAMELABELTEXT = "Name: ";
	private static final String HPLABELTEXT = "HP";
	private static final String DEBUFFSLABELTEXT = "(DE)BUFFS (active duration)";

	// panel elements only labels are initiated because their String value is
	// final
	JButton dragButton = new JButton("");
	JSpinner iniativeSpinner = null;
	JLabel iniativeLabel = new JLabel(INITIATIVELABELTEXT);
	JLabel nameLabel = new JLabel(NAMELABELTEXT);
	JTextArea nameArea = null;
	JTextArea notesArea = null;
	JSpinner hpSpinner = null;
	JLabel hpLabel = new JLabel(HPLABELTEXT);
	JSpinner debuffTopLeftSpinner = null;
	JSpinner debuffTopCenterSpinner = null;
	JSpinner debuffTopRightSpinner = null;
	JSpinner debuffBottomLeftSpinner = null;
	JSpinner debuffBottomCenterSpinner = null;
	JSpinner debuffBottomRightSpinner = null;
	JLabel debuffsLabel = new JLabel(DEBUFFSLABELTEXT);

	// Spinnermodels to determine values (initial value, min, max, step
	// increase)
	SpinnerModel iniativeModel = new SpinnerNumberModel(0, 0, 999, 1);
	SpinnerModel hpModel = new SpinnerNumberModel(6, 0, 9999, 1);
	SpinnerModel debuffModelTL = new SpinnerNumberModel(0, 0, 9999, 1);
	SpinnerModel debuffModelTC = new SpinnerNumberModel(0, 0, 9999, 1);
	SpinnerModel debuffModelTR = new SpinnerNumberModel(0, 0, 9999, 1);
	SpinnerModel debuffModelBL = new SpinnerNumberModel(0, 0, 9999, 1);
	SpinnerModel debuffModelBC = new SpinnerNumberModel(0, 0, 9999, 1);
	SpinnerModel debuffModelBR = new SpinnerNumberModel(0, 0, 9999, 1);

	/**
	 * Constructor to make a new playerCharacter panel
	 */
	public PlayerCharacter() {

		Utility.increasePlayerCharacterCounter();
		panel_Y_pos = repository.getPanelYpos(Utility
				.getPlayerCharacterCounter());

		// Alternates background color of the created instances between gray and
		// light gray
		if (Utility.getPlayerCharacterCounter() % 2 <= 0) {
			setBackground(Color.GRAY);
		} else {
			setBackground(Color.LIGHT_GRAY);
		}

		// Set the bounds of the new instance (x pos, y pos, width, height)
		setBounds(PANEL_X, panel_Y_pos, PANELWIDTH, PANELHEIGHT);
		setLayout(null);

		// initiate components with values
		iniativeSpinner = new JSpinner(iniativeModel);
		nameArea = new JTextArea("Player "
				+ Utility.getPlayerCharacterCounter());
		notesArea = new JTextArea("Notes: ");
		hpSpinner = new JSpinner(hpModel);
		debuffTopLeftSpinner = new JSpinner(debuffModelTL);
		debuffTopCenterSpinner = new JSpinner(debuffModelTC);
		debuffTopRightSpinner = new JSpinner(debuffModelTR);
		debuffBottomLeftSpinner = new JSpinner(debuffModelBL);
		debuffBottomCenterSpinner = new JSpinner(debuffModelBC);
		debuffBottomRightSpinner = new JSpinner(debuffModelBR);

		// set pos x, pos y, width and height of each element
		dragButton.setBounds(5, TOP_Y, 40, 90);
		iniativeSpinner.setBounds(50, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		iniativeLabel.setBounds(50, LABEL_Y, 60, LABELHEIGHT);
		nameLabel.setBounds(115, TOP_Y, 40, LABELHEIGHT);
		nameArea.setBounds(160, TOP_Y, 200, 30);
		notesArea.setBounds(160, 40, 350, 55);
		hpSpinner.setBounds(515, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		hpLabel.setBounds(515, LABEL_Y, 60, LABELHEIGHT);
		debuffTopLeftSpinner.setBounds(580, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffTopCenterSpinner.setBounds(645, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffTopRightSpinner.setBounds(710, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomLeftSpinner.setBounds(580, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomCenterSpinner.setBounds(645, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomRightSpinner.setBounds(710, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffsLabel.setBounds(580, LABEL_Y, ((DEBUFFWIDTH * 3) + 5),
				LABELHEIGHT);

		// add elementals to the instance panel
		this.add(dragButton);
		this.add(iniativeSpinner);
		this.add(iniativeLabel);
		this.add(nameLabel);
		this.add(nameArea);
		this.add(notesArea);
		this.add(hpSpinner);
		this.add(hpLabel);
		this.add(debuffTopLeftSpinner);
		this.add(debuffTopCenterSpinner);
		this.add(debuffTopRightSpinner);
		this.add(debuffBottomLeftSpinner);
		this.add(debuffBottomCenterSpinner);
		this.add(debuffBottomRightSpinner);
		this.add(debuffsLabel);

	}//end of constructor

	// getters and setters !modified!
	// getters first check swing component
	// setters also set the component to new value if not the same as current
	// value
	public String getName() {
		setName(nameArea.getText());
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (!nameArea.getText().equals(name)) {
			nameArea.setText(name);
		}
	}

	public int getIniative() {
		setIniative((int) iniativeSpinner.getValue());
		return iniative;
	}

	public void setIniative(int iniative) {
		this.iniative = iniative;
		if ((int) iniativeSpinner.getValue() != iniative) {
			iniativeSpinner.setValue(iniative);
		}
	}

	public String getNotes() {
		setNotes(notesArea.getText());
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
		if (!notesArea.getText().equals(notes)) {
			notesArea.setText(notes);
		}
	}

	public int getHp() {
		setHp((int) hpSpinner.getValue());
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		if ((int) hpSpinner.getValue() != hp) {
			hpSpinner.setValue(hp);
		}
	}

	public int getDebuffTopLeft() {
		setDebuffTopLeft((int) debuffTopLeftSpinner.getValue());
		return debuffTopLeft;
	}

	public void setDebuffTopLeft(int debuffTopLeft) {
		this.debuffTopLeft = debuffTopLeft;
		if ((int) debuffTopLeftSpinner.getValue() != debuffTopLeft) {
			debuffTopLeftSpinner.setValue(debuffTopLeft);
		}
	}

	public int getDebuffTopCenter() {
		setDebuffTopCenter((int) debuffTopCenterSpinner.getValue());
		return debuffTopCenter;
	}

	public void setDebuffTopCenter(int debuffTopCenter) {
		this.debuffTopCenter = debuffTopCenter;
		if ((int) debuffTopCenterSpinner.getValue() != debuffTopCenter) {
			debuffTopCenterSpinner.setValue(debuffTopCenter);
		}
	}

	public int getDebuffTopRight() {
		setDebuffTopRight((int) debuffTopRightSpinner.getValue());
		return debuffTopRight;
	}

	public void setDebuffTopRight(int debuffTopRight) {
		this.debuffTopRight = debuffTopRight;
		if ((int) debuffTopRightSpinner.getValue() != debuffTopRight) {
			debuffTopRightSpinner.setValue(debuffTopRight);
		}
	}

	public int getDebuffBottomLeft() {
		setDebuffBottomLeft((int) debuffBottomLeftSpinner.getValue());
		return debuffBottomLeft;
	}

	public void setDebuffBottomLeft(int debuffBottomLeft) {
		this.debuffBottomLeft = debuffBottomLeft;
		if ((int) debuffBottomLeftSpinner.getValue() != debuffBottomLeft) {
			debuffBottomLeftSpinner.setValue(debuffBottomLeft);
		}
	}

	public int getDebuffBottomCenter() {
		setDebuffBottomCenter((int) debuffBottomCenterSpinner.getValue());
		return debuffBottomCenter;
	}

	public void setDebuffBottomCenter(int debuffBottomCenter) {
		this.debuffBottomCenter = debuffBottomCenter;
		if ((int) debuffBottomCenterSpinner.getValue() != debuffBottomCenter) {
			debuffBottomCenterSpinner.setValue(debuffBottomCenter);
		}
	}

	public int getDebuffBottomRight() {
		setDebuffBottomRight((int) debuffBottomRightSpinner.getValue());
		return debuffBottomRight;
	}

	public void setDebuffBottomRight(int debuffBottomRight) {
		this.debuffBottomRight = debuffBottomRight;
		if ((int) debuffBottomRightSpinner.getValue() != debuffBottomRight) {
			debuffBottomRightSpinner.setValue(debuffBottomRight);
		}
	}

}
