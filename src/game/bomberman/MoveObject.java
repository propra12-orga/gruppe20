package game.bomberman;

import java.awt.image.BufferedImage;

/**
 * Fuer Player und Monster Sie haben gleiche Methoden Hier wird ein obere Klasse
 * erstellt.
 * 
 * */
public class MoveObject {

	// private boolean walkthroughboxes;
	//
	// // Getters Setters
	//
	// public boolean isWalkthroughboxes() {
	// return walkthroughboxes;
	// }
	//
	// public void setWalkthroughboxes(boolean walkthroughboxes) {
	// this.walkthroughboxes = walkthroughboxes;
	// }
	//
	// public void PickUpItem(MoveObject moveobject) {
	//
	// }

	private int x;
	private int y;
	private int speed = 48;
	private int life = 1;
	/**
	 * MoveStatus von Player
	 * 
	 */
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	private BufferedImage showImage;

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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	/**
	 * Bewegungsmethoden werden im PLayer und Monster Klassen ueberschrieben
	 * 
	 */
	public void leftmove() {

		if ((this.x - speed) >= 0) {
			this.x -= speed;
		}
		this.showImage = StaticValue.allPlayerImage.get(1);
	}

	/**
	 * Bewegungsmethoden werden im PLayer und Monster Klassen ueberschrieben
	 * 
	 */
	public void rightmove() {
		// change the speed
		if ((this.x + speed) <= 432) {
			this.x += speed;
		}
		this.showImage = StaticValue.allPlayerImage.get(3);
	}

	/**
	 * Bewegungsmethoden werden im PLayer und Monster Klassen ueberschrieben
	 * 
	 */
	public void upmove() {
		if ((this.y - speed) >= 20) {
			this.y -= speed;
		}
		this.showImage = StaticValue.allPlayerImage.get(4);
	}

	/**
	 * Bewegungsmethoden werden im PLayer und Monster Klassen ueberschrieben
	 * 
	 */
	public void downmove() {
		if ((this.y + speed) <= 452) {
			this.y += speed;

		}
		this.showImage = StaticValue.allPlayerImage.get(0);
	}
}
