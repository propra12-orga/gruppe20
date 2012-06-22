package game.bomberman;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Background enthaelt Informationen ueber das Spielfeld wie Koordinaten der
 * Steine und Boxen, Computergegner
 * 
 * @author timozjx
 * 
 */
public class BackGround {

	// background Bild
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
	 * Background enthaelt Informationen ueber das Spielfeld wie Koordinaten der
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
			 * die Steine Koordinaten von XML einlesen
			 */
			ReadXML r = new ReadXML();

			r.initObLocation();
			/*
			 * List<Integer> stoneX = r.getStoneX(); List<Integer> stoneY =
			 * r.getStoneY(); List<Integer> boxX = r.getBoxX(); List<Integer>
			 * boxY = r.getBoxY();
			 */
			List<Obstruction> obs = r.getObs();
			this.allObstruction = obs;
			/*
			 * for (int i = 0; i < stoneX.size(); i++) { int x1 =
			 * stoneX.get(i).intValue();
			 * 
			 * int y1 = stoneY.get(i).intValue(); Obstruction ob = new
			 * Obstruction(x1, y1, 0); ob.setType(0);
			 * this.allObstruction.add(ob);
			 * 
			 * }
			 * 
			 * 
			 * for (int i = 0; i < boxX.size(); i++) {
			 * 
			 * int x1 = boxX.get(i).intValue(); int y1 = boxY.get(i).intValue();
			 * if (x1 == 0 && y1 == 116) {
			 * 
			 * } else { Obstruction ob = new Obstruction(x1, y1, 1);
			 * ob.setType(1); this.allObstruction.add(ob);
			 * 
			 * }
			 * 
			 * 
			 * }
			 * 
			 * // Ausgang(Exit)
			 * 
			 * /* MyFrame.setAusgangX(392); MyFrame.setAusgangY(360);
			 */
			/*
			 * int t[] = this.getRandom(); while (t[0] < 2 || t[1] < 2) { t =
			 * this.getRandom(); }
			 * 
			 * MyFrame.setAusgangX(48 * ((t[0] * 2) - 1));
			 * MyFrame.setAusgangY(48 * ((t[1] * 2) - 1) + 20); int temp1 = 48 *
			 * ((t[0] * 2) - 1); int temp2 = 48 * ((t[1] * 2) - 1) + 20;
			 */

			List<Obstruction> box = new ArrayList<Obstruction>();
			for (Obstruction ob : this.allObstruction) {
				if (ob.getType() == 1) {
					box.add(ob);
				}
			}
			int t = box.size();

			int random = new Random().nextInt(t);

			int temp1 = box.get(random).getX();
			int temp2 = box.get(random).getY();
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
