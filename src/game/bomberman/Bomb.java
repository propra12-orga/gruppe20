package game.bomberman;

import java.awt.image.BufferedImage;

public class Bomb implements Runnable {

	// Koordinaten
	private int x;
	private int y;
	// Icon
	private BufferedImage showImage;
	// Countdown bis zur Explosion
	private int countdown;

	// Getters Setters
	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
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

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public void Decreasecountdown() {
		this.countdown = countdown - 1;
	}

	public void Explode() {

		this.showImage = StaticValue.allBoomImage.get(1);

	}

	public void Disappear() {
		this.showImage = null;

	}

	public Bomb(int x, int y, int countdown) {
		this.x = x;
		this.y = y;
		this.countdown = countdown;

		this.showImage = StaticValue.allBoomImage.get(0);

		// Thread t = new Thread(this);
		// t.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
