package Game.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import Game.view.Control;
import Game.view.Puzzle;
import Game.view.main;

public class PuzzleAction implements ActionListener {

	private Puzzle pz;
	Control c;
	main M = new main();
	JColorChooser colorChoose;

	public PuzzleAction(Puzzle pz) {
		super();
		this.pz = pz;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();

		switch (cmd) {
		// case "Start":
		case "Reset":
			// pz.getC().StartGame();
			pz.c.ResetGame();
			break;
		case "OutGame":
			int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to Exit", "Exit",
					JOptionPane.YES_NO_OPTION);
			if (confirmed == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			break;
		case "NewGame":
			pz.c.NewGame();
			pz.c.StartGame();

			break;
		default:
			break;
		}
	}

}
