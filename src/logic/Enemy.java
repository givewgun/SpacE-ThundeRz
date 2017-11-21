package logic;

import window.SceneManager;

public abstract class Enemy extends CollidableEntity {

	public Enemy(double hp, double speed) {
		super(hp, speed);
		this.side = -1;
	}

	public void onCollision() {
		this.hp -= 100;
	}

	public boolean isOutOfScreen() {
		return (int) this.x > SceneManager.SCENE_WIDTH;
	}

}
