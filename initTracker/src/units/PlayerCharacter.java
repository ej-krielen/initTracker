package units;

import static utility.EN_res.DEBUFFSLABELTEXT;
import static utility.EN_res.HPLABELTEXT;
import static utility.EN_res.INITIATIVELABELTEXT;
import static utility.EN_res.MONSTERINPUT;
import static utility.EN_res.NAMELABELTEXT;
import static utility.EN_res.NOTESINPUT;
import static utility.EN_res.PLAYERINPUT;
import static utility.EN_res.REMOVE;
import static utility.FixedNumbers.BOTTOM_DEBUFF;
import static utility.FixedNumbers.DEBUFFHEIGHT;
import static utility.FixedNumbers.DEBUFFWIDTH;
import static utility.FixedNumbers.LABELHEIGHT;
import static utility.FixedNumbers.LABEL_Y;
import static utility.FixedNumbers.PANELHEIGHT;
import static utility.FixedNumbers.PANELWIDTH;
import static utility.FixedNumbers.STARTINGHP;
import static utility.FixedNumbers.TEXTAREABOX;
import static utility.FixedNumbers.TOP_DEBUFF;
import static utility.FixedNumbers.TOP_Y;
import static utility.FixedValues.BACKGROUND_MONSTER;
import static utility.FixedValues.BACKGROUND_PC;
import static utility.FixedValues.DEBUFF_SPINNER_MODEL;
import static utility.FixedValues.DEFAULT_SPINNER;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ui.AppMain;
import utility.Utility;

/**
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update January 19th 2015 Latest update of this file
 * @LatestUpdate Refactoring
 * 
 * */

