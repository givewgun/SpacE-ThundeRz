package logic;

import window.SceneManager;

public abstract class Enemy extends CollidableEntity {
	private static int zCounter=-200;     //to generate different z for each Enemy to prevent flashing when 2 or more enemy are overlap.
										// Enemy z is between -200 and -100 inclusive.
	public Enemy(double hp, double speed) {
		super(hp, speed);
		this.side = -1;
		this.z=zCounter;
		zCounter++;
		if(zCounter>-100) {
			zCounter=-200;
		}
	}

	public void onCollision(CollidableEntity others) {
		this.hp -= others.collideDamage;
		if (this.hp <= 0) {
			this.destroyed = true;
			this.visible = false;
			calculateScore(this);
		}
		System.out.println(this.getClass() + " is collided! by player " + this.hp);
	}

	public boolean isOutOfScreen() {
		return (int) this.y > SceneManager.SCENE_HEIGHT;
	}
	
	private void calculateScore(Enemy e) {
		if(e instanceof EBoss) {
			Score.score += 30;
		}
		if(e instanceof EBug) {
			Score.score += 1;
		}
		if(e instanceof EEyeball) {
			Score.score += 5;
		}
		if(e instanceof ESquid) {
			Score.score += 3;
		}
		if(e instanceof EWing) {
			Score.score += 10;
		}
		if(e instanceof EBig) {
			Score.score += 15;
		}
	}

}
