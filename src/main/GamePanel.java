package main;




import entity.Entity;
import entity.Monster;
import entity.Player;
import tile.TileManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import object.Heart;

public class GamePanel extends StackPane {
  //SCREEN SETTING
	private final int originalTileSize = 16 ;//16x16 tile
	private final int scale =3;
	
	private final int tileSize= originalTileSize * scale ; //48x48 tile
	private final int maxScreenCol = 16;
	private final int maxScreenRow = 12;
	private final int screenWidth= maxScreenCol*tileSize;
	private final int screenHeight= maxScreenRow*tileSize;
	
   //WORLD SETTING
	 private final int maxWorldCol = 50;
	 private final int maxWorldRow = 50;

   //FPS
	 private static final double targetFPS=90;
   //Time for run FPS
	 private final long frameTimeNano;
	
	 
	 
	private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    
    private Sound bgSound = new Sound(0);
	private KeyHandler keyH	= new KeyHandler();
	private CollisionChecker collisionChecker = new CollisionChecker(this);
	private Player player = new Player(this);
	private Monster monsters[] = new Monster[10];
	private Monster testMonster = new Monster(this);
	private Heart heart = new Heart(this);
	private UI ui;
	
	private int gameState=0;
	public static final int titleState=0;
	public static final int playingState=1;
	public static final int pauseState=2;
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	TileManager tilemanager = new TileManager(this);
	
	public GamePanel() {
		 canvas = new Canvas(screenWidth, screenHeight);
		 gc = canvas.getGraphicsContext2D();
		 ui = new UI(this,gc);
		 gc.setImageSmoothing(false);
//		 gc.setMiterLimit(2.0);
//		 gc.setLineCap(StrokeLineCap.SQUARE);
		 getChildren().add(canvas);
		 
		 setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.BLACK, null, null)));
		 setFocusTraversable(true);
		 
		 this.frameTimeNano = (long) (1000000000 / targetFPS);
		 
		 this.setOnKeyPressed( event -> {
				KeyHandler.setKeyPressed(event.getCode(), true);
				if(getGameState() == playingState) 
				{
				
				}
				
				if(event.getCode().equals(KeyCode.ESCAPE) && (getGameState() == playingState || getGameState()==pauseState) ) // if press ESC change gamestate to pause or to playing
		    	{	
					if(getGameState()==playingState) {this.setGameState(pauseState);}
					else if(getGameState()==pauseState) {this.setGameState(playingState);}
		    	}
				
				
				if(getGameState() == titleState)
				{
					if(KeyHandler.getKeyPressed(KeyCode.S))
					{
						ui.setCursorNum( (ui.getCursorNum()+1)%3);
					}
					else if(KeyHandler.getKeyPressed(KeyCode.W))
					{
						if(ui.getCursorNum()-1<0) {ui.setCursorNum(ui.getCursorNum()+3);}
						ui.setCursorNum( (ui.getCursorNum()-1)%3);
					}
					
					if(KeyHandler.getKeyPressed(KeyCode.SPACE))
					{
						switch(ui.getCursorNum())
						{
						case 0: setGameState(playingState);playMusic(bgSound);break;
						case 1: break;
						case 2:	System.exit(0);break;
						}
					}
				}
				
			 });
		 this.setOnKeyReleased( event -> {
			 KeyHandler.setKeyPressed(event.getCode(), false);	
		 });
		 
		 this.startGameLoop();
		
	}			

	private void startGameLoop() {
		playMusic(bgSound);
		genMonster(1);
	    gameLoop = new AnimationTimer() {
	    	private long lastUpdate = 0;

	        @Override
	        public void handle(long now) {
	        	if (now - lastUpdate >= frameTimeNano) {
	                update();
	                draw();
//	                   System.out.println(now-lastUpdate);
	                lastUpdate = now;   
	            }
	        }
	    };
	    gameLoop.start();
	}
	// Generate monster in map
	public void genMonster(int maxMonster) {
		if(maxMonster >= 10) {
			maxMonster = 10;
		}
		for(int i=0;i<maxMonster;i++) {
			this.monsters[i] = new Monster(this);
		}
	}

	private void update() {
	    // Update your game logic here
	    	
		//PLAYING
	    if(getGameState()==playingState) {
	    	player.update();
//	    	testMonster.update();
	    	for(int i=0;i<monsters.length;i++) {
	    		if(monsters[i] != null) {
	    			monsters[i].update();
	    		}
	    	}
	    }
	    //PAUSE
	    if(getGameState() ==pauseState) {
	    		
	    }
	}

	
	public Player getPlayer() {
		return player;
	}
	
	private void draw() {
	    gc.clearRect(0, 0, screenWidth, screenHeight);
	    ui.draw(gc);
	    if(getGameState()==titleState) {
	        ui.draw(gc);
	    } else {
	    	tilemanager.draw(gc); 
	        player.draw(gc);
	        heart.draw(gc);
//	        testMonster.draw(gc);
	        for(int i=0;i<monsters.length;i++) {
	    		if(monsters[i] != null) {
	    			monsters[i].draw(gc);
	    		}
	    	}
	        if(getGameState()==playingState) {
	        	heart.draw(gc);  
	        }
	        if(getGameState()==pauseState) {
	        	this.DrawOptionScreen();
	    	} 
	    }
	       
	}
	    
	public void DrawOptionScreen() {
	    ui.drawScreen(this.getTileSize()*4,this.getTileSize()*2, this.getTileSize()*8, this.getTileSize()*8);
	}


		public CollisionChecker getCollisionChecker() {
			return collisionChecker;
		}


		public void setCollisionChecker(CollisionChecker collisionChecker) {
			this.collisionChecker = collisionChecker;
		}

		// For bg sound
		public void playMusic(Sound sound) {
			sound.play();
			sound.loop();
		}
				
		public void stopMusic(Sound sound) {
			sound.stop();
		}
		// For sound effect
		public void playSE(Sound sound) {
			sound.play();
		}
		
		public int getMaxWorldCol() {
			return maxWorldCol;
		}


		public int getMaxWorldRow() {
			return maxWorldRow;
		}


		public int getScreenWidth() {
			return screenWidth;
		}


		public int getScreenHeight() {
			return screenHeight;
		}


		public int getTileSize() {
			return tileSize;
		}


		public Monster[] getMonsters() {
			return monsters;
		}


		public int getGameState() {
			return gameState;
		}


		public void setGameState(int gameState) {
			this.gameState = gameState;
		}
		

		
		
}
