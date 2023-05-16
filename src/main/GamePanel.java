package main;




import entity.Player;
import tile.TileManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GamePanel extends StackPane {
  //SCREEN SETTING
	final int originalTileSize = 16 ;//16x16 tile
	final int scale =3;
	
	public final int tileSize= originalTileSize * scale ; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth= maxScreenCol*tileSize;
	public final int screenHeight= maxScreenRow*tileSize;
	
	//FPS

	 private static final double targetFPS=60;
	 private final long frameTimeNano;
	
	private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    
	KeyHandler keyH	= new KeyHandler();
	Thread gameThread;
	Player player = new Player(this);
	TileManager tilemanager = new TileManager(this);
	
	public GamePanel()
	{
		 canvas = new Canvas(screenWidth, screenHeight);
		 gc = canvas.getGraphicsContext2D();
		 getChildren().add(canvas);
		 
		 setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.DARKBLUE, null, null)));
		 setFocusTraversable(true);
		 
		 this.frameTimeNano = (long) (1000000000 / targetFPS);
		 
		 this.setOnKeyPressed( event -> {
			KeyHandler.setKeyPressed(event.getCode(), true);
		 });
		 this.setOnKeyReleased( event -> {
			 KeyHandler.setKeyPressed(event.getCode(), false);	
		 });
//		
		 this.startGameLoop();
		
	}

//	public void StartGameThread() {
//		gameThread = new Thread(this);
//		gameThread.start();
//	}
//
//	@Override
//	public void run() {
//		
//		double drawInterval =1000000000/FPS; // time for drawing one frame which = 1sec/fps (framePerSec)
//		double dT =0;
//		long lastT=System.nanoTime();
//		long currentT;
//		long timer =0;
//		int drawCount=0;
//		
//		while(gameThread != null)
//		{
//			currentT=System.nanoTime();
//			dT += (currentT - lastT) / drawInterval;// [sec]
//			timer += (currentT - lastT);
//			lastT=currentT;
//			
//			if(dT >= 1) // when time past / drawIntervalTime >= 1 means it's time to draw 
//			{
//				update();
//				repaint();
//				dT-=1;
//				drawCount++;
//			}
//			
//			if(timer >= 1000000000) //timer = 1 sec
//			{
//				System.out.println("FPS :"+drawCount);
//				drawCount=0;
//				timer=0;
//			}
//			
//			
//			
//		
//		}
//	}
//	
//	public void update()
//	{	
//		player.update();
//		
//	}
//	public void paintComponent(Graphics g) {
//		
//		 super.paintComponent(g);
//		
//		 Graphics2D g2 = (Graphics2D)g;
//		 
////		 TileM.draw(g2);
//		 player.draw(g2);
//		 
//		 g2.dispose();
//	}
//	
	  private void startGameLoop() {
	        gameLoop = new AnimationTimer() {
	            private long lastUpdate = 0;

	            @Override
	            public void handle(long now) {
	                if (now - lastUpdate >= frameTimeNano) {
	                    update();
	                    draw();
//	                     System.out.println(now-lastUpdate);
	                    lastUpdate = now;
	                  
	                }
	            }
	        };
	        gameLoop.start();
	    }

	    private void update() {
	        // Update your game logic here
	    	player.update();
	    }

	    private void draw() {
	        gc.clearRect(0, 0, screenWidth, screenHeight);
	       
	        tilemanager.draw(gc); 
	        player.draw(gc);
	    }
}
