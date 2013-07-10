// AtlantisEngine.java - Copyright (C) Yannick Comte.
// This file is subject to the terms and conditions defined in
// file 'LICENSE', which is part of this source code package.
package atlantis.framework;

/**
 * Define a vector with four coordinates.
 * @author Yannick
 */
public class Vector4 {
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4() {
		this(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public Vector4(float value) {
		this(value, value, value, value);
	}
	
	public Vector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4(Vector4 vector) {
		this(vector.x, vector.y, vector.z, vector.w);
	}
	
	/**
	 * Linear interpolation.
	 * @param vector A vector to use for interpolation
	 * @param amount Value between 0 and 1 indicating the weight of vector.
	 */
	public void lerp(Vector4 vector, float amount) {
		this.x = MathHelper.lerp(this.x, vector.x, amount);
		this.y = MathHelper.lerp(this.y, vector.y, amount);
		this.z = MathHelper.lerp(this.z, vector.z, amount);
		this.w = MathHelper.lerp(this.w, vector.w, amount);
	}

	/**
	 * Linearly interpolates between two vectors.
	 * @param vec1 First vector
	 * @param vec2 Second vector
	 * @param amount Value between 0 and 1 indicating the weight of vec2.
	 * @return Return a interpolated Vector3.
	 */
	public static Vector4 lerp(Vector4 vec1, Vector4 vec2, float amount) {
		Vector4 vector = new Vector4(vec1);
		vector.lerp(vec2, amount);
		return vector;
	}
}
