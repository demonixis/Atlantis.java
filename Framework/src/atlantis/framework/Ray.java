package atlantis.framework;

public class Ray {
	protected Vector3 direction;
	protected Vector3 position;
	
	public Ray(Vector3 position, Vector3 direction) {
		this.direction = direction;
		this.position = position;
	}

	public boolean equals(Ray other) {
		return this.position.equals(other.position) && this.direction.equals(other.direction);
	}
	
	public float intersects(BoundingBox box) {
		return 0;
	}
	
	public float intersects(BoundingFrustrum frustrum) {
		return 0;
	}
	
	public float intersects(BoundingSphere sphere) {
		return 0;
	}
	
	public String toString() {
		return "Position: " + this.position.toString() + " Direction: " + this.direction.toString();
	}

	/**
	 * @return the direction
	 */
	public final Vector3 getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public final void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	/**
	 * @return the position
	 */
	public final Vector3 getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public final void setPosition(Vector3 position) {
		this.position = position;
	}
}
