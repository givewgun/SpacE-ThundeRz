package logic;

import window.SceneManager;

public abstract class Enemy extends CollidableEntity {

	public Enemy(double hp, double speed) {
		super(hp, speed);
		this.side = -1;
	}

	public void onCollision(CollidableEntity others) {
		this.hp -= others.collideDamage;
		if (this.hp <= 0) {
			this.destroyed = true;
			this.visible = false;
		}
		System.out.println(this.getClass() + " is collided! by player " + this.hp);
	}

	public boolean isOutOfScreen() {
		return (int) this.y > SceneManager.SCENE_HEIGHT;
	}

}
