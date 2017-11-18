package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas{
	public GameScreen(double width, double height) {
		// TODO Auto-generated constructor stub
		super(width, height);
		this.setVisible(true);
	}
	
	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}

		// System.out.println("===============");
		// System.out.println("===============");

	}
	

}
