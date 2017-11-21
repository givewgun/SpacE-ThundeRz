package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBig extends Enemy {
	int xDirection;

	public EBig() {
		super(200, 10);
		this.width = RenderableHolder.eBig.getWidth();
		this.height = RenderableHolder.eBig.getHeight();
		xDirection = 1;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x += (this.speed * xDirection);
		if (this.x < 0) {
			this.x = 0;
			xDirection *= -1;
		}
		if (this.x > SceneManager.SCENE_WIDTH - this.width) {
			this.x = SceneManager.SCENE_WIDTH - this.width;
			xDirection *= -1;
		}
		this.y += this.speed;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
