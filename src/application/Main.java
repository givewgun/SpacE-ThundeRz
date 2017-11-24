package application;
	
import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.Background;
import sharedObject.RenderableHolder;
import window.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMainMenu();
			primaryStage.setTitle("Space Thunderz");
			primaryStage.centerOnScreen();
			/*
			//testing
			StackPane root = new StackPane();                       //move to/replace by SceneManager
			Scene scene = new Scene(root,600,800);                  //move to SceneManager gotoSceneOf()
			primaryStage.setScene(scene);                           //move to/replace by SceneManager
			primaryStage.setTitle("Space Thunderz");                //still here...
			//I have no idea  what i'm doing
			RenderableHolder.getInstance().add(new background());   //(temp) move to GameMain newGame()  //might move to GameLogic in the future
			GameScreen gameScreen = new GameScreen(600, 800);       //move to GameMain newGame()
			root.getChildren().add(gameScreen);						//move to/replace by SceneManager
			gameScreen.requestFocus();                              //move to SceneManager
			
			primaryStage.show();									//move to SceneManager
			
			AnimationTimer animation = new AnimationTimer() {		//replaced by thread in GameScreen
				public void handle(long now) {                       
					gameScreen.paintComponent();                    //move to GameScreen animationLoop
					RenderableHolder.getInstance().update();		//move to GameScreen animationLoop

				}
			};
			animation.start();*/
			//this will stop all thread / free all memory when we click exit button of windows (X)
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					// TODO Auto-generated method stub
					Platform.exit();
					System.exit(0);
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
