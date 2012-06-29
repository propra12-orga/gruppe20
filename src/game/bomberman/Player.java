package game.bomberman;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Plaer enthaelt Koordinaten x,y , ShowImage ist das momentan anzuzeigene Bild
 * des Spielers, die Bewegungsmethoden des Spielers sowie das Bewegungstempo,
 * und die Variablen Bombcapacity und Bombradius
 * 
 * @author timozjx, Yuankun, KingManuel
 * 
 */
public class Player implements Runnable {

	private Bomb[] bombs;
	private MyFrame mf;

	private boolean isComputer = false;

	private int lastDir = -1;

	public boolean isComputer() {
		return isComputer;
	}

	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	private Thread t;
	// Koordinaten
	private int x;
	private int y;
	private BackGround nowBG;
	// Gewonnen
	private boolean flag = false;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Geschwindigkeit
	private int xmove = 0;
	/**
	 * Anzahl der maximalen Felder in jede Richtung in der die Bombe explodiert
	 */
	private int bombradius;
	/**
	 * Anzahl der Bomben die der Spieler auf einmal legen kann
	 */
	private int bombcapacity;

	// Getters Setters

	public void setX(int x) {
		this.x = x;
	}

	public int getBombcapacity() {
		return bombcapacity;
	}

	public void setBombcapacity(int bombcapacity) {
		this.bombcapacity = bombcapacity;
	}

	public int getBombradius() {
		return bombradius;
	}

	public void setBombradius(int bombradius) {
		this.bombradius = bombradius;
	}

	public void setY(int y) {
		this.y = y;
	}

	private int ymove = 0;

	// Icon
	protected BufferedImage showImage;

	// Konstruktor
	public Player(int x, int y, BackGround nowBG, Bomb[] bombs, MyFrame mf) {
		this.mf = mf;
		this.bombs = bombs;
		this.x = x;
		this.y = y;
		this.nowBG = nowBG;
		this.showImage = StaticValue.allPlayerImage.get(0);
		this.bombradius = 1;
		this.bombcapacity = 3;
		t = new Thread(this);
		t.start();

		this.status = "Standing";

	}

	public boolean getFlag() {
		return flag;
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

		this.xmove = -48;
		// change the status
		x += xmove;
		this.status = "left--moving";
	}

	public void leftstop() {
		this.xmove = 0;

		this.status = "left--standing";
	}

	public void rightmove() {
		// change the speed
		this.xmove = 48;
		// change the status
		x += xmove;
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
		this.ymove = -48;
		// change the status
		y += ymove;
		this.status = "up--moving";
	}

	public void upstop() {
		// change the speed
		this.ymove = 0;
		// change the status
		this.status = "up--standing";
	}

	public void downmove() {
		// change the speed
		this.ymove = 48;
		// change the status
		y += ymove;
		this.status = "down--moving";
	}

	public void downstop() {
		// change the speed
		this.ymove = 0;
		// change the status
		this.status = "down--standing";
	}

	/**
	 * setzte Koordinaten auf Anfangsposition (Momentan (550,658))
	 */
	public void reset() {
		this.x = 550;
		this.y = 658;
	}

	/**
	 * Setze showImage=null
	 */
	public void remove() {
		this.showImage = null;
	}

	/**
	 * Wenn type==0 wird Bombenkapazitaet um 1 erhoet Wenn type==1 wird
	 * Bombenradius um 1 erhoet
	 * 
	 * @param type
	 */
	public void UseItem(int type) {
		if (type == 0) {
			if (this.bombcapacity < 4) {
				this.bombcapacity++;
			}
		}

		if (type == 1) {
			if (this.bombradius < 10) {
				this.bombradius++;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			flag = false;

			/*
			 * x += xmove;
			 * 
			 * y += ymove;
			 */
			// Status Image siehen Sie bitte in StaticValue.java
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
				this.showImage = StaticValue.allPlayerImage.get(4);
			}
			// nach unten
			if (this.status.indexOf("down") == 0) {
				this.showImage = StaticValue.allPlayerImage.get(0);
			}

			// Figur soll nicht den Spielberreich verlassen
			if (this.x < 0)
				x = 0;
			if (this.x > 432)
				x = 432;
			if (this.y < 24)
				y = 20;
			if (this.y > 452)
				y = 452;

			/*
			 * if (this.x <= 240 && this.y <= 308 && this.x >= 192 && this.y >=
			 * 260) { flag = true; this.reset(); new EndMenu(); }
			 */

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void randomMove() {
		int oldX = this.x;
		int oldY = this.y;
		int direction = -2;
		while (true) {
			direction = new Random().nextInt(4);
			if (direction != lastDir)
				break;
		}
		mf.moveProcess(this, direction);
		if (this.x == oldX && this.y == oldY) {
			lastDir = direction;
		} else {
			lastDir = -1;
		}
	}
}