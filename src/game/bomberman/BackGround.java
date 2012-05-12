package game.bomberman;



import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class BackGround {
	
		//Definiere Hintergrundbild
		private BufferedImage bgImage=null;		

		//Getters
		public BufferedImage getBgImage() {
			this.bgImage=StaticValue.bgImage;
			return bgImage;
		}



	
		
		//ArrayList speichert alle Gegner
		private ArrayList Foemans=new ArrayList();
		
		//ArrayList mit generischem Datentype speichert alle Gegenstaende
		private ArrayList<Obstruction> allObstruction=new ArrayList<Obstruction>();
		
		//ArrayList speichert alle eliminierten Gegner
		private ArrayList removeFoemans=new ArrayList();
		
		//ArrayList speichert alle zerstoerten Gegenstaende
		private ArrayList removedObstruction=new ArrayList();
		
		//Konstruktor
		public BackGround(){
					
			bgImage=StaticValue.bgImage;
			//this.allObstruction.add(new Obstruction(48,48));
			
		}
		
		
		//Neustart
		public void reset(){
			
		}


}
