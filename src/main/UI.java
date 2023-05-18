
package main;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UI {
	
	private GamePanel gp;
	private GraphicsContext gc;
	private int cursorNum=0;
	
	public UI(GamePanel gp,GraphicsContext gc) {
		this.gp = gp;
		this.gc= gc;
	}
	public void draw(GraphicsContext gc) {
		
		if(gp.getGameState()==GamePanel.titleState)
		{
			drawTitleScreen();
		}
	}
	public void drawTitleScreen()
	{
			gc.setFont(Font.font("Courier New",FontWeight.BOLD,70));
			String text ="HIT MONSTER GAME"; 
			
			gc.setFill(Color.BLUEVIOLET);
			gc.fillText(text, gp.getTileSize()+3, gp.getTileSize()*1.7+3);
			gc.setFill(Color.WHITE);
			gc.fillText(text, gp.getTileSize(), gp.getTileSize()*1.7);
			
			
			
			
			int x = (int) getCenteredTextX(new Text(text),70);
			int y =(int) (gp.getTileSize()*2.5);
			
//			Image logo = new Image(getClass().getResourceAsStream("/gameLOGO.png"));
//			gc.drawImage(logo, x,y, gp.getTileSize()*4,gp.getTileSize()*4);
			
			
			gc.setFont(Font.font("Courier New",FontWeight.BOLD,40));
			gc.setFill(Color.WHITE);
			
			text ="NEW GAME"; 
			x= (int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize()*5;
			gc.fillText(text, x, y);
			if(cursorNum==0) {
				gc.fillText("‣", x-gp.getTileSize(), y);
			}
			
			
			text ="LOAD GAME"; 
			x=(int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize();
			gc.fillText(text,x, y);
			if(cursorNum==1) {
				gc.fillText("‣", x-gp.getTileSize(), y);
			}
			
			
			text ="QUIT"; 
			x=(int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize();
			gc.fillText(text, x, y);
			if(cursorNum==2) {
				gc.fillText("‣", x-gp.getTileSize(), y);
			}
			
			
	}	

	
	public void drawScreen(int x,int y,int width,int height){
			
		Color color = Color.rgb(0,0,0,0.8);

		this.gc.setStroke(Color.WHITE);
		gc.setLineWidth(4);
		gc.strokeRoundRect(x, y, width, height,40,40);
		 
		gc.setFill(color);
		gc.fillRoundRect(x+3, y+3, width-6, height-6, 34,34);
		
		Text text = new Text("OPTION");
		Font font = Font.font("Courier New",40);
		Color textColor = Color.WHITE;
		int textX = (int) getCenteredTextX(text,40);
		int textY= y+gp.getTileSize();
		
		gc.setFont(font);
		gc.setFill(textColor);
		gc.fillText(text.getText(),textX,textY );
	
	}
	private double measureStringWidth(Text text) {
        text.applyCss();
        return text.getLayoutBounds().getWidth();
    }
	private double getCenteredTextX(Text text,int fontSize) {
	    double screenWidth = gp.getScreenWidth();
	    double textWidth = measureStringWidth(text);
	    return (screenWidth - textWidth/text.getFont().getSize()*fontSize) / 2   ;
	}
	public int getCursorNum() {
		return cursorNum;
	}
	public void setCursorNum(int cursorNum) {
		this.cursorNum = cursorNum;
	}
	
}