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

	// this.effectiveu = MyFrame.getBb().getBombradius();
	// this.effectived = MyFrame.getBb().getBombradius();
	// this.effectiver = MyFrame.getBb().getBombradius();
	// this.effectivel = MyFrame.getBb().getBombradius();

	// Kettenexplosion
	// for (int i = 1; i <= this.effectiveu; i++) {
	// for (int j = 0; j < MyFrame.getBb().getBombcapacity(); j++) {

	// if (java.lang.Math.abs(this.x + 48 * i
	// - MyFrame.getBb().getBombs()[j].getX()) < 30) {
	// if (java.lang.Math.abs(this.y + 48 * i
	// - MyFrame.getBb().getBombs()[j].getY()) < 30) {
	// MyFrame.getBb().getBombs()[j].setCountdown(20);
	// }
	// }
	// }
	// }

	// }

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
