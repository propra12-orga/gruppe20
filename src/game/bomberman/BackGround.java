package game.bomberman;



import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class BackGround {
	
		//��ǰ��������ʾͼƬ
		private BufferedImage bgImage=null;	
	


		public BufferedImage getBgImage() {
			this.bgImage=StaticValue.bgImage;
			return bgImage;
		}



	
		//ͨ������������
		//ȫ���ĵ���
		private ArrayList Foemans=new ArrayList();
		
		//����
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
