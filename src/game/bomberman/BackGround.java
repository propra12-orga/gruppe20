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
	 * Speichert alle Gegenstaende
	 */
	private List<Obstruction> allObstruction = new ArrayList<Obstruction>();

	/**
	 * Speichert all Items
	 */

	private List<Item> allItem = new ArrayList<Item>();

	public List<Item> getAllItem() {
		return allItem;
	}

	public void setAllItem(List<Item> allItem) {
		this.allItem = allItem;
	}

	/**
	 * 
	 * Get all obstruction
	 */
	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}

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

		// this.sort = sort;
		// this.flag = flag;

		bgImage = StaticValue.allBackGroundImage.get(0);

		/**
		 * Senze 1
		 * 
		 */

		/*
		 * die Steine Koordinaten von XML einlesen
		 */
		ReadXML r = new ReadXML();
		if (Config.mapEditor == 1) {
			r.initObLocation("Mapeditor.xml");
		} else if (Config.select == 1) {
			r.initObLocation("last.xml");
		} else if (Config.select == 0) {
			r.initObLocation();
		}

		List<Obstruction> obs = r.getObs();
		this.allObstruction = obs;

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
		Config.dx = temp1;
		Config.dy = temp2;
		System.out.println(temp1 + "door" + temp2);
		this.allObstruction.add(new Obstruction(temp1, temp2, 3));

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
