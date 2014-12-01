package units;

import static utility.EN_res.DEBUFFSLABELTEXT;
import static utility.EN_res.HPLABELTEXT;
import static utility.EN_res.INITIATIVELABELTEXT;
import static utility.EN_res.NAMELABELTEXT;
import static utility.EN_res.NOTESINPUT;
import static utility.EN_res.PLAYERINPUT;
import static utility.EN_res.REMOVE;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import utility.Utility;

/**
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update December 1st 2014 Latest update of this file
 * @LatestUpdate Added method increaseDebuffs, and methods for sortList
 * 
 * */

@SuppressWarnings("serial")
public class PlayerCharacter extends JPanel implements Comparable<PlayerCharacter> {

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	// variables used

	// variables of the instance of the panel
	public static final int PANEL_X = 5;
	public static final int PANELWIDTH = 1150;
	public static final int PANELHEIGHT = 100;

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

	// panel elements only labels are initiated because their String value is
	// final
	JButton dragButton = new JButton("");
	JSpinner iniativeSpinner = null;
	JLabel iniativeLabel = new JLabel(INITIATIVELABELTEXT,
			SwingConstants.CENTER);
	JLabel nameLabel = new JLabel(NAMELABELTEXT);
	JTextArea nameArea = null;
	JTextArea notesArea = null;
	JSpinner hpSpinner = null;
	JLabel hpLabel = new JLabel(HPLABELTEXT, SwingConstants.CENTER);
	JSpinner debuffTopLeftSpinner = null;
	JSpinner debuffTopCenterSpinner = null;
	JSpinner debuffTopRightSpinner = null;
	JSpinner debuffBottomLeftSpinner = null;
	JSpinner debuffBottomCenterSpinner = null;
	JSpinner debuffBottomRightSpinner = null;
	JLabel debuffsLabel = new JLabel(DEBUFFSLABELTEXT, SwingConstants.CENTER);
	JButton removeButton = new JButton(REMOVE);

	// Spinnermodels to determine values (initial value, min, max, step
	// increase)
	SpinnerModel iniativeModel = new SpinnerNumberModel(0, -999, 999, 1);
	SpinnerModel hpModel = new SpinnerNumberModel(6, -999, 999, 1);
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
		panel_Y_pos = repository.getPanelYpos((Utility
				.getPlayerCharacterCounter()-1));

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
		nameArea = new JTextArea(PLAYERINPUT
				+ Utility.getPlayerCharacterCounter());
		notesArea = new JTextArea(NOTESINPUT);
		hpSpinner = new JSpinner(hpModel);
		debuffTopLeftSpinner = new JSpinner(debuffModelTL);
		debuffTopCenterSpinner = new JSpinner(debuffModelTC);
		debuffTopRightSpinner = new JSpinner(debuffModelTR);
		debuffBottomLeftSpinner = new JSpinner(debuffModelBL);
		debuffBottomCenterSpinner = new JSpinner(debuffModelBC);
		debuffBottomRightSpinner = new JSpinner(debuffModelBR);

		// set pos x, pos y, width and height of each element
		dragButton.setBounds(5, TOP_Y, 40, 90);
		iniativeSpinner.setBounds(60, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		iniativeLabel.setBounds(60, LABEL_Y, 60, LABELHEIGHT);
		nameLabel.setBounds(135, TOP_Y, 40, LABELHEIGHT);
		nameArea.setBounds(180, TOP_Y, 200, 30);
		notesArea.setBounds(180, 40, 350, 55);
		hpSpinner.setBounds(545, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		hpLabel.setBounds(545, LABEL_Y, 60, LABELHEIGHT);
		debuffTopLeftSpinner.setBounds(630, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffTopCenterSpinner.setBounds(695, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffTopRightSpinner.setBounds(770, TOP_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomLeftSpinner.setBounds(630, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomCenterSpinner.setBounds(695, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffBottomRightSpinner.setBounds(770, BOTTOM_DEBUFF, DEBUFFWIDTH,
				DEBUFFHEIGHT);
		debuffsLabel.setBounds(630, LABEL_Y, ((DEBUFFWIDTH * 3) + 5),
				LABELHEIGHT);
		removeButton.setBounds((PANELWIDTH - 175), 25, 150, 50);

		// edit spinners
		JFormattedTextField ftf = null;
		ftf = getSpinnerTextField(iniativeSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setFont(new Font("Verdana", Font.BOLD, 20));
		}
		ftf = getSpinnerTextField(hpSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setFont(new Font("Verdana", Font.BOLD, 20));
			ftf.setForeground(Color.RED);
		}
		ftf = getSpinnerTextField(debuffTopLeftSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
		}
		ftf = getSpinnerTextField(debuffTopCenterSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setBackground(Color.CYAN);
		}
		ftf = getSpinnerTextField(debuffTopRightSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setBackground(Color.BLACK);
			ftf.setForeground(Color.WHITE);
		}
		ftf = getSpinnerTextField(debuffBottomLeftSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setBackground(Color.ORANGE);
		}
		ftf = getSpinnerTextField(debuffBottomCenterSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setBackground(Color.MAGENTA);
		}
		ftf = getSpinnerTextField(debuffBottomRightSpinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.CENTER);
			ftf.setBackground(Color.RED);
		}

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
		this.add(removeButton);

	}// end of constructor

	/**
	 * Gets the textField (through the editor (from JComponent)) and centers the
	 * text in the spinner. Also sets the color depending on which spinner was
	 * entered.
	 * 
	 * @param spinner
	 *            The JSpinner to alter
	 * @return The TextField of the JSpinner
	 */
	private JFormattedTextField getSpinnerTextField(JSpinner spinner) {
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

	/**
	 * Increases all Debuffs that do not have a value of 0
	 * @return Returns true if anything was changed
	 */
	public boolean increaseDebuffs() {
		boolean b = false;
		if (getDebuffTopLeft() != 0) {
			setDebuffTopLeft((getDebuffTopLeft()) + 1);
			b = true;
		}
		if (getDebuffTopCenter() != 0) {
			setDebuffTopCenter((getDebuffTopCenter()) + 1);
			b = true;
		}
		if (getDebuffTopRight() != 0) {
			setDebuffTopRight((getDebuffTopRight()) + 1);
			b = true;
		}
		if (getDebuffBottomLeft() != 0) {
			setDebuffBottomLeft((getDebuffBottomLeft()) + 1);
			b = true;
		}
		if (getDebuffBottomCenter() != 0) {
			setDebuffBottomCenter((getDebuffBottomCenter()) + 1);
			b = true;
		}
		if (getDebuffBottomRight() != 0) {
			setDebuffBottomRight((getDebuffBottomRight()) + 1);
			b = true;
		}
		return b;
	}
	
	/**
	 * Used to sort the arrList from {@link AppMain} from highest to lowest
	 */
	@Override
	public int compareTo(PlayerCharacter pc) {
		Integer i1 = this.getIniative();
		Integer i2 = pc.getIniative();
		return i2.compareTo(i1);
		
	}
	
	/**
	 * Gets the new Y position based on the position in the arrayList, also sets the background color.
	 * @param listNumber position in arrayList
	 * @return new Y position for the panel
	 */
	public int newPosition(int listNumber){
		if (listNumber % 2 <= 0) {
			setBackground(Color.GRAY);
		} else {
			setBackground(Color.LIGHT_GRAY);
		}
		return repository.getPanelYpos(listNumber);
		
	}
	
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
