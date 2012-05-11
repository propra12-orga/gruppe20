package game.bomberman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.Random;
public class MyFrame extends JFrame implements KeyListener,Runnable {
	
	
	

	
	private BackGround nowBackground=null;
	private Player bb=null;
	private Thread t=new Thread(this);

	public static void main(String[] args) {
		new MyFrame();
	}
	
	/*
	 * Ein Spielfeld zu erzeugen
	 * */
	public MyFrame() {
		
		this.setTitle("Bombermann");//Titel
		this.setSize(480, 480);//Die Größe des Spielfelds
		//Zentriere Spielfeld
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;//
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 480) / 2, (height - 480) / 2);
		
		//Die Größe des Spielfelds unveränderbar
		this.setResizable(false);
		//init alle bilder
		StaticValue.init();
		
		//Aktuelle Hintergrund zu erzeugen
		this.nowBackground=new BackGround();
		//Figurobjekt zu erzeugen
		this.bb=new Player(0,70);
		t.start();
		this.repaint();
		this.addKeyListener(this);
		

		//Beende das Pragramm bei Schließen des Spielfelds
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//sichtbar
		this.setVisible(true);

		
	}

	
	//das Spielfeld zeichnen
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// temporal bufferedImage
		//BufferedImage zu erzeugen
		BufferedImage image=new BufferedImage(480,480,BufferedImage.TYPE_3BYTE_BGR);
		//g2 übernimmt image
		Graphics g2=image.getGraphics();	
		//
		g2.drawImage(this.nowBackground.getBgImage(),0,0,this);	
		
		//randomInt erzeugt ein Integer Zahl mit Interval[0,9]
		//Random randomGenerator = new Random();	    
	    // int randomInt = randomGenerator.nextInt(9);
	    
		//zeichne die Mauer in Spielfeld
		for(int i=1;i<10;i++){
		Obstruction ob1=new Obstruction(48*i,25); 
		g2.drawImage(ob1.getBlockImage(),ob1.getX(),ob1.getY(),this);
		}
		
		//zeichne die Steine in Spielfeld
		for(int i=1;i<=5;i++){
		Obstruction ob2=new Obstruction(480-48*i,480-72*i);
		g2.drawImage(ob2.getRockImage(),ob2.getX(),ob2.getY(),this);
		}
		
		//zeichne die Figur in Spielfeld
		g2.drawImage(this.bb.getShowImage(),this.bb.getX(),this.bb.getY(),this);
		//zeichne die Pufferbild in Spiefeld
		g.drawImage(image,0,0,this);
		
		//this.nowBackground

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		// System.out.println(arg0.getKeyChar());
		// System.out.println(ke.getKeyCode());

		//wenn man 39 drücken -->
		if(ke.getKeyCode()==39){
			this.bb.rightmove();
		}
		
		if(ke.getKeyCode()==37){
			this.bb.leftmove();
		}	
		
		if(ke.getKeyCode()==40){
			this.bb.upmove();
		}
		
		if(ke.getKeyCode()==38){
			this.bb.downmove();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		//wenn man auslassen stopmove
		if(ke.getKeyCode()==39){
			this.bb.rightstop();
		}
		
		if(ke.getKeyCode()==37){
			this.bb.leftstop();
		}	
		
		if(ke.getKeyCode()==40){
			this.bb.upstop();
		}
		
		if(ke.getKeyCode()==38){
			this.bb.downstop();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
		// TODO Auto-generated method stub

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	}


