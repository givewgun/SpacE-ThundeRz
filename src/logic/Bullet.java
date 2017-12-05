package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Bullet extends CollidableEntity {

	private Image bulletType;
	private static int zCounter = -500; // Bullet z is between -700 and -300 inclusive.

	protected Bullet(double x, double y, int side, CollidableEntity type) {
		super(0.001, 20);
		// TODO Auto-generated constructor stub
		this.collideDamage = 10;
		this.side = side;
		if (side == -1) {
			this.z = zCounter - 200;
		} else {
			this.z = zCounter;
		}
		zCounter++;
		if (zCounter > -300) {
			zCounter = -500;
		}
		if (type instanceof Player) {
			this.height = RenderableHolder.bullet.getHeight();
			this.width = RenderableHolder.bullet.getWidth();
			bulletType = RenderableHolder.bullet;
		} else if (type instanceof EBoss) {
			this.height = RenderableHolder.bossBullet.getHeight();
			this.width = RenderableHolder.bossBullet.getWidth();
			bulletType = RenderableHolder.bossBullet;
		}
		if (side == 1) {
			this.x = x + (type.width - this.width) / 2.0;
			this.y = y - this.height;
		} else if (side == -1) {
			this.x = x + (type.width - this.width) / 2.0;
			this.y = y + type.height;
		}
		this.visible = true;
		this.destroyed = false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		// if (side == 1) {
		// gc.setFill(Color.WHITE);
		// gc.fillRect(x, y, width, height);
		gc.drawImage(bulletType, x, y);

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
