package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	public void checkTile(Entity entity) {
		// En : Entity
		int EnX = entity.getPosition()[0];
		int EnY = entity.getPosition()[1];
		int EnSolidX = (int) entity.getSolidArea().getX();
		int EnSolidY = (int) entity.getSolidArea().getY();
		int EnSolidW = (int) entity.getSolidArea().getWidth();
		int EnSolidH = (int) entity.getSolidArea().getHeight();
		
		int EnLeftEdge = EnX + EnSolidX;
		int EnRightEdge = EnX + EnSolidX + EnSolidW ;
		int EnTopEdge = EnY + EnSolidY;
		int EnBottomEdge = EnY + EnSolidY +EnSolidH;
		
		int EnLeftCol = EnLeftEdge/gp.getTileSize();
		int EnRightCol = EnRightEdge/gp.getTileSize();
		int EnTopRow = EnTopEdge/gp.getTileSize();
		int EnBottomRow = EnBottomEdge/gp.getTileSize();
		
		int EnSpeed = entity.getSpeed();
		int tileLeft,tileRight,tileTop,tileBottom,tileCorner,predictTopRow,predictBottomRow,predictLeftCol,predictRightCol;
		
		switch(entity.getDirection()) 
    	{
    	case "up" :
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "left" :
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][EnBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "right" :
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][EnBottomRow];
    		if(gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "right and up" :
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictTopRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][EnBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictRightCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true || gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true || gp.tilemanager.getTile()[tileCorner].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "right and down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictBottomRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictRightCol][EnBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictRightCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true || gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true || gp.tilemanager.getTile()[tileCorner].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "left and up" :
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictTopRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][EnBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictLeftCol][predictTopRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true || gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true || gp.tilemanager.getTile()[tileCorner].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	case "left and down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileLeft = gp.tilemanager.getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.tilemanager.getMapTileNum()[EnRightCol][predictBottomRow];
    		tileTop = gp.tilemanager.getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.tilemanager.getMapTileNum()[predictLeftCol][EnBottomRow];
    		tileCorner = gp.tilemanager.getMapTileNum()[predictLeftCol][predictBottomRow];
    		if(gp.tilemanager.getTile()[tileLeft].isCollision()==true || gp.tilemanager.getTile()[tileRight].isCollision()==true || gp.tilemanager.getTile()[tileTop].isCollision()==true || gp.tilemanager.getTile()[tileBottom].isCollision()==true || gp.tilemanager.getTile()[tileCorner].isCollision()==true)
    		{entity.setCollide(true);}
    		break;
    	}
	}
	
	public void checkOtherEntity(Entity entity, Entity entities[]) {
		// En : Entity
		int EnX = entity.getPosition()[0];
		int EnY = entity.getPosition()[1];
		int EnSolidX = (int) entity.getSolidArea().getX();
		int EnSolidY = (int) entity.getSolidArea().getY();
		int EnSolidW = (int) entity.getSolidArea().getWidth();
		int EnSolidH = (int) entity.getSolidArea().getHeight();
		
		int EnLeftEdge = EnX + EnSolidX;
		int EnRightEdge = EnX + EnSolidX + EnSolidW ;
		int EnTopEdge = EnY + EnSolidY;
		int EnBottomEdge = EnY + EnSolidY +EnSolidH;
		
		int EnSpeed = entity.getSpeed();
		int predictTopEdge,predictBottomEdge,predictLeftEdge,predictRightEdge;
		int predictTopRow,predictBottomRow,predictLeftCol,predictRightCol;
		switch(entity.getDirection()) 
    	{
    	case "up" :
    		predictTopEdge = EnTopEdge - EnSpeed;
    		predictBottomEdge = EnBottomEdge - EnSpeed;
    		predictLeftEdge = EnLeftEdge;
    		predictRightEdge = EnRightEdge;
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "down" :
    		predictTopEdge = EnTopEdge + EnSpeed;
    		predictBottomEdge = EnBottomEdge + EnSpeed;
    		predictLeftEdge = EnLeftEdge;
    		predictRightEdge = EnRightEdge;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "left" :
    		predictTopEdge = EnTopEdge;
    		predictBottomEdge = EnBottomEdge;
    		predictLeftEdge = EnLeftEdge - EnSpeed;
    		predictRightEdge = EnRightEdge - EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "right" :
    		predictTopEdge = EnTopEdge;
    		predictBottomEdge = EnBottomEdge;
    		predictLeftEdge = EnLeftEdge + EnSpeed;
    		predictRightEdge = EnRightEdge + EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "right and up" :
    		predictTopEdge = EnTopEdge - EnSpeed;
    		predictBottomEdge = EnBottomEdge - EnSpeed;
    		predictLeftEdge = EnLeftEdge + EnSpeed;
    		predictRightEdge = EnRightEdge + EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "right and down" :
    		predictTopEdge = EnTopEdge + EnSpeed;
    		predictBottomEdge = EnBottomEdge + EnSpeed;
    		predictLeftEdge = EnLeftEdge + EnSpeed;
    		predictRightEdge = EnRightEdge + EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "left and up" :
    		predictTopEdge = EnTopEdge - EnSpeed;
    		predictBottomEdge = EnBottomEdge - EnSpeed;
    		predictLeftEdge = EnLeftEdge - EnSpeed;
    		predictRightEdge = EnRightEdge - EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	case "left and down" :
    		predictTopEdge = EnTopEdge + EnSpeed;
    		predictBottomEdge = EnBottomEdge + EnSpeed;
    		predictLeftEdge = EnLeftEdge - EnSpeed;
    		predictRightEdge = EnRightEdge - EnSpeed;
    		for(int i=0;i<entities.length;i++) {
    			if(entities[i] != null) {
    				entity.setCollide(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge));
    			}
    		}
    		break;
    	}
		
	}
	
	private boolean checkCollide(Entity otherEntity, int predLeftEdge, int predRightEdge, int predTopEdge, int predBottomEdge) {
		// Entity attribute
		int EnX = otherEntity.getPosition()[0];
		int EnY = otherEntity.getPosition()[1];
		int EnSolidX = (int) otherEntity.getSolidArea().getX();
		int EnSolidY = (int) otherEntity.getSolidArea().getY();
		int EnSolidW = (int) otherEntity.getSolidArea().getWidth();
		int EnSolidH = (int) otherEntity.getSolidArea().getHeight();
		
		int otherEnLeftEdge = EnX + EnSolidX;
		int otherEnRightEdge = EnX + EnSolidX + EnSolidW;
		int otherEnTopEdge = EnY + EnSolidY;
		int otherEnBottomEdge = EnY + EnSolidY +EnSolidH;
		int otherEnSpeed = otherEntity.getSpeed();
		
		if((otherEnLeftEdge < predRightEdge && predRightEdge < otherEnRightEdge) && (otherEnBottomEdge >= predTopEdge && predTopEdge >= otherEnTopEdge)) {
			System.out.println("true 1");
			return true;
		}
		
		if((otherEnLeftEdge < predLeftEdge && predLeftEdge < otherEnRightEdge) && (otherEnBottomEdge >= predBottomEdge && predBottomEdge >= otherEnTopEdge)) {
			System.out.println("true 2");
			return true;
		}
		
		if((otherEnLeftEdge < predRightEdge && predRightEdge < otherEnRightEdge) && (otherEnBottomEdge >= predTopEdge && predTopEdge >= otherEnTopEdge)) {
			System.out.println("true 3");
			return true;
		}
		
		if((otherEnLeftEdge < predLeftEdge && predLeftEdge < otherEnRightEdge) && (otherEnBottomEdge >= predBottomEdge && predBottomEdge >= otherEnTopEdge)) {
			System.out.println("true 4");
			return true;
		}
		
		return false;	
	}
}
