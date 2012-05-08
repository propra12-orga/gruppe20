package game.bomberman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;

public class StaticValue {


	
	public static BufferedImage play1= null;

	//public static BufferedImage startImage = null;
	//public static BufferedImage endImage = null;
	
	//public static List<BufferedImage> allObstructionImage=new ArrayList<BufferedImage>();

	public static BufferedImage foemans1= null;


	public static BufferedImage block = null;
	
	public static BufferedImage bomb = null;
	
	public static BufferedImage boom = null;
	
	public static BufferedImage fire = null;
	
	public static BufferedImage rock = null;
	
	public static BufferedImage bgImage = null;	

	public static String imagePath = System.getProperty("user.dir") + "/pics/";//Actually Position
	
	/*alle bilder initialisieren*/
	public static void init() {
		
		try {
			play1=ImageIO.read(new File(imagePath+"play1.png"));
			bomb=ImageIO.read(new File(imagePath+"bomb.png"));
			boom=ImageIO.read(new File(imagePath+"boom.png"));
			fire=ImageIO.read(new File(imagePath+"fire.png"));
			foemans1=ImageIO.read(new File(imagePath+"foemans1.png"));
			rock=ImageIO.read(new File(imagePath+"rock.png"));
			bgImage=ImageIO.read(new File(imagePath+"bgImage.png"));
			block=ImageIO.read(new File(imagePath+"block.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
