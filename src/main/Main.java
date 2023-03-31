package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D game");
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		
		window.setLocationRelativeTo(null); //default = set center
		window.setVisible(true);
		
		gamePanel.StartGameThread();
	}

}
