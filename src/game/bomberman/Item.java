package game.bomberman;

import java.awt.image.BufferedImage;

/**
 * Items werden zuf�llig erzeugt wenn eine Box zerstoert wird. Tritt ein Spieler
 * auf ein offen liegendes Item verschwindet es und veraendert den Spieler auf
 * verschiedene Arten. Items haben verschiedene Typen: 0:=erhoet
 * BombenKapazitaet um 1 1:=erhoet BombenRadius um 1
 * 
 * @author HC
 * 
 */
public class Item {
	// Koordinaten
	private int x;
	private int y;
	// Icon
	private BufferedImage showImage;

	/**
	 * Um welche Art von Item handelt es sich? 0:=BombCapacity Up 1:=BombRadius
	 * Up
	 * 
	 */
	private int type;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Items werden zuf�llig erzeugt wenn eine Box zerstoert wird. Tritt ein
	 * Spieler auf ein offen liegendes Item verschwindet es und veraendert den
	 * Spieler auf verschiedene Arten. Items haben verschiedene Typen: 0:=erhoet
	 * BombenKapazitaet um 1 1:=erhoet BombenRadius um 1
	 * 
	 * @param x
	 *            Koordinaten
	 * @param y
	 * @param type
	 *            0:=erhoet BombenKapazitaet um 1, 1:=erhoet BombenRadius um 1
	 */
	public Item(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.showImage = StaticValue.allItemImage.get(type);
	}

}
