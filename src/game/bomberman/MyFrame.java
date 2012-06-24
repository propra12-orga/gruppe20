package game.bomberman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.media.CannotRealizeException;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Enthaelt Main Methode
 * Erzeugt Fenster mit bestimmter Groesse
 * 
 * @author timozjx, Yuankun, KingManuel
 *
 */
public class MyFrame extends JPanel implements KeyListener, Runnable {
	private Boolean doublePlayer = false;
	private Boolean startGame = false;
	private Boolean gameover = true;
	// the Ausgang object
	private Obstruction Ausgang = null;
	/**
	 * Der Ausgang wird sichtbar wenn true
	 */
	private boolean AusgangShow = false;
	// if has winner or game over ,draw this string
	private String logoString = "";
	// the Ausgang location
	private static int AusgangX = -100, AusgangY = -100;

	public Process process;
	/**
	 * Liste die die versciedenen Level enthält
	 */
	private List<BackGround> allBG = new ArrayList<BackGround>();
	/**
	 * aktuelles Level
	 */
	private BackGround nowBG = null;

	// zur Zeit nicht benutzete isStart
	// private boolean isStart = false;

	public static int getAusgangX() {
		return AusgangX;
	}

	public static void setAusgangX(int ausgangX) {
		AusgangX = ausgangX;
	}

	public static int getAusgangY() {
		return AusgangY;
	}

	public static void setAusgangY(int ausgangY) {
		AusgangY = ausgangY;
	}

	private Player bb = null;
	private Player bb2 = null;
	private Thread t = new Thread(this);

	// Array fuer Bomben
	private Bomb[] bombs = new Bomb[4];
	private Bomb[] bombs2 = new Bomb[4];
	/**
	 * Zaehler fuer das Bombenarray Jedesmal wenn eine Bombe gelegt wird erhoet
	 * er sich
	 */
	private int bombcount = 0;
	/**
	 * Zaehler fuer das Bombenarray Jedesmal wenn eine Bombe gelegt wird erhoet
	 * er sich
	 */
	private int bombcount2 = 0;

	/**
	 * 
	 * Main Methode ist hier
	 */

	public Player getBb() {
		return bb;
	}

	public void setBb(Player bb) {
		this.bb = bb;
	}

	/**
	 * Erzeugt Fenster mit bestimmter Groesse
	 */

	public MyFrame() {

		// Fenster
		// this.setTitle("Bombermann");
		this.setSize(480, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		// this.setResizable(false);

		// Instalisieren alle BufferImage
		StaticValue.init();

		// Bilden allen Senze
		for (int i = 1; i <= 5; i++) {

			this.allBG.add(new BackGround(i, i == 5 ? true : false));
		}

		// Setzen Senz(1) als angesichte Senze
		this.nowBG = this.allBG.get(0);

		// initialisiere BombenArrays
		for (int i = 0; i < 4; i++) {
			this.bombs[i] = new Bomb(0, -20, 0);
			this.bombs2[i] = new Bomb(0, -20, 0);
		}

		t.start();

		this.addKeyListener(this);

		// this.addKeyListener(new player2KeyListener());

		// Beende das Pragramm bei Schliessen des Spielfelds
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);

	}

	/**
	 * override paint Methode extends JFrame
	 * 
	 */
	@Override
	public void paint(Graphics g) {

		g.drawString("press F1 start game ,F2 double player, F4 game over",
				100, 520);

		// temporal bufferedImage
		// BufferedImage zu erzeugen
		BufferedImage image = new BufferedImage(480, 500,
				BufferedImage.TYPE_3BYTE_BGR);
		// g2 uebernimmt image
		Graphics g2 = image.getGraphics();

		// Malen BackGroundImage
		g2.drawImage(this.nowBG.getBgImage(), 0, 20, this);

		// Zeichne Items
		/*
		 * Iterator<Item> iteritem = this.nowBG.getAllItem().iterator(); while
		 * (iteritem.hasNext()) { Item item = iteritem.next();
		 * g2.drawImage(item.getShowImage(), item.getX(), item.getY(), this); }
		 */

		// Malen Obstruction
		Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();

		while (iter.hasNext()) {
			Obstruction ob = iter.next();
			if (ob.getType() == 3 && !AusgangShow) {

				this.Ausgang = ob;
				this.Ausgang.setX(-100);
				this.Ausgang.setY(-100);

			}
			g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);

		}

		// Malen BufferImage in Fenster(Frame)

		// zeichne die Bomben
		// this.drawBombs(g2, this.bombs, bb);
		// this.drawBombs(g2, this.bombs2, bb2);

