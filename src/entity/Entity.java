package entity;
//
//import java.awt.image.BufferedImage;
//
//public class Entity {
//	public int x,y;
//	public int speed;
//	
//	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
//	public String direction;
//	
//	public int spriteCounter=0;
//	public int spriteNum=1;
//}

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity {
    public int WorldX, WorldY;
    public int speed;
    public int diaSpeed;

    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea;
    public boolean isCollide = false;
    
    //CHARACTER STATUS
    private int maxLife;
    private int life;
    
    
    
	public int getMaxLife() {
		return maxLife;
	}
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}