package game.bomberman;

import game.bomberman.thing.BombPoint;
import game.bomberman.thing.Life;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.media.CannotRealizeException;
import javax.swing.JPanel;

/**
 * Enthaelt Main Methode Erzeugt Fenster mit bestimmter Groesse
 * 
 * @author timozjx, Yuankun, KingManuel
 * 
 */
@SuppressWarnings("serial")
public class MyFrame extends JPanel implements KeyListener, Runnable {
	public Vector<Monster> vc = new Vector<Monster>();

	public Vector<Monster> getVc() {
		return vc;
	}

	public void setVc(Vector<Monster> vc) {
		this.vc = vc;
	}

	public boolean netGame = false;
	/**
	 * es gibt einen 2. Spieler wenn true
	 */
	public static Boolean doublePlayer = false;
	private Boolean startGame = false;
	private Boolean gameover = true;
	// the Ausgang object
	private Obstruction Ausgang = null;
	private String level;
	/**
	 * Der Ausgang wird sichtbar wenn true
	 */
	private boolean AusgangShow = false;
	// if has winner or game over ,draw this string
	private String logoString = "";
	// the Ausgang location
	protected static int AusgangX = -100, AusgangY = -100;

	public Process process;
	/**
	 * Liste die die versciedenen Level enthält
	 */
	private List<BackGround> allBG = new ArrayList<BackGround>();
	/**
	 * aktuelles Level
	 */
	protected BackGround nowBG = null;

	// zur Zeit nicht benutzete isStart
	// private boolean isStart = false;

	public Bomb[] getBomb2() {
		return bombs2;
	}

	public void setBomb2(Bomb[] bombs2) {
		this.bombs2 = bombs2;
	}

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

	public static void main(String[] args) {
		// new MyFrame();

	}

	/**
	 * Erzeugt Fenster mit bestimmter Groesse
	 */

	public MyFrame() {

		this.level = level;
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
			this.bombs[i] = new Bomb(0, -2000, 0);
			this.bombs2[i] = new Bomb(0, -2000, 0);
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
	 * Zeichne Lebens Items
	 * 
	 * @param g
	 */
	public void drawThing(Graphics g) {
		// if (Config.bomb != null) {
		// BufferedImage bf = StaticValue.allObstructionImage.get(4);
		// g.drawImage(bf, Config.bomb.getX(), Config.bomb.getY(), this);
		// }
		// if (Config.life != null) {
		// BufferedImage bf = StaticValue.allItemImage.get(3);
		// g.drawImage(bf, Config.life.getX(), Config.life.getY(), this);
		// }

		for (BombPoint bp : Config.bombs) {
			BufferedImage bf = StaticValue.allObstructionImage.get(4);
			g.drawImage(bf, bp.getX(), bp.getY(), this);
		}
		for (Life life : Config.lifes) {
			BufferedImage bf = StaticValue.allItemImage.get(3);
			g.drawImage(bf, life.getX(), life.getY(), this);
		}

	}

	// public void drawRecoder(Graphics g) {
	// g.setColor(Color.RED);
	// g.drawString("1", 450, 300);
	// }
	/**
	 * Zeichne Monster
	 * 
	 * @param g
	 */
	public void drawMonster(Graphics g) {
		for (Monster mon : this.vc) {
			int x = mon.getX();
			int y = mon.getY();
			if (/* mon.getShowImage() != null && /* x != 0 && */y != 0)
				g.drawImage(StaticValue.allEnemyImage.get(0), x, y, this);
		}
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

		Iterator<Item> iteritem = this.nowBG.getAllItem().iterator();
		if (!Config.netGame) {
			while (iteritem.hasNext()) {
				Item item = iteritem.next();
				g2.drawImage(item.getShowImage(), item.getX(), item.getY(),
						this);
			}
		}
		// Malen Obstruction
		Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();

		while (iter.hasNext()) {
			Obstruction ob = iter.next();
			if (ob.getType() == 3) {

				this.Ausgang = ob;

				if (!Config.AusgangShow) {
					this.Ausgang.setX(-100);
					this.Ausgang.setY(-100);

				} else if (Config.AusgangShow) {
					this.Ausgang.setX(Config.dx);
					this.Ausgang.setY(Config.dy);
					g2.drawImage(ob.getShowImage(), Config.dx, Config.dy, this);
				}

			}
			g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);

		}

		// Malen BufferImage in Fenster(Frame)

		// zeichne die Bomben
		this.drawBombs(g2, this.bombs, bb);
		this.drawBombs(g2, this.bombs2, bb2);

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
		this.drawMonster(g);

		this.drawThing(g);
	}

