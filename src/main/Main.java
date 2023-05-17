package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {

//	public static void main(String[] args) {
//	
//		JFrame window = new JFrame();
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(false);
//		window.setTitle("2D game");
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		
//		GamePanel gamePanel = new GamePanel();
//		window.add(gamePanel);
//		window.pack();
//		
//		
//		window.setLocationRelativeTo(null); //default = set center
//		window.setVisible(true);
//		
//		gamePanel.StartGameThread();
//	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage){
		  primaryStage.setTitle("2D game");
		  primaryStage.setResizable(false);
		  
		  GamePanel gamePanel = new GamePanel();

		   StackPane root = new StackPane(gamePanel);
		   Scene scene = new Scene(root);
		   System.setProperty("prism.forceGPU", "true");
		   primaryStage.setScene(scene);
		   primaryStage.show();

//		   gamePanel.startGameThread();
	}

}