@SuppressWarnings("serial")
public class PlayerCharacter extends JPanel implements
		Comparable<PlayerCharacter> {

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	// variables used
	private boolean isRemoveMe = false;
	private boolean isMonster = false;

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

	/**
	 * Constructor to make a new playerCharacter panel
	 */
	public PlayerCharacter(boolean b) {

		this.isMonster = b;
		

		//Configures based on whether or not the unit is a monster
		if (isMonster) {
			setBackground(BACKGROUND_MONSTER);
			Utility.increaseMonsterCounter();
			nameArea = new JTextArea(MONSTERINPUT
          + (Utility.getMonsterCounter() + 1));
			
		} else {
			setBackground(BACKGROUND_PC);
			Utility.increasePlayerCharacterCounter();
			nameArea = new JTextArea(PLAYERINPUT
          + (Utility.getPlayerCharacterCounter() +1 ));
		}

		// Set the size of the new instance (width, height)
		setMinimumSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		setMaximumSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		setSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		setLayout(null);
		// create a black border around the panel
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// initiate components with values
		iniativeSpinner = new JSpinner(DEFAULT_SPINNER);
		notesArea = new JTextArea(NOTESINPUT);
		hpSpinner = new JSpinner(DEFAULT_SPINNER);
		setHp(STARTINGHP);
		initializeDebuffSpinners();
		

		// set pos x, pos y, width and height of each element
		dragButton.setBounds(5, TOP_Y, 40, 90);
		iniativeSpinner.setBounds(60, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		iniativeLabel.setBounds(60, LABEL_Y, 60, LABELHEIGHT);
		nameLabel.setBounds(135, TOP_Y, 40, LABELHEIGHT);
		nameArea.setBounds(180, TOP_Y, 200, 30);
		notesArea.setBounds(180, 40, 350, 55);
		hpSpinner.setBounds(545, TOP_Y, TEXTAREABOX, TEXTAREABOX);
		hpLabel.setBounds(545, LABEL_Y, 60, LABELHEIGHT);
		removeButton.setBounds((PANELWIDTH - 175), 25, 150, 50);

		

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

		interfaceControls();

	}// end of constructor

	private void initializeDebuffSpinners() {
    //Set to use the debuffSpinnerModel
	  debuffTopLeftSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    debuffTopCenterSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    debuffTopRightSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    debuffBottomLeftSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    debuffBottomCenterSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    debuffBottomRightSpinner = new JSpinner(DEBUFF_SPINNER_MODEL);
    
 // set pos x, pos y, width and height of each element
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
    
  //edit spinners look-and-feel
    JFormattedTextField ftf = null;
    ftf = repository.getSpinnerTextField(iniativeSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setFont(new Font("Verdana", Font.BOLD, 20));
    }
    ftf = repository.getSpinnerTextField(hpSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setFont(new Font("Verdana", Font.BOLD, 20));
      ftf.setForeground(Color.RED);
    }
    ftf = repository.getSpinnerTextField(debuffTopLeftSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
    }
    ftf = repository.getSpinnerTextField(debuffTopCenterSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setBackground(Color.CYAN);
    }
    ftf = repository.getSpinnerTextField(debuffTopRightSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setBackground(Color.BLACK);
      ftf.setForeground(Color.WHITE);
    }
    ftf = repository.getSpinnerTextField(debuffBottomLeftSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setBackground(Color.ORANGE);
    }
    ftf = repository.getSpinnerTextField(debuffBottomCenterSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setBackground(Color.MAGENTA);
    }
    ftf = repository.getSpinnerTextField(debuffBottomRightSpinner);
    if (ftf != null) {
      ftf.setHorizontalAlignment(JTextField.CENTER);
      ftf.setBackground(Color.RED);
    }
    
    
  }

  /**
	 * 
	 */
	private void interfaceControls() {
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				isRemoveMe = true;
				AppMain.removePanel(this);
			}

		});

	}

	/**
	 * Decreases all Debuffs that do not have a value of 0 Then checks if a new
	 * value is 0 and gives that back in the form of the return
	 * 
	 * @return Returns true if a newly changed value is now 0
	 */
	public boolean updateDebuffs() {
		boolean isChangedToZero = false;
		if (getDebuffTopLeft() != 0) {
			setDebuffTopLeft((getDebuffTopLeft()) - 1);
			if (getDebuffTopLeft() == 0) {
				isChangedToZero = true;
			}
		}
		if (getDebuffTopCenter() != 0) {
			setDebuffTopCenter((getDebuffTopCenter()) - 1);
			if (getDebuffTopCenter() == 0) {
				isChangedToZero = true;
			}
		}
		if (getDebuffTopRight() != 0) {
			setDebuffTopRight((getDebuffTopRight()) - 1);
			if (getDebuffTopRight() == 0) {
				isChangedToZero = true;
			}
		}
		if (getDebuffBottomLeft() != 0) {
			setDebuffBottomLeft((getDebuffBottomLeft()) - 1);
			if (getDebuffBottomLeft() == 0) {
				isChangedToZero = true;
			}
		}
		if (getDebuffBottomCenter() != 0) {
			setDebuffBottomCenter((getDebuffBottomCenter()) - 1);
			if (getDebuffBottomCenter() == 0) {
				isChangedToZero = true;
			}
		}
		if (getDebuffBottomRight() != 0) {
			setDebuffBottomRight((getDebuffBottomRight()) - 1);
			if (getDebuffBottomRight() == 0) {
				isChangedToZero = true;
			}
		}
		return isChangedToZero;
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

	// getters and setters

	public boolean getIsMonster() {
		return isMonster;
	}

	public void setIsMonster(boolean isMonster) {
		this.isMonster = isMonster;
	}

	public boolean getIsRemoveMe() {
		return isRemoveMe;
	}

	public void setisRemoveMe(boolean isRemoveMe) {
		this.isRemoveMe = isRemoveMe;
	}

	// getters and setters !modified!
	public String getName() {
		return nameArea.getText();
	}

	public void setName(String name) {
			nameArea.setText(name);
	}

	public int getIniative() {
	  return (int) iniativeSpinner.getValue();
	}

	public void setIniative(int iniative) {
			iniativeSpinner.setValue(iniative);
	}

	public String getNotes() {
		return notesArea.getText();
	}

	public void setNotes(String notes) {
	  notesArea.setText(notes);
	}

	public int getHp() {
		return (int) hpSpinner.getValue();
	}

	public void setHp(int hp) {
			hpSpinner.setValue(hp);
	}

	public int getDebuffTopLeft() {
		return (int) debuffTopLeftSpinner.getValue();
	}

	public void setDebuffTopLeft(int debuffTopLeft) {
		debuffTopLeftSpinner.setValue(debuffTopLeft);
	}

	public int getDebuffTopCenter() {
		return (int) debuffTopCenterSpinner.getValue();
	}

	public void setDebuffTopCenter(int debuffTopCenter) {
			debuffTopCenterSpinner.setValue(debuffTopCenter);
	}

	public int getDebuffTopRight() {
		return (int) debuffTopRightSpinner.getValue();
	}

	public void setDebuffTopRight(int debuffTopRight) {
			debuffTopRightSpinner.setValue(debuffTopRight);
	}

	public int getDebuffBottomLeft() {
	  return (int) debuffBottomLeftSpinner.getValue();
	}

	public void setDebuffBottomLeft(int debuffBottomLeft) {
			debuffBottomLeftSpinner.setValue(debuffBottomLeft);
	}

	public int getDebuffBottomCenter() {
		return (int) debuffBottomCenterSpinner.getValue();
	}

	public void setDebuffBottomCenter(int debuffBottomCenter) {
		debuffBottomCenterSpinner.setValue(debuffBottomCenter);
	}

	public int getDebuffBottomRight() {
		return (int) debuffBottomRightSpinner.getValue();
	}

	public void setDebuffBottomRight(int debuffBottomRight) {
		debuffBottomRightSpinner.setValue(debuffBottomRight);
	}

}
