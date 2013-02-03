package atlantis.framework;

/**
 * Represents a 2-dimensional vector
 * @author Yannick
 *
 */
public class Vector2 {
	protected float x;
	protected float y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Add a value to vector
	 * @param value
	 */
	public void add(float value) {
		this.x += value;
		this.y += value;
	}
	
	/**
	 * Add a vector to vector
	 * @param vector
	 */
	public void add(Vector2 vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	/**
	 * Subtract a value to vector
	 * @param value
	 */
	public void subtract(float value) {
		this.x -= value;
		this.y -= value;
	}
	
	/**
	 * Subtract a vector to vector
	 * @param vector
	 */
	public void subtract(Vector2 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	/**
	 * Mutiply (scalar) a value to vector
	 * @param value
	 */
	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
	}
	
	/**
	 * Mutiply (scalar) a vector to vector
	 * @param vector
	 */
	public void multiply(Vector2 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	/**
	 * Divide a value to vector
	 * @param value
	 */
	public void divide(float value) {
		this.x /= value;
		this.y /= value;
	}
	
	/**
	 * Divide a vector to vector
	 * @param vector
	 */
	public void divide(Vector2 vector) {
		this.x /= vector.x;
		this.y /= vector.y;
	}
	
	/**
	 * Normalize vector
	 */
	public void normalize() {
		double value = 1 / (Math.sqrt(this.x * this.x) + (this.y * this.y));
		this.x *= value;
		this.y *= value;
	}
	
	/**
	 * Gets the distance between the specified vector and this vector
	 * @param vector
	 * @return distance between two vectors
	 */
	public double getDistance(Vector2 vector) {
		double dx = this.x - vector.x;
		double dy = this.y - vector.y;
		
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
