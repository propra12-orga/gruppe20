package game.bomberman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Erzeugt Fenster in dem man eine Map erstellen kann. Man benutzt die
 * Pfeiltasten um den Cursor zu bewegen und drueckt a um einen Weg zu erstellen,
 * s um einen Stein und d um eine Box zu erstellen
 * 
 * @author KingManuel
 * 
 */
public class MapEditor extends JFrame implements KeyListener, Runnable {

	public static void main(String[] args) {

		new MapEditor();

	}

	private Thread t = new Thread(this);
	/**
	 * Zeigt auf das Kaestchen, das man veraendern kann
	 */
	private Pointer pointer;
	private static final long serialVersionUID = 1L;
	/**
	 * Jeder Wert am Eintrag im Array (i,j) steht fuer ein Objekt an den
	 * Koordinaten 48*i, 48*j auf dem Spielfeld 0=Weg/Path, 1=Stone, 2=Box,
	 * 3=player1, 4=player2, 5=Ausgang
	 */
	private int[][] map = new int[10][10];
	/**
	 * Bei Click auf die Map wird bei verschiedenen chosenobject folgende
	 * Objekte gesetzt: 0=Weg/Path, 1=Stone, 2=Box, 3=player1, 4=player2,
	 * 5=Ausgang
	 */
	private int chosenobject;

	private BufferedImage[] showimage = new BufferedImage[8];

	/**
	 * Erzeugt Fenster in dem man eine Map erstellen kann. Man benutzt die
	 * Pfeiltasten um den Cursor zu bewegen und drueckt a um einen Weg zu
	 * erstellen, s um einen Stein und d um eine Box zu erstellen
	 */
	public MapEditor() {
		this.pointer = new Pointer();
		this.setSize(528, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
		// Beende das Pragramm bei Schliessen des Spielfelds
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);
		try {
			this.showimage[0] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "path" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.showimage[1] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "rock" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[2] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "ob2" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[3] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "p1" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[4] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "pathA" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[5] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "ob1S" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[6] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "ob3D" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.showimage[7] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "p1F" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.start();
		this.addKeyListener(this);

	}

	public void paint(Graphics g) {
		g.drawString(
				"Press:  'f2' to Save 'a'  for  Path,   's'  for  Stone,   'd'  for  Box",
				100, 520);
		BufferedImage image = new BufferedImage(528, 500,
				BufferedImage.TYPE_3BYTE_BGR);
		// g2 uebernimmt image
		Graphics g2 = image.getGraphics();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				g2.drawImage(showimage[map[i][j]], 48 + 48 * i, 48 * j + 20,
						this);

			}
		}
		g2.drawImage(pointer.getShowimage(), pointer.getX(), pointer.getY(),
				this);

		g2.drawImage(showimage[4], 0, 40, this);
		g2.drawImage(showimage[5], 0, 100, this);
		g2.drawImage(showimage[6], 0, 160, this);
		g2.drawImage(showimage[7], 0, 220, this);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {

			this.repaint();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * speichert die gezeichnete Map als xml Datei "Mapeditor.xml"
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		String savePath = System.getProperty("user.dir") + "/src/";
		File file = new File(savePath + "Mapeditor.xml");
		FileWriter fw;

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			fw.write("<config>\r\n");
			// als XML speichern
			// String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
			// s = s + "<config>\r\n";
			String s = "";
			for (int i = 0; i < 10; i++) {

				for (int j = 0; j < 10; j++) {

					System.out.print(map[j][i]);

				}
				System.out.println();
			}

			for (int i = 0; i < 10; i++) {

				for (int j = 0; j < 10; j++) {

					if (map[i][j] == 2) {
						s = s + "<ob id=\"box\" x=\"" + i * 48 + "\" y=\""
								+ (j * 48 + 20) + "\"/>" + "\n";
					} else if (map[i][j] == 1) {
						s = s + "<ob id=\"stone\" x=\"" + i * 48 + "\" y=\""
								+ (j * 48 + 20) + "\"/>" + "\n";
					} else if (map[i][j] == 3) {
						Config.point1[0] = i * 48;
						Config.point1[1] = j * 48 + 20;
					}

				}

			}
			System.out.println(s);
			fw.write(s + "\r\n");
			// s = s + "</config>";
			fw.write("</config>\r\n");
			// fw.write(s);
			fw.close();
			System.out.println(file.canWrite());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Pointer {
		private int x;
		private int y;
		private BufferedImage showimage;

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

		public BufferedImage getShowimage() {
			return showimage;
		}

		public void setShowimage(BufferedImage showimage) {
			this.showimage = showimage;
		}

		public Pointer() {
			this.x = 66;
			this.y = 38;
			try {
				this.showimage = ImageIO.read(new File(System
						.getProperty("user.dir")
						+ "/pics/"
						+ "pointer"
						+ ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void leftmove() {
			if (this.x > 96)
				this.x = this.getX() - 48;
		}

		public void rightmove() {
			if (this.getX() < 480) {
				this.setX(this.getX() + 48);
			}
		}

		public void upmove() {
			if (this.y > 68)
				this.y += -48;
		}

		public void downmove() {
			if (this.y < 452)
				this.y += 48;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		/**
		 * wenn man Pfeil nach rechts drueckt rightmove -->
		 * 
		 */
		if (ke.getKeyCode() == 39) {

			this.pointer.rightmove();
		}
		/**
		 * wenn man Pfeil nach links drueckt leftmove
		 * 
		 */
		if (ke.getKeyCode() == 37) {

			this.pointer.leftmove();
		}
		/**
		 * wenn man Pfeil nach unten drueckt downmove
		 * 
		 */
		if (ke.getKeyCode() == 40) {

			this.pointer.downmove();
		}
		/**
		 * wenn man Pfeil nach oben drueckt upmove
		 * 
		 */
		if (ke.getKeyCode() == 38) {

			this.pointer.upmove();
		}
		/**
		 * wenn man "d" drueckt setze Box
		 * 
		 */
		if (ke.getKeyCode() == 68) {

			this.map[(this.pointer.getX() - 66) / 48][(this.pointer.getY() - 38) / 48] = 2;
		}
		/**
		 * wenn man "a" drueckt setze Weg
		 * 
		 */
		if (ke.getKeyCode() == 65) {

			this.map[(this.pointer.getX() - 66) / 48][(this.pointer.getY() - 38) / 48] = 0;
		}
		/**
		 * wenn man "s" drueckt setze Stein
		 * 
		 */
		if (ke.getKeyCode() == 83) {

			this.map[(this.pointer.getX() - 66) / 48][(this.pointer.getY() - 38) / 48] = 1;
		}
		/**
		 * Wenn man "f" dr¨¹ckt setze Spieler
		 */
		if (ke.getKeyCode() == 70) {
			for (int i = 0; i < 10; i++) {

				for (int j = 0; j < 10; j++) {

					if (map[i][j] == 3) {
						map[i][j] = 0;
					}
				}
			}

			this.map[(this.pointer.getX() - 66) / 48][(this.pointer.getY() - 38) / 48] = 3;

		}
		if (ke.getKeyCode() == 113) {
			try {
				this.save();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}