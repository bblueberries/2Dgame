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
	private KeyHandler keyHandler	= new KeyHandler(this);
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
//	public static final int optionState=3;
	
	public void setPlayer(Player player) {
		this.player = player;
	}


	private Entity monster[] = new Entity[10];
	private TileManager tileManager = new TileManager(this);
	public TileManager tilemanager = new TileManager(this);
	
//	public void setPlayer(Player player) {
//		this.player = player;
//	}

	

	
	public GamePanel()
	{
		 canvas = new Canvas(screenWidth, screenHeight);
		 gc = canvas.getGraphicsContext2D();
		 ui = new UI(this,gc);
		 gc.setImageSmoothing(false);

		 getChildren().add(canvas);
		 
		 setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.BLACK, null, null)));
		 setFocusTraversable(true);
		 
		 this.frameTimeNano = (long) (1000000000 / targetFPS);
		 
		 this.setOnKeyPressed( event -> {
				keyHandler.setKeyPressed(event.getCode(), true);
				if(getGameState() == playingState) 
				{
					
				}
				
				// To toggle option Screen
				if(getGameState() == pauseState || getGameState() == playingState) 
				{
				keyHandler.keyPressedChangeState();
				}
				
				// title Screen
				if(getGameState() == titleState){ 
				
				keyHandler.keyPressedTitleState();
				}
				
				// option Screen
				if(getGameState() == pauseState){ 
				
					
					keyHandler.keyPressedPauseState();
					keyHandler.optionPressed(ui.getOptionNum());
				}
			 });
		 
		 this.setOnKeyReleased( event -> {
			 keyHandler.setKeyPressed(event.getCode(), false);	
		 });
//		
		
		 this.startGameLoop();
		 
	}
	

	  private void startGameLoop() 
	  {
		//  playMusic(bgSound);
	      gameLoop = new AnimationTimer() {
	    	  private long lastUpdate = 0;
	          @Override
	          public void handle(long now) {
	              if (now - lastUpdate >= frameTimeNano) {
	                  update();
	                  draw();
	                  lastUpdate = now;

	              }
	          }
	          };
	          gameLoop.start(); 

	      }
	

	    private void update() {
	        // Update your game logic here
	    	
	    	//PLAYING
	    	if(getGameState()==playingState)
	    	{
	    		player.update();
	    		testMonster.update();
	    	}
	    	//PAUSE
	    	if(getGameState() ==pauseState)
	    	{
	    		
	    	}
	    	
	    }


	


	    private void draw() {
	        gc.clearRect(0, 0, screenWidth, screenHeight);
	        
	        //title Screen
	        if(getGameState()==titleState)
	        {
	        	ui.draw(gc);
	         }
	        
	        else {
	        tilemanager.draw(gc); 
	        player.draw(gc);
	        heart.draw(gc);
	        testMonster.draw(gc);
	        
	        //Drawing HP only when playing
	        if(getGameState()==playingState)
	        {
	        	heart.draw(gc);  
	        }
	        
	        //Drawing Option screen when pausing
	        if(getGameState()==pauseState)
	    	{
	        	ui.draw(gc);
	    	} 
	        }
	       
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



//		public Monster[] getMonster() {
//			return monsters;
//		}


		public int getGameState() {
			return gameState;
		}


		public void setGameState(int gameState) {
			this.gameState = gameState;
		}
		
		public Entity[] getMonster()
		{
			return monster;

		}


		public Player getPlayer() {
			return player;
		}


		public UI getUi() {
			return ui;
		}


		public void setUi(UI ui) {
			this.ui = ui;
		}


		public Sound getBgSound() {
			return bgSound;
		}


		public void setBgSound(Sound bgSound) {
			this.bgSound = bgSound;
		}


		public KeyHandler getKeyHandler() {
			return keyHandler;
		}


		public void setKeyHandler(KeyHandler keyHandler) {
			this.keyHandler = keyHandler;
		}
	}
		


		
