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
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/maptest.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = new Image(getClass().getResourceAsStream("/tiles/grass01.png"));
            
            tile[1] = new Tile();
            tile[1].image = new Image(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;
            
            tile[2] = new Tile();
            tile[2].image = new Image(getClass().getResourceAsStream("/tiles/water00.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        for (int row = 0; row < gp.maxWorldRow; row++) {
        	
        
        	
            for (int col = 0; col < gp.maxWorldCol; col++) 
            {	
            	int drawY = row*gp.tileSize;
        		int drawX = col*gp.tileSize;
            	this.screenY=drawY + gp.getPlayer().getScreenY() - gp.getPlayer().WorldY;
            	this.screenX=drawX + gp.getPlayer().getScreenX() - gp.getPlayer().WorldX;
            	
            	if( drawX + gp.tileSize > gp.getPlayer().WorldX - gp.getPlayer().getScreenX()&&
            		drawX - gp.tileSize< gp.getPlayer().WorldX + gp.getPlayer().getScreenX()&&
            		drawY + gp.tileSize > gp.getPlayer().WorldY - gp.getPlayer().getScreenY()&&
            		drawY - gp.tileSize< gp.getPlayer().WorldY + gp.getPlayer().getScreenY()
            			)
            	{
                gc.drawImage(tile[mapTileNum[col][row]].image, screenX, screenY, gp.tileSize, gp.tileSize);
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
