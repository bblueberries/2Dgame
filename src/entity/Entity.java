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

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public class Entity {
	private GamePanel gp;
	private int[] Position; // world position
	private int[] screenPos;
    private int speed;
    private int diaSpeed;

    private ArrayList<Image> images;
    private String direction;

    private int spriteCounter = 0;
    private int spriteNum = 1;
    
    private Rectangle solidArea;
    private boolean isCollide = false;
    
    //CHARACTER STATUS
    private int maxLife;
    private int life;
    
    public Entity() {
    	images = new ArrayList<Image>();   //index up:0,1  | down:2,3 | left:4,5 | right:6,7 
    	solidArea = new Rectangle();
    	Position = new int[2];
    	screenPos = new int[2];
    }  
    
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
	public int[] getPosition() {
		return Position;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDiaSpeed() {
		return diaSpeed;
	}
	public void setDiaSpeed(int diaSpeed) {
		this.diaSpeed = diaSpeed;
	}
	public ArrayList<Image> getImages() {
		return images;  
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getSpriteCounter() {
		return spriteCounter;
	}
	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
	public int getSpriteNum() {
		return spriteNum;
	}
	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}
	public Rectangle getSolidArea() {
		return solidArea;
	}
	
	public boolean isCollide() {
		return isCollide;
	}
	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}


	public void setPosition(int x,int y) {
		Position[0] = x;
		Position[1] = y;
	}

	public GamePanel getGp() {
		return gp;
	}
}
