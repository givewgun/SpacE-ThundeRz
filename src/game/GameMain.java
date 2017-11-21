package game;

import drawing.GameScreen;
import logic.Background;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameMain {

	private static GameScreen gameScreen;
	private static GameLogic gameLogic;

	public static void newGame() {
		// TODO fill code
		
		gameScreen = new GameScreen(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gameLogic = new GameLogic(gameScreen);
		SceneManager.gotoSceneOf(gameScreen);
		gameLogic.startGame();
		gameScreen.startAnimation();
	}

	public static void stopGameLogicAndAnimation() {
		// TODO fill code

	}

	public static void stopGame() {
		stopGameLogicAndAnimation();
	}

}
