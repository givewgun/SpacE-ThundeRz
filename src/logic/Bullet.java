package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Bullet extends CollidableEntity {

	private Image bulletSprite;
	private int type;
	private static int zCounter = -500; // Bullet z is between -700 and -300 inclusive.
	private int speedX, speedY;

	protected Bullet(double x, double y, int speedX, int speedY, int side, int type, CollidableEntity e) {
		super(0.001, speedY);
		// TODO Auto-generated constructor stub
		this.speedX = speedX;
		this.speedY = speedY;
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
		this.type = type;
		if (type == 0) {
			this.height = RenderableHolder.bullet.getHeight();
			this.width = RenderableHolder.bullet.getWidth();
			bulletSprite = RenderableHolder.bullet;
			this.collideDamage = 10;
		} else if (type == 1) {
			this.height = RenderableHolder.bossBullet.getHeight();
			this.width = RenderableHolder.bossBullet.getWidth();
			bulletSprite = RenderableHolder.bossBullet;
			this.collideDamage = 20;
		} else if (type == 2) {
			this.height = RenderableHolder.roundBulletB.getHeight();
			this.width = RenderableHolder.roundBulletB.getWidth();
			bulletSprite = RenderableHolder.roundBulletB;
			this.collideDamage = 10;
		} else if (type == 3) {
			this.height = RenderableHolder.roundBulletY.getHeight();
			this.width = RenderableHolder.roundBulletY.getWidth();
			bulletSprite = RenderableHolder.roundBulletY;
			this.collideDamage = 10;
		} else if (type == 4) {
			this.height = RenderableHolder.roundBulletR.getHeight();
			this.width = RenderableHolder.roundBulletR.getWidth();
			bulletSprite = RenderableHolder.roundBulletR;
			this.collideDamage = 10;
		} else if (type == 5) {
			this.height = RenderableHolder.roundBulletP.getHeight();
			this.width = RenderableHolder.roundBulletP.getWidth();
			bulletSprite = RenderableHolder.roundBulletP;
			this.collideDamage = 10;
		}
		if (side == 1) {
			this.x = x + (e.width - this.width) / 2.0;
			this.y = y - this.height;
		} else if (side == -1) {
			this.x = x + (e.width - this.width) / 2.0;
			this.y = y + e.height;
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
		gc.drawImage(bulletSprite, x, y);

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
		x -= speedX;
		if (side == 1) {
			y -= speedY;

		} else {
			y += speedY;
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
		if (type == 0 || type == 1) {
			Rectangle bound = new Rectangle();
			bound.setX(x);
			bound.setY(y);
			bound.setWidth(width);
			bound.setHeight(height);
			return bound;
		} else {
			Circle bound = new Circle();
			bound.setCenterX(x + width / 2);
			bound.setCenterY(y + height / 2);
			bound.setRadius(width / 2);
			return bound;
		}
	}

}
