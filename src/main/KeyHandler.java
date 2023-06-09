package main;

import java.util.ArrayList;


import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class KeyHandler{

	private ArrayList<KeyCode> keyPressed; 
	private GamePanel gp;
	
	public KeyHandler(GamePanel gp)
	{	//set game panel
		keyPressed = new ArrayList<>();
		this.gp = gp;
	}
	
	public boolean getKeyPressed(KeyCode keycode) {
		//get if key code is press?
		return keyPressed.contains(keycode);
	}
	
	public void setKeyPressed(KeyCode keycode,boolean pressed) {
		//Add key Press to Array list for use later
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
			}
		}
		//Remove key from Array list
		else{
			keyPressed.remove(keycode);
		}}
	
	public void pressedChangeState() {
	    		
				// acknowledge game advice to play game
				acknowledgeGameAdvicePlayingState();
				//back to title screen after ending(ESC)
				backToTitleEndingState();
				
				
				if(gp.getUi().getOptionState()==0)
	    		{
		    	// toggle option screen(pauseState)
					togglePauseState();
	    		}
				
	    		else
		    	{	
	    			// back to option screen (ESC)
		    		backPauseState();
		    	}
		    }
	private void backPauseState() {
		if(getKeyPressed(KeyCode.ESCAPE)) 
		{
			gp.getUi().setOptionNum(1);
			gp.getUi().setOptionState(0);
		}
	}
	private void togglePauseState() {
		if(getKeyPressed(KeyCode.ESCAPE))
    	{	
			gp.getUi().setOptionNum(1);
			if(gp.getGameState() == GamePanel.playingState) {gp.setGameState(GamePanel.pauseState);}
			else if(gp.getGameState() == GamePanel.pauseState) {gp.setGameState(GamePanel.playingState);}
    	}
	}
	private void  acknowledgeGameAdvicePlayingState() {
		if(gp.getGameState() == GamePanel.playingState)
		{	
			if(gp.isFirstTimeStart())
			{
				if(getKeyPressed(KeyCode.SPACE))
				{
					gp.setFirstTimeStart(false);
				}
			}
		}
	}
	private void backToTitleEndingState() {
		//back to title after ending
		if(gp.getGameState() == GamePanel.endingState)
		{
			if(getKeyPressed(KeyCode.ESCAPE))
			{
				
				gp.getEzSound().stop();
				gp.setFirstTimeStart(true);
				gp.playMusic(gp.getTitleSound());
				gp.setGameState(GamePanel.titleState);
				
			}
		}
	}
	public void scrollPauseState() {
		    	//choose option 
				scrollOptionPauseState();
		  		//select option in EndGame's option
				scrollExitPauseState();
	  }
	private void scrollExitPauseState() {
		//choose option Y/N in Exit option
		if(gp.getUi().getOptionState() == 3)
		{
			if(getKeyPressed(KeyCode.S) && gp.getUi().getOptionNum() < 2)
			{
				gp.getUi().setOptionNum((gp.getUi().getOptionNum()+1));
				gp.playSE(gp.getSelectSound());
			}
			else if(getKeyPressed(KeyCode.W) && gp.getUi().getOptionNum()>1)
				{
					gp.getUi().setOptionNum((gp.getUi().getOptionNum()-1));
					gp.playSE(gp.getSelectSound());
				}
		}
	}
	private void scrollOptionPauseState() {
		//choose option in Option screen
		if(gp.getUi().getOptionState() == 0)
		{
			if(getKeyPressed(KeyCode.S) && gp.getUi().getOptionNum()<4)
				{
					gp.getUi().setOptionNum((gp.getUi().getOptionNum()+1));
					gp.playSE(gp.getSelectSound());
				}
				 
				
			else if(getKeyPressed(KeyCode.W) && gp.getUi().getOptionNum()>1)	
				{
					gp.getUi().setOptionNum((gp.getUi().getOptionNum()-1));
					 gp.playSE(gp.getSelectSound());
				}
		}
	}
	public void pressedPauseState(int optionNum) {
		
		//in Option screen
		if(gp.getUi().getOptionState() == 0)
		{
			if(gp.getUi().getOptionNum() == 4) //optionNum4 = back option
			{
				//press back option
				pressBackOption();
			}
			//set state from pressed option
			else pressOption();
		}
		
		//in Exit game option
		else if(gp.getUi().getOptionState() == 3)
		{	
			if(getKeyPressed(KeyCode.SPACE)) 
			{	//confirm YES
				pressYesExitOption();
				//confirm NO
				pressNoExitOption();
			}
		}
	}
	private void pressYesExitOption() {
			if(gp.getUi().getOptionNum() == 1)
			{
				//set default,change song
				gp.getUi().setTitleNum(0);
				gp.getUi().setOptionNum(1);
				gp.getUi().setOptionState(0);
				gp.stopMusic(gp.getBgSound());
				gp.playMusic(gp.getTitleSound());
				gp.setGameState(GamePanel.titleState);
			}
		}	
	private void pressNoExitOption() {
		if(gp.getUi().getOptionNum() == 2)
		{	
			gp.getUi().setOptionState(0);
			gp.getUi().setOptionNum(1);
		}
	}
	private void pressOption() {
		//confirm each option -> change state to that option
		if(getKeyPressed(KeyCode.SPACE)) 
		{ 
				gp.getUi().setOptionState(gp.getUi().getOptionNum());
				gp.getUi().setOptionNum(1);
		}
	}
	private void pressBackOption() {
		
			if(getKeyPressed(KeyCode.SPACE)) 
				{
				gp.getUi().setOptionNum(1);
				gp.setGameState(GamePanel.playingState);
				}
		
	}

	public void pressedTitleState() {
	    	
			//in Title Screen
	    	if(gp.getUi().getTitleState() == 0)
				{
	    		//Choose option in Title Screen
				scrollOptionTitleScreen();				
				//Select the option
				pressOptionTitleScreen();
				}
	    	//In Warning Screen
	    	exitWarningLoadGame();
			}
	
	private void pressOptionTitleScreen() {
		if(getKeyPressed(KeyCode.SPACE))
		{
			switch(gp.getUi().getTitleNum())
			{
			case 0: createNewGame();gp.playMusic(gp.getBgSound());break; //new Game
			case 1: loadGame();break; //load game
			case 2:	System.exit(0);break; //Exit
			}
		}
	}
	private void scrollOptionTitleScreen() {
		//scrolling option in title screen (W/S)
		if(getKeyPressed(KeyCode.S))
		{
			gp.getUi().setTitleNum((gp.getUi().getTitleNum()+1)%3);
			gp.playSE(gp.getSelectSound());
		}
		else if(getKeyPressed(KeyCode.W))
		{
			if(gp.getUi().getTitleNum()-1 < 0)
				{gp.getUi().setTitleNum(gp.getUi().getTitleNum()+3);}
			
			gp.getUi().setTitleNum((gp.getUi().getTitleNum()-1)%3);
			gp.playSE(gp.getSelectSound());
			
		}
	}
	private void exitWarningLoadGame() {
		//back to title screen from loading warning Screen
		if(gp.getUi().getTitleState() == 1)
		{
   		 	if(getKeyPressed(KeyCode.ESCAPE))
   		 	{
   		 		gp.getUi().setTitleNum(0);
   		 		gp.getUi().setTitleState(0);
   		 		gp.setFirstTimeStartWarning(false);
			}
		}
	}
	private void createNewGame() {
		//Create new Game
		gp.startNewGameLoop();
		gp.stopMusic(gp.getTitleSound());
		gp.playMusic(gp.getBgSound());
		gp.setFirstTimeStart(true);
		gp.setGameState(GamePanel.playingState);		
	}
	private void loadGame() {
		
		//FirstTimeStart (never New Game)
		if(gp.isFirstTimeStart())
		{
			//Take user to Warning Screen
			gp.getUi().setTitleState(1);
			gp.setFirstTimeStartWarning(true);
			
		}
		//Load Game
		else
		{
		gp.setGameState(GamePanel.playingState);
		gp.stopMusic(gp.getTitleSound());
		gp.playMusic(gp.getBgSound());
		}
		
	}
	public String updatePlayerDirection() {
		    
		    		//Set player direction to walk from key press
		    		if (getKeyPressed(KeyCode.D) && getKeyPressed(KeyCode.W)) {
		    			return "right and up";
		                
		            }
		            else if (getKeyPressed(KeyCode.D) && getKeyPressed(KeyCode.S)) {
		            	return "right and down";
		                
		            }
		            else if (getKeyPressed(KeyCode.A) && getKeyPressed(KeyCode.S)) {
		            	return "left and down";
		                
		            }
		            else if (getKeyPressed(KeyCode.A) && getKeyPressed(KeyCode.W)) {
		            	return "left and up";
		                
		            }
		            else if (getKeyPressed(KeyCode.W)) {
		            	return "up"; 
		            
			        }
			        else if (getKeyPressed(KeyCode.S)) {
			        	return "down";
			           
			        }
			        else if (getKeyPressed(KeyCode.A)) {
			        	return "left";
			            
			        } 
			        else if (getKeyPressed(KeyCode.D)) {
			        	return "right";  
			        }
		    	
		    	return gp.getPlayer().getDirection();
		    }
	public boolean isWalkPressed()
		    {
		    	//Check if user press walking button (W A S D)
		    	if(getKeyPressed(KeyCode.W)||getKeyPressed(KeyCode.S)||getKeyPressed(KeyCode.A)||getKeyPressed(KeyCode.D)) return true;
		    	return false;
		    }
}

