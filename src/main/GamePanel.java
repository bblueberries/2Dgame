package main;




import entity.Player;
import tile.TileManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class GamePanel extends StackPane {
  //SCREEN SETTING
	final int originalTileSize = 16 ;//16x16 tile
	final int scale =3;
	
	public final int tileSize= originalTileSize * scale ; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth= maxScreenCol*tileSize;
	public final int screenHeight= maxScreenRow*tileSize;
	
   //WORLD SETTING
	 public final int maxWorldCol = 50;
	 public final int maxWorldRow = 50;
	 public final int worldWidth = tileSize*maxWorldCol;
	 public final int worldHeight = tileSize*maxWorldRow;
   //FPS
	 private static final double targetFPS=90;
   //Time for run FPS
	 private final long frameTimeNano;
	
	private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    
	KeyHandler keyH	= new KeyHandler();
	Thread gameThread;
	private CollisionChecker collisionChecker = new CollisionChecker(this);
	private Player player = new Player(this);
	


	public void setPlayer(Player player) {
		this.player = player;
	}

	TileManager tilemanager = new TileManager(this);
	
	public GamePanel()
	{
		 canvas = new Canvas(screenWidth, screenHeight);
		 gc = canvas.getGraphicsContext2D();
		 gc.setImageSmoothing(false);
//		 gc.setMiterLimit(2.0);
//		 gc.setLineCap(StrokeLineCap.SQUARE);
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
	    public Player getPlayer() 
	    {
		return player;
	    }


		public CollisionChecker getCollisionChecker() {
			return collisionChecker;
		}


		public void setCollisionChecker(CollisionChecker collisionChecker) {
			this.collisionChecker = collisionChecker;
		}


		
}
