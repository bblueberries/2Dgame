package main;

import entity.Entity;

public class CollisionChecker {
	private GamePanel gp;
	
	// Constructor
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	// Check whether entity can move through the tile.
	public void checkTile(Entity entity) {
		// Initialize new name to entity attributes.
		int EnLeftEdge = entity.getPosition()[0] + (int) entity.getSolidArea().getX();
		int EnRightEdge = entity.getPosition()[0] + (int) entity.getSolidArea().getX() + (int) entity.getSolidArea().getWidth();
		int EnTopEdge = entity.getPosition()[1] + (int) entity.getSolidArea().getY();;
		int EnBottomEdge = entity.getPosition()[1] + (int) entity.getSolidArea().getY() + (int) entity.getSolidArea().getHeight();
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
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictTopRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictBottomRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "left" :
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileTop = gp.getTileManager().getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictLeftCol][EnBottomRow];
    		if(gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "right" :
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileTop = gp.getTileManager().getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictRightCol][EnBottomRow];
    		if(gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "right and up" :
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictTopRow];
    		tileTop = gp.getTileManager().getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictRightCol][EnBottomRow];
    		tileCorner = gp.getTileManager().getMapTileNum()[predictRightCol][predictTopRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision() || gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision() || gp.getTileManager().getTile()[tileCorner].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "right and down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		predictRightCol = (EnRightEdge + EnSpeed)/gp.getTileSize();
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictBottomRow];
    		tileTop = gp.getTileManager().getMapTileNum()[predictRightCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictRightCol][EnBottomRow];
    		tileCorner = gp.getTileManager().getMapTileNum()[predictRightCol][predictBottomRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision() || gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision() || gp.getTileManager().getTile()[tileCorner].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "left and up" :
    		predictTopRow = (EnTopEdge - EnSpeed)/gp.getTileSize();
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictTopRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictTopRow];
    		tileTop = gp.getTileManager().getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictLeftCol][EnBottomRow];
    		tileCorner = gp.getTileManager().getMapTileNum()[predictLeftCol][predictTopRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision() || gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision() || gp.getTileManager().getTile()[tileCorner].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	case "left and down" :
    		predictBottomRow = (EnBottomEdge + EnSpeed)/gp.getTileSize();
    		predictLeftCol = (EnLeftEdge - EnSpeed)/gp.getTileSize();
    		tileLeft = gp.getTileManager().getMapTileNum()[EnLeftCol][predictBottomRow];
    		tileRight = gp.getTileManager().getMapTileNum()[EnRightCol][predictBottomRow];
    		tileTop = gp.getTileManager().getMapTileNum()[predictLeftCol][EnTopRow];
    		tileBottom = gp.getTileManager().getMapTileNum()[predictLeftCol][EnBottomRow];
    		tileCorner = gp.getTileManager().getMapTileNum()[predictLeftCol][predictBottomRow];
    		if(gp.getTileManager().getTile()[tileLeft].isCollision() || gp.getTileManager().getTile()[tileRight].isCollision() || gp.getTileManager().getTile()[tileTop].isCollision() || gp.getTileManager().getTile()[tileBottom].isCollision() || gp.getTileManager().getTile()[tileCorner].isCollision()) {
    			entity.setIsCollide(true);
    		}
    		break;
    	}
	}
	
	// Set the value of every other entity that collides to null.
	private void checkCollideEveryEntity(Entity entity,Entity entities[], int predictTopEdge, int predictBottomEdge, int predictLeftEdge, int predictRightEdge) {
		for(int i=0;i<entities.length;i++) {
			if(checkCollide(entities[i],predictLeftEdge,predictRightEdge,predictTopEdge,predictBottomEdge)) {
				entities[i] = null;
			} else {
				entity.setIsCollide(false);
			}
		}
	}
	
	// Check whether the entity is collide with every other entity
	public void checkOtherEntity(Entity entity, Entity entities[]) {
		// Initialize new name to entity attributes.
		int entityLeftEdge = entity.getPosition()[0] + (int) entity.getSolidArea().getX();
		int entityRightEdge = entity.getPosition()[0] + (int) entity.getSolidArea().getX() + (int) entity.getSolidArea().getWidth() ;
		int entityTopEdge = entity.getPosition()[1] + (int) entity.getSolidArea().getY();
		int entityBottomEdge = entity.getPosition()[1] + (int) entity.getSolidArea().getY() + (int) entity.getSolidArea().getHeight();
		int EnSpeed = entity.getSpeed();
		int predictTopEdge,predictBottomEdge,predictLeftEdge,predictRightEdge;
		
		switch(entity.getDirection()) {
		
    	case "up" :
    		predictTopEdge = entityTopEdge - EnSpeed;
    		predictBottomEdge = entityBottomEdge - EnSpeed;
    		predictLeftEdge = entityLeftEdge;
    		predictRightEdge = entityRightEdge;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "down" :
    		predictTopEdge = entityTopEdge + EnSpeed;
    		predictBottomEdge = entityBottomEdge + EnSpeed;
    		predictLeftEdge = entityLeftEdge;
    		predictRightEdge = entityRightEdge;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "left" :
    		predictTopEdge = entityTopEdge;
    		predictBottomEdge = entityBottomEdge;
    		predictLeftEdge = entityLeftEdge - EnSpeed;
    		predictRightEdge = entityRightEdge - EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "right" :
    		predictTopEdge = entityTopEdge;
    		predictBottomEdge = entityBottomEdge;
    		predictLeftEdge = entityLeftEdge + EnSpeed;
    		predictRightEdge = entityRightEdge + EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "right and up" :
    		predictTopEdge = entityTopEdge - EnSpeed;
    		predictBottomEdge = entityBottomEdge - EnSpeed;
    		predictLeftEdge = entityLeftEdge + EnSpeed;
    		predictRightEdge = entityRightEdge + EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "right and down" :
    		predictTopEdge = entityTopEdge + EnSpeed;
    		predictBottomEdge = entityBottomEdge + EnSpeed;
    		predictLeftEdge = entityLeftEdge + EnSpeed;
    		predictRightEdge = entityRightEdge + EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "left and up" :
    		predictTopEdge = entityTopEdge - EnSpeed;
    		predictBottomEdge = entityBottomEdge - EnSpeed;
    		predictLeftEdge = entityLeftEdge - EnSpeed;
    		predictRightEdge = entityRightEdge - EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	case "left and down" :
    		predictTopEdge = entityTopEdge + EnSpeed;
    		predictBottomEdge = entityBottomEdge + EnSpeed;
    		predictLeftEdge = entityLeftEdge - EnSpeed;
    		predictRightEdge = entityRightEdge - EnSpeed;
    		checkCollideEveryEntity(entity, entities, predictTopEdge, predictBottomEdge, predictLeftEdge, predictRightEdge);
    		break;
    	}
		
	}
	
	// Check if the edges of both entities are colliding.
	private boolean checkEdgeCollide(int predTopEdge, int predBottomEdge, int predLeftEdge, int predRightEdge, int predOtherEnTopEdge, int predOtherEnBottomEdge, int predOtherEnLeftEdge, int predOtherEnRightEdge) {
		if((predOtherEnLeftEdge <= predRightEdge && predRightEdge <= predOtherEnRightEdge) && (predOtherEnBottomEdge >= predTopEdge && predTopEdge >= predOtherEnTopEdge)) {
			return true;
		} else if((predOtherEnLeftEdge <= predLeftEdge && predLeftEdge <= predOtherEnRightEdge) && (predOtherEnBottomEdge >= predTopEdge && predTopEdge >= predOtherEnTopEdge)) {
			return true;
		} else if((predOtherEnLeftEdge <= predRightEdge && predRightEdge <= predOtherEnRightEdge) && (predOtherEnBottomEdge >= predBottomEdge && predBottomEdge >= predOtherEnTopEdge)) {
			return true;
		} else if((predOtherEnLeftEdge <= predLeftEdge && predLeftEdge <= predOtherEnRightEdge) && (predOtherEnBottomEdge >= predBottomEdge && predBottomEdge >= predOtherEnTopEdge)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if both entities are colliding.
	private boolean checkCollide(Entity otherEntity, int predLeftEdge, int predRightEdge, int predTopEdge, int predBottomEdge) {
		if(otherEntity != null) {
			// Initialize new name to entity attributes.		
			int otherEnLeftEdge = otherEntity.getPosition()[0] + (int) otherEntity.getSolidArea().getX();
			int otherEnRightEdge = otherEntity.getPosition()[0] + (int) otherEntity.getSolidArea().getX() + (int) otherEntity.getSolidArea().getWidth();
			int otherEnTopEdge = otherEntity.getPosition()[1] + (int) otherEntity.getSolidArea().getY();
			int otherEnBottomEdge = otherEntity.getPosition()[1] + (int) otherEntity.getSolidArea().getY() + (int) otherEntity.getSolidArea().getHeight();
			int otherEnSpeed = otherEntity.getSpeed();
			
			int predOtherEnLeftEdge, predOtherEnRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge;
			
			switch(otherEntity.getDirection()) 
	    	{
	    	case "up" :
	    		predOtherEnTopEdge = otherEnTopEdge - otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge - otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge;
	    		predOtherEnRightEdge = otherEnRightEdge;	
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);
	    	case "down" :
	    		predOtherEnTopEdge = otherEnTopEdge + otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge + otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge;
	    		predOtherEnRightEdge = otherEnRightEdge;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "left" :
	    		predOtherEnTopEdge = otherEnTopEdge;
	    		predOtherEnBottomEdge = otherEnBottomEdge;
	    		predOtherEnLeftEdge = otherEnLeftEdge - otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge - otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "right" :
	    		predOtherEnTopEdge = otherEnTopEdge;
	    		predOtherEnBottomEdge = otherEnBottomEdge;
	    		predOtherEnLeftEdge = otherEnLeftEdge + otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge + otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "right and up" :
	    		predOtherEnTopEdge = otherEnTopEdge - otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge - otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge + otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge + otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "right and down" :
	    		predOtherEnTopEdge = otherEnTopEdge + otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge + otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge + otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge + otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "left and up" :
	    		predOtherEnTopEdge = otherEnTopEdge - otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge - otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge - otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge - otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	case "left and down" :
	    		predOtherEnTopEdge = otherEnTopEdge + otherEnSpeed;
	    		predOtherEnBottomEdge = otherEnBottomEdge + otherEnSpeed;
	    		predOtherEnLeftEdge = otherEnLeftEdge - otherEnSpeed;
	    		predOtherEnRightEdge = otherEnRightEdge - otherEnSpeed;
	    		return checkEdgeCollide(predTopEdge, predBottomEdge, predLeftEdge, predRightEdge, predOtherEnTopEdge, predOtherEnBottomEdge, predOtherEnLeftEdge, predOtherEnRightEdge);	
	    	default:
	    		return false;		
	    	}
		} else {
			return false;
		}
	}
}
