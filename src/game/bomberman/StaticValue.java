package game.bomberman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {

	/*
	 * Lade alle Bilder als BufferedImage
	 */

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

	// speicher StartImage
	public static BufferedImage startImage = null;
	// speicher StartImage
	public static BufferedImage bgImage = null;
	// speicher End Image
	public static BufferedImage endImage = null;
	// speicher Player Dead Image
	public static BufferedImage playerDeadImage = null;

	public static BufferedImage play1 = null;

	public static List<BufferedImage> allBomberman = new ArrayList<BufferedImage>();

	public static BufferedImage foemans1 = null;
	public static BufferedImage block = null;
	public static BufferedImage rock = null;
	public static BufferedImage bomb = null;
	public static BufferedImage boom = null;
	public static BufferedImage fire = null;

	// der Pfad von den Bildern
	public static String imagePath = System.getProperty("user.dir") + "/pics/";

	/* alle bilder initialisieren */
	public static void init() {

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
		for (int i = 1; i <= 3; i++) {
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
		for (int i = 1; i <= 5; i++) {
			try {
				allBackGroundImage.add(ImageIO.read(new File(imagePath + "bg"
						+ i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Alle einzele Bilder, die nicht in eine List gespeichert werden
		try {
			startImage = ImageIO.read(new File(imagePath + "start.png"));
			endImage = ImageIO.read(new File(imagePath + "end.png"));
			playerDeadImage = ImageIO.read(new File(imagePath + "p3.png"));
			bgImage = ImageIO.read(new File(imagePath + "bg1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			/* alle Bilder einlesen und Speichern */
			play1 = ImageIO.read(new File(imagePath + "play1.png"));
			bomb = ImageIO.read(new File(imagePath + "bomb.png"));
			boom = ImageIO.read(new File(imagePath + "boom.png"));
			fire = ImageIO.read(new File(imagePath + "fire.png"));
			foemans1 = ImageIO.read(new File(imagePath + "foemans1.png"));
			rock = ImageIO.read(new File(imagePath + "rock.png"));
			bgImage = ImageIO.read(new File(imagePath + "bgImage.png"));
			block = ImageIO.read(new File(imagePath + "block.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
