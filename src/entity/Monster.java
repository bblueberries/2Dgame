package entity;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.GamePanel;
import main.KeyHandler;

public class Monster extends Entity{
	private final GamePanel gp;
    private int ScreenX;
    private int ScreenY;
    private int index;
	
	public Monster(GamePanel gp) {
        this.gp = gp;
        this.setDefaultValue();
//		this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
//		this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();
//        this.ScreenX =	this.gp.getScreenWidth()/2 - (this.gp.getTileSize()/2);
//		this.ScreenY =  this.gp.getScreenHeight()/2-(this.gp.getTileSize()/2);
        
        
		this.getSolidArea().setX(10);
		this.getSolidArea().setY(18);
		this.getSolidArea().setWidth(28);
		this.getSolidArea().setHeight(28);//= new Rectangle(10,18,28,28);
		
        this.getMonsterImage();
    }
    private void setDefaultValue()
    {
    	boolean checkSpawn = false;
    	Random random = new Random();
    	int xPos = random.nextInt(10)+18;
    	int yPos = random.nextInt(10)+16;
    	while(!checkSpawn){
        	int spawnTile = this.gp.tilemanager.getMapTileNum()[xPos][yPos];
        	if(gp.tilemanager.getTile()[spawnTile].isCollision() == true) {
        		checkSpawn = false;
        		xPos = random.nextInt(20)+13;
        		yPos = random.nextInt(20)+11;
        	} else {
        		checkSpawn = true;
        	}
    	}
    	
    	setPosition(gp.getTileSize()*xPos,gp.getTileSize()*yPos);
        setSpeed(4);
        setDiaSpeed ((int) (getSpeed()/Math.sqrt(2.0)));
        setDirection("down");
        
        //MONSTER STATUS
        this.setMaxLife(5);
        this.setLife(getMaxLife());
    }
	private void getMonsterImage() {
		 
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_up_1.png"))) ;
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_up_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_down_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_down_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_left_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_left_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_right_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/orc_right_2.png")));
		 
	}
	int dirCounter = 59;
	Random firstRandom = new Random();
	int i = firstRandom.nextInt(800)+1;
	public void update() {
		dirCounter++;
	    if(dirCounter % 60 == 0) {
	    	Random random = new Random();
	    	i = random.nextInt(800)+1;
	    	
//	    	if(i <= 100) {
//	    		this.setDirection("up");
//	    	} else if(i <= 200) {
//	    		this.setDirection("down");
//	    	} else if(i <= 300) {
//	    		this.setDirection("left");
//	    	} else if(i <= 400) {
//	    		this.setDirection("right");
//	    	} else if(i <= 500) {
//	    		this.setDirection("right and up");
//	    	} else if(i <= 600) {
//	    		this.setDirection("right and down");
//	    	} else if(i <= 700) {
//	    		this.setDirection("left and up");
//	    	} else if(i <= 800) {
//	    		this.setDirection("left and down");
//	    	}
	    	
	    	if(i <= 400) {
	    		this.setDirection("up");
	    	} else if(i <= 800) {
	    		this.setDirection("down");
	    	}
	    	
	    	dirCounter = 0;
	    }

			 
		setCollide(true);
		Entity player[] = new Entity[1];
		player[0] = gp.getPlayer();
		gp.getCollisionChecker().checkOtherEntity(this, player);
//		System.out.println(otherMonster.toString());
//		gp.getCollisionChecker().checkOtherEntity(this, otherMonster);
//		System.out.println("isCollide = "+isCollide());
		if(!isCollide()) {
			gp.getCollisionChecker().checkTile(this);
		}
		        
		if(!isCollide()) {	
			switch(getDirection()) {
				case "up" :
			    	setPosition(getPosition()[0],getPosition()[1] - getSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "down" :
			    	setPosition(getPosition()[0],getPosition()[1] + getSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left" :
			    	setPosition(getPosition()[0] - getSpeed(),getPosition()[1]);
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right" :
			    	setPosition(getPosition()[0] + getSpeed(),getPosition()[1]);
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right and up" :
			    	setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right and down" :
			    	setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left and up" :
			    	setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left and down" :
			    	setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());
			    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
				}
		    } else {
		    	this.ScreenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
		    	this.ScreenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();
		    }
	    setSpriteCounter(getSpriteCounter()+1);
	    if(getSpriteCounter()>=12) {
	        setSpriteCounter(0);
	        setSpriteNum((getSpriteNum()+1)%2);	
	    }
	}
	    
	    public void draw(GraphicsContext gc) {
//	      if(spriteNum==0)
//	      {
//	    	gc.setFill(Color.RED);
//	        gc.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);}
//	      if(spriteNum==1)
//	      {
//	    	gc.setFill(Color.DARKRED);
//	        gc.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);}
	    	Image imagetofill = null;
	    	int spNum = getSpriteNum();
	    	ArrayList<Image> imgs = getImages();
	    	switch(getDirection()) 
	    	{
	    	case "up" :
	    		imagetofill = ((spNum == 1)? imgs.get(0):imgs.get(1));
	    		break;
	    	case "down" :
	    		imagetofill = ((spNum == 1)? imgs.get(2):imgs.get(3));
	    		break;
	    	case "left" :
	    		imagetofill = ((spNum == 1)? imgs.get(4):imgs.get(5));
	    		break;
	    	case "right" :
	    		imagetofill = ((spNum == 1)? imgs.get(6):imgs.get(7));
	    		break;
	    	case "right and up" :
	    		imagetofill = ((spNum == 1)? imgs.get(6):imgs.get(7)); //right
	    		break;	
			case "right and down" :
				imagetofill = ((spNum == 1)? imgs.get(6):imgs.get(7)); //right
				break;	
			case "left and up" :
				imagetofill = ((spNum == 1)? imgs.get(4):imgs.get(5)); // left
				break;	
			case "left and down" :
				imagetofill = ((spNum == 1)? imgs.get(4):imgs.get(5)); //left
				break;	
			}
	    	
	    	
	    	gc.drawImage(imagetofill,ScreenX,ScreenY ,gp.getTileSize(), gp.getTileSize());
//	    	gc.fillRect(ScreenX, ScreenX, 32, 32);
	    	
	    }
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	 
}
