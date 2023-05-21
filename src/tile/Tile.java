package tile;

import javafx.scene.image.Image;

public class Tile {
	
	private Image image;
	private boolean isCollision;
	
	public Tile() {
		setImage(null);
		setCollision(false);
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isCollision() {
		return isCollision;
	}
	public void setCollision(boolean isCollision) {
		this.isCollision = isCollision;
	}
}
