package atlantis.framework;

/**
 * A Vector3 class that represent a vector with 3 coordinates X, Y and Z.
 * @author Yannick
 * @see Inspired by MonoGame Vector3.cs class.
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
	
	public static Vector3 add(Vector3 vec1, Vector3 vec2) {
		Vector3 result = new Vector3(vec1);
		result.add(vec2);
		return result;
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
	
	public static Vector3 divide(Vector3 vec1, Vector3 vec2) {
		Vector3 vec = new Vector3(vec1);
		vec.divide(vec2);
		return vec;
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
	 * Multiply two vectors.
	 * @param vec1
	 * @param vec2
	 * @return Return the multiplication for the two vectors.
	 */
	public static Vector3 multiply(Vector3 vec1, Vector3 vec2) {
		Vector3 vec = new Vector3(vec1);
		vec.multiply(vec2);
		return vec;
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
		Vector3 result = new Vector3();
		float factor = (float)distance(vector, Vector3.Zero);
		
		if (factor != 0) {
			factor = 1.0f / factor;
			result.setValues(vector.x * factor, vector.y * factor, vector.z * factor);
		}
		
		return result;
	}
	
	public void cross(Vector3 vector) {
		float x = (this.y * vector.z) - (vector.y * this.z);
		float y = -((this.x * vector.z) - (vector.x * this.z));
		float z = (this.x * vector.y) - (vector.x * this.y);
		this.setValues(x, y, z);
	}
	
	public static Vector3 cross(Vector3 vec1, Vector3 vec2) {
		Vector3 vector = new Vector3(vec1);
		vector.cross(vec2);
		return vector;
	}
	
	public float dot(Vector3 vector) {
		return (this.x * vector.x) + (this.y * vector.y) + (this.z * vector.z);
	}
	
	public static float dot(Vector3 vec1, Vector3 vec2) {
		return (vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z);
	}
	
	public void lerp(Vector3 vector, float amount) {
		this.x = MathHelper.lerp(this.x, vector.x, amount);
		this.y = MathHelper.lerp(this.y, vector.y, amount);
		this.z = MathHelper.lerp(this.z, vector.z, amount);
	}
	
	public static Vector3 lerp(Vector3 vec1, Vector3 vec2, float amount) {
		Vector3 vector = new Vector3(
				MathHelper.lerp(vec1.x, vec2.x, amount), 
				MathHelper.lerp(vec1.y, vec2.y, amount),
				MathHelper.lerp(vec1.z, vec2.z, amount));
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
	 * Negate a vector.
	 * @param vector
	 * @return Return a negated vector.
	 */
	public static Vector3 negate(Vector3 vector) {
		Vector3 vec = new Vector3(vector);
		vec.negate();
		return vec;
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
	 * Gets the distance between two vectors.
	 * @param vector
	 * @return Return the distance between two vectors.
	 */
	public static double distance(Vector3 vec1, Vector3 vec2) {
		double dx = vec1.x - vec2.x;
		double dy = vec1.y - vec2.y;
		double dz = vec1.z - vec2.z;
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
	
	public void setValues(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return "x: " + this.x + " y: " + this.y + " z: " + this.z;
	}
}
