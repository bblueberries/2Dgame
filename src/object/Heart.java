package object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GamePanel;
import tile.Tile;

public class Heart{
	private GamePanel gp;
	private Image image1,image2,image3;
	public Heart(GamePanel gp) {
		this.gp = gp;
		
		image1 = new Image(getClass().getResourceAsStream("/objects/heart_full.png"));
        image2 = new Image(getClass().getResourceAsStream("/objects/heart_half.png"));
        image3 = new Image(getClass().getResourceAsStream("/objects/heart_blank.png"));
        
	}
	public void draw(GraphicsContext gc) {
		
		int x = gp.getTileSize()/2;
		int y = gp.getTileSize()/2;
		
		for(int i=0;i<3;i++)
		{
			gc.drawImage(image3, x, y);
			x+=gp.getTileSize();
		}
		x = gp.getTileSize()/2;
		y = gp.getTileSize()/2;
		for(int i=0;i<gp.getPlayer().getLife();i++)
		{
			gc.drawImage(image2, x, y);
			i++;
			if( i<gp.getPlayer().getLife()) {
			gc.drawImage(image1, x, y);
			}
			
			x+=gp.getTileSize();
		}
		
	}
}
