package ui;

import static utility.EN_res.ADDMONSTER;
import static utility.EN_res.ADDPLAYER;
import static utility.EN_res.DEBUFFEXPIRED;
import static utility.EN_res.FILEERROR;
import static utility.EN_res.FILENOTFOUND;
import static utility.EN_res.LOADPRESET;
import static utility.EN_res.NEXTTURN;
import static utility.EN_res.SAVEPRESET;
import static utility.EN_res.SORTLIST;
import static utility.EN_res.WINDOWNAME;
import static utility.FixedNumbers.BUTTONHEIGHT;
import static utility.FixedNumbers.BUTTONWIDTH;
import static utility.FixedNumbers.GUIHEIGHT;
import static utility.FixedNumbers.GUIWIDTH;
import static utility.FixedNumbers.HGAP;
import static utility.FixedNumbers.VGAP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import units.PlayerCharacter;
import utility.DragHandler;
import utility.Utility;

/**
 * User interface is built here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update January 19th 2015 Latest update of this file
 * @LatestUpdate Refactoring
 * 
 */

@SuppressWarnings("serial")
public class AppMain extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new AppMain();
	}

	public static ArrayList<PlayerCharacter> arrList = new ArrayList<>();

	// panels where elements can be put in
	private static JPanel panel = new JPanel();
	private JPanel topPanel = new JPanel();
	private static JPanel contentPanel = new JPanel();
	private static Box box = Box.createVerticalBox();
	// panel elements
	private JButton loadPresetButton = new JButton(LOADPRESET);
	private JButton savePresetButton = new JButton(SAVEPRESET);
	private JButton addPlayerButton = new JButton(ADDPLAYER);
	private JButton addMonsterButton = new JButton(ADDMONSTER);

	private JButton sortListButton = new JButton(SORTLIST);
	private JButton nextTurnButton = new JButton(NEXTTURN);

	/**
	 * Constructor to make the interface
	 */
	AppMain() {
		// info about the app
		// title of the window
		super(WINDOWNAME);
		// width and height of the panel (inclusive title bar and borders)
		setSize(GUIWIDTH, GUIHEIGHT);
		// sets initial location on screen
		setLocation(250, 50);

		// Layout options
		// places contentPanel in a panel that automatically gets a scrollbar
		JScrollPane scrollPanel = new JScrollPane(contentPanel);
		// sets the layouts for the panels
		panel.setLayout(new BorderLayout());
		panel.add(scrollPanel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// make items drag-able
		// TODO refine size issues (panel gets dragged to the left when you
		// start dragging)
		// TODO make it change arrList
		// TODO make it drag only on PlayerCharacter.dragButton
		// TODO fix bug where if you drop a compenent between 2nd and 3rd that
		// it ends up top of the list
		DragHandler dh = new DragHandler();
		box.addMouseListener(dh);
		box.addMouseMotionListener(dh);
		contentPanel.add(box, BorderLayout.NORTH);

		// set width and height of each buttons
		Dimension buttonSize = new Dimension(BUTTONWIDTH, BUTTONHEIGHT);
		loadPresetButton.setPreferredSize(new Dimension(buttonSize));
		savePresetButton.setPreferredSize(new Dimension(buttonSize));
		addPlayerButton.setPreferredSize(new Dimension(buttonSize));
		addMonsterButton.setPreferredSize(new Dimension(buttonSize));

		sortListButton.setPreferredSize(new Dimension(buttonSize));
		nextTurnButton.setPreferredSize(new Dimension(buttonSize));

		// add buttons to the topPanel layout
		topPanel.add(loadPresetButton);
		topPanel.add(savePresetButton);
		topPanel.add(addPlayerButton);
		topPanel.add(addMonsterButton);
		topPanel.add(sortListButton);
		topPanel.add(nextTurnButton);

		// add topPanel to panel
		panel.add(topPanel, BorderLayout.PAGE_START);

		// methods needed for default interface behavior
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		interfaceControls();

	}// end of constructor

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	/**
	 * Behavior of interface elements are defined here
	 */
	public void interfaceControls() {

		loadPresetButton.addActionListener(this);
		savePresetButton.addActionListener(this);
		addPlayerButton.addActionListener(this);
		addMonsterButton.addActionListener(this);
		sortListButton.addActionListener(this);
		nextTurnButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == loadPresetButton) {
			loadPreset();
		} else if (ae.getSource() == savePresetButton) {
			savePreset();
		} else if (ae.getSource() == addPlayerButton) {
			createUnit(false);
		} else if (ae.getSource() == addMonsterButton) {
			createUnit(true);
		} else if (ae.getSource() == sortListButton) {
			sortList();
		} else if (ae.getSource() == nextTurnButton) {
			nextTurn();
		}

	}

	/**
	 * Opens a window to select a previously saved preset
	 */
	private void loadPreset() {

		JFileChooser fc = new JFileChooser();
		// Filter to restrict the type of files that can be chosen to .txt files
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text files", "txt");
		fc.setFileFilter(filter);
		if (fc.showOpenDialog(rootPane) == JFileChooser.APPROVE_OPTION) {
			final ArrayList<PlayerCharacter> arrListTMP = repository
					.loadPreset(fc.getSelectedFile());

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					for (PlayerCharacter pc : arrList) {
						removeUnit(pc);
					}
					arrList.clear();
					for (PlayerCharacter pc : arrListTMP) {
						arrList.add(pc);
						addPlayerCharacter(pc);
					}
					arrListTMP.clear();
					repository.repositionPanels(arrList);
					updateBox();
				}

			});
		} else {
			JOptionPane.showMessageDialog(panel, FILENOTFOUND, FILEERROR,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Removes a unit while updating the monster/player counter from
	 * {@link utility.Utility}
	 * 
	 * @param pc
	 *            PlayerCharacter to remove
	 */
	private void removeUnit(PlayerCharacter pc) {
		if (pc.getIsMonster()) {
			repository.decreaseMonsterCounter();
		} else if (!pc.getIsMonster()) {
			repository.decreasePlayerCharacterCounter();
		}
		box.remove(pc);
		updateBox();
	}

	/**
	 * Opens a file dialog window to input name of preset and where to store it
	 */
	private void savePreset() {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(rootPane) == JFileChooser.APPROVE_OPTION) {
			// check if extension is .txt if not add .txt
			File file = fc.getSelectedFile();
			String filePath = file.getPath();
			if (!filePath.toLowerCase().endsWith(".txt")) {
				file = new File(filePath + ".txt");
			}
			repository.savePreset(arrList, file);
		}
	}

	/**
	 * Creates a new PlayerCharacter object and adds it to the GUI through
	 * {@link addUnit}
	 */
	private void createUnit(final boolean isMonster) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				PlayerCharacter newUnit = new PlayerCharacter(isMonster);
				addUnit(newUnit);
				repository.repositionPanels(arrList);
			}
		});
	}

	/**
	 * Adds a PlayerCharacter to the GUI
	 */
	private void addUnit(PlayerCharacter newUnit) {
		arrList.add(newUnit);
		box.add(newUnit);
		updateBox();
	}

	/**
	 * Runs revalidate and repaint on AppMain.box
	 */
	private void updateBox() {
		box.revalidate();
		box.repaint();
	}

	/**
	 * Sort the list of panels by value of their iniative (descending)
	 */
	private void sortList() {
		Collections.sort(arrList);
		repository.repositionPanels(arrList);
	}

	/**
	 * Rearrange panels. Highest becomes lowest. All others move up one place.
	 */
	private void nextTurn() {
		Collections.rotate(arrList.subList(0, arrList.size()), -1);
		repository.repositionPanels(arrList);
		if (repository.updateDebuffs(arrList.get(0))) {
			JOptionPane.showMessageDialog(panel, DEBUFFEXPIRED);
		}
	}

	/**
	 * Adds a PlayerCharacter to the arrList and GUI
	 * 
	 * @param pc
	 *            PlayerCharacter to add to the arrList and GUI
	 */
	public void addPlayerCharacter(PlayerCharacter pc) {
		arrList.add(pc);
		box.add(pc);
		updateBox();
	}

	/**
	 * Removes a PlayerCharacter from the GUI and arrList, while updating the
	 * monster/player counter when the remove button is pressed
	 * 
	 * @param actionListener
	 *            Listens to PlayerCharacter.removeButton
	 */
	public static void removePanel(ActionListener actionListener) {

		for (final PlayerCharacter pc : arrList) {
			if (pc.getIsRemoveMe()) {
				Utility repository = Utility.getInstance();
				if (pc.getIsMonster()) {
					repository.decreaseMonsterCounter();
				} else {
					repository.decreasePlayerCharacterCounter();
				}
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						box.remove(pc);
						arrList.remove(pc);
						box.revalidate();
						box.repaint();
						Utility repository = Utility.getInstance();
						repository.repositionPanels(arrList);
					}

				});
			}
		}
	}

}
