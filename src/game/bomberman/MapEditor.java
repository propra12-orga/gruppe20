package game.bomberman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MapEditor extends JFrame implements Runnable {
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

	private BufferedImage[] showimage = new BufferedImage[6];

	public MapEditor() {
		this.setSize(528, 550);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		this.setResizable(false);
		// Beende das Pragramm bei Schliessen des Spielfelds
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sichtbar
		this.setVisible(true);
		try {
			this.showimage[0] = ImageIO.read(new File(System
					.getProperty("user.dir") + "/pics/" + "path" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		g.drawString("I am a fucking Genious", 200, 520);
		BufferedImage image = new BufferedImage(528, 500,
				BufferedImage.TYPE_3BYTE_BGR);
		// g2 uebernimmt image
		Graphics g2 = image.getGraphics();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				g2.drawImage(showimage[0], 48 + 48 * i, 48 * j + 20, this);
				g.drawImage(image, 0, 0, this);
			}
		}
	}

	@Override
	public void run() {
		while (true) {

			this.repaint();
		}
	}

}