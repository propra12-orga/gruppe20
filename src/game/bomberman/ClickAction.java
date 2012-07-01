package game.bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
				this.save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("load game")) {
			Config.select = 1;
			newmf.setVisible(false);
			NewMyFrame newmf = new NewMyFrame();
			newmf.setLocationRelativeTo(null);
			newmf.setVisible(true);
			newmf.jPanel1.requestFocus();

		} else if (e.getActionCommand().equals("new game")) {
			Config.select = 0;
			newmf.setVisible(false);
			NewMyFrame newmf = new NewMyFrame();
			newmf.setLocationRelativeTo(null);
			newmf.setVisible(true);
			newmf.jPanel1.requestFocus();
		} else if (e.getActionCommand().equals("computer game")) {
			newmf.setVisible(false);
			NewMyFrame newmf = new NewMyFrame();
			newmf.setLocationRelativeTo(null);
			newmf.setVisible(true);
			newmf.jPanel1.requestFocus();
			// doublePlayer: set true£¬init player2
			newmf.jPanel1.doublePlayer = true;
			newmf.jPanel1.go();
			newmf.jPanel1.getBb2().setComputer(true);
		}

	}

	public void save() throws IOException {
		savePath = System.getProperty("user.dir") + "/bin/game/bomberman/ob/";
		System.out.println();
		File file = new File(savePath + "last.xml");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		// als xml speichern
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		fw.write("<config>\r\n");

		List<Obstruction> obs = panel.nowBG.getAllObstruction();
		for (Obstruction ob : obs) {
			int x = ob.getX();
			int y = ob.getY();
			String s = "";
			//
			if (ob.getType() == 1) {
				s = "<ob id=\"box\" x=\"" + x + "\" y=\"" + y + "\"/>";
			} else if (ob.getType() == 0) {
				s = "<ob id=\"stone\" x=\"" + x + "\" y=\"" + y + "\"/>";
			}
			fw.write(s + "\r\n");

		}

		fw.write("</config>\r\n");

		fw.flush();
		fw.close();
	}

}
