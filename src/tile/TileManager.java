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
        tile = new Tile[20];
        getTileImage();
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        loadMap("/maps/map.txt");
    }

    private void getTileImage() {
        try {
        	//set tile for use later
        	//List index of tile
        	//0 : normal ground
        	//1 : tree1
        	//2 : tree2
        	//3 : mossy ground
        	//4 : foot path
        	//5 : foot path2
        	//6 : corrupt area (didn't use)
        	//7 : half foot path (didn't use)
        	//8 : mystery crystal in the middle of crater
        	//9 : crater ground
        	
            tile[0] = new Tile();
            tile[0].setImage(new Image(getClass().getResourceAsStream("/tiles/tile00.png")));  
            
            tile[1] = new Tile();
            tile[1].setImage(new Image(getClass().getResourceAsStream("/tiles/tile01.png")));
            tile[1].setCollision(true);
            
            tile[2] = new Tile();
            tile[2].setImage(new Image(getClass().getResourceAsStream("/tiles/tile02.png"))); 
            tile[2].setCollision(true);
            
            tile[3] = new Tile();
            tile[3].setImage(new Image(getClass().getResourceAsStream("/tiles/tile03.png")));  
            
            tile[4] = new Tile();
            tile[4].setImage(new Image(getClass().getResourceAsStream("/tiles/tile04.png"))); 
            
            tile[5] = new Tile();
            tile[5].setImage(new Image(getClass().getResourceAsStream("/tiles/tile05.png")));  
            
            tile[6] = new Tile();
            tile[6].setImage(new Image(getClass().getResourceAsStream("/tiles/tile06.png")));  

            tile[7] = new Tile();
            tile[7].setImage(new Image(getClass().getResourceAsStream("/tiles/tile07.png")));  
            
            tile[8] = new Tile();
            tile[8].setImage(new Image(getClass().getResourceAsStream("/tiles/tile08.png")));  
            
            tile[9] = new Tile();
            tile[9].setImage(new Image(getClass().getResourceAsStream("/tiles/tile09.png"))); 


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filePath) {
        try {
        	//read file and convert to array mapTileNum
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
    	//loop in mapTileNum 
    	//which each value in array represent index of tile[] above
    	
        for (int row = 0; row < gp.getMaxWorldRow(); row++) {
            for (int col = 0; col < gp.getMaxWorldCol(); col++) 
            {	
            	int drawY = row * gp.getTileSize();
        		int drawX = col * gp.getTileSize();
            	this.screenY=drawY + gp.getPlayer().getScreenY() - gp.getPlayer().getPosition()[1];
            	this.screenX=drawX + gp.getPlayer().getScreenX() - gp.getPlayer().getPosition()[0];
            	
            	//only draw in player POV
            	if( drawX + gp.getTileSize() > gp.getPlayer().getPosition()[0] - gp.getPlayer().getScreenX()&&
            		drawX - gp.getTileSize() < gp.getPlayer().getPosition()[0] + gp.getPlayer().getScreenX()&&
            		drawY + gp.getTileSize() > gp.getPlayer().getPosition()[1] - gp.getPlayer().getScreenY()&&
            		drawY - gp.getTileSize() < gp.getPlayer().getPosition()[1] + gp.getPlayer().getScreenY()
            			)
            	{
            	//draw tile's image depend on value in mapTileNum
                gc.drawImage(tile[mapTileNum[col][row]].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize());
            	}
            }
        }
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
