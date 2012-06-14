package game.bomberman;

import java.awt.image.BufferedImage;

/**
 * 
 * Obstruction haben 3 Typen; 1: Stein-unzerstoerbar; 2:Box-zerstoerbar;
 * 3:Ausgang(Tuer)
 * 
 * @author tomozjx, yuankun
 * 
 */
public class Obstruction {

	// Koordinaten
	private int x;
	private int y;

	/**
	 * Gegenstand is zerstoert wenn true
	 */
	private boolean remove = false;

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	/**
	 * Obstruction haben 3 Typen; 1: Stein-unzerstoerbar; 2:Box-zerstoerbar;
	 * 3:Ausgang(Tuer)
	 * 
	 */
	private int type;
	private int startType;
	/**
	 * Das momentane Aussehen des Objektes
	 */
	private BufferedImage showImage = null;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param type
	 *            1: Stein-unzerstoerbar; 2:Box-zerstoerbar; 3:Ausgang(Tuer)
	 * 
	 * 
	 */
	Obstruction(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		setImage();

	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStartType() {
		return startType;
	}

	public void setStartType(int startType) {
		this.startType = startType;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	/**
	 * Stellt urspruenglichen Zustand vom Objekt her
	 * 
	 */
	public void reset() {
		this.type = startType;
		this.setImage();

	}

	/**
	 * Verschiedene Typen von Obstruction haben verschiedene Images
	 * 
	 */
	public void setImage() {
		showImage = StaticValue.allObstructionImage.get(type);
	}
}
