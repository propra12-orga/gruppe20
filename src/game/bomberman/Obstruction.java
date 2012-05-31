package game.bomberman;

import java.awt.image.BufferedImage;

public class Obstruction {

	// Koordinaten
	private int x;
	private int y;
	// if it is in the area of a bomb and it is a box ,mark it is going to
	// remove
	private boolean remove = false;

	// 1.box 2.stone 3.ausgang

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	// Obstuction haben 3 Type; 0: Stein; 1:Block; 2:Nix; 3:Ausgang(Tuer)
	private int type;
	private int startType;
	// private BackGround bg;
	private BufferedImage showImage = null;

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
	 * Class Obstruction haben zwei Methode reset()
	 */

	// reset() alle
	public void reset() {
		this.type = startType;
		this.setImage();

	}

	// Verschiedene Type Obstruction haben verschiedenen Image
	public void setImage() {
		showImage = StaticValue.allObstructionImage.get(type);
	}
}
