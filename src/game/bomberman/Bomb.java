package game.bomberman;

import java.awt.image.BufferedImage;

public class Bomb implements Runnable {

	// Koordinaten
	private int x;
	private int y;

	// Getters Setters
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

	// Icon
	private BufferedImage showImage;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;

		this.showImage = StaticValue.allBoomImage.get(0);

		// Thread t = new Thread(this);
		// t.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