		this.drawBombs(g2, this.bombs);
		this.drawBombs(g2, this.bombs2);

		// zeichne die Player in Spielfeld
		if (bb != null)
			g2.drawImage(this.bb.getShowImage(), this.bb.getX(),
					this.bb.getY(), this);
		if (bb2 != null)
			g2.drawImage(this.bb2.getShowImage(), this.bb2.getX(),
					this.bb2.getY(), this);
		// zeichne die Pufferbild in Spiefeld
		g.drawImage(image, 0, 0, this);

		// this.nowBackground
		g2.drawImage(Ausgang.getShowImage(), Ausgang.getX(), Ausgang.getY(),
				this);

		g.setColor(new Color(238, 238, 238));
		g.fillRect(0, 0, 480, 20);
	}

	/**
	 * 
	 * @param bb
	 * @param bomb
	 * @return true wenn Spieler bb im Explosionsradius einer Bombe steht
	 */
	public boolean ifKillplayer(Player bb, Bomb bomb) {
		boolean flag = false;
		/*
		 * int bombX = bomb.getX(); int bombY = bomb.getY(); int obX =
		 * bb.getX(); int obY = bb.getY();
		 */
		int bbX = bb.getX();
		int bbY = bb.getY();
		List<int[]> area = this.ExplodeArea(bomb);
		for (int[] a : area) {
			if (bbX == a[0] && bbY == a[1])
				flag = true;
			this.PlaySound(MySound.dead, -1);
		}

		return flag;
	}

	/**
	 * Kettenreaktion Ist eine Bombe im Explosionsradius einer anderen,
	 * explodiert sie
	 * 
	 * @param bomb2
	 * @param bomb
	 */
	public void ifbombOverlay(Bomb bomb2, Bomb bomb) {
		int bombX = bomb.getX();
		int bombY = bomb.getY();
		int b2X = bomb2.getX();
		int b2Y = bomb2.getY();

		/*
		 * pruefen ob der zweite Bomb in der Radius vom erste Bomb
		 */
		if ((bombX - 48 * 2 < b2X + 2 && b2X < bombX + 48 * 3 - 5 && (b2Y < bombY + 2
				&& bombY + 2 <= b2Y + 45 || (bombY + 45 < b2Y + 45 && bombY + 35 > b2Y)))
				|| (bombY - 48 * 3 < b2Y && b2Y < bombY + 48 * 3 - 5 && (b2X < bombX + 2
						&& bombX + 2 <= b2X + 45 || (bombX + 45 < b2X + 45 && bombX + 35 > b2X)))) {

			if (bomb2.getCountdown() != 0 && bomb2.getCountdown() > 20) {
				bomb2.setCountdown(20);
				bomb2.Explode();
			}

		}

	}

	/**
	 * die Koordinaten pruefen von Bomb#
	 * 
	 * @param toExplode
	 */
	public void bombChain(Bomb toExplode) {
		Bomb[] allbombs = { bombs[0], bombs[1], bombs[2], bombs[3], bombs2[0],
				bombs2[1], bombs2[2], bombs2[3] };
		for (Bomb b : allbombs) {
			if (b != toExplode)
				this.ifbombOverlay(b, toExplode);

		}

	}

	/**
	 * Zeichne Bomben und entferne Objekte im Exlposionsradius
	 * 
	 */
	public void drawBombs(Graphics g2, Bomb[] bombs) {
		for (int i = 0; i < 4; i++) {
			g2.drawImage(bombs[i].getShowImage(), bombs[i].getX(),
					bombs[i].getY(), this);
			// Zeichne Explosion
			if (0 < bombs[i].getCountdown() & bombs[i].getCountdown() < 21) {
				this.PlaySound(MySound.fire, -1);
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				// entferne die Obstruction
				this.destoryObCheck(bombs[i], obstructions);
				// invoke the mothod if kill player
				this.bombChain(bombs[i]);
				if (startGame) {
					boolean f1 = false;
					if (bb != null)
						f1 = this.ifKillplayer(bb, bombs[i]);
					// Wenn Spieler stirbt beende Spiel
					if (f1) {

						startGame = false;
						// change the image , the player die-------------------
						if (bb2 != null)
							logoString = "player2 win!";
						else
							logoString = "Game over !";
					}
					boolean f2 = false;
					if (doublePlayer && bb2 != null)
						f2 = this.ifKillplayer(bb2, bombs[i]);
					if (f2) {
						// System.out.println("----------------true2");
						startGame = false;
						if (bb != null)
							logoString = "player1 win!";
						else
							logoString = "Game over !";
					}
				}
				obstructions = this.nowBG.getAllObstruction();
				for (int a = 0; a < obstructions.size(); a++) {
					Obstruction ob = this.nowBG.getAllObstruction().get(a);

					if (ob != null && ob.isRemove()) {

						if (ob.getX() == this.AusgangX
								&& ob.getY() == this.AusgangY) {
							Ausgang.setX(ob.getX());
							Ausgang.setY(ob.getY());
							this.AusgangShow = true;
							/*
							 * System.out.println(Ausgang.getX() + "door" +
							 * Ausgang.getY()); System.out.println(this.AusgangX
							 * + "aus" + this.AusgangY);
							 */
						}
						obstructions.remove(ob);
						// Erzeuge Items wo Boxen verschwinden
						int random = new Random().nextInt(100);
						/*
						 * if (random < 30) { // List<Item> items =
						 * this.nowBG.getAllItem(); if (random < 15) {
						 * items.add(new Item(ob.getX(), ob.getY(), 0)); } else
						 * { items.add(new Item(ob.getX(), ob.getY(), 1)); } //
						 * this.nowBG.setAllItem(items); }
						 */
					}

				}

				List<int[]> area = this.ExplodeArea(bombs[i]);
				for (int[] a : area) {
					g2.drawImage(StaticValue.allBoomImage.get(2), a[0], a[1],
							this);
				}

				/*
				 * 
				 * for (int j = 1; j <= player.getBombradius(); j++) {
				 * g2.drawImage(StaticValue.allBoomImage.get(2),
				 * bombs[i].getX(), bombs[i].getY() + j * 48, this);
				 * g2.drawImage(StaticValue.allBoomImage.get(2),
				 * bombs[i].getX(), bombs[i].getY() - j * 48, this);
				 * g2.drawImage(StaticValue.allBoomImage.get(2), bombs[i].getX()
				 * + j * 48, bombs[i].getY(), this);
				 * g2.drawImage(StaticValue.allBoomImage.get(2), bombs[i].getX()
				 * - j * 48, bombs[i].getY(), this); }
				 */
			}
		}
	}

	private void PlaySound(String path, int status) {
		try {
			MySound.PlaySound(path, status);
		} catch (CannotRealizeException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private List<int[]> ExplodeArea(Bomb bomb) {

		/*
		 * int a[] speichert die Koordinaten der Rechteck und den Radius vom
		 * Bomb besteht aus Rechtecke area speichet die Bereich der echten
		 * Radius vom Bomb
		 */
		List<int[]> area = new ArrayList<int[]>();

		// das Bereich vom Bomb zerlegen wir in 4 Richtungen,
		// pruefe jeder Richtung zu vermeiden die Sache hinter der Obstructions
		// zu zerstoeren

		// clockwise
		this.ExplodeAreatiny(bomb, area, 0);
		this.ExplodeAreatiny(bomb, area, 1);
		this.ExplodeAreatiny(bomb, area, 2);
		this.ExplodeAreatiny(bomb, area, 3);

		return area;
	}

	// pruefen die Bereichen vom Bomb,area ist die Einheit,
	// wenn nicht hinter vom Stein,fuegen wir in List ein,explode
	private void ExplodeAreatiny(Bomb bomb, List<int[]> area, int dir) {
		List<Obstruction> obs = this.nowBG.getAllObstruction();
		int x = bomb.getX();
		int y = bomb.getY();

		int length = 0;
		if (dir == 1 || dir == 3)
			length = bomb.getXlength();
		else if (dir == 0 || dir == 2)
			length = bomb.getYlength();

		boolean mark = true;

		/*
		 * je zwei Richtung plus zentrale punkt deswegen (length-1)/2=2
		 */
		for (int i = 0; i <= (length - 1) / 2; i++) {
			int tempX = 0;
			int tempY = 0;
			if (dir == 0) {
				tempX = x;
				tempY = y - i * 48;
			} else if (dir == 1) {
				tempX = x + i * 48;
				tempY = y;
			} else if (dir == 2) {
				tempX = x;
				tempY = y + i * 48;
			} else if (dir == 3) {
				tempX = x - i * 48;
				tempY = y;
			}

			for (Obstruction ob : obs) {

				// wenn steine
				if (ob.getType() == 0) {
					if (ob.getX() == tempX && ob.getY() == tempY) {
						// wenn obstruction break
						mark = false;
						break;
					}
				}
			}

			if (mark) {
				int a[] = { tempX, tempY };
				area.add(a);
			} else
				break;// schleifen anhalten
		}
	}

	private void destoryObCheck(Bomb bomb, List<Obstruction> obs) {
		List<int[]> area = this.ExplodeArea(bomb);
		for (int a[] : area) {
			for (Obstruction ob : obs) {
				// 1 fuer box
				if (ob.getType() != 1)
					continue;
				int obX = ob.getX();
				int obY = ob.getY();

				if (a[0] == obX && a[1] == obY)
					ob.setRemove(true);
			}
		}

	}

	/*
	 * Beim Bewegen wird diese Methoden aufgerufen
	 */
	public void moveProcess(Player bb, int direction) {
		List<Obstruction> obstructions = nowBG.getAllObstruction();

		boolean flag = true;
		/*
		 * temporaere varieable wenn der Player bewegen wird, die Koordinaten
		 * der Players und die Koordinaten der Obstructions nicht ueberlappend
		 * dann wir der Player echt bewegt flag default true
		 */
		int mX = bb.getX();
		int mY = bb.getY();

		switch (direction) {
		case 0:
			mY -= 48;
			break;
		case 1:
			mX += 48;
			break;
		case 2:
			mY += 48;
			break;
		case 3:
			mX -= 48;
			break;
		}

		// wenn Ooobstruction tuer ist,dann darf hrein
		for (Obstruction ob : obstructions) {
			if (ob.getType() != 3) {
				int obX = ob.getX();
				int obY = ob.getY();
				if (mX == obX && mY == obY) {
					flag = false;
					break;
				}
			}
		}

		if (flag) {
			switch (direction) {
			case 0:
				bb.upmove();
				break;
			case 1:
				bb.rightmove();
				break;
			case 2:
				bb.downmove();
				break;
			case 3:
				bb.leftmove();
				break;
			}

		}
	}

	/**
	 * override the keyPressed method implements KeyListener
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		// neue Start
		if (ke.getKeyCode() == 112) {

			startGame = true;
			this.gameover = false;

			this.AusgangShow = false;
			// reloead

			this.allBG.clear();
			this.logoString = "";

			for (int i = 1; i <= 5; i++) {

				this.allBG.add(new BackGround(i, i == 5 ? true : false));
				this.nowBG = this.allBG.get(0);
				this.repaint();
			}

			this.bb = new Player(0, 68, this.nowBG);

			if (doublePlayer == true)
				this.bb2 = new Player(384, 452, this.nowBG);

			int times = 15;
			while (times > 0) {

				Graphics g = this.getGraphics();
				g.setColor(Color.blue);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString("let's go!", 200, 150);
				this.repaint();
				try {

					Thread.sleep(100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				times--;
			}

		}
		// F2 2-Player mode

		if (ke.getKeyCode() == 113) {
			if (doublePlayer == false) {
				// this.bb2 = new Player(384, 452, this.nowBG);
				// this.repaint();
				doublePlayer = true;
			}

		}
		// F4 game over
		if (ke.getKeyCode() == 115) {
			int times = 20;
			while (times > 0) {

				Graphics g = this.getGraphics();
				g.setColor(Color.red);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString("game over !", 180, 150);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				times--;
			}
			System.exit(0);
		}
		//
		if (!startGame)
			return;

		// wenn man 39 dr¨¹cken -->
		if (ke.getKeyCode() == 39) {

			this.moveProcess(bb, 1);
		}

		if (ke.getKeyCode() == 37) {

			this.moveProcess(bb, 3);
		}

		if (ke.getKeyCode() == 40) {

			this.moveProcess(bb, 2);
		}

		if (ke.getKeyCode() == 38) {

			this.moveProcess(bb, 0);
		}

		// keyListener player2
		if (ke.getKeyCode() == 68) {
			if (bb2 != null) {
				this.moveProcess(bb2, 1);
			}
		}
		if (ke.getKeyCode() == 65) {
			if (bb2 != null) {
				this.moveProcess(bb2, 3);
			}
		}
		if (ke.getKeyCode() == 83) {
			if (bb2 != null) {
				this.moveProcess(bb2, 2);
			}
		}
		if (ke.getKeyCode() == 87) {
			if (bb2 != null) {
				this.moveProcess(bb2, 0);
			}
		}
		/**
		 * Wenn Leertaste gedrueckt wird Setzte Bombe
		 * 
		 */
		if (ke.getKeyCode() == 32) {
			if (this.bombs[bombcount].getCountdown() == 0) {

				Bomb bomb = this.bombs[bombcount];
				this.bombs[bombcount].setX(this.bb.getX());
				this.bombs[bombcount].setY(this.bb.getY());

				this.bombs[bombcount].setCountdown(70);
				this.bombs[bombcount].setShowImage(StaticValue.allBoomImage
						.get(0));

				bombcount = (bombcount + 1) % bb.getBombcapacity();
			}
		}
		// 2 Player legt Bombe wenn er "j" drueckt
		if (ke.getKeyCode() == 74 && bb2 != null) {
			if (this.bombs2[bombcount2].getCountdown() == 0) {
				Bomb bomb = this.bombs2[bombcount2];
				this.bombs2[bombcount2].setX(this.bb2.getX());
				this.bombs2[bombcount2].setY(this.bb2.getY());
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				for (int i = 0; i < obstructions.size(); i++) {
					Obstruction ob = obstructions.get(i);
					int obX = ob.getX();
					int obY = ob.getY();
					int bombX = bomb.getX();
					int bombY = bomb.getY();

					this.destoryObCheck(bomb, obstructions);
					this.repaint();

				}

			}

			this.bombs2[bombcount2].setCountdown(70);
			this.bombs2[bombcount2].setShowImage(StaticValue.allBoomImage
					.get(0));
			bombcount2 = (bombcount2 + 1) % bb2.getBombcapacity();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if (!startGame)
			return;

		// wenn man loslaesst: stopmove
		if (ke.getKeyCode() == 39) {
			this.bb.rightstop();
		}

		if (ke.getKeyCode() == 37) {
			this.bb.leftstop();
		}

		if (ke.getKeyCode() == 40) {
			this.bb.upstop();
		}

		if (ke.getKeyCode() == 38) {
			this.bb.downstop();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * 
	 * Return true or false ,if the player enter into the Ausgang, return
	 * true,the player win and gameover
	 */

	public boolean ifFindAusgang(Player pp) {
		boolean flag = false;

		if (pp.getX() == AusgangX && pp.getY() == AusgangY && AusgangShow) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Verringert Countdown der Bomben. Wenn der Countdown 20 erreicht
	 * explodiert Bombe. Wenn der Countdown 0 erreicht verschwindet Bombe
	 */

	public void bombStatus(Bomb[] bombs) {
		for (int i = 0; i < 4; i++) {
			if (bombs[i].getCountdown() > 0) {
				bombs[i].Decreasecountdown();
				if (bombs[i].getCountdown() == 20) {
					bombs[i].Explode();
				}
				if (bombs[i].getCountdown() == 0) {
					bombs[i].Disappear();
				}

			}

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			// if win
			this.repaint();

			boolean flag1 = false;

			boolean flag2 = false;

			if (this.bb != null) {

				flag1 = this.ifFindAusgang(this.bb);

				if (flag1 && this.AusgangShow == true)
					logoString = "player1 win !";
			}
			if (this.bb2 != null) {
				flag2 = this.ifFindAusgang(this.bb2);
				if (flag2 && this.AusgangShow == true)
					logoString = "player2 win !";
			}

			if (((this.AusgangShow == true && (flag1 || flag2)) || (!logoString
					.equals("let's go!") && !logoString.equals("")))) {
				// System.out.println(logoString);
				this.startGame = false;
				this.doublePlayer = false;

				bb = null;
				bb2 = null;
				int count = 50;
				while (!startGame && count > 0) {
					this.startGame = false;
					Graphics g = this.getGraphics();
					g.setColor(Color.blue);
					g.setFont(new Font("Arial", Font.BOLD, 20));
					g.drawString(logoString, 200, 150);
					try {
						Thread.sleep(100);
						this.AusgangShow = false;
						for (int i = 0; i < 4; i++) {
							this.bombs[i].setCountdown(0);
							this.bombs2[i].setCountdown(0);
							this.bombs[i].Disappear();
							this.bombs2[i].Disappear();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					for (int i = 0; i < 2; i++) {
						if (this.bombs[i].getCountdown() > 0) {
							this.bombs[i].Decreasecountdown();
							if (this.bombs[i].getCountdown() == 20) {
								this.bombs[i].Explode();
							}
							if (this.bombs[i].getCountdown() == 0) {
								this.bombs[i].Disappear();
							}

						}

					}
					count--;
				}

				this.allBG.clear();

				this.logoString = "";

				for (int i = 1; i <= 5; i++) {

					this.allBG.add(new BackGround(i, i == 5 ? true : false));
					this.nowBG = this.allBG.get(0);
					this.repaint();
				}
			}

			this.bombStatus(bombs);
			this.bombStatus(bombs2);

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
