

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
	private int titleNum = 0;
	//0:NEW GAME
	//1:LOAD GAME
	//2:EXIT	
	private int titleState = 0;
	//0:normal Title Screen
	//1:Warning Screen when press LOAD GAME before NEW GAME
	private int optionState = 0;
	//0:default state/option screen
	//1:in INFO option
	//2:in CONTROL option
	//3:in EXIT GAME option
	private int optionNum = 1;
	//1:INFO option (default state)
	//2:CONTROL option
	//3:EXIT GAME option
	//4:BACK option

	
	private boolean isGameFinished=false;
	
	public UI(GamePanel gp,GraphicsContext gc) {
		this.gp = gp;
		this.gc = gc;
		setTitleNum(0);
		setTitleState(0);
		setOptionNum(1);
		setOptionState(0);
	}
	public void draw(GraphicsContext gc) {
		
		if(gp.getGameState() == GamePanel.endingState)
		{	//draw ending text
			if(isGameFinished)
			{
				drawEndingText();
			}
		}
		else if(gp.getGameState() == GamePanel.titleState)
		{
			drawTitleScreen();
		}
		else if(gp.getGameState() == GamePanel.pauseState)
		{
			drawOptionScreen();
		}
		else if(gp.getGameState() == GamePanel.playingState)
		{
			//if first time draw advice window
			if(gp.getFirstTimeStart())
			{
				DrawAdviceWindow();
			}
			//draw UI monster-left in top-right corner
			drawMonsterLeft(gp.getMonsterAlive((Monster[]) gp.getMonster()));
		}
	}
	public void drawEndingText()
	{
		
		Font font;
		//shading
		font = Font.font("Courier New",FontWeight.BOLD,80);
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		String text="CONGRATULATION!";	
		int x = getCenteredX(text,font);
		int y = gp.getScreenHeight()/2 - gp.getTileSize()*2;
		gc.fillText(text, x, y);
		
		//text - Congratulation
		x += 3;
		y += 3;
		font=Font.font("Courier New",FontWeight.BOLD,80);
		gc.setFont(font);
		gc.setFill(Color.GOLD);
		text="CONGRATULATION!";
		gc.fillText(text, x, y);
		
		//shading
		
		font = Font.font("Courier New",FontWeight.BOLD,25);
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		text="PRESS ESC TO GO BACK TO TITLE SCREEN..";
		x = getCenteredX(text,font);
		y += gp.getTileSize() -20;
		gc.fillText(text, x, y);
		
		//text - press ESC to go back
		x += 2;
		y += 2;
		font=Font.font("Courier New",FontWeight.BOLD,25);
		gc.setFont(font);
		gc.setFill(Color.LEMONCHIFFON);
		text="PRESS ESC TO GO BACK TO TITLE SCREEN..";
		gc.fillText(text, x, y);
	}
	
	public void drawTitleScreen()
	{
		
			drawTitleHeader();
			drawTitleImage();
			drawTitleOption();
		
	}	
	public void drawTitleHeader() {
		//Draw Game's name
		Font font = Font.font("Courier New",FontWeight.BOLD,70);
		gc.setFont(font);
		String text ="THE GHOST BUSTER"; 
		int x = getCenteredX(text,font);
		int y = (int) (gp.getTileSize()*1.7);
		//shading
		gc.setFill(Color.BLUEVIOLET);
		gc.fillText(text,x+3, y+3);
		//text-game name
		gc.setFill(Color.WHITE);
		gc.fillText(text, x, y);
	}
	public void drawTitleImage()
	{
		//set x,y for monster lean next to player image
		int x = (int)gp.getTileSize()*5-15;
		int y = (int) (gp.getTileSize()*3);
		int playerSize =  gp.getTileSize()*4;
		int monSize = gp.getTileSize()*3;
		
		Image monsImage = new Image(getClass().getResourceAsStream("/monster/mons_down_1.png"));
		Image playerImage = new Image(getClass().getResourceAsStream("/player/ranger4.png"));
		
		//draw player image
		gc.drawImage(playerImage, x,y,playerSize,playerSize);
		//draw monster image next to player image
		x += gp.getTileSize()*2+20;
		y += gp.getTileSize();
		gc.drawImage(monsImage, x,y, monSize,monSize);
	}
	public void drawTitleOption() {
		//x,y from title image
		int x = (int)gp.getTileSize()*5-15;
		int y = (int) (gp.getTileSize()*3);
		
		Font font = Font.font("Courier New",FontWeight.SEMI_BOLD,40);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		
		//draw NEW GAME option
		String text ="NEW GAME"; 
		x = gp.getTileSize();
		y += gp.getTileSize()*6-20;
		gc.fillText(text, x, y);
		if(titleNum==0) { //draw cursor in front of this option
			gc.fillText("‣", x-gp.getTileSize()/2, y+2);
		}
		
		//draw LOAD GAME option
		text = "LOAD GAME"; 
		x = gp.getTileSize();
		y += gp.getTileSize()+10;
		gc.fillText(text,x, y);
		if(titleNum == 1) { //draw cursor in front of this option
			gc.fillText("‣", x-gp.getTileSize()/2, y+2);
		}
		
		//draw QUIT option
		text = "QUIT"; 
		x = gp.getTileSize();
		y += gp.getTileSize()+10;
		gc.fillText(text, x, y);
		if(titleNum == 2) { //draw cursor in front of this option
			gc.fillText("‣", x-gp.getTileSize()/2, y+2);
		}
	}
	public void drawMonsterLeft(int monsterLeft) {
		//set top right corner
		int x = gp.getTileSize()*9 +10;
		int y = gp.getTileSize()*1;
		Font font = Font.font("Courier New",FontWeight.NORMAL,30);
		gc.setFont(font);
		gc.setFill(Color.YELLOW);
		String text = "Monsters Left :"+Integer.toString(monsterLeft);
		gc.fillText(text, x, y);
	}
	public void drawOptionScreen()
    {	
		//set size of option screen
		int optionScreenW = gp.getTileSize()*8;
		int optionScreenH = gp.getTileSize()*8;
    	this.DrawScreen(gp.getTileSize()*4,gp.getTileSize()*2, optionScreenW, optionScreenH);
    	//position to place option screen
    	int x = gp.getTileSize()*4;
    	int y = gp.getTileSize()*2;
    	
    	switch(getOptionState()) {
    	case 0:drawOption(x,y);break;
    	case 1:drawInfo(x,y);break;
    	case 2:drawControl(x,y);break;
    	case 3:drawEndGameOption(x,y);break;
    	}
    }
	public void drawOption (int x,int y) {
		
		int textX;
		int textY;
		
		//option header (OPTION)
		Text header = new Text("OPTION");
		Font font = Font.font("Courier New",FontWeight.BOLD,40);
		Color textColor = Color.WHITE;
		gc.setFont(font);
		gc.setFill(textColor);
		textX = gp.getTileSize()*13/2 -6;
		textY = y+gp.getTileSize()+10;
		
		gc.fillText(header.getText(),textX,textY );
	
		gc.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD,30));
		gc.setFill(Color.WHITE);
		
		//INFO option
		String text = "INFO";
		textX = x +gp.getTileSize();
		textY  = gp.getTileSize()*4 +25;
		gc.fillText(text, textX, textY);
		//draw cursor in front of this option
		if(optionNum == 1) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		//Control option
		text = "CONTROL";
		textY += gp.getTileSize()+10;
		gc.fillText(text, textX, textY);
		//draw cursor in front of this option
		if(optionNum == 2) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		//END GAME option
		text = "END GAME";
		textY  += gp.getTileSize()+10;
		gc.fillText(text, textX, textY);
		//draw cursor in front of this option
		if(optionNum == 3) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
	
		//Back option
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,30));
		text = "BACK";
		textY  += gp.getTileSize()*2 +10;
		gc.fillText(text, textX, textY);
		//draw cursor in front of this option
		if(optionNum == 4) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+4);
		}
		
		
		
		
	}
	public void drawControl(int x,int y) {
		
		Font font = Font.font("Courier New",FontWeight.BOLD,30);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		//HEADER TEXT
		String text = "CONTROL";
		int textX = getCenteredX(text,font);
		int textY  = y+gp.getTileSize()*1;
		gc.fillText(text, textX, textY);
		
		//MOVE KEY CONTROL//
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "MOVE";
		textX = x +gp.getTileSize()-20;
		textY  += gp.getTileSize()+20;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                 WASD";
		gc.fillText(text, textX+8, textY);
		/////////////////
		
		//CONFIRM KEY CONTROL//
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "CONFIRM";
		textY  += gp.getTileSize()+5;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                 SPACE";
		gc.fillText(text, textX, textY);
		/////////////////
		
		//OPTION/BACK KEY CONTROL//
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "OPTION/BACK";
		textY  += gp.getTileSize()+5;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                  ESC";
		gc.fillText(text, textX, textY);
		/////////////////
		
		//CHOOSING KEY CONTROL//
		gc.setFont(Font.font("Courier New",FontWeight.BOLD,25));
		text = "CHOOSING";
		textY  += gp.getTileSize()+5;
		gc.fillText(text, textX, textY);
		
		gc.setFont(Font.font("Courier New",FontWeight.MEDIUM,25));
		text = "                  W/S";
		gc.fillText(text, textX, textY);
		////////////////
	}
	public void drawInfo(int x,int y) {
		//Draw info text
		Font font = Font.font("Courier New",FontWeight.LIGHT,15);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		String text = "This is ProgMeth's Final Project";
		int textX = getCenteredX(text,font);
		int textY  = y + gp.getTileSize()*2;
		gc.fillText(text, textX, textY);
		
		text = "By Atsawin Sungsuwan";
		textX = getCenteredX(text,font);;
		textY += gp.getTileSize();
		gc.fillText(text, textX, textY);
		
		text = "& Navanon Neknhum";
		textX = getCenteredX(text,font);;
		textY += gp.getTileSize();
		gc.fillText(text, textX, textY);
		
		text = "Hope you enjoy the game<3";
		textX = getCenteredX(text,font);;
		textY += gp.getTileSize()*2.5;
		gc.fillText(text, textX, textY);

	}
	public void drawEndGameOption(int x,int y) {
		
		Font font =Font.font("Courier New",FontWeight.LIGHT,20);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		
		//asking text
		String text = "Do you want to save game";
		int textX = getCenteredX(text,font);
		int textY  = (int) (y + gp.getTileSize()*2);
		gc.fillText(text, textX, textY);
		
		text = "and return to title screen?";
		textX = getCenteredX(text,font);
		textY  += gp.getTileSize()/2;
		gc.fillText(text, textX, textY);
		
		//warning text 
		font = Font.font("Courier New",FontWeight.LIGHT,15);
		gc.setFont(font);
		gc.setFill(Color.RED);
		text = "Game won't save if you didn't";
		textX = getCenteredX(text,font);
		textY  += gp.getTileSize();
		gc.fillText(text, textX, textY);
		
		text = "acknowledge game advice yet..";
		textX = getCenteredX(text,font);
		textY += gp.getTileSize()/2;
		gc.fillText(text, textX, textY);
		
		
		
		font = Font.font("Courier New",FontWeight.LIGHT,20);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		text = "YES";
		textX = getCenteredX(text,font);
		textY  += gp.getTileSize()*3/2;
		gc.fillText(text, textX, textY);
		if(optionNum == 1) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+2);}
		
		text = "NO";
		textX = getCenteredX(text,font);
		textY  += gp.getTileSize();
		gc.fillText(text, textX, textY);
		if(optionNum == 2) {gc.fillText("▸", textX-gp.getTileSize()/2, textY+2);}
	
	}
	
	public void DrawScreen(int x,int y,int width,int height){
			
		Color color = Color.rgb(0,0,0,0.8);

		this.gc.setStroke(Color.WHITE);
		gc.setLineWidth(4);
		gc.strokeRoundRect(x, y, width, height,40,40);
		 
		gc.setFill(color);
		gc.fillRoundRect(x+3, y+3, width-6, height-6, 34,34);
		
		
	}
	
	public void DrawAdviceWindow()
	{
		int x = gp.getTileSize()*2;
		int y = gp.getTileSize()*3/2;
		int W = gp.getTileSize()*12;
		int H = gp.getTileSize()*7/2 +10;
		DrawScreen(x,y,W,H);
		
		Font font =Font.font("Courier New",FontWeight.LIGHT,16);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		String text = "Mysterious meteorites landed on Earth in many places.";
		y += gp.getTileSize()/2;
		x += gp.getTileSize()/2;
		gc.fillText(text,x,y);
		
		text = "Ghosts appeared around the crater. People are unable";
		y += gp.getTileSize()/2;
		gc.fillText(text, x, y);
		
		text = "to live. It's your job to take down these ghosts! ";
		y += gp.getTileSize()/2;
		gc.fillText(text, x, y);
		
		
		text = "Use WASD walk to the ghosts and make them disappear! ";
		x = getCenteredX(text,font);
		gc.setFill(Color.YELLOW);
		y += gp.getTileSize();
		gc.fillText(text, x, y);
		
		text = "Press SPACE to continue...";
		x = getCenteredX(text,font);
		y += gp.getTileSize();
		gc.setFill(Color.BEIGE);
		gc.fillText(text, x, y);
	}
	public void drawLoadGameWarning()
	{
	
		int y = (5*gp.getTileSize());
		
		Text text = new Text("PLEASE NEW GAME FIRST..");
		Font font = Font.font("Courier New",40);
		Color textColor = Color.WHITE;
		int textX = getCenteredX(text.getText(),font);
		int textY= y+gp.getTileSize();
		
		gc.setFont(font);
		gc.setFill(textColor);
		gc.fillText(text.getText(),textX,textY );
	}
	
	public int getCenteredX(String text,Font font)
	{
		double textWidth  = measureTextLength(text,font);
		double centerX = gp.getScreenWidth()/2 -textWidth/2;
		return (int) centerX;
	}
	 public double measureTextLength(String text, Font font) {
		Text textNode = new Text(text);
	 	textNode.setFont(font);
	 	return textNode.getBoundsInLocal().getWidth();
	}
	public int getTitleNum() {
		return titleNum;
	}
	public void setTitleNum(int titleNum) {
		this.titleNum = titleNum;
	}
	public int getOptionState() {
		return optionState;
	}
	public void setOptionState(int optionState) {
		this.optionState = optionState;
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
		return isGameFinished;
	}
	public void setGameFinished(boolean gameFinished) {
		this.isGameFinished = gameFinished;
	}
	
}

