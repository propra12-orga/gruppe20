package game.bomberman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements KeyListener, Runnable {

	// Alle Senze in List speicher
	private List<BackGround> allBG = new ArrayList<BackGround>();
	// angesicht Senze
	private BackGround nowBG = null;
	// zur Zeit nicht benutzete isStart
	// private boolean isStart = false;

	private Player bb = null;
	private Thread t = new Thread(this);
	// Array fuer Bomben
	private Bomb[] bombs = new Bomb[2];

	private int bombcount = 0;

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
		this.setSize(480, 500);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);

		// Instalisieren alle BufferImage
		StaticValue.init();

		// Bilden allen Senze
		for (int i = 1; i <= 5; i++) {

			this.allBG.add(new BackGround(i, i == 5 ? true : false));
		}

		// Setzen Senz(1) als angesichte Senze
		this.nowBG = this.allBG.get(0);

		// Figurobjekt erzeugen
		this.bb = new Player(0, 68);
		// initialisiere Bomben
		this.bombs[0] = new Bomb(0, -20);
		this.bombs[1] = new Bomb(0, -20);
		t.start();
		this.repaint();
		this.addKeyListener(this);

		// Beende das Pragramm bei Schliessen des Spielfelds
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);

	}

	// das Spielfeld zeichnen
	@Override
	public void paint(Graphics g) {
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
		g.drawImage(image, 0, 0, this);

		// zeichne die Bomben
		for (int i = 0; i <= 1; i++) {
			g2.drawImage(this.bombs[i].getShowImage(), this.bombs[i].getX(),
					this.bombs[i].getY(), this);
		}
		// zeichne die Player in Spielfeld
		g2.drawImage(this.bb.getShowImage(), this.bb.getX(), this.bb.getY(),
				this);
		// zeichne die Pufferbild in Spiefeld
		g.drawImage(image, 0, 0, this);

		// this.nowBackground

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		// System.out.println(ke.getKeyChar());
		System.out.println(ke.getKeyCode());

		// wenn man 39 dr¨¹cken -->
		if (ke.getKeyCode() == 39) {
			this.bb.rightmove();
		}

		if (ke.getKeyCode() == 37) {
			this.bb.leftmove();
		}

		if (ke.getKeyCode() == 40) {
			this.bb.upmove();
		}

		if (ke.getKeyCode() == 38) {
			this.bb.downmove();
		}
		// Wenn Leertaste gedrückt wird Setzte Bombe
		if (ke.getKeyCode() == 32) {
			this.bombs[bombcount].setX(this.bb.getX() + 22
					- (this.bb.getX() + 22) % 48);
			this.bombs[bombcount].setY(this.bb.getY() - this.bb.getY() % 48
					+ 20);
			bombcount = (bombcount + 1) % 2;
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
