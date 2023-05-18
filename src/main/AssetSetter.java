package main;

import entity.Monster;

public class AssetSetter {
	private GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
	}
	
	public void setMonster() {
		gp.getMonster()[0] = new Monster(gp);
	}
}
