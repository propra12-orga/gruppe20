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

	// effektiver Explosionsradius oben, unten, rechts, links

	// private int effectiveu;
	// private int effectived;
	// private int effectiver;
	// private int effectivel;

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

	// this.effectiveu = MyFrame.getBb().getBombradius(); Coming soon
	// this.effectived = MyFrame.getBb().getBombradius();
	// this.effectiver = MyFrame.getBb().getBombradius();
	// this.effectivel = MyFrame.getBb().getBombradius();

	public void Disappear() {
		this.showImage = null;

	}

	/**
	 * @param x
	 *            Koordinaten
	 * @param y
	 * @param countdown
	 *            :Zeit bis zur Explosion
	 */
	public Bomb(int x, int y, int countdown) {
		this.x = x;
		this.y = y;
		this.countdown = countdown;

		this.showImage = StaticValue.allBoomImage.get(0);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
