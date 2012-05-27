package game.bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CopyOfBackGround {

	private BufferedImage bgImage = null;
	// sort: Senze Nummer
	private int sort;
	// flag: ob diese Senze das Finall ist
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
	private List<Obstruction> removedObstruction = new ArrayList<Obstruction>();

	/**
	 * Design Senze
	 * 
	 */

	public CopyOfBackGround(int sort, boolean flag) {

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

			// Ausgang(Exit), hinter Stein
			this.allObstruction.add(new Obstruction(192, 260, 3));

			// type 1: Block
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					this.allObstruction.add(new Obstruction(48 * ((i * 2) - 1),
							48 * ((j * 2) - 1) + 20, 1));
				}
			}

			// type 0: Stein
			this.allObstruction.add(new Obstruction(192, 260, 0));
			this.allObstruction.add(new Obstruction(0, 116, 0));
			this.allObstruction.add(new Obstruction(0, 212, 0));
			this.allObstruction.add(new Obstruction(0, 260, 0));
			this.allObstruction.add(new Obstruction(0, 308, 0));
			this.allObstruction.add(new Obstruction(96, 406, 0));
			this.allObstruction.add(new Obstruction(192, 406, 0));
			this.allObstruction.add(new Obstruction(288, 406, 0));

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					this.allObstruction.add(new Obstruction(
							48 * (((i * 2) + 1) + 1), 48 * ((j * 2)) + 20, 0));
				}
			}

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
