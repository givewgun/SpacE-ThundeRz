package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Bullet extends CollidableEntity {

	protected Bullet(double x, double y, int side) {
		super(0.001, 20);
		// TODO Auto-generated constructor stub
		this.collideDamage = 10;
		this.side = side;
		this.x = x;
		this.y = y;
		this.height = RenderableHolder.bullet.getHeight();
		this.width = RenderableHolder.bullet.getWidth();
		this.visible = true;
		this.destroyed = false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (side == 1) {
			gc.drawImage(RenderableHolder.bullet, x - this.width / 2, y);
		} else {
			gc.save();
			gc.rotate(180);
			gc.drawImage(RenderableHolder.bullet, -(x + this.width / 2), -y);
			gc.restore();
		}

	}

	@Override
	public void onCollision(CollidableEntity others) {
		// TODO Auto-generated method stub
		this.hp -= others.collideDamage;
		System.out.println("Bullet hit!");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (side == 1) {
			y -= speed;

		} else {
			y += speed;
		}
		if (this.hp <= 0 || isOutOfScreen()) {
			this.destroyed = true;
			this.visible = false;
		}

	}

	private boolean isOutOfScreen() {
		return (int) this.y > SceneManager.SCENE_HEIGHT || (int) this.y < 0;
	}

	@Override
	public Shape getBoundary() {
		Rectangle bound = new Rectangle();
		bound.setX(x);
		bound.setY(y);
		bound.setWidth(width);
		bound.setHeight(height);
		return bound;
	}

}
