

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
	private int titleNum=0;
	private int state=0;
	private int optionNum=1;
	
	public UI(GamePanel gp,GraphicsContext gc) {
		this.gp = gp;
		this.gc= gc;
	}
	public void draw(GraphicsContext gc) {
		
		if(gp.getGameState()==GamePanel.titleState)
		{
			DrawTitleScreen();
		}
		else if(gp.getGameState()==GamePanel.pauseState)
		{
			DrawOptionScreen();
		}
	}
	public void DrawTitleScreen()
	{
			gc.setFont(Font.font("Courier New",FontWeight.BOLD,70));
			String text ="HIT MONSTER GAME"; 
			int x = (int) getCenteredTextX(new Text(text),70);
			int y =(int) (gp.getTileSize()*1.7);
			gc.setFill(Color.BLUEVIOLET);
			gc.fillText(text, gp.getTileSize()+3, gp.getTileSize()*1.7+3);
			gc.setFill(Color.WHITE);
			gc.fillText(text, gp.getTileSize(), gp.getTileSize()*1.7);
			
			
			
			
			x = (int) getCenteredTextX(new Text(text),70);
			y =(int) (gp.getTileSize()*2.5);
			
//			Image logo = new Image(getClass().getResourceAsStream("/gameLOGO.png"));
//			gc.drawImage(logo, x,y, gp.getTileSize()*4,gp.getTileSize()*4);
			
			
			gc.setFont(Font.font("Courier New",FontWeight.BOLD,40));
			gc.setFill(Color.WHITE);
			
			text ="NEW GAME"; 
			x= (int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize()*5;
			gc.fillText(text, x, y);
			if(titleNum==0) {
				gc.fillText("‣", x-gp.getTileSize(), y+4);
				
			}
			
			
			text ="LOAD GAME"; 
			x=(int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize();
			gc.fillText(text,x, y);
			if(titleNum==1) {
				gc.fillText("‣", x-gp.getTileSize(), y+4);
			}
			
			
			text ="QUIT"; 
			x=(int) getCenteredTextX(new Text(text),40);
			y+=gp.getTileSize();
			gc.fillText(text, x, y);
			if(titleNum==2) {
				gc.fillText("‣", x-gp.getTileSize(), y+4);
			}
			
			
	}	

	public void DrawOptionScreen()
    {
		int OptionScreenW=gp.getTileSize()*8;
		int OptionScreenH=gp.getTileSize()*8;
    	this.DrawScreen(gp.getTileSize()*4,gp.getTileSize()*2, OptionScreenW, OptionScreenH);
    	int X = gp.getTileSize()*4;
    	int Y =gp.getTileSize()*2;
    	
    	switch(getState()) {
    	case 0:Option(X,Y);break;
    	case 1:Option1(X,Y);break;
    	case 2:Option2(X,Y);break;
    	case 3:OptionEndGame(X,Y);break;
    	}
    }
	public void Option (int X,int Y) {
		
		int textX;
		int textY;
//		setState(0);
//		setOptionNum(1);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,30));
		gc.setFill(Color.WHITE);
		
		
		//option1
		String text = "option1";
		textX = X +gp.getTileSize();
		textY  = gp.getTileSize()*4 +14;
		gc.fillText(text, textX, textY);
		if(optionNum==1) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		//option2
		text = "option2";
		textY  += gp.getTileSize()+10;
		gc.fillText(text, textX, textY);
		if(optionNum==2) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		//option3
		text = "END GAME";
		textY  += gp.getTileSize()+10;
		gc.fillText(text, textX, textY);
		if(optionNum==3) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		
		//back
		text = "BACK";
		textY  += gp.getTileSize()*2 +24;
		gc.fillText(text, textX, textY);
		if(optionNum==4) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		
		
		
	}
	public void Option1(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,30));
		gc.setFill(Color.WHITE);
		String text = "this is option 1";
		int textX = X +gp.getTileSize();
		int textY  = Y+gp.getTileSize()*4;
		gc.fillText(text, textX, textY);
	}
	public void Option2(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,30));
		gc.setFill(Color.WHITE);
		String text = "this is option2";
		int textX = X +gp.getTileSize()-8;
		int textY  = Y+gp.getTileSize()*4;
		gc.fillText(text, textX, textY);

	}
	public void OptionEndGame(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.LIGHT,20));
		gc.setFill(Color.WHITE);
		
		String text = "Do you want to quit game";
		int textX = X+gp.getTileSize()-6;
		int textY  = (int) (Y+gp.getTileSize()*2);
		gc.fillText(text, textX, textY);
		
		text = "and return to title screen?";
		textX -= 12;
		textY  += gp.getTileSize();
		gc.fillText(text, textX, textY);
			
		text = "YES";
		textX = X+ gp.getTileSize()*3 +25;
		textY  += gp.getTileSize()*3;
		gc.fillText(text, textX, textY);
		if(optionNum==1) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+2);}
		
		text = "NO";
		textX += 5;
		textY  += gp.getTileSize();
		gc.fillText(text, textX, textY);
		if(optionNum==2) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+2);}
	
	}
	
	public void DrawScreen(int x,int y,int width,int height){
			
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
	    return (gp.getScreenWidth() - textWidth/text.getFont().getSize()*fontSize) / 2   ;
	}
	public int getTitleNum() {
		return titleNum;
	}
	public void setTitleNum(int titleNum) {
		this.titleNum = titleNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int optionState) {
		this.state = optionState;
	}
	public int getOptionNum() {
		return optionNum;
	}
	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}
	
}

