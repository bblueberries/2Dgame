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
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    private final GamePanel gamePanel;
   
    // Player properties and variables
//    private int x;
//    private int y;
//    private int speed;
    private final int ScreenX;
    private final int ScreenY;
    
    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
		this.ScreenX =	gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
		this.ScreenY =  gamePanel.screenHeight/2-(gamePanel.tileSize/2);
        
      
        this.getPlayerImage();
        this.setDefaultValue();
    }
    private void setDefaultValue()
    {
    	WorldX = gamePanel.tileSize*23;
        WorldY = gamePanel.tileSize*21; 
        speed = 6;
        direction = "up";
    }
	  private void getPlayerImage()
	  {
		  	up1 = new Image(getClass().getResourceAsStream("/player/down1.png"));
	  		up2 =  new Image(getClass().getResourceAsStream("/player/down2.png"));
	  		down1 = new Image(getClass().getResourceAsStream("/player/up1.png"));
	  		down2 = new Image(getClass().getResourceAsStream("/player/up2.png"));
	  		left1 = new Image(getClass().getResourceAsStream("/player/left1.png"));
	  		left2 = new Image(getClass().getResourceAsStream("/player/left2.png"));
	  		right1 = new Image(getClass().getResourceAsStream("/player/right1.png"));
	  		right2 = new Image(getClass().getResourceAsStream("/player/right2.png"));
	
	  	}
    public void update() {
        // Update player position based on input from keyHandler
        if (KeyHandler.getKeyPressed(KeyCode.W)) {
        	 WorldY -= speed;
            direction="up";
        }
        else if (KeyHandler.getKeyPressed(KeyCode.S)) {
        	WorldY += speed;
            direction="down";
        }
        else if (KeyHandler.getKeyPressed(KeyCode.A)) {
        	 WorldX -= speed;
            direction="left";
        } 
        else if (KeyHandler.getKeyPressed(KeyCode.D)) {
        	 WorldX += speed;
            direction="right";
        }

//        // Keep the player within the gamePanel boundaries
//        if ( WorldX < 0 ) {
//        	 WorldX = 0 ;
//        } 
//        if ( WorldX > gamePanel.screenWidth - gamePanel.tileSize) {
//        	 WorldX = gamePanel.screenWidth-gamePanel.tileSize;
//        }
//        if (WorldY  < 0) {
//        	WorldY  = 0;
//        }
//        if (WorldY  > gamePanel.screenHeight - gamePanel.tileSize) {
//        	WorldY  = gamePanel.screenHeight-gamePanel.tileSize;
//        }
        spriteCounter++;
        if(spriteCounter>=12)
        {
        	spriteCounter=0;
        	spriteNum=(spriteNum+1)%2;
        	
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
    	
    	switch(direction) 
    	{
    	case "up" :
    		imagetofill = ((spriteNum == 1)? up1:up2);
    		break;
    	case "down" :
    		imagetofill = ((spriteNum == 1)? down1:down2);
    		break;
    	case "left" :
    		imagetofill = ((spriteNum == 1)? left1:left2);
    		
    		break;
    	case "right" :
    		imagetofill = ((spriteNum == 1)? right1:right2);
    		break;	
    	}
    	
    	gc.drawImage(imagetofill,ScreenX,ScreenY ,gamePanel.tileSize, gamePanel.tileSize);
    	
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