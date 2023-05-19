package main;

import java.util.ArrayList;


import javafx.scene.input.KeyCode;


public class KeyHandler{

	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	private GamePanel gp;
	
	public KeyHandler(GamePanel gp)
	{
		this.gp=gp;
	}
	
	public boolean getKeyPressed(KeyCode keycode) {
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
	
	public void keyPressedChangeState() {
	    		if(gp.getUi().getState()==0)
	    		{
		    	// toggle option screen(pauseState)
				if(getKeyPressed(KeyCode.ESCAPE))
		    	{	
					if(gp.getGameState()==GamePanel.playingState) {gp.setGameState(GamePanel.pauseState);}
					else if(gp.getGameState()==GamePanel.pauseState) {gp.setGameState(GamePanel.playingState);}
		    	}
	    		}
				
	    		else
		    	{	
	    			
	    			// back to option screen (ESC)
		    		if(getKeyPressed(KeyCode.ESCAPE)) 
		    		{
		    			gp.getUi().setState(0);
		    		}
		    	}
		    }

	public void keyPressedPauseState() {
		    	//select option 
				if(gp.getUi().getState()==0)
				{
					if(getKeyPressed(KeyCode.S)&&gp.getUi().getOptionNum()<4)
						gp.getUi().setOptionNum( (gp.getUi().getOptionNum()+1));
					else if(getKeyPressed(KeyCode.W)&&gp.getUi().getOptionNum()>1)	
						gp.getUi().setOptionNum( (gp.getUi().getOptionNum()-1));
				}
		  		//select option in EndGame's option
				if(gp.getUi().getState()==3)
				{
					if(getKeyPressed(KeyCode.S)&&gp.getUi().getOptionNum()<2)
					{
						gp.getUi().setOptionNum( (gp.getUi().getOptionNum()+1));
					}
					else if(getKeyPressed(KeyCode.W)&&gp.getUi().getOptionNum()>1)
						{gp.getUi().setOptionNum( (gp.getUi().getOptionNum()-1));}
				}
	  }
	public void optionPressed(int optionNum) {
		//in Option screen
		if(gp.getUi().getState()==0)
		{
		if(gp.getUi().getOptionNum()==4)
		{
			if(getKeyPressed(KeyCode.SPACE)) 
				{
				gp.getUi().setOptionNum(1);
				gp.setGameState(GamePanel.playingState);
				}
		}
		else if(getKeyPressed(KeyCode.SPACE)) { 
				gp.getUi().setState(optionNum);
				gp.getUi().setOptionNum(1);
		}
		}
		//in Exist Option screen
		else if(gp.getUi().getState()==3)
		{	
			if(getKeyPressed(KeyCode.SPACE)) 
			{	//confirm YES
				if(gp.getUi().getOptionNum()==1)
				{
					gp.getUi().setOptionNum(1);
					gp.getUi().setState(0);
					gp.stopMusic(gp.getBgSound());
					gp.setGameState(GamePanel.titleState);
				}
				//confirm NO
				else if(gp.getUi().getOptionNum()==2)
				{	
					gp.getUi().setState(0);
					gp.getUi().setOptionNum(1);
				}
			}
			
		}
	}
	

	public void keyPressedTitleState() {
	    		
	    		//Choosing Option in Title Screen
				if(getKeyPressed(KeyCode.S))
				{
					gp.getUi().setTitleNum( (gp.getUi().getTitleNum()+1)%3);
				}
				else if(getKeyPressed(KeyCode.W))
				{
					if(gp.getUi().getTitleNum()-1<0) {gp.getUi().setTitleNum(gp.getUi().getTitleNum()+3);}
					gp.getUi().setTitleNum( (gp.getUi().getTitleNum()-1)%3);
				}
				
				//Select the option
				if(getKeyPressed(KeyCode.SPACE))
				{
					switch(gp.getUi().getTitleNum())
					{
					case 0: gp.setGameState(GamePanel.playingState);gp.playMusic(gp.getBgSound());break; //Play
					case 1: break; //
					case 2:	System.exit(0);break; //Exit
					}
				}
			}
	public String playerUpdate() {
		    
		    		//Set player direction to walk
		    		if (getKeyPressed(KeyCode.D) && getKeyPressed(KeyCode.W)) {
		    			return"right and up";
		                
		            }
		            else if (getKeyPressed(KeyCode.D) && getKeyPressed(KeyCode.S)) {
		            	return "right and down";
		                
		            }
		            else if (getKeyPressed(KeyCode.A) && getKeyPressed(KeyCode.S)) {
		            	return"left and down";
		                
		            }
		            else if (getKeyPressed(KeyCode.A) && getKeyPressed(KeyCode.W)) {
		            	return"left and up";
		                
		            }
		            else if (getKeyPressed(KeyCode.W)) {
		            	return "up"; 
		            
			        }
			        else if (getKeyPressed(KeyCode.S)) {
			        	return"down";
			           
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