	/**
	 * 
	 * @param bb
	 * @param bomb
	 * @return true wenn Spieler bb im Explosionsradius einer Bombe steht
	 */
	public boolean ifKillplayer(Player bb, Bomb bomb) {
		boolean flag = false;
		/**
		 * int bombX = bomb.getX(); int bombY = bomb.getY(); int obX =
		 * bb.getX(); int obY = bb.getY();
		 */
		int bbX = bb.getX();
		int bbY = bb.getY();
		List<int[]> area = this.ExplodeArea(bomb, bb);
		for (int[] a : area) {
			if (bbX == a[0] && bbY == a[1]) {
				if (TimeCounter.time1 == 0) {
					bb.setLife(bb.getLife() - 1);
					this.PlaySound(MySound.dead, -1);
				}
				if (TimeCounter.time1 == 0 && TimeCounter.time2 == 0) {
					TimeCounter.time1 = Calendar.getInstance()
							.getTimeInMillis();
					TimeCounter.time2 = Calendar.getInstance()
							.getTimeInMillis();

				} else {
					if (TimeCounter.time1 - TimeCounter.time2 > 0)
						TimeCounter.time2 = Calendar.getInstance()
								.getTimeInMillis();
					else
						TimeCounter.time1 = Calendar.getInstance()
								.getTimeInMillis();
				}
				if (Math.abs(TimeCounter.time1 - TimeCounter.time2) > 1000) {
					bb.setLife(bb.getLife() - 1);
					this.PlaySound(MySound.dead, -1);

					bb.setShowImage(StaticValue.allPlayerImage.get(2));
				}

				if (bb.getLife() <= 0) {
					flag = true;

				}
			}
		}

		// if (flag == true) {
		// this.PlaySound(MySound.dead, -1);
		// }
		return flag;
	}

	/**
	 * Entfernt Monster wenn es von Bombe getroffen wird
	 * 
	 * @param bomb
	 * @param vc
	 * @param player
	 */
	public void ifKillMonster(Bomb bomb, Vector<Monster> vc, Player player) {
		List<int[]> area = this.ExplodeArea(bomb, player);
		for (int a[] : area) {
			for (int i = 0; i < vc.size(); i++) {
				int x = vc.get(i).getX();
				int y = vc.get(i).getY();
				if (a[0] == x && a[1] == y) {

					vc.remove(i);
					i--;
					Config.existMonster--;

					Temp.currentGrade += 10;
				}
			}
		}
	}

