package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
  //SCREEN SETTING
	final int originalTileSize = 16 ;//16x16 tile
	final int scale =3;
	
	public final int tileSize= originalTileSize * scale ; //48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth= maxScreenCol*tileSize;
	final int screenHeight= maxScreenRow*tileSize;
	
	//FPS
	private int FPS =60;
	KeyHandler keyH	= new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	//set player default position
	private int PlayerX=100;
	private int PlayerY=100;
	private int PlayerSpeed=4;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}

	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval =1000000000/FPS; // time for drawing one frame which = 1sec/fps (framePerSec)
		double dT =0;
		long lastT=System.nanoTime();
		long currentT;
		long timer =0;
		int drawCount=0;
		
		while(gameThread != null)
		{
			currentT=System.nanoTime();
			dT += (currentT - lastT) / drawInterval;// [sec]
			timer += (currentT - lastT);
			lastT=currentT;
			
			if(dT >= 1) // when time past / drawIntervalTime >= 1 means it's time to draw 
			{
				update();
				repaint();
				dT-=1;
				drawCount++;
			}
			
			if(timer >= 1000000000) //timer = 1 sec
			{
				System.out.println("FPS :"+drawCount);
				drawCount=0;
				timer=0;
			}
			
			
			
		
		}
	}
	
	public void update()
	{	
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 player.draw(g2);
		 g2.dispose();
	}
	
}
