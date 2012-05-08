package game.bomberman;



import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class BackGround {
	
		//当前场景的显示图片
		private BufferedImage bgImage=null;	
	


		public BufferedImage getBgImage() {
			this.bgImage=StaticValue.bgImage;
			return bgImage;
		}



	
		//通过集合来保存
		//全部的敌人
		private ArrayList Foemans=new ArrayList();
		
		//泛型
		private ArrayList<Obstruction> allObstruction=new ArrayList<Obstruction>();
		
		private ArrayList removeFoemans=new ArrayList();
		
		private ArrayList removedObstruction=new ArrayList();
		
		public BackGround(){
					
			bgImage=StaticValue.bgImage;
			//this.allObstruction.add(new Obstruction(48,48));
			
		}
		
		
		
		public void reset(){
			
		}


}