	/**
	 * 
	 * @param bb
	 * @param vc
	 * @return boolean true wenn Player von einem Monster beruehrt wird
	 */
	public boolean isKilledByMonster(Player bb, Vector<Monster> vc) {
		boolean flag = false;
		for (Monster mon : vc) {
			int x = mon.getX();
			int y = mon.getY();
			if (bb != null) {

				if (bb.getX() == x && bb.getY() == y) {
					flag = true;
				}

			}

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
	public void ifbombOverlay(Bomb bomb2, Bomb bomb, Player player) {
		int bombX = bomb.getX();
		int bombY = bomb.getY();
		int b2X = bomb2.getX();
		int b2Y = bomb2.getY();

		/*
		 * pruefen ob der zweite Bomb in der Radius vom erste Bomb
		 */
		if (player != null) {
			if ((bombX - 48 * player.getBombradius() < b2X + 2
					&& b2X < bombX + 48 * player.getBombradius() + 5 && (b2Y < bombY + 2
					&& bombY + 2 <= b2Y + 45 || (bombY + 45 < b2Y + 45 && bombY + 35 > b2Y)))
					|| (bombY - 48 * player.getBombradius() <= b2Y
							&& b2Y < bombY + 48 * player.getBombradius() + 5 && (b2X < bombX + 2
							&& bombX + 2 <= b2X + 45 || (bombX + 45 < b2X + 45 && bombX + 35 > b2X)))) {

				if (bomb2.getCountdown() != 0 && bomb2.getCountdown() > 20) {
					bomb2.setCountdown(20);
					bomb2.Explode();
				}

			}
		}

	}

	/**
	 * die Koordinaten pruefen von Bomb#
	 * 
	 * @param toExplode
	 */
	public void bombChain(Bomb toExplode, Player player) {
		Bomb[] allbombs = { bombs[0], bombs[1], bombs[2], bombs[3], bombs2[0],
				bombs2[1], bombs2[2], bombs2[3] };
		for (Bomb b : allbombs) {
			if (b != toExplode)
				this.ifbombOverlay(b, toExplode, player);

		}

	}

	/**
	 * Zeichne Bomben und entferne Objekte im Exlposionsradius
	 * 
	 */
	public void drawBombs(Graphics g2, Bomb[] bombs, Player player) {
		for (int i = 0; i < 4; i++) {
			if (bombs[i].getCountdown() == 20) {
				this.bombChain(bombs[i], player);
			}
		}
		for (int i = 0; i < 4; i++) {
			if (bombs[i].getCountdown() == 20) {
				this.bombChain(bombs[i], player);
			}
		}
		for (int i = 0; i < 4; i++) {
			if (bombs[i].getCountdown() == 20) {
				bombs[i].setArea(this.ExplodeArea(bombs[i], player));
			}
		}
		for (int i = 0; i < 4; i++) {
			g2.drawImage(bombs[i].getShowImage(), bombs[i].getX(),
					bombs[i].getY(), this);

			// Zeichne Explosion
			if (0 < bombs[i].getCountdown() & bombs[i].getCountdown() < 21) {
				if (bombs[i].getCountdown() == 20) {

					this.PlaySound(MySound.fire, -1);
				}
				if (bombs[i].getCountdown() < 5) {
					bombs[i].setY(-600);
				}

				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				// entferne die Obstruction
				System.out.println(bombs[i].getCountdown());
				if (bombs[i].getCountdown() == 20) {
					this.destoryObCheck(bombs[i], obstructions, player);
					bombs[i].Decreasecountdown();
				}
				this.ifKillMonster(bombs[i], this.vc, player);
				// this.bombChain(bombs[i], player);
				if (startGame) {
					boolean f1 = false;
					if (bb != null)
						f1 = this.ifKillplayer(bb, bombs[i]);
					// Wenn Spieler stirbt beende Spiel
					if (f1) {

						startGame = false;
						Config.startGame = startGame;
						// change the image , the player die
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
						Config.startGame = startGame;
						if (bb != null)
							logoString = "player1 win!";
						else
							logoString = "Game over !";
					}
				}
				// if (bombs[i].getCountdown() == 20) {
				// bombs[i].setArea(this.ExplodeArea(bombs[i], player));
				// }
				obstructions = this.nowBG.getAllObstruction();
				for (int a = 0; a < obstructions.size(); a++) {
					List<Obstruction> removeObj = new ArrayList<Obstruction>();
					Obstruction ob = this.nowBG.getAllObstruction().get(a);

					if (ob != null && ob.isRemove()) {
						if (ob.getX() == Config.dx && ob.getY() == Config.dy) {
							Ausgang.setX(ob.getX());
							Ausgang.setY(ob.getY());
							Config.AusgangShow = true;
							this.AusgangShow = true;
							/*
							 * System.out.println(Ausgang.getX() + "door" +
							 * Ausgang.getY()); System.out.println(this.AusgangX
							 * + "aus" + this.AusgangY);
							 */
						}

						removeObj.add(ob);

					}
					obstructions.removeAll(removeObj);
				}

				for (int[] a : bombs[i].getArea()) {
					g2.drawImage(StaticValue.allBoomImage.get(2), a[0], a[1],
							this);
				}
				// List<int[]> area = this.ExplodeArea(bombs[i], player);
				// for (int[] a : area) {
				// g2.drawImage(StaticValue.allBoomImage.get(2), a[0], a[1],
				// this);
				// }
			}
		}
		// hier auslagern
	}

	/**
	 * Spielt Sounddatei ab
	 * 
	 * @param path
	 * @param status
	 */
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

	/**
	 * 
	 * @param bomb
	 * @param player
	 * @return List<int> mit Koordinaten der Felder in der die Bombe explodieren
	 *         soll
	 */
	private List<int[]> ExplodeArea(Bomb bomb, Player player) {

		/*
		 * int a[] speichert die Koordinaten der Rechteck und den Radius vom
		 * Bomb besteht aus Rechtecke area speichet die Bereich der echten
		 * Radius vom Bomb
		 */
		List<int[]> area = new ArrayList<int[]>();

		// das Bereich vom Bomb zerlegen wir in 4 Richtungen,
		// pruefe jeder Richtung zu vermeiden die Sache hinter der Obstructions
		// zu zerstoeren

		// uhrzeigersinn 0=oben, 1=rechts, 2=unten, 3=links
		this.ExplodeAreatiny(bomb, area, 0, player);
		this.ExplodeAreatiny(bomb, area, 1, player);
		this.ExplodeAreatiny(bomb, area, 2, player);
		this.ExplodeAreatiny(bomb, area, 3, player);
		// for (int i = 0; i < 4; i++) {
		// System.out.println(area.get(i));
		// }
		return area;
	}

	/**
	 * pruefen die Bereichen vom Bomb,area ist die Einheit, wenn nicht hinter
	 * vom Stein,fuegen wir in List ein,explode
	 * 
	 * @param bomb
	 * @param area
	 * @param dir
	 * @param player
	 */
	private void ExplodeAreatiny(Bomb bomb, List<int[]> area, int dir,
			Player player) {
		List<Obstruction> obs = this.nowBG.getAllObstruction();
		int x = bomb.getX();
		int y = bomb.getY();

		int length = 0;
		if (dir == 1 || dir == 3) {
			if (player != null) {
				length = player.getBombradius();
			}
		} else if (dir == 0 || dir == 2) {
			if (player != null) {
				length = player.getBombradius();
			}
		}

		boolean mark = true;

		/**
		 * prueft nach Objekten in angegebener Richtung
		 */

		for (int i = 0; i <= length; i++) {//
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

				// wenn boxen
				if (mark) {

					if (ob.getType() == 1) {

						if (ob.getX() == tempX && ob.getY() == tempY) {
							// wenn obstruction break
							mark = false;
							int a[] = { tempX, tempY };
							area.add(a);

							break;

						}
					}
				}// wenn steine
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
			} else {
				break;
			}
			// schleifen anhalten
		}
	}

	/**
	 * entferne die Obstructions von List<Obstruction> generiere zufaellig Items
	 * an Stelle der zerstoerten Objekte
	 * 
	 * @param bomb
	 * @param obs
	 * @param player
	 */
	private void destoryObCheck(Bomb bomb, List<Obstruction> obs, Player player) {
		// List<int[]> area = this.ExplodeArea(bomb, player);
		List<int[]> area = bomb.getArea();
		for (int a[] : area) {
			for (Obstruction ob : obs) {
				// 1 fuer box
				if (ob.getType() != 1)
					continue;
				int obX = ob.getX();
				int obY = ob.getY();
				for (int j = 1; j <= a.length / 2; j++) {
					if (a[2 * j - 2] == obX && a[2 * j - 1] == obY) {
						ob.setRemove(true);

						if (!Config.netGame) {
							int random = new Random().nextInt(100);

							if (random < 40) {
								List<Item> items = this.nowBG.getAllItem();
								if (random < 15) {
									items.add(new Item(ob.getX(), ob.getY(), 0));
								} else if (random < 30) {
									items.add(new Item(ob.getX(), ob.getY(), 1));
								} else if (random < 40) {
									items.add(new Item(ob.getX(), ob.getY(), 2));
								}
								this.nowBG.setAllItem(items);
							}
						}
					}
				}
			}
		}

	}

	/**
	 * wenn der spieler ueber ein item laeuft veraendern sich variablen des
	 * Spielers und das item verschwindet
	 * 
	 * @param player
	 */
	public void PickUpItem(Player player) {
		int x = player.getX();
		int y = player.getY();
		for (int i = 0; i < this.nowBG.getAllItem().size(); i++) {
			if (x == this.nowBG.getAllItem().get(i).getX()
					&& y == this.nowBG.getAllItem().get(i).getY()) {

				player.UseItem(this.nowBG.getAllItem().get(i).getType());
				this.nowBG.getAllItem().remove(i);
				Temp.currentGrade += 5;
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

		Config.x1 = mX;
		Config.y1 = mY;

		switch (direction) {
		case 0:

			bb.setStatus("up");
			mY -= bb.getSpeed();
			break;
		case 1:
			bb.setStatus("right");
			mX += bb.getSpeed();
			break;
		case 2:
			bb.setStatus("down");
			mY += bb.getSpeed();
			break;
		case 3:
			bb.setStatus("left");
			mX -= bb.getSpeed();
			break;
		}

		// wenn Ooobstruction tuer ist,dann darf hrein
		for (Obstruction ob : obstructions) {
			if (ob.getType() != 3) {
				if (ob.getType() == 0
						|| (ob.getType() == 1 && !bb.isWalkthroughwalls())) {
					int obX = ob.getX();
					int obY = ob.getY();
					if (mX == obX && mY == obY) {
						flag = false;
						break;
					}
				}
			}
		}
		if (flag) {
			switch (direction) {
			case 0:
				bb.upmove();
				if (!Config.netGame)
					PickUpItem(bb);
				break;
			case 1:
				bb.rightmove();
				if (!Config.netGame)
					PickUpItem(bb);
				break;
			case 2:

				bb.downmove();
				if (!Config.netGame)
					PickUpItem(bb);
				break;
			case 3:
				bb.leftmove();
				if (!Config.netGame)
					PickUpItem(bb);
				break;
			}

		}
	}

	/*
	 * Beim Bewegen wird diese Methoden aufgerufen
	 */
	public void moveProcess(Monster bb, int direction) {
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

			bb.setStatus("up");
			mY -= bb.getSpeed();
			break;
		case 1:
			bb.setStatus("right");
			mX += bb.getSpeed();
			break;
		case 2:
			bb.setStatus("down");
			mY += bb.getSpeed();
			break;
		case 3:
			bb.setStatus("left");
			mX -= bb.getSpeed();
			break;
		}

		// wenn Ooobstruction tuer ist,dann darf hrein
		for (Obstruction ob : obstructions) {
			if (ob.getType() != 3) {
				if (ob.getType() == 0 || (ob.getType() == 1)) {
					int obX = ob.getX();
					int obY = ob.getY();
					if (mX == obX && mY == obY) {
						flag = false;
						break;
					}
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
			this.go();

		}
		// F2 2-Player mode

		if (ke.getKeyCode() == 113) {
			if (doublePlayer == false) {
				// this.bb2 = new Player(384, 452, this.nowBG);
				// this.repaint();
				doublePlayer = true;
				Config.computerFight = true;
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
			if (netGame) {
				this.sendPlayerStatus(1);
			}
		}

		if (ke.getKeyCode() == 37) {

			this.moveProcess(bb, 3);
			{
				this.sendPlayerStatus(1);
			}
		}

		if (ke.getKeyCode() == 40) {

			this.moveProcess(bb, 2);
			{
				this.sendPlayerStatus(1);
			}
		}

		if (ke.getKeyCode() == 38) {

			this.moveProcess(bb, 0);
			{
				this.sendPlayerStatus(1);
			}
		}

		// keyListener player2
		if (ke.getKeyCode() == 68) {
			if (bb2 != null && !netGame) {
				this.moveProcess(bb2, 1);
			}
		}
		if (ke.getKeyCode() == 65 && !netGame) {
			if (bb2 != null) {
				this.moveProcess(bb2, 3);
			}
		}
		if (ke.getKeyCode() == 83 && !netGame) {
			if (bb2 != null) {
				this.moveProcess(bb2, 2);
			}
		}
		if (ke.getKeyCode() == 87 && !netGame) {
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
				if (bombcount == 0)
					this.sendPlayerStatus(31);
				else if (bombcount == 1)
					this.sendPlayerStatus(32);
				this.PlaySound(MySound.bombSet, -1);
				this.bombs[bombcount].setCountdown(70);
				this.bombs[bombcount].setShowImage(StaticValue.allBoomImage
						.get(0));

				bombcount = (bombcount + 1) % bb.getBombcapacity();
			}
		}
		// 2 Player legt Bombe wenn er "j" drueckt
		if (ke.getKeyCode() == 74 && bb2 != null) {
			// computergame or netgame return kein Ausfuehren
			if (netGame) {
				return;
			}
			if (this.bombs2[bombcount2].getCountdown() == 0) {
				this.PlaySound(MySound.bombSet, -1);
				Bomb bomb = this.bombs2[bombcount2];
				this.bombs2[bombcount2].setX(this.bb2.getX());
				this.bombs2[bombcount2].setY(this.bb2.getY());
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				// for (int i = 0; i < obstructions.size(); i++) {
				// Obstruction ob = obstructions.get(i);
				// int obX = ob.getX();
				// int obY = ob.getY();
				// int bombX = bomb.getX();
				// int bombY = bomb.getY();
				//
				// // this.destoryObCheck(bomb, obstructions, bb2);
				// this.repaint();
				//
				// }
				this.bombs2[bombcount2].setCountdown(70);
			}

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
		// if (ke.getKeyCode() == 39) {
		// this.bb.rightstop();
		// }
		//
		// if (ke.getKeyCode() == 37) {
		// this.bb.leftstop();
		// }
		//
		// if (ke.getKeyCode() == 38) {
		// this.bb.upstop();
		// }
		//
		// if (ke.getKeyCode() == 40) {
		// this.bb.downstop();
		// }
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * @param Return
	 *            true or false ,if the player enter into the Ausgang, return
	 *            true,the player win and gameover
	 */
	public boolean ifFindAusgang(Player pp) {
		boolean flag = false;

		if (pp.getX() == Config.dx && pp.getY() == Config.dy
				&& Config.AusgangShow) {
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

	/**
	 * Sammele LebensItems auf
	 * 
	 * @param bb
	 */
	public void ifGetBuff(Player bb) {
		int bbX = bb.getX();
		int bbY = bb.getY();
		// if (Config.bomb != null) {
		// int bombx = Config.bomb.getX();
		// int bomby = Config.bomb.getY();
		//
		// if (bbX == bombx && bbY == bomby) {
		//
		// if (bb != null) {
		//
		// new BombBuff(bb);
		// System.out.println("get a bomb£º" + Config.exist);
		//
		// Config.exist--;
		//
		// Temp.currentGrade += 5;
		// Config.bomb = null;
		// System.out.println("Do you get a bomb?");
		// }
		//
		// }
		//
		// }
		for (int i = 0; i < Config.lifes.size(); i++) {
			Life life = Config.lifes.get(i);
			int x = life.getX();
			int y = life.getY();
			if (bbX == x && bbY == y) {
				if (bb != null) {
					bb.setLife(bb.getLife() + 1);

					Config.exist--;

					Temp.currentGrade += 5;
					Config.lifes.remove(life);
					i--;

				}
			}
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			this.repaint();
			if (this.bb != null)
				this.ifGetBuff(bb);
			if (this.bb2 != null)
				this.ifGetBuff(bb2);
			boolean flag1 = false;

			boolean flag2 = false;

			if (this.bb != null) {

				flag1 = this.ifFindAusgang(this.bb);

				if (flag1 && Config.AusgangShow == true)
					logoString = "player1 win !";

			}
			if (this.isKilledByMonster(this.bb, vc))
				logoString = "Game Over!";

			if (this.bb2 != null) {
				if (this.isKilledByMonster(this.bb2, vc))
					logoString = "Game Over!";
				flag2 = this.ifFindAusgang(this.bb2);
				if (flag2 && Config.AusgangShow == true)
					logoString = "player2 win !";
			}

			if (((Config.AusgangShow == true && (flag1 || flag2)) || (!logoString
					.equals("let's go!") && !logoString.equals("")))) {
				// System.out.println(logoString);
				this.startGame = false;
				Config.startGame = false;
				MyFrame.doublePlayer = false;

				bb = null;
				bb2 = null;
				int count = 50;
				while (!startGame && count > 0) {
					this.startGame = false;
					Config.startGame = false;
					Graphics g = this.getGraphics();
					g.setColor(Color.blue);
					g.setFont(new Font("Arial", Font.BOLD, 20));
					g.drawString(logoString, 200, 150);
					try {
						Thread.sleep(100);
						Config.AusgangShow = false;

						for (int i = 0; i < 4; i++) {
							this.bombs[i].setCountdown(0);
							this.bombs2[i].setCountdown(0);
							this.bombs[i].Disappear();
							this.bombs2[i].Disappear();
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// for (int i = 0; i < 2; i++) {
					// if (this.bombs[i].getCountdown() > 0) {
					// this.bombs[i].Decreasecountdown();
					// if (this.bombs[i].getCountdown() == 20) {
					// this.bombs[i].Explode();
					// }
					// if (this.bombs[i].getCountdown() == 0) {
					// this.bombs[i].Disappear();
					// }
					//
					// }
					//
					// }
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

			try {// bei sleep 50 funktioniert kettenexplosion nicht mehr
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Erzeuge 2. Spieler bei 0 68 wenn es noch keinen 2. spieler gibt
	 */
	public void doublePlayer() {
		if (doublePlayer == false) {
			this.bb2 = new Player(0, 68, this.nowBG, bombs2, this);
			this.repaint();
			doublePlayer = true;
		}
	}

	/**
	 * starte netGame
	 */
	public void netGame() {
		netGame = true;
		doublePlayer();
		go();
		process = new Process();
		ObjectContainer.process = process;
	}

	/**
	 * Spiel starten
	 */
	public void go() {

		MySound.stop();
		this.PlaySound(MySound.bg, -1);

		this.gameover = false;
		if (Config.select == 0)
			Config.AusgangShow = false;

		// this.allBG.clear();
		// init bomb
		this.bombs[0] = new Bomb(0, -20000, 0);
		this.bombs[1] = new Bomb(0, -20000, 0);
		this.bombs2[0] = new Bomb(0, -20000, 0);
		this.bombs2[1] = new Bomb(0, -20000, 0);
		this.logoString = "";

		// for (int i = 1; i <= 5; i++) {
		//
		// this.allBG.add(new BackGround(level));
		// this.nowBG = this.allBG.get(0);
		// this.repaint();
		// }

		if (doublePlayer == true) {
			// fuer Player2
			int x2 = Config.point2[0];
			int y2 = Config.point2[1];
			this.bb2 = new Player(x2, y2, this.nowBG, bombs2, this);

			this.repaint();
		}
		int x1 = Config.point1[0];
		int y1 = Config.point1[1];
		this.bb = new Player(x1, y1, this.nowBG, bombs, this);
		// hier senden wir die Koordinaten wenn oline game ist
		if (ObjectContainer.olserver != null) {
			this.sendPlayerStatus(2);
		}
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
		startGame = true;
		Config.startGame = startGame;
		if (this.netGame && ObjectContainer.olclient != null) {

		} else {
			new Global(this).start();
		}
	}

	public Player getBb2() {
		return bb2;
	}

	public void setBb2(game.bomberman.Player bb2) {
		this.bb2 = bb2;
	}

	/**
	 * Informationen senden
	 * 
	 * @param type
	 *            0 fuer Bomb Type 1 fuer Koordinaten der Player
	 */

	public void sendPlayerStatus(int type) {
		// wenn nicht online game return

		if (ObjectContainer.olserver == null
				&& ObjectContainer.olclient == null)
			return;
		int s = 0;
		if (bb.getX() < 0)
			bb.setX(0);
		if (bb.getX() > 432)
			bb.setX(432);
		if (bb.getY() < 24)
			bb.setY(24);
		if (bb.getY() > 452)
			bb.setY(452);
		int b1X = this.bombs[0].getX();
		int b1Y = this.bombs[0].getY();
		int b2X = this.bombs[1].getX();
		int b2Y = this.bombs[1].getY();
		String str = "";
		if (type == 1) {
			// str = "1x:" + bb.getX() + "_y:" + bb.getY() + "_s:"
			// + bb.getStatus() + "_b1:" + b1X + "|" + b1Y + "_b2:" + b2X
			// + "|" + b2Y;
			str = "1x:" + bb.getX() + "_y:" + bb.getY() + "_s:"
					+ bb.getStatus();

		} else if (type == 31) {
			str = "311x:" + bombs[0].getX() + "_y:" + bombs[0].getY();

		} else if (type == 32) {
			str = "312x:" + bombs[1].getX() + "_y:" + bombs[1].getY();
		} else if (type == 2) {

			str = "2x:" + Config.dx + "_y:" + Config.dy;

		}
		System.out.println("send information£º" + str);

		if (ObjectContainer.olclient != null) {
			ObjectContainer.olclient.send(str);
		}
		if (ObjectContainer.olserver != null) {

			ObjectContainer.olserver.send(str);

		}
	}
}
