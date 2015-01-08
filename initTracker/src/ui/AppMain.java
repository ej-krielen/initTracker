package ui;

import static utility.EN_res.ADDMONSTER;
import static utility.EN_res.ADDPLAYER;
import static utility.EN_res.DEBUFFEXPIRED;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;

import units.PlayerCharacter;
import utility.Utility;

/**
 * User interface is built here.
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update January 8th 2015 Latest update of this file
 * @LatestUpdate Updated for new Layout functions so we can get a scrollbar
 * 
 */

@SuppressWarnings("serial")
public class AppMain extends JFrame {

	public static void main(String[] args) {
		new AppMain();
	}

	public static ArrayList<PlayerCharacter> arrList = new ArrayList<>();

	// panels where elements can be put in
	private static JPanel panel = new JPanel();
	private JPanel topPanel = new JPanel();
	private static JPanel contentPanel = new JPanel();
	// panel elements
	private JButton loadPresetButton = new JButton(LOADPRESET);
	private JButton savePresetButton = new JButton(SAVEPRESET);
	private JButton addPlayerButton = new JButton(ADDPLAYER);
	private JButton addMonsterButton = new JButton(ADDMONSTER);

	private JButton sortListButton = new JButton(SORTLIST);
	private JButton nextTurnButton = new JButton(NEXTTURN);

	JFileChooser fc = new JFileChooser();
	/**
	 * Filter to restrict the type of files that can be chosen to .txt files
	 */
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files",
			"txt");

	/**
	 * Constructor to make the interface
	 */
	AppMain() {
		// info about the app
		// title of the window
		super(WINDOWNAME);
		// width and height of the panel (inclusive title bar and borders)
		setSize(GUIWIDTH, GUIHEIGHT);
		//sets initial location on screen
		setLocation(250, 50);
		
		//Layout options
		//places contentPanel in a panel that automatically gets a scrollbar
		JScrollPane scrollPanel = new JScrollPane(contentPanel);
		//sets the layouts for the panels
		panel.setLayout(new BorderLayout());		
		panel.add(scrollPanel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// set width and height of each buttons
		Dimension buttonSize = new Dimension(BUTTONWIDTH, BUTTONHEIGHT);
		loadPresetButton.setPreferredSize(new Dimension(buttonSize));
		savePresetButton.setPreferredSize(new Dimension(buttonSize));
		addPlayerButton.setPreferredSize(new Dimension(buttonSize));
		addMonsterButton.setPreferredSize(new Dimension(buttonSize));
		
		sortListButton.setPreferredSize(new Dimension(buttonSize));
		nextTurnButton.setPreferredSize(new Dimension(buttonSize));


		// makes elements draggable
		// TODO
		// MouseListener listener = new DragMouseAdapter();
		// TODO add dynamically added panels to list of dragable objects
		/*
		 * firstDragButton.addMouseListener(listener);
		 * secondDragButton.addMouseListener(listener);
		 * 
		 * firstDragButton.setTransferHandler(new TransferHandler("text"));
		 * secondDragButton.setTransferHandler(new TransferHandler("text"));
		 */

		// add buttons to the topPanel layout
		topPanel.add(loadPresetButton);
		topPanel.add(savePresetButton);
		topPanel.add(addPlayerButton);
		topPanel.add(addMonsterButton);
		topPanel.add(sortListButton);
		topPanel.add(nextTurnButton);

		//add topPanel to panel
		panel.add(topPanel, BorderLayout.PAGE_START);
		
		// methods needed for default interface behavior
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		interfaceControls();

	}// end of constructor

	/**
	 * 
	 * Drag functionality
	 *
	 */
	class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
			// TODO make it a swap location drag and drop
		}
	}

	// Access the methods stored in controls.Utility
	private Utility repository = Utility.getInstance();

	/**
	 * Behavior of interface elements are defined here
	 */
	public void interfaceControls() {

		/**
		 * Opens a window to select a previously saved preset
		 */
		// Behavior of loadPresetButton
		loadPresetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(rootPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					final ArrayList<PlayerCharacter> arrListTMP = repository
							.loadPreset(fc.getSelectedFile());

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							for (PlayerCharacter pc : arrList) {
								contentPanel.remove(pc);
								contentPanel.revalidate();
								contentPanel.repaint();
							}
							arrList.clear();
							for (PlayerCharacter pc : arrListTMP) {
								arrList.add(pc);
								contentPanel.add(pc);
								contentPanel.revalidate();
								contentPanel.repaint();
							}
							arrListTMP.clear();
							repository.repositionPanels(arrList);

						}
					});
				} else {
					System.out.println("file not found");
				}

			}

		});
		// Behavior of loadPresetButton

		/**
		 * Opens a window to input name of preset and maybe a place to store it
		 */
		// Behavior of savePresetButton
		savePresetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int returnVal = fc.showSaveDialog(rootPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					//check if extension is .txt if not add .txt
					File file = fc.getSelectedFile();
					String filePath = file.getPath();
					if(!filePath.toLowerCase().endsWith(".txt"))
					{
					    file = new File(filePath + ".txt");
					}
					repository.savePreset(arrList, file);
				}				
			}

		});
		// Behavior of savePresetButton

		/**
		 * Sort the list of panels by value of their iniative (descending)
		 */
		// Behavior of sortListButton
		sortListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Collections.sort(arrList);
				repository.repositionPanels(arrList);
			}

		});
		// Behavior of sortListButton

		/**
		 * Rearrange panels. Highest becomes lowest. All others move up one
		 * place.
		 */
		// Behavior of nextTurnButton
		nextTurnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Collections.rotate(arrList.subList(0, arrList.size()), -1);
				repository.repositionPanels(arrList);
				if (repository.nextRound(arrList.get(0))) {
					JOptionPane.showMessageDialog(panel, DEBUFFEXPIRED);
				}
			}

		});
		// Behavior of nextTurnButton

		/**
		 * When user clicks on button, create a new panel
		 */
		// Behavior of addPlayerButton
		addPlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO extract to utility if possible
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						PlayerCharacter newPlayerCharacter = new PlayerCharacter(false);
						arrList.add(newPlayerCharacter);
						contentPanel.add(newPlayerCharacter);
						contentPanel.revalidate();
						contentPanel.repaint();
						repository.repositionPanels(arrList);

					}

				}); // Behavior of addPlayerButton
			}
		});
		
		/**
		 * When user clicks on button, create a new panel
		 */
		// Behavior of addMonsterButton
		addMonsterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO extract to utility if possible
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						PlayerCharacter newMonster = new PlayerCharacter(true);
						arrList.add(newMonster);
						contentPanel.add(newMonster);
						contentPanel.revalidate();
						contentPanel.repaint();
						repository.repositionPanels(arrList);

					}

				}); // Behavior of addMonsterButton
			}
		});
		

	}

	
	public static void removePanel(ActionListener actionListener) {
		
		
		for (final PlayerCharacter pc : arrList) {
			if (pc.getIsRemoveMe()) {
				Utility repository = Utility.getInstance();
				if (pc.getIsMonster()){
					repository.decreaseMonsterCounter();
				} else {
					repository.decreasePlayerCharacterCounter();
				}
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						
						contentPanel.remove(pc);
						arrList.remove(pc);
						contentPanel.revalidate();
						contentPanel.repaint();
						Utility repository = Utility.getInstance();						
						repository.repositionPanels(arrList);
					}

				});
			}
		}
	}

}
