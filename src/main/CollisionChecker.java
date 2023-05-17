package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = (int)(entity.WorldX + entity.solidArea.getX());
		int entityRightWorldX =  (int) (entity.WorldX + entity.solidArea.getX() + entity.solidArea.getWidth());
		int entityTopWorldY = (int)(entity.WorldY + entity.solidArea.getY());
		int entityBottomWorldY =  (int) (entity.WorldY + entity.solidArea.getY() + entity.solidArea.getHeight());
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		
		int tileLeft,tileRight,tileTop,tileBottom;
		switch(entity.direction) 
    	{
    	case "up" :
    		int predictTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true)
    		{entity.collisionOn = true;}
    		break;
    	case "down" :
    		int predictBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true)
    		{entity.collisionOn = true;}
    		break;
    	case "left" :
    		int predictLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][entityBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true)
    		{entity.collisionOn = true;}
    		break;
    		

    	case "right" :
    		int predictRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][entityBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true)
    		{entity.collisionOn = true;}
    		break;	
    	}
	}
}
