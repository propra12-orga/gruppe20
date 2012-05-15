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

		this.status = "Standing";
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

			// nach links
			if (this.status.indexOf("left") == 0) {
				this.showImage = StaticValue.allPlayerImage.get(1);
			}
			// nach rechts
			if (this.status.indexOf("right") == 0) {
				this.showImage = StaticValue.allPlayerImage.get(3);
			}
			// nach oben
			if (this.status.indexOf("up") == 0) {
				this.showImage = StaticValue.allPlayerImage.get(0);
			}
			// nach unten
			if (this.status.indexOf("down") == 0) {
				this.showImage = StaticValue.allPlayerImage.get(4);
			}

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
