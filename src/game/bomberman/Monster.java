package game.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Enthaelt Informationen ueber Monster
 * 
 * @author timo
 * 
 */
public class Monster extends MoveObject implements Runnable {
	/**
	 * Speichert die Richtungen in die er sich zuletzt versucht hat zu bewegen
	 */
	private List<Integer> lastDir = new ArrayList<Integer>();
	private MyFrame mf;

	public Monster(MyFrame mf, int x, int y) {
		this.mf = mf;
		this.setX(x);
		this.setY(y);

	}

	/**
	 * erzeugt zufellige bewegung fuer Monster
	 */
	public void randomMove() {
		int oldX = this.getX();
		int oldY = this.getY();
		int direction = -2;
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			direction = new Random().nextInt(4);
			System.out.println("richtung: " + direction);
			System.out.println("letzte richtung: " + lastDir);
			/**
			 * Wenn die Richtung gleich der letzten Richtung ist: break
			 */
			boolean mark = false;
			for (int a : lastDir) {
				if (direction == a) {
					mark = true;
					break;
				}
			}
			/**
			 * Wenn keine Kollision mit Gegenstaenden vorhanden ist kann sich
			 * das Monster in diese Richtung bewegen
			 */
			if (!mark)
				break;
		}
		// System.out.println("Koordinaten" + this.getX() + "," + this.getY());
		mf.moveProcess(this, direction);
		mf.repaint();
		// System.out.println("移动后坐标：" + this.getX() + "," + this.getY());
		if (this.getX() == oldX && this.getY() == oldY) {
			// 上次碰到障碍物的方向
			lastDir.add(direction);

		} else {
			lastDir.clear();
		}
		if (lastDir.size() == 4)
			lastDir.clear();
	}

	@Override
	public void run() {
		/**
		 * Counter kontrolliert die Geschwindigkeit des Computers. Nach einer
		 * Sekunde bewegt sich das Monster
		 */
		int counter = 15;
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (counter < 10) {
				this.randomMove();
				counter = 20;
			}

			counter--;
		}
	}

}
