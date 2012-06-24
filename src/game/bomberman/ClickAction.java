package game.bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickAction implements ActionListener {

	private MyFrame panel;
	private NewMyFrame newMF;

	public ClickAction(MyFrame panel, NewMyFrame newMyFrame) {
		this.panel = panel;
		this.newMF = newMyFrame;
	}

	public ClickAction() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("new Game")) {

			newMF.setVisible(false);
			NewMyFrame newMF = new NewMyFrame();
			newMF.setVisible(true);
			newMF.jPanel1.requestFocus();

		}
	}

}
