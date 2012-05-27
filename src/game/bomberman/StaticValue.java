package game.bomberman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {

	/**
	 * // BufferedImage Information Lade alle Bilder als BufferedImage; *[0]
	 * allWindowImag: 0:Start Img; 1:Dead Img; 2:End Img ;
	 * 
	 * *[1] allPlayerImage: 0:stehen ; 1:nach link; 2:sterben(PlayerDead);
	 * 3:nach richt; 4: nach oben;
	 * 
	 * **[2] allEnemyImage: ;
	 * 
	 * **[3] allObstructionImage: 0:Stein;1:Block;2:Transparenzt; 3:
	 * Ausgang(Exit) ;
	 * 
	 * **[4] allItemImage: 0:BoomIterm(Boom Staerken); 1:Stern(mehr Punkt
	 * sammeln);2:Soprtchuhe(beschleunigen);
	 * 
	 * **[5] allBoomImage: 0:Boom; 1:Boom Explodieren;2: Fire
	 * 
	 * **[6] allBackGroundImag:0:Senze 1; 1:Senze2...usw
	 */

	// 0.speicher alle Fenster Image in eine List wie Start Img, End Img, Dead
	// Img
	public static List<BufferedImage> allWindowsImage = new ArrayList<BufferedImage>();
	// 1.speicher alle PlayerImage in eine List
	public static List<BufferedImage> allPlayerImage = new ArrayList<BufferedImage>();
	// 2.speicher alle Enemy Image in eine List
	public static List<BufferedImage> allEnemyImage = new ArrayList<BufferedImage>();
	// 3.Speicher alle Obstrucktion Image in eine List
	public static List<BufferedImage> allObstructionImage = new ArrayList<BufferedImage>();
	// 4.speicher alle Item Image in eine List
	public static List<BufferedImage> allItemImage = new ArrayList<BufferedImage>();
	// 5.speicher alle Boom Image in eine List
	public static List<BufferedImage> allBoomImage = new ArrayList<BufferedImage>();
	// 6.speicher alle Backgrund Image in eine List
	public static List<BufferedImage> allBackGroundImage = new ArrayList<BufferedImage>();

	// public static BufferedImage play1 = null;

	public static List<BufferedImage> allBomberman = new ArrayList<BufferedImage>();

	// der Pfad von den Bildern
	public static String imagePath = System.getProperty("user.dir") + "/pics/";

	/* alle bilder initialisieren */
	public static void init() {

		// 0.Windows Image in eine List
		for (int i = 1; i <= 4; i++) {
			try {
				// allPlayerImage.add(ImageIO.read(new
				// File(System.getProperty("user.dir")+"/pic"+i+".png")));
				allWindowsImage.add(ImageIO.read(new File(imagePath + "img" + i
						+ ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 1.Player Image in eine List
		for (int i = 1; i <= 5; i++) {
			try {
				// allPlayerImage.add(ImageIO.read(new
				// File(System.getProperty("user.dir")+"/pic"+i+".png")));
				allPlayerImage.add(ImageIO.read(new File(imagePath + "p" + i
						+ ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 2.Enemy Image in eine List
		for (int i = 1; i <= 5; i++) {
			try {
				allPlayerImage.add(ImageIO.read(new File(imagePath + "e" + i
						+ ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 3.Obstruction Image in eine List
		for (int i = 1; i <= 4; i++) {
			try {
				allObstructionImage.add(ImageIO.read(new File(imagePath + "ob"
						+ i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 4.Item Image in eine List
		for (int i = 1; i <= 3; i++) {
			try {
				allPlayerImage.add(ImageIO.read(new File(imagePath + "it" + i
						+ ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 5.Boom Image in eine List
		for (int i = 1; i <= 3; i++) {
			try {
				allBoomImage.add(ImageIO.read(new File(imagePath + "bo" + i
						+ ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 6.Backgrund Image in eine List
		for (int i = 1; i <= 6; i++) {
			try {
				allBackGroundImage.add(ImageIO.read(new File(imagePath + "bg"
						+ i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
