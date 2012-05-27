package game.bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackGround {

	private BufferedImage bgImage = null;
	// sort: Senze Nummer
	@SuppressWarnings("unused")
	private int sort;
	// flag: ob diese Senze das Finall ist
	@SuppressWarnings("unused")
	private boolean flag;

	// speicher Alle Enemy
	// private List<Enemy> allEnemy = new ArrayList<Enemy>();

	// speicher Alle Obstuction
	private List<Obstruction> allObstruction = new ArrayList<Obstruction>();

	// getAllObstruction()
	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}

	// speicher Alle vernichteten Enemy
	// private List<Enemy> removedEnemy = new ArrayList<Enemy>();

	// speicher Alle vernichteten Obstruction
	@SuppressWarnings("unused")
	private List<Obstruction> removedObstruction = new ArrayList<Obstruction>();

	/**
	 * Design Senze
	 * 
	 */

	public BackGround(int sort, boolean flag) {

		this.sort = sort;
		this.flag = flag;

		if (flag) {
			System.out.println(StaticValue.allWindowsImage.size());
			bgImage = StaticValue.allWindowsImage.get(2);

		} else {

			bgImage = StaticValue.allBackGroundImage.get(0);
		}

		// Senze 1
		if (sort == 1) {
			// type2: Wall over Around oben und under
			for (int i = 0; i <= 11; i++) {
				// top
				this.allObstruction.add(new Obstruction(48 * (i - 1), 20, 2));
				// bottom
				this.allObstruction.add(new Obstruction(48 * (i - 1), 500, 2));
			}
			// type2: Wall over Around link und richt
			for (int i = 0; i <= 9; i++) {
				// linkSeite
				this.allObstruction.add(new Obstruction(0, 68 + (48 * i), 2));
				// rechtSeite
				this.allObstruction.add(new Obstruction(480, 68 + (48 * i), 2));
			}

			// Ausgang(Exit)
			/*
			 * MyFrame.setAusgangX(392); MyFrame.setAusgangY(360);
			 */
			int t[] = this.getRandom();
			while (t[0] < 2 || t[1] < 2) {
				t = this.getRandom();
			}

			MyFrame.setAusgangX(48 * ((t[0] * 2) - 1));
			MyFrame.setAusgangY(48 * ((t[1] * 2) - 1) + 20);
			int temp1 = 48 * ((t[0] * 2) - 1);
			int temp2 = 48 * ((t[1] * 2) - 1) + 20;
			System.out.println(temp1 + "men" + temp2);
			this.allObstruction.add(new Obstruction(temp1, temp2, 3));

			// type 1: Block
			for (int i = 0; i <= 4; i++) {
				for (int j = 0; j <= 4; j++) {
					this.allObstruction.add(new Obstruction(48 * (i * 2),
							48 * (j * 2) + 20, 1));
				}
			}
			// type 0: Stein
			this.allObstruction.add(new Obstruction(96, 68, 0));
			this.allObstruction.add(new Obstruction(240, 260, 0));
			this.allObstruction.add(new Obstruction(96, 308, 0));
			this.allObstruction.add(new Obstruction(384, 356, 0));
			this.allObstruction.add(new Obstruction(288, 164, 0));

		}
		/**
		 * Senze 2
		 * 
		 */

		/**
		 * Senze 3
		 * 
		 */

		/**
		 * Senze 4
		 * 
		 */

		/**
		 * Senze 5
		 * 
		 */

	}

	/**
	 * get two random digit,it determine the location of the Ausgang ,so the
	 * Ausgang's location is random
	 * 
	 */
	public int[] getRandom() {
		Random random = new Random();
		int a = 0, b = 0;
		a = random.nextInt(6);
		b = random.nextInt(6);
		int t[] = { a, b };
		return t;

	}

	public BufferedImage getBgImage() {
		return bgImage;
	}

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
	public void reset() {

	}

}
