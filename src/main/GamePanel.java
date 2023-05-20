package main;




import java.util.Random;

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
    
    //Sound & Sound Effect
    private Sound bgSound = new Sound(0);
    private Sound hitSound = new Sound(1);
    private Sound winSound = new Sound(2);
    private Sound selectSound = new Sound(3);
    
	private KeyHandler keyHandler	= new KeyHandler(this);
	private CollisionChecker collisionChecker = new CollisionChecker(this);
	private Player player = new Player(this);


	Random random = new Random();
	private int maxMons = random.nextInt(5)+5;
	private Heart heart = new Heart(this);
	private UI ui;
	private boolean FirstTimeStart= true;
	private boolean FirstTimeStartWarning=false;
	
	//GAME STATE
	private int gameState=0;
	public static final int titleState=0;
	public static final int playingState=1;
	public static final int pauseState=2;
	public static final int endingState=3;
	
	

	private int currentMonster = this.getMaxMons();
	private Monster monster[] = new Monster[20];
	private TileManager tileManager = new TileManager(this);
	public TileManager tilemanager = new TileManager(this);
	
//	public void setPlayer(Player player) {
//		this.player = player;
//	}
	
	
	public GamePanel()
	{
		// initialize screen & variable
		 canvas = new Canvas(screenWidth, screenHeight);
		 gc = canvas.getGraphicsContext2D();
		 ui = new UI(this,gc);
		 gc.setImageSmoothing(false);
		 getChildren().add(canvas); 
		 setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.BLACK, null, null)));
		 setFocusTraversable(true);
		 
		 //set time to draw
		 this.frameTimeNano = (long) (1000000000 / targetFPS);
		 
		 //set On key Pressed that use in game
		 this.setOnKeyPressed( event -> {
				keyHandler.setKeyPressed(event.getCode(), true);
				if(getGameState() == endingState) 
				{
					keyHandler.keyPressedChangeState();
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
		 
		 //set on key released
		 this.setOnKeyReleased( event -> {
			 keyHandler.setKeyPressed(event.getCode(), false);	
		 });
		
		//start animation timer
		 this.startNewGameLoop();
		 
	}
	
	//create monster
	private void genMonster(int maxMonster) {
		if(maxMonster > monster.length) {
			maxMonster = monster.length;
		}
		
		for(int i=0;i<maxMonster;i++) {
			monster[i] = new Monster(this);
			monster[i].setIndex(i);
		}
	}
	
	//for reset game when NEW GAME
	  public void resetGame()
	  {
		  //reset variable
		  setPlayer(new Player(this)); 
		  
		  monster = new Monster[20];
		  maxMons = random.nextInt(5)+5;
		  genMonster(maxMons);
		  setCurrentMonster( this.getMaxMons());
//		  System.out.println(maxMons +" "+ getCurrentMonster()+" "+getMonsterAlive(monster));
		  setGameState(GamePanel.titleState);
	  }
	  public void startNewGameLoop() 
	  {
//		 Random random = new Random();
//		 maxMons = random.nextInt(5)+5;
		
//		 genMonster(7);
		 resetGame();
		 if(gameLoop != null)
		 {
			 gameLoop.stop();
		 }
		// playMusic(bgSound);

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
//	    		System.out.println(currentMonster);
	    		player.update();
	    		
	    		if(getMonsterAlive(monster) != currentMonster) {
	    			playSE(hitSound);
    				currentMonster = getMonsterAlive(monster);
	    		}
	    		if(getMonsterAlive(monster)==0)
	    		{
	    			getUi().setGameFinished(true);
	    			stopMusic(bgSound);
	    			playSE(winSound);
	    			setGameState(endingState);
	    		}

	    		
	    		for(int i=0;i<monster.length;i++) {
	    			if(monster[i] != null) {
	    				monster[i].update();
	    			}
	    		}
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
	        	if(isFirstTimeStartWarning())
	        	{ui.LoadGameDraw();}
	        	
	        	else ui.draw(gc);
	         }
	        
	        else {
	        	
	        tilemanager.draw(gc); 
	        player.draw(gc);
	      //  heart.draw(gc);
	        for(int i=0;i<monster.length;i++) {
    			if(monster[i] != null) {
    				monster[i].draw(gc);
    			}
    		}
	        
	        //Drawing HP only when playing
	        if(getGameState()==playingState)
	        {
	        	getUi().draw(gc);
	        }
	        
	        //Drawing Option screen when pausing
	        if(getGameState()==pauseState)
	    	{
	        	getUi().draw(gc);
	    	} 
	        //Drawing Congratulation
	        if(getGameState()==endingState)
	        {

	        	getUi().draw(gc);
	        }
	        }
	       
	    }
	    
	    
	    


	    public int getMonsterAlive(Monster[] monster) {
	    	int monsterCounter=0;
	    	for(Monster x:monster)
	    	{
	    		if(x!=null) monsterCounter++;
	    	}
	    	return monsterCounter;
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
			sound.stop();
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


		public int getGameState() {
			return gameState;
		}


		public void setGameState(int gameState) {
			this.gameState = gameState;
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
		
		
		public AnimationTimer getGameLoop() {
			return gameLoop;
		}


		public void setGameLoop(AnimationTimer gameLoop) {
			this.gameLoop = gameLoop;
		}

		public boolean getFirstTimeStart() {
			return FirstTimeStart;
		}

		public void setFirstTimeStart(boolean firstTimeStart) {
			FirstTimeStart = firstTimeStart;
		}
		public void setPlayer(Player player) {
			this.player = player;
		}

		public GraphicsContext getGc() {
			return gc;
		}

		public void setGc(GraphicsContext gc) {
			this.gc = gc;
		}

		public boolean isFirstTimeStartWarning() {
			return FirstTimeStartWarning;
		}

		public void setFirstTimeStartWarning(boolean firstTimeStartWarning) {
			FirstTimeStartWarning = firstTimeStartWarning;
		}

		public TileManager getTileManager() {
			return tileManager;
		}

		public Sound getHitSound() {
			return hitSound;
		}

		public int getMaxMons() {
			return maxMons;
		}


		public void setMonster(Monster[] monster) {
			this.monster = monster;
		}

		public Monster[] getMonster() {
			return monster;
		}

		public int getCurrentMonster() {
			return currentMonster;
		}

		public void setCurrentMonster(int currentMonster) {
			this.currentMonster = currentMonster;
		}

		public Sound getSelectSound() {
			return selectSound;
		}

		public void setSelectSound(Sound selectSound) {
			this.selectSound = selectSound;
		}
	}
		

