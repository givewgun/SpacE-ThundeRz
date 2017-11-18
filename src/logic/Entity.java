package logic;

public abstract class Entity {
	protected double x, y, hp, speed, attack;;
	protected int z;
	protected boolean visible, destroyed;

	protected Entity() {
		visible = true;
		destroyed = false;
		this.hp = 100;
		this.speed = 20;
		this.attack = 50;
	}

	protected Entity(double hp, double speed, double attack) {
		visible = true;
		destroyed = false;
		this.hp = hp;
		this.speed = speed;
		this.attack = attack;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getZ() {
		return z;
	}

}
