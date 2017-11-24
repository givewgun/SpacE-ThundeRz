package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class EBoss extends Enemy {
	private GameLogic gameLogic;
	private int bulletDelayTick = 0;

	public EBoss(GameLogic gameLogic) {
		super(1000, 0.2);
		this.width = RenderableHolder.eBoss.getWidth();
		this.height = RenderableHolder.eBoss.getHeight();
		this.visible = true;
		this.destroyed = false;
		this.x = (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y = -this.height;
		this.collideDamage = 50;
		this.gameLogic = gameLogic;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		long now = System.nanoTime();
		//this.x = Math.sin(5 * now * 1e-9 + Math.toRadians(90)) * ((SceneManager.SCENE_WIDTH - this.width) / 2)
		//		+ (SceneManager.SCENE_WIDTH - this.width) / 2.0;
		this.y += this.speed;
		if (this.isOutOfScreen()) {
			this.visible = false;
			this.destroyed = true;
		}

		if (bulletDelayTick % 10 == 0) {
			System.out.println("SHOOOOT");
			gameLogic.addPendingBullet(new Bullet(this.x + (this.width / 2.0), this.y + this.height, -1, this));
			RenderableHolder.laser.play();
		}
		bulletDelayTick++;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.eBoss, x, y);
	}

	@Override
	public Shape getBoundary() {
		// TODO Auto-generated method stub
		Circle bound = new Circle();
		bound.setCenterX(x + width / 2);
		bound.setCenterY(y + height / 2);
		bound.setRadius(width / 2);
		return bound;
	}

}
