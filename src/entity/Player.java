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
		this.ScreenX =	this.gp.screenWidth/2 - (this.gp.tileSize/2);
		this.ScreenY =  this.gp.screenHeight/2-(this.gp.tileSize/2);
        
		this.getSolidArea().setX(10);
		this.getSolidArea().setY(18);
		this.getSolidArea().setWidth(30);
		this.getSolidArea().setHeight(28);//= new Rectangle(10,18,28,30);
		
        this.getPlayerImage();
        this.setDefaultValue();
    }
    private void setDefaultValue()
    {
    	setPosition(gp.tileSize*23,gp.tileSize*21);
        setSpeed(6);
        setDiaSpeed ((int) (getSpeed()/Math.sqrt(2.0)));
        setDirection("up");
        
        //PLAYER STATUS
        this.setMaxLife(5);
        this.setLife(getMaxLife());
    }
	  private void getPlayerImage()
	  {
		  	getImages().add( new Image(getClass().getResourceAsStream("/player/boy_up_1.png"))) ;
		  	getImages().add( new Image(getClass().getResourceAsStream("/player/boy_up_2.png")));
		  	getImages().add( new Image(getClass().getResourceAsStream("/player/boy_down_1.png")));
		  	getImages().add(  new Image(getClass().getResourceAsStream("/player/boy_down_2.png")));
		  	getImages().add(  new Image(getClass().getResourceAsStream("/player/boy_left_1.png")));
		  	getImages().add(  new Image(getClass().getResourceAsStream("/player/boy_left_2.png")));
		  	getImages().add(  new Image(getClass().getResourceAsStream("/player/boy_right_1.png")));
		  	getImages().add(  new Image(getClass().getResourceAsStream("/player/boy_right_2.png")));
	
	  	}
    public void update() {
        // Update player position based on input from keyHandler 
    	

    	if(KeyHandler.getKeyPressed(KeyCode.W)||KeyHandler.getKeyPressed(KeyCode.S)||KeyHandler.getKeyPressed(KeyCode.A)||KeyHandler.getKeyPressed(KeyCode.D)) {
    		
    		if (KeyHandler.getKeyPressed(KeyCode.D) && KeyHandler.getKeyPressed(KeyCode.W)) {
//            	System.out.println("wd");
                setDirection("right and up");
                
            }
            else if (KeyHandler.getKeyPressed(KeyCode.D) && KeyHandler.getKeyPressed(KeyCode.S)) {
            	setDirection("right and down");
                
            }
            else if (KeyHandler.getKeyPressed(KeyCode.A) && KeyHandler.getKeyPressed(KeyCode.S)) {
            	setDirection("left and down");
                
            }
            else if (KeyHandler.getKeyPressed(KeyCode.A) && KeyHandler.getKeyPressed(KeyCode.W)) {
            	setDirection("left and up");
                
            }
            else if (KeyHandler.getKeyPressed(KeyCode.W)) {
//	        	System.out.println("W");
            	setDirection("up"); 
            
	        }
	        else if (KeyHandler.getKeyPressed(KeyCode.S)) {
	        	setDirection("down");
	           
	        }
	        else if (KeyHandler.getKeyPressed(KeyCode.A)) {
	        	setDirection("left");
	            
	        } 
	        else if (KeyHandler.getKeyPressed(KeyCode.D)) {
//	        	System.out.println("D");
	        	setDirection("right");
	            
	        }
        
        
	        setCollide(false);
	        gp.getCollisionChecker().checkTile(this);
	        
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
		    		setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());;break;
		    	case "left and up" :
		    		setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] - getDiaSpeed());break;
		    	case "left and down" :
		    		setPosition(getPosition()[0] - getDiaSpeed(),getPosition()[1]);
		    		setPosition(getPosition()[0],getPosition()[1] + getDiaSpeed());;break;
		        }
	        }
    	}
        setSpriteCounter(getSpriteCounter()+1);
        if(getSpriteCounter()>=12)
        {
        	setSpriteCounter(0);
        	setSpriteNum((getSpriteNum()+1)%2);
        	
        }
    }
    
    public void draw(GraphicsContext gc) {
//      if(spriteNum==0)
//      {
//    	gc.setFill(Color.RED);
//        gc.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);}
//      if(spriteNum==1)
//      {
//    	gc.setFill(Color.DARKRED);
//        gc.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);}
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
    	
    	
    	gc.drawImage(imagetofill,ScreenX,ScreenY ,gp.tileSize, gp.tileSize);
//    	gc.fillRect(ScreenX, ScreenX, 32, 32);
    	
    }
	public int getScreenX() {
		return ScreenX;
	}
	public int getScreenY() {
		return ScreenY;
	}
}
//public class Player extends Entity {
//
//private GamePanel gp;
//private KeyHandler keyH;
//
//public Player(GamePanel gp,KeyHandler keyH)
//{
//	this.gp =gp;
//	this.keyH= keyH;
//	setDefaultValues();
//	getPlayerImage();
//	direction="up";
//}
//public void setDefaultValues()
//{
//	x=100;
//	y=100;
//	speed =4;
//}
//public void getPlayerImage()
//{
//	try {
//		up1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
//		up2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
//		down1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
//		down2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
//		left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
//		left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
//		right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
//		right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
//		
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
//
//public void update() {
//	int dx = 0;
//	int dy = 0;
//	if(keyH.upPressed == true)
//	{
//		direction="up";
//		dy-=speed;
//	}
//	else if(keyH.downPressed == true)
//	{
//		direction="down";
//		dy+=speed;
//	}
//	else if(keyH.leftPressed == true)
//	{
//		direction="left";
//		dx-=speed;
//	}
//	else if(keyH.rightPressed == true)
//	{
//		direction="right";
//		dx+=speed;
//	}
//	x+=dx;
//	y+=dy;
//	spriteCounter++;
//	if(spriteCounter>=12) {
//		if(spriteNum==1)
//			spriteNum=2;
//		else if(spriteNum==2)
//			     spriteNum=1;
//		spriteCounter=0;
//	}
//}
//public void draw(Graphics2D g2)
//{
//	BufferedImage image = null;
//	switch(direction) {
//	case "up" :
//		if(spriteNum==1)
//		{
//		image = up1;	
//		}
//		if(spriteNum==2)
//		{
//		image = up2;	
//		}
//		break;
//	case "down" :
//		if(spriteNum==1)
//		{
//		image = down1;	
//		}
//		if(spriteNum==2)
//		{
//		image = down2;	
//		}
//		break;
//	case "left" :
//		if(spriteNum==1)
//		{
//		image = left1;	
//		}
//		if(spriteNum==2)
//		{
//		image = left2;	
//		}
//		break;
//	case "right" :
//		if(spriteNum==1)
//		{
//		image = right1;	
//		}
//		if(spriteNum==2)
//		{
//		image =right2;	
//		}
//		break;
//		
//	}
//	g2.drawImage(image, x, y, gp.tileSize,gp.tileSize,null);
//}
//
//
//}
//
//