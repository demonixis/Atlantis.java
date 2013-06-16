package atlantis.framework;

/**
 * A Vector3 class that represent a vector with 3 coordinates X, Y and Z.
 * @author Yannick
 */
public class Vector3 {
	public static final Vector3 Zero = new Vector3();
	public static final Vector3 One = new Vector3(1.0f, 1.0f, 1.0f);
	public static final Vector3 UnitX = new Vector3(1.0f, 0.0f, 0.0f);
	public static final Vector3 UnitY = new Vector3(0.0f, 1.0f, 0.0f);
	public static final Vector3 UnitZ = new Vector3(0.0f, 0.0f, 1.0f);
	public static final Vector3 Up = new Vector3(0.0f, 1.0f, 0.0f);
	public static final Vector3 Down = new Vector3(0.0f, -1.0f, 0.0f);
	public static final Vector3 Right = new Vector3(1.0f, 0.0f, 0.0f);
	public static final Vector3 Left = new Vector3(-1.0f, 0.0f, 0.0f);
    public static final Vector3 Forward = new Vector3(0.0f, 0.0f, -1.0f);
    public static final Vector3 Backward = new Vector3(0.0f, 0.0f, 1.0f);
	
	protected float x;
	protected float y;
	protected float z;
	
	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3(float value) {
		this.x = value;
		this.y = value;
		this.z = value;
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	/**
	 * Add a value to the current vector.
	 * @param value The value to add.
	 */
	public void add(float value) {
		this.x += value;
		this.y += value;
		this.z += value;
	}
	
	/**
	 * Add a Vector2 to the current vector.
	 * @param vector The Vector2 to add.
	 */
	public void add(Vector2 vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	/**
	 * Add a Vector3 to the current vector.
	 * @param vector The Vector3 to add.
	 */
	public void add(Vector3 vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
	}
	
	/**
	 * Subtract a value to the current vector.
	 * @param value The value to Subtract.
	 */
	public void subtract(float value) {
		this.x -= value;
		this.y -= value;
		this.z -= value;
	}
	
	/**
	 * Subtract a Vector2 to the current vector.
	 * @param vector The Vector2 to Subtract.
	 */
	public void subtract(Vector2 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	/**
	 * Subtract a Vector3 to the current vector.
	 * @param vector The Vector3 to Subtract.
	 */
	public void subtract(Vector3 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
	}
	
	/**
	 * Subtract two Vector3 
	 */
	public static Vector3 subtract(Vector3 vec1, Vector3 vec2) {
		Vector3 result = new Vector3(vec1);
		result.subtract(vec2);
		return result;
	}
	
	/**
	 * divide a value to the current vector.
	 * @param value The value to divide.
	 */
	public void divide(float value) {
		if (value != 0) {
			this.x /= value;
			this.y /= value;
			this.z /= value;
		}
	}
	
	/**
	 * divide a Vector2 to the current vector.
	 * @param vector The Vector2 to divide.
	 */
	public void divide(Vector2 vector) {
		if (vector.x != 0) {
			this.x /= vector.x;
		}
		if (vector.y != 0) {
			this.y /= vector.y;
		}
	}
	
	/**
	 * divide a Vector3 to the current vector.
	 * @param vector The Vector3 to divide.
	 */
	public void divide(Vector3 vector) {
		if (vector.x != 0) {
			this.x /= vector.x;
		}
		if (vector.y != 0) {
			this.y /= vector.y;
		}
		if (vector.z != 0) {
			this.z /= vector.z;
		}
	}
	
	/**
	 * multiply a value to the current vector.
	 * @param value The value to multiply.
	 */
	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}
	
	/**
	 * multiply a Vector2 to the current vector.
	 * @param vector The Vector2 to multiply.
	 */
	public void multiply(Vector2 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	/**
	 * multiply a Vector3 to the current vector.
	 * @param vector The Vector3 to multiply.
	 */
	public void multiply(Vector3 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
	}
	
	/**
	 * Normalize vector
	 */
	public void normalize() {
		double denominator = (Math.sqrt(this.x * this.x) + (this.y * this.y) + (this.z * this.z));
		if (denominator != 0) {
			double value = 1 / denominator;
			this.x *= value;
			this.y *= value;
			this.z *= value;
		}
	}
	
	/**
	 * Normalize a vector.
	 * @param vector A vector to normalize.
	 */
	public static Vector3 normalize(Vector3 vector) {
		// TODO
		
		return null;
	}
	
	/**
	 * Gets the distance between the specified vector and this vector
	 * @param vector
	 * @return distance between two vectors
	 */
	public double getDistance(Vector3 vector) {
		double dx = this.x - vector.x;
		double dy = this.y - vector.y;
		double dz = this.z - vector.z;
		
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}
	
	public static Vector3 cross(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3(
				(vec1.y * vec2.z) - (vec2.y * vec1.z),
                -((vec1.x * vec2.z) - (vec2.x * vec1.z)),
                (vec1.x * vec2.y) - (vec2.x * vec1.y));
		return vector;
	}
	
	public static float dot(Vector3 vec1, Vector3 vec2) {
		return (vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z);
	}
	
	public static Vector3 lerp(Vector3 vec1, Vector3 vec2, float amount) {
		Vector3 vector = new Vector3(
				MathHelper.lerp(vec1.x, vec2.x, amount), 
				MathHelper.lerp(vec2.y, vec2.y, amount),
				MathHelper.lerp(vec2.z, vec2.z, amount));
		return vector;
	}
	
	/**
	 * Gets a vector of the minimum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the minimum of the two vectors.
	 */
	public static Vector3 min(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3();
		vector.x = (vec1.x < vec2.x) ? vec1.x : vec2.x;
		vector.y = (vec1.y < vec2.y) ? vec1.y : vec2.y;
		vector.z = (vec1.z < vec2.z) ? vec1.z : vec2.z;
		return vector;
	}
	
	/**
	 * Gets a vector of the maximum of the two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return a vector that correspond of the maximum of the two vectors.
	 */
	public static Vector3 max(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3();
		vector.x = (vec1.x > vec2.x) ? vec1.x : vec2.x;
		vector.y = (vec1.y > vec2.y) ? vec1.y : vec2.y;
		vector.z = (vec1.z > vec2.z) ? vec1.z : vec2.z;
		return vector;
	}
	
	/**
	 * Negage the vector.
	 */
	public void negate() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
	}

	/**
	 * Gets the length of the vector.
	 * @return Return the length of the vector.
	 */
	public double length() {
		return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
	}
	
	/**
	 * Gets the distance between another vector.
	 * @param vector
	 * @return Return the distance between this vector and the vector passed in parameter.
	 */
	public double distance(Vector3 vector) {
		double dx = this.x - vector.x;
		double dy = this.y - vector.y;
		double dz = this.z - vector.z;
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}
	
	/**
	 * Gets a transformed Vector3 from a position and a matrix.
	 * @param position
	 * @param matrix
	 * @return A tranformed vector.
	 */
	public static Vector3 transform(Vector3 position, Matrix matrix) {
		Vector3 vector = new Vector3(
			(position.x * matrix.M11) + (position.y * matrix.M21) + (position.z * matrix.M31) + matrix.M41,
	        (position.x * matrix.M12) + (position.y * matrix.M22) + (position.z * matrix.M32) + matrix.M42,
	        (position.x * matrix.M13) + (position.y * matrix.M23) + (position.z * matrix.M33) + matrix.M43 
		);
		
		return vector;
	}
	
	public String toString() {
		return "x: " + this.x + " y: " + this.y + " z: " + this.z;
	}
}
