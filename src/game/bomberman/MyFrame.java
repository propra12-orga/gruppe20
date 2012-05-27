package game.bomberman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements KeyListener, Runnable {
	private Boolean doublePlayer = false;
	private Boolean startGame = false;
	@SuppressWarnings("unused")
	private Boolean gameover = true;

	// the Ausgang object
	private Obstruction Ausgang = null;
	// the Ausgang is hidden in a box , if the box is burst , show the Ausgang
	private boolean AusgangShow = false;
	// if has winner or game over ,draw this string
	private String logoString = "";
	// the Ausgang location
	private static int AusgangX = -100, AusgangY = -100;

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

	// Alle Senze in List speicher
	private List<BackGround> allBG = new ArrayList<BackGround>();
	// angesicht Senze
	private BackGround nowBG = null;

	// zur Zeit nicht benutzete isStart
	// private boolean isStart = false;

	private Player bb = null;
	private Player bb2 = null;
	private Thread t = new Thread(this);
	// Array fuer Bomben
	private Bomb[] bombs = new Bomb[2];
	private Bomb[] bombs2 = new Bomb[2];

	private int bombcount = 0;
	private int bombcount2 = 0;

	/**
	 * 
	 * Main Methode ist hier
	 */

	public static void main(String[] args) {
		new MyFrame();

	}

	/**
	 * Erzeugen Fenster
	 */

	public MyFrame() {

		// Fenster
		this.setTitle("Bombermann");
		this.setSize(528, 600);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 528) / 2, (height - 600) / 2);
		this.setResizable(false);

		// Instalisieren alle BufferImage
		StaticValue.init();

		// Bilden allen Senze
		for (int i = 1; i <= 6; i++) {

			this.allBG.add(new BackGround(i, i == 5 ? true : false));
		}

		// Setzen Senz(1) als angesichte Senze
		this.nowBG = this.allBG.get(0);

		// Figurobjekt erzeugen

		// initialisiere Bomben
		this.bombs[0] = new Bomb(0, -20, 0);
		this.bombs[1] = new Bomb(0, -20, 0);
		this.bombs2[0] = new Bomb(0, -20, 0);
		this.bombs2[1] = new Bomb(0, -20, 0);

		t.start();

		this.addKeyListener(this);
		this.addKeyListener(new player2KeyListener());

		// Beende das Pragramm bei Schliessen des Spielfelds
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);

	}

	/**
	 * override the paint method extends JFrame
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		// String als Hinweise
		g.drawString("press F1 start game ,F2 double player, F4 game over",
				100, 578);

		// TODO Auto-generated method stub
		// temporal bufferedImage
		// BufferedImage zu erzeugen
		BufferedImage image = new BufferedImage(528, 548,
				BufferedImage.TYPE_3BYTE_BGR);
		// g2 uebernimmt image
		Graphics g2 = image.getGraphics();

		// Malen BackGroundImage
		g2.drawImage(this.nowBG.getBgImage(), 0, 20, this);

		// Malen Obstruction
		Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();

		/*
		 * if (Ausgang == null) { for (Obstruction ob :
		 * this.nowBG.getAllObstruction()) {
		 * 
		 * if (ob.getType() == 3) { this.Ausgang = ob; }
		 * 
		 * } }
		 */

		// Ausgang type:3
		while (iter.hasNext()) {
			Obstruction ob = iter.next();
			if (ob.getType() == 3 && !AusgangShow) {
				this.Ausgang = ob;
				this.Ausgang.setX(-100);
				this.Ausgang.setY(-100);
				/*
				 * Ausgang.setX(-100); Ausgang.setY(-100);
				 */
			}
			g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);

		}

		// Malen BufferImage in Fenster(Frame)
		// g.drawImage(image, 0, 0, this);

		// zeichne die Bomben
		this.drawBombs(g2, this.bombs);
		this.drawBombs(g2, this.bombs2);

		// zeichne die Players in Spielfeld
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
	}

	// Flag: ob Player sterbt
	public boolean ifKillplayer(Player bb, Bomb bomb) {
		boolean flag = false;
		int bombX = bomb.getX();
		int bombY = bomb.getY();
		int obX = bb.getX();
		int obY = bb.getY();
		if ((bombX - 48 * 2 < obX && obX < bombX + 48 * 3 - 5 && (obY < bombY + 2
				&& bombY + 2 <= obY + 45 || (bombY + 45 < obY + 45 && bombY + 35 > obY)))
				|| (bombY - 48 * 3 < obY && obY < bombY + 48 * 3 - 5 && (obX < bombX + 2
						&& bombX + 2 <= obX + 45 || (bombX + 45 < obX + 45 && bombX + 35 > obX)))) {
			flag = true;
		}
		return flag;
	}

	// zeichen Bombs, wenn Obstruction in the Gebiet von Bombs.
	@SuppressWarnings("static-access")
	public void drawBombs(Graphics g2, Bomb[] bombs) {
		for (int i = 0; i <= 1; i++) {
			g2.drawImage(bombs[i].getShowImage(), bombs[i].getX(),
					bombs[i].getY(), this);
			// Zeichne Explosion
			if (0 < bombs[i].getCountdown() & bombs[i].getCountdown() < 21) {
				// invoke the mothod if kill player
				if (startGame) {
					boolean f1 = false;
					if (bb != null)
						f1 = this.ifKillplayer(bb, bombs[i]);
					if (f1) {
						System.out.println("----------------true1");
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
						System.out.println("----------------true2");
						startGame = false;
						if (bb != null)
							logoString = "player1 win!";
						else
							logoString = "Game over !";
					}
				}

				// remove Obstruction,wenn bomm explodiert
				// obstruction.remove(ob)
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				for (int a = 0; a < obstructions.size(); a++) {
					Obstruction ob = this.nowBG.getAllObstruction().get(a);
					if (ob != null && ob.isRemove()) {

						if (ob.getX() == this.AusgangX
								&& ob.getY() == this.AusgangY) {
							Ausgang.setX(ob.getX());
							Ausgang.setY(ob.getY());
							this.AusgangShow = true;
							System.out.println(Ausgang.getX() + "men"
									+ Ausgang.getY());
							System.out.println(this.AusgangX + "aus"
									+ this.AusgangY);

						}
						obstructions.remove(ob);
					}

				}
				// BoomImage
				for (int j = 1; j <= 2; j++) {
					g2.drawImage(StaticValue.allBoomImage.get(2),
							bombs[i].getX(), bombs[i].getY() + j * 48, this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							bombs[i].getX(), bombs[i].getY() - j * 48, this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							bombs[i].getX() + j * 48, bombs[i].getY(), this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							bombs[i].getX() - j * 48, bombs[i].getY(), this);
				}

			}

		}

	}

	// KeyListen Event
	@Override
	public void keyPressed(KeyEvent ke) {
		// press F1
		if (ke.getKeyCode() == 112) {
			// F1= gamestart
			startGame = true;
			this.gameover = false;
			this.AusgangShow = false;
			// reloead
			this.allBG.clear();
			this.logoString = "";
			// reload
			for (int i = 1; i <= 5; i++) {

				this.allBG.add(new BackGround(i, i == 5 ? true : false));
				this.nowBG = this.allBG.get(0);
				this.repaint();
			}
			// zeichen Player 1
			this.bb = new Player(48, 68, this.nowBG);
			// zeichen Player 2
			if (doublePlayer == true)
				this.bb2 = new Player(432, 452, this.nowBG);
			// Let's Go
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
		// Flag: doubleplayer Press F2
		if (ke.getKeyCode() == 113) {
			if (doublePlayer == false) {
				this.bb2 = new Player(0, 68, this.nowBG);
				this.repaint();
				doublePlayer = true;
			}

		}
		// Presse F4, gameover
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
		// Flag: if Spiele nicht beginnt
		if (!startGame)
			return;
		// TODO Auto-generated method stub
		// System.out.println(ke.getKeyChar());

		// wenn man 39 druecken -->
		if (ke.getKeyCode() == 39) {
			List<Obstruction> obstructions = nowBG.getAllObstruction();
			boolean flag = true;
			// get X.Y
			for (Obstruction ob : obstructions) {
				int mX = this.bb.getX();
				int mY = this.bb.getY();
				int obX = ob.getX();
				int obY = ob.getY();
				if (obX <= mX + 48
						&& mX + 48 <= obX + 48
						&& (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) {
					flag = false;
					// this.bb.setY(ob.getY() + 46);
					break;
				}

			}
			// rightmove()
			if (flag)
				this.bb.rightmove();
		}

		// Presse key=37, rightmove()
		if (ke.getKeyCode() == 37) {
			List<Obstruction> obstructions = nowBG.getAllObstruction();
			boolean flag = true;
			for (Obstruction ob : obstructions) {
				int mX = this.bb.getX();
				int mY = this.bb.getY();
				int obX = ob.getX();
				int obY = ob.getY();
				if (obX < mX
						&& mX <= obX + 48
						&& (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) {
					flag = false;
					// this.bb.setY(ob.getY() + 46);
					break;
				}

			}
			if (flag)
				this.bb.leftmove();
		}

		// presse key=40 upmove
		if (ke.getKeyCode() == 40) {

			List<Obstruction> obstructions = nowBG.getAllObstruction();
			boolean flag = true;
			for (Obstruction ob : obstructions) {
				int mX = this.bb.getX();
				int mY = this.bb.getY();
				int obX = ob.getX();
				int obY = ob.getY();
				if (obY <= mY + 48
						&& mY + 48 <= obY + 48
						&& (obX < mX + 2 && mX + 2 <= obX + 45 || (mX + 45 < obX + 45 && mX + 45 > obX))) {
					flag = false;
					// this.bb.setY(ob.getY() + 46);
					break;
				}

			}
			if (flag)
				this.bb.upmove();
		}

		// presse key=38 Downmove
		if (ke.getKeyCode() == 38) {

			List<Obstruction> obstructions = nowBG.getAllObstruction();
			boolean flag = true;
			for (Obstruction ob : obstructions) {
				int mX = this.bb.getX();
				int mY = this.bb.getY();
				int obX = ob.getX();
				int obY = ob.getY();
				if (obY < mY
						&& mY <= obY + 48
						&& (obX < mX + 2 && mX + 2 <= obX + 45 || (mX + 45 < obX + 45 && mX + 45 > obX))) {
					flag = false;
					// this.bb.setY(ob.getY() + 46);
					break;
				}

			}
			if (flag)
				this.bb.downmove();

		}

		// Wenn Leertaste gedrueckt wird Setzte Bombe
		// KeyPressed Space(32)
		if (ke.getKeyCode() == 32) {
			if (this.bombs[bombcount].getCountdown() == 0) {
				Bomb bomb = this.bombs[bombcount];
				this.bombs[bombcount].setX(this.bb.getX());
				this.bombs[bombcount].setY(this.bb.getY());
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				for (int i = 0; i < obstructions.size(); i++) {
					Obstruction ob = obstructions.get(i);
					int obX = ob.getX();
					int obY = ob.getY();
					int bombX = bomb.getX();
					int bombY = bomb.getY();
					/*
					 * System.out.println("bomb:" + bomb.getX() + " " +
					 * bomb.getY()); System.out.println("s:" + ob.getX() + " " +
					 * ob.getY());
					 */

					if ((bombX - 48 * 2 < obX && obX < bombX + 48 * 3 - 5 && (obY < bombY + 2
							&& bombY + 2 <= obY + 45 || (bombY + 45 < obY + 45 && bombY + 35 > obY)))
							|| (bombY - 48 * 3 < obY
									&& obY < bombY + 48 * 3 - 5 && (obX < bombX + 2
									&& bombX + 2 <= obX + 45 || (bombX + 45 < obX + 45 && bombX + 35 > obX)))) {

						if (ob.getType() == 0)
							ob.setRemove(true);
						this.repaint();
					}

				}
				/*
				 * this.bombs[bombcount].setX(this.bb.getX() + 22 -
				 * (this.bb.getX() + 22) % 48); // durch modulo 48 wird //
				 * Position d bomben // automatisch ans // Spielfeld Raster //
				 * angepasst this.bombs[bombcount].setY(this.bb.getY() -
				 * this.bb.getY() % 48 + 20);
				 */

				this.bombs[bombcount].setCountdown(70);
				this.bombs[bombcount].setShowImage(StaticValue.allBoomImage
						.get(0));
				bombcount = (bombcount + 1) % 2;
			}
		}

		// Player2 kommt
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
					/*
					 * System.out.println("bomb:" + bomb.getX() + " " +
					 * bomb.getY()); System.out.println("s:" + ob.getX() + " " +
					 * ob.getY());
					 */
					if ((bombX - 48 * 2 < obX && obX < bombX + 48 * 3 - 5 && (obY < bombY + 2
							&& bombY + 2 <= obY + 45 || (bombY + 45 < obY + 45 && bombY + 35 > obY)))
							|| (bombY - 48 * 3 < obY
									&& obY < bombY + 48 * 3 - 5 && (obX < bombX + 2
									&& bombX + 2 <= obX + 45 || (bombX + 45 < obX + 45 && bombX + 35 > obX)))) {
						if (ob.getType() == 0)
							ob.setRemove(true);
						this.repaint();
					}

				}
				/*
				 * this.bombs[bombcount].setX(this.bb.getX() + 22 -
				 * (this.bb.getX() + 22) % 48); // durch modulo 48 wird //
				 * Position d bomben // automatisch ans // Spielfeld Raster //
				 * angepasst this.bombs[bombcount].setY(this.bb.getY() -
				 * this.bb.getY() % 48 + 20);
				 */

				this.bombs2[bombcount2].setCountdown(70);
				this.bombs2[bombcount2].setShowImage(StaticValue.allBoomImage
						.get(0));
				bombcount2 = (bombcount2 + 1) % 2;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if (!startGame)
			return;
		// TODO Auto-generated method stub
		// wenn man auslassen stopmove
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

		/*
		 * if ((obX <= mX + 48 && mX + 48 <= obX + 48 && (obY < mY + 2 && mY + 2
		 * <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) || (obX < mX
		 * && mX <= obX + 48 && (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45
		 * < obY + 45 && mY + 45 > obY))) || (obY <= mY + 48 && mY + 48 <= obY +
		 * 48 && (obX < mX + 2 && mX + 2 <= obX + 45 || (mX + 45 < obX + 45 &&
		 * mX + 45 > obX))) || (obY < mY && mY <= obY + 48 && (obX < mX + 2 &&
		 * mX + 2 <= obX + 45 || (mX + 45 < obX + 45 && mX + 45 > obX))))
		 */
		if ((AusgangX <= pp.getX() + 48 && pp.getX() + 48 <= AusgangX + 48 && (AusgangY < pp
				.getY() + 2 && pp.getY() + 2 <= AusgangY + 45 || (pp.getY() + 45 < AusgangY + 45 && pp
				.getY() + 45 > AusgangY)))
				|| (AusgangX < pp.getX() && pp.getX() <= AusgangX + 48 && (AusgangY < pp
						.getY() + 2 && pp.getY() + 2 <= AusgangY + 45 || (pp
						.getY() + 45 < AusgangY + 45 && pp.getY() + 45 > AusgangY)))
				|| (AusgangY <= pp.getY() + 48
						&& pp.getY() + 48 <= AusgangY + 48 && (AusgangX < pp
						.getX() + 2 && pp.getX() + 2 <= AusgangX + 45 || (pp
						.getX() + 45 < AusgangX + 45 && pp.getX() + 45 > AusgangX)))
				|| (AusgangY < pp.getY() && pp.getY() <= AusgangY + 48 && (AusgangX < pp
						.getX() + 2 && pp.getX() + 2 <= AusgangX + 45 || (pp
						.getX() + 45 < AusgangX + 45 && pp.getX() + 45 > AusgangX))))
			flag = true;
		return flag;

	}

	/**
	 * 
	 * bombs if disappear or explode
	 */
	//
	public void bombStatus(Bomb[] bombs) {
		for (int i = 0; i < 2; i++) {
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
			// wenn gewonnen
			this.repaint();
			// player1Êgewonnen
			boolean flag1 = false;
			// player2Êgewonnen
			boolean flag2 = false;
			// zeichen String
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
			//
			if (((this.AusgangShow == true && (flag1 || flag2)) || (!logoString
					.equals("let's go!") && !logoString.equals("")))) {
				System.out.println(logoString);
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

	// KeyPresse Event fuer Player2
	class player2KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			if (bb2 == null)
				return;
			super.keyPressed(ke);
			if (ke.getKeyCode() == 68) {
				List<Obstruction> obstructions = nowBG.getAllObstruction();
				boolean flag = true;
				for (Obstruction ob : obstructions) {
					int mX = bb2.getX();
					int mY = bb2.getY();
					int obX = ob.getX();
					int obY = ob.getY();
					if (obX <= mX + 48
							&& mX + 48 <= obX + 48
							&& (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) {
						flag = false;
						// bb2.setY(ob.getY() + 46);
						break;
					}

				}
				if (flag)
					bb2.rightmove();
			}

			if (ke.getKeyCode() == 65) {
				List<Obstruction> obstructions = nowBG.getAllObstruction();
				boolean flag = true;
				for (Obstruction ob : obstructions) {
					int mX = bb2.getX();
					int mY = bb2.getY();
					int obX = ob.getX();
					int obY = ob.getY();
					if (obX < mX
							&& mX <= obX + 48
							&& (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) {
						flag = false;
						// bb2.setY(ob.getY() + 46);
						break;
					}

				}
				if (flag)
					bb2.leftmove();
			}

			if (ke.getKeyCode() == 83) {

				List<Obstruction> obstructions = nowBG.getAllObstruction();
				boolean flag = true;
				for (Obstruction ob : obstructions) {
					int mX = bb2.getX();
					int mY = bb2.getY();
					int obX = ob.getX();
					int obY = ob.getY();
					if (obY <= mY + 48
							&& mY + 48 <= obY + 48
							&& (obX < mX + 2 && mX + 2 <= obX + 45 || (mX + 45 < obX + 45 && mX + 45 > obX))) {
						flag = false;
						// bb2.setY(ob.getY() + 46);
						break;
					}

				}
				if (flag)
					bb2.upmove();
			}

			if (ke.getKeyCode() == 87) {

				List<Obstruction> obstructions = nowBG.getAllObstruction();
				boolean flag = true;
				for (Obstruction ob : obstructions) {
					int mX = bb2.getX();
					int mY = bb2.getY();
					int obX = ob.getX();
					int obY = ob.getY();
					if (obY < mY
							&& mY <= obY + 48
							&& (obX < mX + 2 && mX + 2 <= obX + 45 || (mX + 45 < obX + 45 && mX + 45 > obX))) {
						flag = false;
						// bb2.setY(ob.getY() + 46);
						break;
					}

				}
				if (flag)
					bb2.downmove();

			}
		}

	}

}
