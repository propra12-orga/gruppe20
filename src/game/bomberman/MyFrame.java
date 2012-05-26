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

	// Flag:ob doublePlayer
	private Boolean doublePlayer = false;
	// Flag:ob Spiele beginnt
	private Boolean startGame = false;
	// Flag:ob Spiele endet
	private Boolean gameover = true;
	// logoString
	private String logoString = "";

	// AusgangX
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

	// Player malen
	private Player bb = null;
	private Player bb2 = null;
	private Thread t = new Thread(this);

	// Array fuer Bomben
	private Bomb[] bombs = new Bomb[2];
	private int bombcount = 0;

	// Main Methode(Game Enterance)
	public static void main(String[] args) {
		new MyFrame();
	}

	/**
	 * Erzeugen Fenster
	 */

	public MyFrame() {

		// Fenster
		this.setTitle("Bombermann");
		this.setSize(480, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 550) / 2);
		this.setResizable(false);

		// Instalisieren alle BufferImage
		StaticValue.init();

		// Bilden allen Senze
		for (int i = 1; i <= 5; i++) {

			this.allBG.add(new BackGround(i, i == 5 ? true : false));
		}

		// Setzen Senz(1) als angesichte Senze
		this.nowBG = this.allBG.get(0);

		// initialisiere Bomben
		this.bombs[0] = new Bomb(0, -20, 0);
		this.bombs[1] = new Bomb(0, -20, 0);
		t.start();
		this.repaint();

		// KeyListener
		this.addKeyListener(this);
		// this.addKeyListener(new player2KeyListener());

		// Beende das Pragramm bei Schliessen des Spielfelds
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);

	}

	// das Spielfeld zeichnen
	@Override
	public void paint(Graphics g) {
		g.drawString("press F1 start game ,F2 double player, F4 game over",
				100, 520);
		// TODO Auto-generated method stub
		// temporal bufferedImage
		// BufferedImage zu erzeugen
		BufferedImage image = new BufferedImage(480, 500,
				BufferedImage.TYPE_3BYTE_BGR);
		// g2 uebernimmt image
		Graphics g2 = image.getGraphics();

		// Malen BackGroundImage
		g2.drawImage(this.nowBG.getBgImage(), 0, 20, this);

		// Malen Obstruction
		Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();
		while (iter.hasNext()) {
			Obstruction ob = iter.next();
			g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
		}

		// Malen BufferImage in Fenster(Frame)
		// g.drawImage(image, 0, 0, this);

		// zeichne die Bomben
		for (int i = 0; i <= 1; i++) {
			g2.drawImage(this.bombs[i].getShowImage(), this.bombs[i].getX(),
					this.bombs[i].getY(), this);
			// Zeichne Explosion
			if (0 < this.bombs[i].getCountdown()
					& this.bombs[i].getCountdown() < 21) {
				List<Obstruction> obstructions = this.nowBG.getAllObstruction();
				for (int a = 0; a < obstructions.size(); a++) {
					Obstruction ob = this.nowBG.getAllObstruction().get(a);
					if (ob != null && ob.isRemove())
						obstructions.remove(ob);
				}
				for (int j = 1; j <= 2; j++) {
					g2.drawImage(StaticValue.allBoomImage.get(2),
							this.bombs[i].getX(),
							this.bombs[i].getY() + j * 48, this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							this.bombs[i].getX(),
							this.bombs[i].getY() - j * 48, this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							this.bombs[i].getX() + j * 48,
							this.bombs[i].getY(), this);
					g2.drawImage(StaticValue.allBoomImage.get(2),
							this.bombs[i].getX() - j * 48,
							this.bombs[i].getY(), this);
				}

			}

		}
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

	}

	// KeyPressed Event
	@Override
	public void keyPressed(KeyEvent ke) {
		// Keypressed F1
		if (ke.getKeyCode() == 112) {
			// reload Background
			this.allBG.clear();
			for (int i = 1; i <= 5; i++) {

				this.allBG.add(new BackGround(i, i == 5 ? true : false));
				this.nowBG = this.allBG.get(0);
				this.repaint();
			}
			// Player 1(112) kommt in Spielfeld
			this.bb = new Player(0, 68, this.nowBG);
			int times = 20;
			while (times > 0) {
				// Zeichen hinweise "Let's Go!"
				Graphics g = this.getGraphics();
				g.setColor(Color.blue);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString("let's go !", 200, 150);
				this.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				times--;
			}
			// flag: startGame=true, gameover=false
			startGame = true;
			this.gameover = false;
		}
		// Keypressed F2(113), dann Player2 kommt ins Spielefeld
		if (ke.getKeyCode() == 113) {
			if (doublePlayer == false) {
				this.bb2 = new Player(432, 444, this.nowBG);
				this.repaint();
				doublePlayer = true;
			}

		}
		// Keypressed F4(115), Gameover
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
		// Flag: startGame= false
		if (!startGame)
			return;

		// TODO Auto-generated method stub
		// System.out.println(ke.getKeyChar());

		// Keypressed -->(39), Player rightMove
		if (ke.getKeyCode() == 39) {
			List<Obstruction> obstructions = nowBG.getAllObstruction();
			boolean flag = true;
			for (Obstruction ob : obstructions) {
				int mX = this.bb.getX(); // get Players Location
				int mY = this.bb.getY();
				int obX = ob.getX(); // get Obstructions Location
				int obY = ob.getY();
				if (obX <= mX + 48
						&& mX + 48 <= obX + 48
						&& (obY < mY + 2 && mY + 2 <= obY + 45 || (mY + 45 < obY + 45 && mY + 45 > obY))) {
					flag = false;
					// this.bb.setY(ob.getY() + 46);
					break;
				}

			}
			if (flag)
				this.bb.rightmove();
		}

		// KeyPressed <--(37), Player leftMove
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

		// KeyPressed <==(40), Player leftMove
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
		// KeyPressed 38, Player downMove
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

		// KeyPressed Space(32), Player Setzet ein Bome
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
							&& bombY + 2 <= obY + 45 || (bombY + 45 < obY + 45 && bombY + 45 > obY)))
							|| (bombY - 48 * 2 < obY
									&& obY < bombY + 48 * 3 - 5 && (obX < bombX + 2
									&& bombX + 2 <= obX + 45 || (bombX + 45 < obX + 45 && bombX + 45 > obX)))) {
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

	}

	// KeyReleased Event
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

	// TODO Auto-generated method stub
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// if win
			this.repaint();
			boolean flag = false;
			if (this.bb != null)
				flag = this.ifFindAusgang(this.bb);
			if (flag) {
				this.startGame = false;
				bb = null;
				bb2 = null;
				int count = 10;
				while (!startGame && count > 0) {
					this.startGame = false;
					Graphics g = this.getGraphics();
					g.setColor(Color.blue);
					g.setFont(new Font("Arial", Font.BOLD, 20));
					logoString = "player1 win !";
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

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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

			if (ke.getKeyCode() == 45) {
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
