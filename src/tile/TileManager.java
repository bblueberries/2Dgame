package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import main.GamePanel;
public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int[][] mapTileNum;
    private int screenX;
    private int screenY;
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        loadMap("/maps/maptest.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].setImage(new Image(getClass().getResourceAsStream("/tiles/tiletest.png")));  
            
            tile[1] = new Tile();
            tile[1].setImage(new Image(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].setCollision(true);
            
            tile[2] = new Tile();
            tile[2].setImage(new Image(getClass().getResourceAsStream("/tiles/water00.png"))); 
            tile[2].setCollision(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.getMaxWorldRow(); row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.getMaxWorldCol(); col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        for (int row = 0; row < gp.getMaxWorldRow(); row++) {
        	
        
        	
            for (int col = 0; col < gp.getMaxWorldCol(); col++) 
            {	
            	int drawY = row*gp.getTileSize();
        		int drawX = col*gp.getTileSize();
            	this.screenY=drawY + gp.getPlayer().getScreenY() - gp.getPlayer().getPosition()[1];
            	this.screenX=drawX + gp.getPlayer().getScreenX() - gp.getPlayer().getPosition()[0];
            	
            	if( drawX + gp.getTileSize() > gp.getPlayer().getPosition()[0] - gp.getPlayer().getScreenX()&&
            		drawX - gp.getTileSize() < gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX()&&
            		drawY + gp.getTileSize() > gp.getPlayer().getPosition()[1] - gp.getPlayer().getScreenY()&&
            		drawY - gp.getTileSize() < gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY()
            			)
            	{
                gc.drawImage(tile[mapTileNum[col][row]].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize());
            	}
            }
        }
    }

	public GamePanel getGp() {
		return gp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public Tile[] getTile() {
		return tile;
	}

	public void setTile(Tile[] tile) {
		this.tile = tile;
	}

	public int[][] getMapTileNum() {
		return mapTileNum;
	}

	public void setMapTileNum(int[][] mapTileNum) {
		this.mapTileNum = mapTileNum;
	}

	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}
}
