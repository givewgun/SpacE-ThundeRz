package game;

import drawing.GameScreen;
import logic.background;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameMain {

	private static GameScreen gameScreen;

	public static void newGame() {
		// TODO fill code
		RenderableHolder.getInstance().add(new background());
		gameScreen = new GameScreen(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		SceneManager.gotoSceneOf(gameScreen);
		gameScreen.startAnimation();
	}

	public static void stopGameLogicAndAnimation() {
		// TODO fill code

	}

	public static void stopGame() {
		stopGameLogicAndAnimation();
	}

}
