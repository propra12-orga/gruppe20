package game.bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Definiert die Events die passieren wenn verschiedene Buttons gedrueckt werden
 * 
 * @author timo
 * 
 */
public class ClickAction implements ActionListener {

	private MyFrame panel;
	private NewMyFrame newmf;
	private String savePath = "";

	/**
	 * 
	 * @param panel
	 * @param newmf
	 */
	public ClickAction(MyFrame panel, NewMyFrame newmf) {

		this.panel = panel;
		this.newmf = newmf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("save")) {
			try {
				if (!Config.startGame) {
					JOptionPane.showMessageDialog(panel,
							"please press F1 to start a new game£¡");
				} else {
					this.save();
					JOptionPane.showMessageDialog(panel, "save succesful");
					// wenn speichert dann set true
					Config.hasSave = true;

				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("load Game")) {
			Config.select = 1;
			newmf.setVisible(false);
			NewMyFrame newmf = new NewMyFrame();
			newmf.setLocationRelativeTo(null);
			newmf.setVisible(true);
			newmf.jPanel1.requestFocus();

		}
		// else if (e.getActionCommand().equals("new game")) {
		// Config.select = 0;
		// Temp.currentGrade = 0;
		// newmf.setVisible(false);
		// NewMyFrame newmf = new NewMyFrame();
		// newmf.setLocationRelativeTo(null);
		// newmf.setVisible(true);
		// newmf.jPanel1.requestFocus();
		// } else if (e.getActionCommand().equals("computer game")) {
		// newmf.setVisible(false);
		// Temp.currentGrade = 0;
		// NewMyFrame newmf = new NewMyFrame();
		// newmf.setLocationRelativeTo(null);
		// newmf.setVisible(true);
		// newmf.jPanel1.requestFocus();
		// // doublePlayer: set true£¬init player2
		// newmf.jPanel1.doublePlayer = true;
		// newmf.jPanel1.go();
		// newmf.jPanel1.getBb2().setComputer(true);
		// }

	}

	public void save() throws IOException {
		savePath = System.getProperty("user.dir") + "/bin/game/bomberman/ob/";
		System.out.println();
		File file = new File(savePath + "last.xml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		// speicher als XML Datei
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		fw.write("<config>\r\n");

		List<Obstruction> obs = panel.nowBG.getAllObstruction();
		// obstructions einlesen
		for (Obstruction ob : obs) {
			int x = ob.getX();
			int y = ob.getY();
			String s = "";

			if (ob.getType() == 1) {
				s = "<ob id=\"box\" x=\"" + x + "\" y=\"" + y + "\"/>";
			} else if (ob.getType() == 0) {
				s = "<ob id=\"stone\" x=\"" + x + "\" y=\"" + y + "\"/>";
			}
			fw.write(s + "\r\n");

		}
		// player Koordinaten einlesen
		int x1 = panel.getBb().getX();
		int y1 = panel.getBb().getY();

		/*
		 * //player 2 if (panel.getBb2() != null) { int x2 =
		 * panel.getBb2().getX(); int y2 = panel.getBb2().getY(); String bb2 =
		 * "<ob id=\"player2\" x=\"" + x2 + "\" y=\"" + y2 + "\"/>";
		 * fw.write(bb2 + "\r\n");
		 * 
		 * }
		 */
		// Player 1
		String bb1 = "<ob id=\"player1\" x=\"" + x1 + "\" y=\"" + y1 + "\"/>";
		fw.write(bb1 + "\r\n");

		// die Tuer Koordinaten einlesen
		int dx = Config.dx;
		int dy = Config.dy;
		int show;
		boolean b = Config.AusgangShow;
		if (b)
			show = 1;
		else
			show = 0;
		String m = "<ob id=\"door\" x=\"" + dx + "\" y=\"" + dy + "\" show=\""
				+ show + "\"/>";
		fw.write(m + "\r\n");
		fw.write("</config>\r\n");

		fw.flush();
		fw.close();
	}

}
