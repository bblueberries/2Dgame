package entity;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.GamePanel;
import main.KeyHandler;
import main.Sound;

public class Monster extends Entity implements Drawable, Updatable{
	private GamePanel gp;
    private int screenX;
    private int screenY;
    private int dirCounter; // Set to random direction when spawn.
	
    // Constructor
	public Monster(GamePanel gp) {
        this.setGp(gp);
        this.setDefaultValue();      
		this.getSolidArea().setX(10);
		this.getSolidArea().setY(18);
		this.getSolidArea().setWidth(28);
		this.getSolidArea().setHeight(28); // = new Rectangle(10,18,28,28);
        this.setMonsterImage();
    }
	
	// Set monster to random spawn and set other attributes.
	@Override
    public void setDefaultValue() {
		randomSpawn(gp);
    	setPosition(gp.getTileSize()*this.getxPos(),gp.getTileSize()*this.getyPos());
        setSpeed(4);
        setDiaSpeed ((int) (getSpeed()/Math.sqrt(2.0)));
        setDirection("down");
        setDirCounter(59);
    }
	
	// Add picture to the ArrayList of images of monster.
	private void setMonsterImage() {
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_up_1.png"))) ;
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_up_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_down_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_down_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_left_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_left_2.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_right_1.png")));
		getImages().add( new Image(getClass().getResourceAsStream("/monster/mons_right_2.png")));
	}
	
	// Random the monster direction to walk.
	public void randomDirection() {
		this.setDirCounter(this.getDirCounter()+1);
	    if(this.getDirCounter() % 60 == 0) {
	    	Random random = new Random();
	    	int i = random.nextInt(800)+1;
	    	
	    	if(i <= 100) {
	    		this.setDirection("up");
	    	} else if(i <= 200) {
	    		this.setDirection("down");
	    	} else if(i <= 300) {
	    		this.setDirection("left");
	    	} else if(i <= 400) {
	    		this.setDirection("right");
	    	} else if(i <= 500) {
	    		this.setDirection("right and up");
	    	} else if(i <= 600) {
	    		this.setDirection("right and down");
	    	} else if(i <= 700) {
	    		this.setDirection("left and up");
	    	} else if(i <= 800) {
	    		this.setDirection("left and down");
	    	} 	
	    	this.setDirCounter(0);
	    }
	}
	
	// Random direction and calculate new position and sprite value.
	@Override
	public void update() {
		randomDirection();
		setIsCollide(true);
		Entity player[] = {gp.getPlayer()};
		gp.getCollisionChecker().checkOtherEntity(this, player);
		if(!getIsCollide()) {
			gp.getCollisionChecker().checkTile(this);
		}
		        
		if(!getIsCollide()) {	
			switch(getDirection()) {
				case "up" :
			    	setPosition(getPosition()[0],getPosition()[1] - getSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "down" :
			    	setPosition(getPosition()[0],getPosition()[1] + getSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left" :
			    	setPosition(getPosition()[0] - getSpeed(),getPosition()[1]);
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right" :
			    	setPosition(getPosition()[0] + getSpeed(),getPosition()[1]);
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right and up" :
			    	setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "right and down" :
			    	setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left and up" :
			    	setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
			    case "left and down" :
			    	setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
			    	setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());
			    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
			    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();break;
				}
		    } else {
		    	this.screenX =	this.getPosition()[0] - gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX();
		    	this.screenY =  this.getPosition()[1] - gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY();
		    }
	    calculateSprite();
	}
	
	// Update new position and sprite value on screen.
	@Override
	public void draw(GraphicsContext gc) {
	    Image imageToFill = null;
	    int spNum = getSpriteNum();
	    ArrayList<Image> imgs = getImages();
	    switch(getDirection()) {
	    case "up" :
	    	imageToFill = ((spNum == 1)? imgs.get(0):imgs.get(1));
	    	break;
	    case "down" :
	    	imageToFill = ((spNum == 1)? imgs.get(2):imgs.get(3));
	    	break;
	    case "left" :
	    	imageToFill = ((spNum == 1)? imgs.get(4):imgs.get(5));
	    	break;
	    case "right" :
	    	imageToFill = ((spNum == 1)? imgs.get(6):imgs.get(7));
	    	break;
	    case "right and up" :
	    	imageToFill = ((spNum == 1)? imgs.get(6):imgs.get(7));
	    	break;	
		case "right and down" :
			imageToFill = ((spNum == 1)? imgs.get(6):imgs.get(7));
			break;	
		case "left and up" :
			imageToFill = ((spNum == 1)? imgs.get(4):imgs.get(5));
			break;	
		case "left and down" :
			imageToFill = ((spNum == 1)? imgs.get(4):imgs.get(5));
			break;	
		}
	    
	    gc.drawImage(imageToFill,screenX,screenY ,gp.getTileSize(), gp.getTileSize());
	}
	
	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public int getDirCounter() {
		return dirCounter;
	}

	public void setDirCounter(int dirCounter) {
		this.dirCounter = dirCounter;
	}
	 
}
