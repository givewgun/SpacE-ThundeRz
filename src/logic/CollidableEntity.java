package logic;

public abstract class CollidableEntity extends Entity {
	protected double width, height, collideDamage;
	protected int side;

	protected CollidableEntity(double hp, double speed) {
		super(hp, speed);
	}

	protected boolean collideWith(CollidableEntity other) {
		if (this.side != other.side) {
			return (other.x + other.width >= x && other.y + other.height >= y && other.x <= x + width
					&& other.y <= y + height);
		}
		return false;
	}

	public abstract void onCollision(CollidableEntity others);
}
