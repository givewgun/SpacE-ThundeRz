package drawing;

import input.CharacterInput;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	private Thread gameAnimation;
	private boolean isAnimationRunning;

	public GameScreen(double width, double height) {
		// TODO Auto-generated constructor stub
		super(width, height);
		this.setVisible(true);
		this.isAnimationRunning = false;
		addKeyEventHandler();
	}

	public void startAnimation() {
		gameAnimation = new Thread(this::animationLoop, "Game Animation Thread");
		isAnimationRunning = true;
		gameAnimation.start();
	}

	public void stopAnimation() {
		isAnimationRunning = false;
	}

	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isAnimationRunning) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateAnimation();
				RenderableHolder.getInstance().update();
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateAnimation() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
		int i = 0;
		while (i < RenderableHolder.getInstance().getEntities().size()) {
			if (RenderableHolder.getInstance().getEntities().get(i).isDestroyed()) {
				RenderableHolder.getInstance().getEntities().remove(i);
			} else {
				i++;
			}

		}

	}

	private void addKeyEventHandler() {
		// TODO fill code
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if (event.getCode().isArrowKey() || event.getCode() == KeyCode.SPACE) {
					CharacterInput.setKeyPressed(event.getCode(), true);
					System.out.println("Adddddd");
				}
			}
		});
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode().isArrowKey() || event.getCode() == KeyCode.SPACE) {
					CharacterInput.setKeyPressed(event.getCode(), false);
				}

			}
		});
	}

}
