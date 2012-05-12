package game.bomberman;

import java.awt.image.BufferedImage;

public class Obstruction {
		
		/*Koordinaten und GetMethode*/
		private int x;
		
		public int getX() {
			return x;
		}
	
		private int y;
		
		public int getY() {
			return y;
		}
		private int type;
		
		//private int startType;	
		
		//Bild Mauer
		private BufferedImage blockImage=null;
		
		public BufferedImage getBlockImage() {
			return blockImage;
		}
		
		//Bild Stein
		private BufferedImage rockImage=null;
		
		public BufferedImage getRockImage(){
			return rockImage;
		}

		/*Konstruktor mit Koordinaten und Type*/
		public Obstruction(int x,int y,int type){
			this.x=x;
			this.y=y;
			this.type=type;
			
			setImage();
		}
		
		/*Konstruktor mit Koordinaten*/
		public Obstruction(int x,int y){
			this.x=x;
			this.y=y;
			
			setImage();
			}
		
		//
		public void reset(){
			
			this.setImage();
			
		}
		
		//Setze die gezeigten Bilder
		public void setImage(){
			//showImage=StaticValue.allObstructionImage.get(type);
			blockImage=StaticValue.block;
			
			rockImage=StaticValue.rock;
		}
}

