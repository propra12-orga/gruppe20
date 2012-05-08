package game.bomberman;

import java.awt.image.BufferedImage;

public class Obstruction {
		
		//����
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
		
		//��ʾͼƬ
		private BufferedImage blockImage=null;
		
		public BufferedImage getBlockImage() {
			return blockImage;
		}
		
		private BufferedImage rockImage=null;
		
		public BufferedImage getRockImage(){
			return rockImage;
		}

		public Obstruction(int x,int y,int type){
			this.x=x;
			this.y=y;
			this.type=type;
			
			setImage();
		}
		
		//���췽��
		public Obstruction(int x,int y){
			this.x=x;
			this.y=y;
			
			setImage();
			}
		
		//���÷���
		public void reset(){
			//�޸�Ϊԭʼ����
			//this.type=startType;
			//�ı���ʾͼƬ
			this.setImage();
			
		}
		//�������͸ı���ʵͼƬ
		public void setImage(){
			//showImage=StaticValue.allObstructionImage.get(type);
			blockImage=StaticValue.block;
			//ArrayList.get ����
			rockImage=StaticValue.rock;
		}
}

