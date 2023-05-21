package main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {

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

	}

}
