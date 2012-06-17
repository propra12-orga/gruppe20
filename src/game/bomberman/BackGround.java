package game.bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Background enthält Informationen ueber das Spielfeld wie Koordinaten der
 * Steine und Boxen, Computergegner
 * 
 * @author timozjx
 * 
 */
public class BackGround {

	private BufferedImage bgImage = null;
	// sort: welche Senze
	private int sort;
	// flag: ob diese Senze das Finall
	private boolean flag;

	/**
	 * Speichert alle Gegner
	 */
	private List<Enemy> allEnemy = new ArrayList<Enemy>();

	/**
	 * Speichert alle Gegenstaende
	 */
	private List<Obstruction> allObstruction = new ArrayList<Obstruction>();

	/**
	 * 
	 * Get all obstruction
	 */
	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}

	/**
	 * Speichert alle vernichteten Gegner
	 */
	private List<Enemy> removedEnemy = new ArrayList<Enemy>();

	/**
	 * Speichert alle zerstoerten Gegenstaende
	 */
	private List<Obstruction> removedObstruction = new ArrayList<Obstruction>();

	/**
	 * Background enthält Informationen ueber das Spielfeld wie Koordinaten der
	 * Steine und Boxen, Computergegner
	 * 
	 * @param sort
	 *            Nummer des Levels
	 * @param flag
	 *            Level wird sichtbar wenn true
	 */
	public BackGround(int sort, boolean flag) {

		this.sort = sort;
		this.flag = flag;

		if (flag) {
			// System.out.println(StaticValue.allWindowsImage.size());
			bgImage = StaticValue.allWindowsImage.get(2);

		} else {

			bgImage = StaticValue.allBackGroundImage.get(0);
		}

		/**
		 * Senze 1
		 * 
		 */
		if (sort == 1) {
			/*
			 * // Stein for (int i = 0; i < 6; i++) { for (int j = 0; j < 6;
			 * j++) {
			 * 
			 * Obstruction ob = new Obstruction(48 * ((i * 2) - 1), 48 * ((j *
			 * 2) - 1) + 20, 1); ob.setType(1); this.allObstruction.add(ob); } }
			 */

			/*
			 * die Steine Koordinaten von XML einlesen
			 */
			ReadXML r = new ReadXML();
			// System.out.println("initialisierung...");
			r.initObLocation();
			List<Integer> stoneX = r.getStoneX();
			List<Integer> stoneY = r.getStoneY();
			List<Integer> boxX = r.getBoxX();
			List<Integer> boxY = r.getBoxY();
			for (int i = 0; i < stoneX.size(); i++) {
				int x1 = stoneX.get(i).intValue();

				int y1 = stoneY.get(i).intValue();
				Obstruction ob = new Obstruction(x1, y1, 0);
				ob.setType(0);
				this.allObstruction.add(ob);

			}
			// Stein
			// this.allObstruction.add(new Obstruction(0, 116, 0));
			// this.allObstruction.add(new Obstruction(0, 212, 0));
			// this.allObstruction.add(new Obstruction(0, 260, 0));
			// this.allObstruction.add(new Obstruction(0, 308, 0));
			/*
			 * for (int i = 0; i < 6; i++) { for (int j = 0; j < 6; j++) { if (i
			 * == 0 && j == 1) {
			 * 
			 * } else { Obstruction ob = new Obstruction(48 * ((i * 2)), 48 *
			 * ((j * 2)) + 20, 0); ob.setType(2); this.allObstruction.add(ob); }
			 * } }
			 */
			/**
			 * Kaesten
			 */
			for (int i = 0; i < boxX.size(); i++) {

				int x1 = boxX.get(i).intValue();
				int y1 = boxY.get(i).intValue();
				if (x1 == 0 && y1 == 116) {

				} else {
					Obstruction ob = new Obstruction(x1, y1, 1);
					ob.setType(1);
					this.allObstruction.add(ob);

				}

			}

			// Ausgang(Exit)

			/*
			 * MyFrame.setAusgangX(392); MyFrame.setAusgangY(360);
			 */
			/*
			 * int t[] = this.getRandom(); while (t[0] < 2 || t[1] < 2) { t =
			 * this.getRandom(); }
			 * 
			 * MyFrame.setAusgangX(48 * ((t[0] * 2) - 1));
			 * MyFrame.setAusgangY(48 * ((t[1] * 2) - 1) + 20); int temp1 = 48 *
			 * ((t[0] * 2) - 1); int temp2 = 48 * ((t[1] * 2) - 1) + 20;
			 */

			int t = boxX.size();

			int random = new Random().nextInt(t);

			int temp1 = boxX.get(random).intValue();
			int temp2 = boxY.get(random).intValue();
			MyFrame.setAusgangX(temp1);
			MyFrame.setAusgangY(temp2);
			System.out.println(temp1 + "door" + temp2);
			this.allObstruction.add(new Obstruction(temp1, temp2, 3));

		}

		/**
		 * Senze 2
		 * 
		 */

		/**
		 * Senze 3
		 * 
		 */

	}

	/**
	 * Erzeugt zwei zufaellige Ganzwerte, die als Koordinaten fuer zB den
	 * Ausgang benutzt werden
	 * 
	 */
	/*
	 * public int[] getRandom() { Random random = new Random(); int a = 0, b =
	 * 0; a = random.nextInt(6); b = random.nextInt(6); int t[] = { a, b };
	 * return t;
	 * 
	 * }
	 */
	/**
	 * Get BufferedImage
	 * 
	 */
	public BufferedImage getBgImage() {
		return bgImage;
	}

	/**
	 * Set the value of Image
	 * 
	 */
	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}

	/*
	 * alt Code, nicht benutenbar // Definiere Hintergrundbild private
	 * BufferedImage bgImage = null;
	 * 
	 * // Getters public BufferedImage getBgImage() { this.bgImage =
	 * StaticValue.bgImage; return bgImage; }
	 * 
	 * // ArrayList speichert alle Gegner private ArrayList Foemans = new
	 * ArrayList();
	 * 
	 * // ArrayList mit generischem Datentype speichert alle Gegenstaende
	 * private ArrayList<Obstruction> allObstruction = new
	 * ArrayList<Obstruction>();
	 * 
	 * // ArrayList speichert alle eliminierten Gegner private ArrayList
	 * removeFoemans = new ArrayList();
	 * 
	 * // ArrayList speichert alle zerstoerten Gegenstaende private ArrayList
	 * removedObstruction = new ArrayList();
	 * 
	 * // Konstruktor public BackGround() {
	 * 
	 * bgImage = StaticValue.bgImage; // this.allObstruction.add(new
	 * Obstruction(48,48));
	 * 
	 * }
	 */

	// Neustart
	/**
	 * Alle zerstoerten Steine werden wiederhergestellt
	 */
	public void reset() {
		//
		this.allObstruction.addAll(this.removedObstruction);
		//

	}

}
