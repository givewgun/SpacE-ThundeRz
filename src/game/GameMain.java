package game;

import java.util.Optional;

import drawing.GameScreen;
import input.CharacterInput;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import logic.Background;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import soundtrack.BackgroundMusic;
import window.SceneManager;

public class GameMain {

	private static GameScreen gameScreen;
	private static BackgroundMusic gameBgm;
	private static GameLogic gameLogic;

	public static void newGame() {
		// TODO fill code
		
		gameScreen = new GameScreen(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gameBgm = new BackgroundMusic();
		gameLogic = new GameLogic(gameScreen);
		SceneManager.gotoSceneOf(gameScreen);
		gameLogic.startGame();
		gameScreen.startAnimation();
		gameBgm.startBackgroundMusic();
	}

	public static void stopGameLogicAndAnimation() {
		// TODO fill code
		gameScreen.stopAnimation();
		gameLogic.stopGame();
		gameBgm.stopBackgroundMusic();

	}

	public static void stopGame() {
		stopGameLogicAndAnimation();
		Platform.runLater(GameMain::displayResult);
		CharacterInput.clear();
		RenderableHolder.getInstance().clear();
		
	}
	
	private static void displayResult() {
		// TODO fill code
		Alert alert = new Alert(AlertType.NONE, "Game Over!\n Click Ok to return to restart.",ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.isPresent() && result.get() == ButtonType.OK) {
		     SceneManager.gotoMainMenu();
		 }
		
		
	}

}
