package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][] ;
	
	
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile = new Tile[10];
		getTileImage();
		mapTileNum =new int [gp.maxScreenCol][gp.maxScreenRow];
		loadMap();
		
		
	}
	public void getTileImage()
	{
		
		try {
			tile[0]=new Tile();
			tile[0].image =ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
			
			tile[1]=new Tile();
			tile[1].image =ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

			tile[2]=new Tile();
			tile[2].image =ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap()
	{
		try {
			InputStream is = getClass().getResourceAsStream("/maps/maptest.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			

			
			for(int row=0;row<gp.maxScreenRow;row++ )
			{
				String line=br.readLine();
				String numbers[]= line.split(" ");
				for(int col=0;col<gp.maxScreenCol;col++)
				{
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row]=num;		
				}
			}
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2)
	{
		for(int row=0;row<gp.maxScreenRow;row++)
		{
			for(int col=0;col<gp.maxScreenCol;col++)
			{
				g2.drawImage( tile[mapTileNum[col][row] ].image, col*48, row*48, gp.tileSize, gp.tileSize, null);
				
			}
		}
	
			
		
	}
}
