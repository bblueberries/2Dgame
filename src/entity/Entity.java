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
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import main.GamePanel;

public abstract class Entity {
	private GamePanel gp;
	private int[] position; // world position
    private int speed;
    private int diaSpeed;
    private ArrayList<Image> images;
    private String direction;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private Rectangle solidArea;
    private boolean isCollide = false;
    private int xPos;
    private int yPos;
    
    // Constructor
    public Entity() {
    	this.setImages(new ArrayList<Image>());
    	this.setSolidArea(new Rectangle());
    	this.setPosition(new int[2]);
    	
    }
    
    // Switch character sprite
    public void calculateSprite() {
    	this.setSpriteCounter(this.getSpriteCounter()+1);
	    if(this.getSpriteCounter()>=12) {
	        this.setSpriteCounter(0);
	        this.setSpriteNum((this.getSpriteNum()+1)%2);	
	    }
    }
    
    // Abstract method
    public abstract void setDefaultValue();
    
    // Getter and Setter method
	public int[] getPosition() {
		return position;
	}
	
	public void setPosition(int[] position) {
		this.position = position;
	}
	
	public void setPosition(int x,int y) {
		position[0] = x;
		position[1] = y;
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
	
	public void setImages(ArrayList<Image> images) {
		this.images = images;
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
	
	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}

	public boolean getIsCollide() {
		return isCollide;
	}
	
	public void setIsCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}
	
	public GamePanel getGp() {
		return gp;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
}
