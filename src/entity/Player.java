//package entity;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import main.GamePanel;
//import main.KeyHandler;
//
//public class Player extends Entity {
//	
//	private GamePanel gp;
//	private KeyHandler keyH;
//	
//	public Player(GamePanel gp,KeyHandler keyH)
//	{
//		this.gp =gp;
//		this.keyH= keyH;
//		setDefaultValues();
//		getPlayerImage();
//		direction="up";
//	}
//	public void setDefaultValues()
//	{
//		x=100;
//		y=100;
//		speed =4;
//	}
//	public void getPlayerImage()
//	{
//		try {
//			up1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
//			up2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
//			down1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
//			down2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
//			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
//			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
//			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
//			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void update() {
//		int dx = 0;
//		int dy = 0;
//		if(keyH.upPressed == true)
//		{
//			direction="up";
//			dy-=speed;
//		}
//		else if(keyH.downPressed == true)
//		{
//			direction="down";
//			dy+=speed;
//		}
//		else if(keyH.leftPressed == true)
//		{
//			direction="left";
//			dx-=speed;
//		}
//		else if(keyH.rightPressed == true)
//		{
//			direction="right";
//			dx+=speed;
//		}
//		x+=dx;
//		y+=dy;
//		spriteCounter++;
//		if(spriteCounter>=12) {
//			if(spriteNum==1)
//				spriteNum=2;
//			else if(spriteNum==2)
//				     spriteNum=1;
//			spriteCounter=0;
//		}
//	}
//	public void draw(Graphics2D g2)
//	{
//		BufferedImage image = null;
//		switch(direction) {
//		case "up" :
//			if(spriteNum==1)
//			{
//			image = up1;	
//			}
//			if(spriteNum==2)
//			{
//			image = up2;	
//			}
//			break;
//		case "down" :
//			if(spriteNum==1)
//			{
//			image = down1;	
//			}
//			if(spriteNum==2)
//			{
//			image = down2;	
//			}
//			break;
//		case "left" :
//			if(spriteNum==1)
//			{
//			image = left1;	
//			}
//			if(spriteNum==2)
//			{
//			image = left2;	
//			}
//			break;
//		case "right" :
//			if(spriteNum==1)
//			{
//			image = right1;	
//			}
//			if(spriteNum==2)
//			{
//			image =right2;	
//			}
//			break;
//			
//		}
//		g2.drawImage(image, x, y, gp.tileSize,gp.tileSize,null);
//	}
//
//	
//}
//	
//
package entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.lang.Math;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    private final GamePanel gp;
   
    // Player properties and variables
//    private int x;
//    private int y;
//    private int speed;
    private final int ScreenX;
    private final int ScreenY;
    
    public Player(GamePanel gp) {
        this.gp = gp;
		this.ScreenX =	this.gp.getScreenWidth()/2 - (this.gp.getTileSize()/2);
		this.ScreenY =  this.gp.getScreenHeight()/2-(this.gp.getTileSize()/2);
		this.getSolidArea().setX(10);
		this.getSolidArea().setY(18);
		this.getSolidArea().setWidth(28);
		this.getSolidArea().setHeight(28);//= new Rectangle(10,18,28,28);
		
        this.getPlayerImage();
        this.setDefaultValue();
    }
    private void setDefaultValue()
    {
    	setPosition(gp.getTileSize()*23,gp.getTileSize()*21);
        setSpeed(6);
        setDiaSpeed ((int) (getSpeed()/Math.sqrt(2.0)));
        setDirection("up");
        
        //PLAYER STATUS
        this.setMaxLife(5);
        this.setLife(getMaxLife());
    }
	 private void getPlayerImage() {

		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_up_1.png"))) ;
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_up_2.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_down_1.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_down_2.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_left_1.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_left_2.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_right_1.png")));
		 getImages().add( new Image(getClass().getResourceAsStream("/player/boy_right_2.png")));
		 

	  }
	 public void update() {
        // Update player position based on input from keyHandler 
    	
    		if(gp.getKeyHandler().isWalkPressed())
    		{

    	
    		//set direction to walk
	        setDirection(gp.getKeyHandler().playerUpdate()); 
	        
	        //set isCollide
	        setCollide(false);
	        gp.getCollisionChecker().checkOtherEntity(this, gp.getMonster());
	        gp.getCollisionChecker().checkTile(this);
	   //     System.out.println(this.isCollide());

	        //character walk
	        if(!isCollide()) {
		        switch(getDirection()) {
		        case "up" :
		    		setPosition(getPosition()[0],getPosition()[1] - getSpeed());break;
		    	case "down" :
		    		setPosition(getPosition()[0],getPosition()[1] + getSpeed());break;
		    	case "left" :
		    		setPosition(getPosition()[0] - getSpeed(),getPosition()[1]);break;
		    	case "right" :
		    		setPosition(getPosition()[0] + getSpeed(),getPosition()[1]);break;
		    	case "right and up" :
		    		setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());break;
		    	case "right and down" :
		    		setPosition(getPosition()[0] + getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());break;
		    	case "left and up" :
		    		setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());break;
		    	case "left and down" :
		    		setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());break;
		        }
	        }
    	//switch character sprite
        setSpriteCounter(getSpriteCounter()+1);
        if(getSpriteCounter()>=12)
        {
        	setSpriteCounter(0);
        	setSpriteNum((getSpriteNum()+1)%2);
        	
        }}
    }
    
    public void draw(GraphicsContext gc) {

    	Image imagetofill = null;
    	int spNum = getSpriteNum();
    	ArrayList<Image> imgs = getImages();
    	
    	//draw character sprite depend on direction & spriteNumber
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

    	
    }
	public int getScreenX() {
		return ScreenX;
	}
	public int getScreenY() {
		return ScreenY;
	}
}
