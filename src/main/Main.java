package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
	 private GamePanel gamePanel;
	 private StackPane root ;
	 private Scene scene ;
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage){
		  primaryStage.setTitle("2D game");
		  primaryStage.setResizable(false);

		  gamePanel = new GamePanel();

		   root = new StackPane(gamePanel);
		   scene = new Scene(root);
		   System.setProperty("prism.forceGPU", "true");
		   primaryStage.setScene(scene);
		   primaryStage.show();

	}

}
