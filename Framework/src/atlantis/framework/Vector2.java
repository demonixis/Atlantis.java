package atlantis.framework;

/**
 * Represents a 2-dimensional vector
 * @author Yannick
 *
 */
public class Vector2 {
	public static final Vector2 Zero = new Vector2();
	public static final Vector2 One = new Vector2(1.0f, 1.0f);
	public static final Vector2 UnitX = new Vector2(1.0f, 0.0f);
	public static final Vector2 UnitY = new Vector2(0.0f, 1.0f);
	
	public float x;
	public float y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(float value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	// ---
	// --- Basic operations
	// ---
	
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
	
	// ---
	// --- Advanced operations
	// ---
	
	/**
	 * Gets the distance between another vector.
	 * @param vector
	 * @return Return the distance between this vector and the vector passed in parameter.
	 */
	public double distance(Vector2 vector) {
		double dx = this.x - vector.x;
		double dy = this.y - vector.y;
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	/**
	 * Calculate the dot product of two vectors.
	 * @param vec1 First vector to use.
	 * @param vec2 Second vector to use.
	 * @return Return the dot product of the two vectors.
	 */
	public static float dot(Vector2 vec1, Vector2 vec2) {
		return (vec1.x * vec2.x) + (vec1.y * vec2.y);
	}
	
	/**
	 * Gets the length of the vector.
	 * @return Return the length of the vector.
	 */
	public double length() {
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	/**
	 * Performs a linear interpolation between to vectors.
	 * @param vec1
	 * @param vec2
	 * @param amount
	 * @return
	 */
	public static Vector2 lerp(Vector2 vec1, Vector2 vec2, float amount) {
		Vector2 vector2 = new Vector2(MathHelper.lerp(vec1.x, vec2.x, amount), MathHelper.lerp(vec2.y, vec2.y, amount));
		return vector2;
	}
	
	/**
	 * Gets a vector of the minimum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the minimum of the two vectors.
	 */
	public static Vector2 min(Vector2 vec1, Vector2 vec2) {
		Vector2 vector2 = new Vector2();
		vector2.x = (vec1.x < vec2.x) ? vec1.x : vec2.x;
		vector2.y = (vec1.y < vec2.y) ? vec1.y : vec2.y;
		return vector2;
	}
	
	/**
	 * Gets a vector of the maximum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the maximum of the two vectors.
	 */
	public static Vector2 max(Vector2 vec1, Vector2 vec2) {
		Vector2 vector2 = new Vector2();
		vector2.x = (vec1.x > vec2.x) ? vec1.x : vec2.x;
		vector2.y = (vec1.y > vec2.y) ? vec1.y : vec2.y;
		return vector2;
	}
	
	/**
	 * Negage the vector.
	 */
	public void negate() {
		this.x *= -1;
		this.y *= -1;
	}
	
	/**
	 * Negage a vector.
	 * @param vector A vector to negate.
	 */
	public static void negate(Vector2 vector) {
		vector.x *= -1;
		vector.y *= -1;
	}
	
	/**
	 * Normalize vector
	 */
	public void normalize() {
		double denominator = (Math.sqrt(this.x * this.x) + (this.y * this.y));
		if (denominator != 0) {
			double value = 1 / denominator;
			this.x *= value;
			this.y *= value;
		}
	}

	/**
	 * Normalize a vector.
	 * @param vector A vector to use to normalize (It's not modified).
	 * @return A normalized vector.
	 */
	public Vector2 normalize(Vector2 vector) {
		Vector2 vec = new Vector2(vector);
		vec.normalize();
		return vec;
	}
	
	// ---
	// --- Conversion methods.
	// ---
	
	/**
	 * Return a Point of this vector.
	 * @return Return a Point of this vector.
	 */
	public Point toPoint() {
		return new Point((int)this.x, (int)this.y);
	}
	
	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}

	// ---
	// --- Getters and setters
	// ---
	
	/**
	 * Sets values of the vector.
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
