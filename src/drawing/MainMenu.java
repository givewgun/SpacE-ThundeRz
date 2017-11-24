package drawing;

import game.GameMain;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import window.SceneManager;

public class MainMenu extends Canvas {
	private static final Font TITLE_FONT = Font.loadFont(ClassLoader.getSystemResource("font/Digital_tech.otf").toString(),70);
	private static final Font MENU_FONT = Font.loadFont(ClassLoader.getSystemResource("font/Digital_tech.otf").toString(),40);

	public MainMenu() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);

		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.DODGERBLUE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Space Thunderz", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 4);
		gc.setFont(MENU_FONT);
		gc.fillText("Press Enter to start", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT * 3 / 4);

		this.addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		// TODO Fill Code

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				// System.out.println(event.getCode());
				if (event.getCode() == KeyCode.ENTER) {
					GameMain.newGame();
				} else if (event.getCode() == KeyCode.ESCAPE) {
					Platform.exit();
				}
			}
		});
	}
}
