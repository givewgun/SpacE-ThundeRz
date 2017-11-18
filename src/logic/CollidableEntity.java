package logic;

public abstract class CollidableEntity extends Entity {
	protected double width, height;

	protected boolean collideWith(CollidableEntity other) {
		return (other.x + other.width >= x && other.y + other.height >= y && other.x <= x + width
				&& other.y <= y + height);
	}
}
