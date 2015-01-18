package temp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.AppMain;
import units.PlayerCharacter;
import utility.Utility;

public class InterfaceControlActionListener implements ActionListener {
	public static final String LOAD_PRESET_BUTTON = "ABC";
	
	private AppMain mainWindow;

	public InterfaceControlActionListener(AppMain mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals(LOAD_PRESET_BUTTON)) {
			performLoadPreset();
//		} else if(ae.getActionCommand().equals(SAVE_PRESET_BUTTON)) {
//			performSavePreset();
		}
	}

	/**
	 * Filter to restrict the type of files that can be chosen to .txt files
	 */
	private FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Text files", "txt");

	private void performLoadPreset() {
		JFileChooser fc = new JFileChooser();

		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(mainWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final ArrayList<PlayerCharacter> arrListTMP = Utility.getInstance().loadPreset(fc.getSelectedFile());

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
//					for (PlayerCharacter pc : arrList) {
//						box.remove(pc);
//						box.revalidate();
//						box.repaint();
//					}
//					arrList.clear();
//					for (PlayerCharacter pc : arrListTMP) {
//						arrList.add(pc);
//						box.add(pc);
//						box.revalidate();
//						box.repaint();
//					}
//					arrListTMP.clear();
//					repository.repositionPanels(arrList);
					mainWindow.resetCharacters();
					for (PlayerCharacter pc : arrListTMP) {
						mainWindow.addPlayerCharacter(pc);
					}
//					mainWindow.repositionPanels();
				}
			});
		} else {
			System.out.println("file not found");
		}

	}
	
	private void performAddPlayer() {
		PlayerCharacter pc = new PlayerCharacter(false);
		//...
		
		mainWindow.addPlayerCharacter(pc);
	}

}
