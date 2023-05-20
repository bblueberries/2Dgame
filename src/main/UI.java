

package main;

import entity.Monster;
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
	private int titleState=0;
	private int state=0;
	private int optionNum=1;
	private boolean gameFinished=false;
	public UI(GamePanel gp,GraphicsContext gc) {
		this.gp = gp;
		this.gc= gc;
	}
	public void draw(GraphicsContext gc) {
		if(gp.getGameState()==GamePanel.endingState)
		{
			if(gameFinished)
			{
				DrawEndingText();
			}
		}
		else if(gp.getGameState()==GamePanel.titleState)
		{
			DrawTitleScreen();
		}
		else if(gp.getGameState()==GamePanel.pauseState)
		{
			DrawOptionScreen();
		}
		else if(gp.getGameState()==GamePanel.playingState)
		{
			DrawMonsterLeft(gp.getMonsterAlive((Monster[]) gp.getMonster()));
		}
	}
	public void DrawEndingText()
	{
		int x= gp.getTileSize();
		int y= gp.getScreenHeight()/2 - gp.getTileSize()*2;
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,80));
		gc.setFill(Color.BLACK);
		String text="CONGRATUATION!";
		gc.fillText(text, x, y);
		
		x= gp.getTileSize()+5;
		y= gp.getScreenHeight()/2 - gp.getTileSize()*2 +5;
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,80));
		gc.setFill(Color.GOLD);
		text="CONGRATUATION!";
		gc.fillText(text, x, y);
		
		x+= gp.getTileSize();
		y+=gp.getTileSize() -20;
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		gc.setFill(Color.BLACK);
		text="PRESS ESC TO GO BACK TO TITLE SCREEN..";
		gc.fillText(text, x, y);
		
		x+= 2;
		y+= 2;
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		gc.setFill(Color.LEMONCHIFFON);
		text="PRESS ESC TO GO BACK TO TITLE SCREEN..";
		gc.fillText(text, x, y);
	}
	public void DrawTitleScreen()
	{
			gc.setFont(Font.font("Courier New",FontWeight.BOLD,70));
			String text ="THE GHOST BUSTER"; 
			int x = gp.getTileSize()-5;
			int y =(int) (gp.getTileSize()*1.7);
			gc.setFill(Color.BLUEVIOLET);
			gc.fillText(text, gp.getTileSize()+3, gp.getTileSize()*1.7+3);
			gc.setFill(Color.WHITE);
			gc.fillText(text, gp.getTileSize(), gp.getTileSize()*1.7);
			
			
			
			
			x = (int)gp.getTileSize()*5-20;
			y =(int) (gp.getTileSize()*3);
			Image monsImage = new Image(getClass().getResourceAsStream("/monster/mons_down_1.png"));
			Image playerImage = new Image(getClass().getResourceAsStream("/player/ranger4.png"));
			gc.drawImage(gp.getPlayer().getImages().get(3), x,y, gp.getTileSize()*4,gp.getTileSize()*4);
			gc.drawImage(monsImage, x+gp.getTileSize()*2+20,y+gp.getTileSize(), gp.getTileSize()*3,gp.getTileSize()*3);
			
			gc.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD,40));
			gc.setFill(Color.WHITE);
			
			text ="NEW GAME"; 
			x= gp.getTileSize();
			y+=gp.getTileSize()*6-20;
			gc.fillText(text, x, y);
			if(titleNum==0) {
				gc.fillText("‣", x-gp.getTileSize()/2, y+2);
				
			}
			
			
			text ="LOAD GAME"; 
			x=gp.getTileSize();
			y+=gp.getTileSize()+10;
			gc.fillText(text,x, y);
			if(titleNum==1) {
				gc.fillText("‣", x-gp.getTileSize()/2, y+2);
			}
			
			
			text ="QUIT"; 
			x=gp.getTileSize();
			y+=gp.getTileSize()+10;
			gc.fillText(text, x, y);
			if(titleNum==2) {
				gc.fillText("‣", x-gp.getTileSize()/2, y+2);
			}
			
			
	}	
	public void DrawMonsterLeft(int monsterLeft) {
		int x= gp.getTileSize()*9 +10;
		int y= gp.getTileSize()*1;
		gc.setFont(Font.font("Courier New",FontWeight.NORMAL,30));
		gc.setFill(Color.YELLOW);
		String text= "Monsters Left :"+Integer.toString(monsterLeft);
		gc.fillText(text, x, y);
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
    	case 1:Control(X,Y);break;
    	case 2:Info(X,Y);break;
    	case 3:OptionEndGame(X,Y);break;
    	}
    }
	public void Option (int X,int Y) {
		
		int textX;
		int textY;
//		setState(0);
//		setOptionNum(1);
		
		
	
		
		Text header = new Text("OPTION");
		Font font = Font.font("Courier New",FontWeight.BOLD,40);
		Color textColor = Color.WHITE;
		gc.setFont(font);
		gc.setFill(textColor);
		textX = gp.getTileSize()*13/2 -6;
		textY= Y+gp.getTileSize()+10;
		
		gc.fillText(header.getText(),textX,textY );
	
		gc.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD,30));
		gc.setFill(Color.WHITE);
		//option1
		String text = "CONTROL";
		textX = X +gp.getTileSize();
		textY  = gp.getTileSize()*4 +25;
		gc.fillText(text, textX, textY);
		if(optionNum==1) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		//option2
		text = "INFO";
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
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,30));
		text = "BACK";
		textY  += gp.getTileSize()*2 +10;
		gc.fillText(text, textX, textY);
		if(optionNum==4) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		
		
		
	}
	public void Control(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,30));
		gc.setFill(Color.WHITE);
		String text = "CONTROL";
		int textX = X +gp.getTileSize()*5/2 +10;
		int textY  = Y+gp.getTileSize()*1;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "MOVE";
		textX = X +gp.getTileSize()-20;
		textY  += gp.getTileSize()+20;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                 WASD";
		gc.fillText(text, textX+8, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "CONFIRM";
		textY  += gp.getTileSize()+5;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                 SPACE";
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "OPTION/BACK";
		textY  += gp.getTileSize()+5;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                  ESC";
		gc.fillText(text, textX, textY);
	}
	public void Info(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.LIGHT,15));
		gc.setFill(Color.WHITE);
		String text = "This is ProgMeth's Final Project";
		int textX = X +gp.getTileSize();
		int textY  = Y+gp.getTileSize()*2;
		gc.fillText(text, textX, textY);
		
		text = "By Atsawin Sungsuwan";
		textX+=gp.getTileSize();
		textY+=gp.getTileSize();
		gc.fillText(text, textX, textY);
		
		text = "& Navanon Neknhum";
		textX+=gp.getTileSize()/2 - 16;
		textY+=gp.getTileSize();
		gc.fillText(text, textX, textY);
		
		text = "Hope you enjoy the game<3";
		textX-=gp.getTileSize()/2;
		textY+=gp.getTileSize()*2.5;
		gc.fillText(text, textX, textY);

	}
	public void OptionEndGame(int X,int Y) {
		
		gc.setFont(Font.font("Courier New",FontWeight.LIGHT,20));
		gc.setFill(Color.WHITE);
		
		String text = "Do you want to save game";
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
		
		
	}
	
	
	public void LoadGameDraw()
	{
	
		int y = (5*gp.getTileSize());
		
		Text text = new Text("PLEASE NEW GAME FIRST..");
		Font font = Font.font("Courier New",40);
		Color textColor = Color.WHITE;
		int textX = gp.getTileSize()*3-gp.getTileSize()+5;
		int textY= y+gp.getTileSize();
		
		gc.setFont(font);
		gc.setFill(textColor);
		gc.fillText(text.getText(),textX,textY );
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
	public int getTitleState() {
		return titleState;
	}
	public void setTitleState(int titleState) {
		this.titleState = titleState;
	}
	public boolean isGameFinished() {
		return gameFinished;
	}
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}
	
}

