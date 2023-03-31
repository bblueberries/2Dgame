package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
  //SCREEN SETTING
	final int originalTileSize = 16 ;//16x16 tile
	final int scale =3;
	
	final int tileSize= originalTileSize * scale ; //48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth= maxScreenCol*tileSize;
	final int screenHeight= maxScreenRow*tileSize;
	
	Thread gameThread;
	
	
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenHeight,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
