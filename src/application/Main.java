package application;
	
import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.background;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//testing
			StackPane root = new StackPane();
			Scene scene = new Scene(root,600,800);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Space Thunderz");
			//I have no idea  what i'm doing
			RenderableHolder.getInstance().add(new background());
			GameScreen gameScreen = new GameScreen(600, 800);
			root.getChildren().add(gameScreen);
			gameScreen.requestFocus();
			
			primaryStage.show();
			
			AnimationTimer animation = new AnimationTimer() {
				public void handle(long now) {
					gameScreen.paintComponent();
					RenderableHolder.getInstance().update();

				}
			};
			animation.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
