package game.bomberman;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * SidePanel wird neben dem Spielfeld gezeichnet. Dort werden der aktuelle
 * Punktestand und der Highscore abgebildet
 * 
 * @author timo
 * 
 */
public class SidePanel extends JPanel implements Runnable {
	/**
	 * ueberschriebene paint Methode
	 * 
	 * @Override
	 */
	public void paint(Graphics g) {
		super.paint(g);
		String m = "Highscore£º";
		g.drawString(m, 20, 30);
		int max = Config.maxGrade;
		if (Temp.currentGrade > max)
			max = Temp.currentGrade;
		String maxGrade = "" + max;
		g.drawString(maxGrade, 40, 50);
		String str = "Score£º";
		g.drawString(str, 20, 150);
		String grade = "" + Temp.currentGrade;
		g.drawString(grade, 40, 180);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
