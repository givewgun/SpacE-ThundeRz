package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBoss extends Enemy {

	public EBoss() {
		super(1000, 0.2);
		this.width = RenderableHolder.eBoss.getWidth();
		this.height = RenderableHolder.eBoss.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = -this.height;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		long now = System.nanoTime();
		this.x = Math.sin(5 * now * 1e-9+Math.toRadians(90)) * ((SceneManager.SCENE_WIDTH - this.width) / 2)
				+ (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y += this.speed;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.eBoss, x, y);

	}

}
