package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBig extends Enemy {
	private double yOffset;

	public EBig() {
		super(200, 0.5);
		this.width = RenderableHolder.eBig.getWidth();
		this.height = RenderableHolder.eBig.getHeight();
		yOffset = 0;
		this.visible = true;
		this.destroyed = false;
		this.x = (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = -this.height;
		this.collideDamage = 30;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		yOffset += this.speed;
		long now = System.nanoTime();
		this.x = Math.sin(2 * now * 1e-9) * ((SceneManager.SCENE_WIDTH - this.width) / 2)
				+ (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = Math.cos(2 * now * 1e-9) * (200) + yOffset - 200;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}

	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

		gc.drawImage(RenderableHolder.eBig, x, y);

	}

}
