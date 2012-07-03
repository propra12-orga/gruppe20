package game.bomberman;

import game.bomberman.thing.Life;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * Global generiert Items und GameCount generiert Monster am Anfang Game wird
 * diese Thread gestartet
 * 
 * @author timo,Luo
 */
public class Global extends Thread {
	private static int counter = 0;
	private MyFrame panel;

	public Global(MyFrame panel) {
		this.panel = panel;
	}

	public static int getCounter() {
		return counter;
	}

	/**
	 * Gibt eine zufaellige Koordinate auf der Map zurueck an denen keine
	 * Objekte stehen
	 * 
	 * @return
	 */
	private int[] randomPoint() {

		List<Obstruction> obs = panel.nowBG.getAllObstruction();
		int result[] = { -1, -1 };
		int a = new Random().nextInt(13);
		int b = new Random().nextInt(13);
		int x = a * 48;
		int y = b * 48 + 20;
		if (x < 0 || x > 432 || y > 452 || y < 20)
			return result;
		else {
			boolean mark = false;
			for (Obstruction ob : obs) {
				if (ob.getX() == x && ob.getY() == y) {
					mark = true;
				}
			}
			if (Config.x1 == x && Config.y1 == y) {
				mark = true;
			}
			if (!mark) {
				result[0] = x;
				result[1] = y;
			}
			return result;
		}

	}

	/**
	 * erzeugt neue monster. Bis zu 5 auf einmal und bis zu 30 in einem Spiel.
	 */
	public void monsterProduce() {
		/**
		 * hoert auf Monster zu generieren wenn maximale Anzahl erreicht ist
		 */
		if (Config.monsterCounter >= Config.maxMonster)
			return;
		if (Config.existMonster < Config.allowexist) {
			int randomPoint[];
			while (true) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				randomPoint = this.randomPoint();
				if (randomPoint[0] > 0 && randomPoint[1] > 0) {
					break;

				}
			}
			int x = randomPoint[0];
			int y = randomPoint[1];
			Monster mon = new Monster(panel, x, y);

			Vector<Monster> vc = panel.getVc();
			vc.add(mon);
			Config.monsterCounter++;
			Config.existMonster++;
			new Thread(mon).start();

		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			/**
			 * hoert auf Monster zu generieren wenn schon 5 Monster auf dem
			 * Spielfeld sind
			 */
			if (Config.computerFight && counter > 2) {
				this.monsterProduce();

			}

			if (Config.exist < Config.max && Config.startGame && counter > 5
					&& !Config.netGame) {
				int ran = new Random().nextInt(3);

				int randomPoint[];
				while (true) {
					randomPoint = this.randomPoint();
					if (randomPoint[0] > 0 && randomPoint[1] > 0) {
						break;

					}
				}
				int x = randomPoint[0];
				int y = randomPoint[1];

				switch (ran) {
				case 0:

					// Config.bomb = new BombPoint(x, y);
					Config.life = new Life(x, y);

					Config.exist++;
					break;
				case 1:

					Config.life = new Life(x, y);
					Config.exist++;
					break;
				default:
					break;
				}

			}

		}

	}
}
