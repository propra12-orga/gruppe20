package game.bomberman;

import java.awt.image.BufferedImage;

public class Player implements Runnable {

	// Koordinaten
	private int x;
	private int y;

	// Geschwindigkeit
	private int xmove = 0;
	private int ymove = 0;

	// MoveStatus von Player
	private String status;

	// Icon
	private BufferedImage showImage;

	// Konstruktor
	public Player(int x, int y) {
		this.x = x;
		this.y = y;

		this.showImage = StaticValue.play1;

		Thread t = new Thread(this);
		t.start();

		// this.status="rightStanding";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void leftmove() {
		// change the speed

		this.xmove = -5;
		// change the status
		this.status = "left--moving";
	}

	public void leftstop() {
		this.xmove = 0;

		this.status = "left--standing";
	}

	public void rightmove() {
		// change the speed
		this.xmove = 5;
		// change the status
		this.status = "right--moving";
	}

	public void rightstop() {
		// change the speed
		this.xmove = 0;
		// change the status
		this.status = "right--standing";
	}

	public void upmove() {
		// change the speed
		this.ymove = 5;
		// change the status
		this.status = "up--standing";
	}

	public void upstop() {
		// change the speed
		this.ymove = 0;
		// change the status
		this.status = "up--standing";
	}

	public void downmove() {
		// change the speed
		this.ymove = -5;
		// change the status
		this.status = "down--standing";
	}

	public void downstop() {
		// change the speed
		this.ymove = 0;
		// change the status
		this.status = "down--standing";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			x += xmove;

			y += ymove;

			// Figur soll nicht den Spielberreich verlassen
			if (this.x < 0)
				x = 0;
			if (this.x > 432)
				x = 432;
			if (this.y < 24)
				y = 24;
			if (this.y > 452)
				y = 452;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
