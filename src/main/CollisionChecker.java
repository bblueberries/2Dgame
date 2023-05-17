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
		
		
		int tileLeft,tileRight,tileTop,tileBottom,tileCorner,predictTopRow,predictBottomRow,predictLeftCol,predictRightCol;
		switch(entity.direction) 
    	{
    	case "up" :
    		predictTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "down" :
    		predictBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "left" :
    		predictLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][entityBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "right" :
    		predictRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][entityBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "right and up" :
    		predictTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
    		predictRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictTopRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][entityBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictRightCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true || gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true || gp.tilemanager.getTile()[tileCorner].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "right and down" :
    		predictBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
    		predictRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictBottomRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][entityBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictRightCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true || gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true || gp.tilemanager.getTile()[tileCorner].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "left and up" :
    		predictTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
    		predictLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictTopRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][entityBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictLeftCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true || gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true || gp.tilemanager.getTile()[tileCorner].collision==true)
    		{entity.isCollide = true;}
    		break;
    	case "left and down" :
    		predictBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
    		predictLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
    		tileLeft = gp.tilemanager.getMapTileNum()[entityLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[entityRightCol][predictBottomRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][entityTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][entityBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictLeftCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].collision==true || gp.tilemanager.getTile()[tileRight].collision==true || gp.tilemanager.getTile()[tileTop].collision==true || gp.tilemanager.getTile()[tileBottom].collision==true || gp.tilemanager.getTile()[tileCorner].collision==true)
    		{entity.isCollide = true;}
    		break;
    	}
	}
}
