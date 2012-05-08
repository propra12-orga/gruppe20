package game.bomberman;

import java.awt.image.BufferedImage;

public class Obstruction {
		
		//坐标
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
		
		//显示图片
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
		
		//构造方法
		public Obstruction(int x,int y){
			this.x=x;
			this.y=y;
			
			setImage();
			}
		
		//重置方法
		public void reset(){
			//修改为原始类型
			//this.type=startType;
			//改变显示图片
			this.setImage();
			
		}
		//根据类型改变现实图片
		public void setImage(){
			//showImage=StaticValue.allObstructionImage.get(type);
			blockImage=StaticValue.block;
			//ArrayList.get 方法
			rockImage=StaticValue.rock;
		}
}

