// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Represents a 2-dimensional vector
 * @author Yannick
 *
 */
public class Vector2 {
	public float x;
	public float y;
	
	public static final Vector2 Zero() {
		return new Vector2();
	}
	
	public static final Vector2 One() {
		return new Vector2(1.0f, 1.0f);
	}
	
	public static final Vector2 UnitX() {
		return new Vector2(1.0f, 0.0f);
	}
	
	public static final Vector2 UnitY() {
		return new Vector2(0.0f, 1.0f);
	}
	
	/**
	 * Create a default vector with X and Y equal 0.
	 */
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Create a vector with the same value for X and Y.
	 * @param value The value to use for X and Y coordinates.
	 */
	public Vector2(float value) {
		this.x = value;
		this.y = value;
	}
	
	/**
	 * Create a vector with x and y coordinates.
	 * @param x Value of X coordinate.
	 * @param y Value of Y coordinate.
	 */
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Create a vector with an existing point. Values are copied.
	 * @param point
	 */
	public Vector2(Point point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	/**
	 * Create a vector with an existing vector. Values are copied.
	 * @param vector A vector.
	 */
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
	 * Add two vectors.
	 * @param vec1 A vector 
	 * @param vec2 Another vector
	 * @return Return a new vector.
	 */
	public static Vector2 add(Vector2 vec1, Vector2 vec2) {
		Vector2 vec = new Vector2(vec1);
		vec.add(vec2);
		return vec;
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
	 * Subtract two vectors.
	 * @param vec1 A vector 
	 * @param vec2 Another vector
	 * @return Return a new vector.
	 */
	public static Vector2 subtract(Vector2 vec1, Vector2 vec2) {
		Vector2 vec = new Vector2(vec1);
		vec.subtract(vec2);
		return vec;
	}
	
	/**
	 * Multiply (scalar) a value to vector
	 * @param value
	 */
	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
	}
	
	/**
	 * Multiply (scalar) a vector to vector
	 * @param vector
	 */
	public void multiply(Vector2 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	/**
	 * Multiply two vectors.
	 * @param vec1 A vector 
	 * @param vec2 Another vector
	 * @return Return a new vector.
	 */
	public static Vector2 multiply(Vector2 vec1, Vector2 vec2) {
		Vector2 vec = new Vector2(vec1);
		vec.multiply(vec2);
		return vec;
	}
	
	/**
	 * Divide a value to vector
	 * @param value
	 */
	public void divide(float value) {
		if (value != 0) {
			this.x /= value;
			this.y /= value;
		}
	}
	
	/**
	 * Divide a vector to vector
	 * @param vector A vector.
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
	 * Divide two vectors.
	 * @param vec1 A vector 
	 * @param vec2 Another vector
	 * @return Return a new vector.
	 */
	public static Vector2 divide(Vector2 vec1, Vector2 vec2) {
		Vector2 vec = new Vector2(vec1);
		vec.divide(vec2);
		return vec;
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
	 * Gets the distance between two vectors.
	 * @param vec1 A vector
	 * @param vec2 Another vector
	 * @return Return a new vector.
	 */
	public static double distance(Vector2 vec1, Vector2 vec2) {
		Vector2 vec = new Vector2(vec1);
		return vec.distance(vec2);
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
	 * @return Return the negated vector.
	 */
	public static Vector2 negate(Vector2 vector) {
		Vector2 vec = new Vector2(vector);
		vec.x *= -1;
		vec.y *= -1;
		return vec;
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
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
